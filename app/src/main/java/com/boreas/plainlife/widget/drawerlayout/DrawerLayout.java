package com.boreas.plainlife.widget.drawerlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import androidx.core.view.ViewCompat;

import com.boreas.plainlife.R;

public class DrawerLayout extends HorizontalScrollView {
    private View mMenuView, mContentView, mShadow;
    private int mMenuWidth;
    private Context mContext;
    private GestureDetector mDetector;//系统自带手势处理类
    private boolean mMenuIsOpen = false;//菜单页是否已经打开
    private boolean mIntercept = false;//是否拦截事件
    private float startX, startY, offsetX, offsetY;

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        float rightMargin = array.getDimension(R.styleable.SlidingMenu_rightMargin, dip2px(50));
        mMenuWidth = (int) (getScreenWidth(context) - rightMargin);
        array.recycle();
        //用于处理快速滑动时，进行打开或者关闭菜单页
        mDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //快速滑动时就会回调，打开菜单页时往右滑动，关闭的时候往左快速滑动
                //快速往左滑velocityX是负数，快速往右滑velocityX是正数
                if (mMenuIsOpen) {
                    if (Math.abs(velocityX) > Math.abs(velocityY)) {//横向滑动大于纵向滑动再响应，增加用户体验
                        if (velocityX < 0) {
                            close();
                            return true;
                        }
                    }
                } else {
                    if (Math.abs(velocityX) > Math.abs(velocityY)) {//横向滑动大于纵向滑动再响应，增加用户体验
                        if (velocityX > 0) {
                            open();
                            return true;
                        }
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        //xml布局文件解析完毕后调用
        super.onFinishInflate();
        //1、内容页指定宽高,就是屏幕的宽度
        //2、菜单页指定宽度，就是屏幕宽度减去 - 自定义宽度
        ViewGroup container = (ViewGroup) getChildAt(0);//外层LinearLayout
        if (container.getChildCount() != 2) {
            throw new RuntimeException("只能且必须放置两个子View!");
        }
        mMenuView = container.getChildAt(0);//菜单页
        //设置宽高
        ViewGroup.LayoutParams menuParams = mMenuView.getLayoutParams();
        menuParams.width = mMenuWidth;
        mMenuView.setLayoutParams(menuParams);//7.0以下的手机必须加这句

        //给内容页添加一层阴影：先从父布局取出内容页，然后放进另一个容器，
        // 同时在该容器添加一层阴影（用View设置背景颜色即可），然后再把该容器放回父布局。
        mContentView = container.getChildAt(1);//内容页
        //设置宽高
        container.removeView(mContentView);//从父布局取出内容页
        RelativeLayout outContainer = new RelativeLayout(mContext);//创建新容器
        outContainer.addView(mContentView);//把内容页加入新容器
        mShadow = new View(mContext);//创建阴影层
        mShadow.setBackgroundColor(Color.parseColor("#70000000"));//阴影层设置背景颜色
        outContainer.addView(mShadow);//在新容器加入阴影层
        ViewGroup.LayoutParams contentParams = mContentView.getLayoutParams();
        contentParams.width = getScreenWidth(mContext);
        outContainer.setLayoutParams(contentParams);//7.0以下的手机必须加这句
        container.addView(outContainer);//把新容器加入父布局
        mShadow.setAlpha(0.0f);//初始化阴影层为不透明

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //记录按下屏幕的初始位置
            startX = ev.getX();
            startY = ev.getY();
        }
        mIntercept = false;
        //处理当菜单页打开的时候，触摸内容页部门时关闭菜单页，并且拦截事件
        if (mMenuIsOpen) {
            float currentX = ev.getX();
            if (currentX > mMenuWidth) {
                close();
                //返回true ：拦截子View的事件,但是会执行自己的onTouchEvent方法
                mIntercept = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //要在onLayout执行后再调用scrollTo，否则无效果
        //初始化的时候是关闭的，注意，此时的getScrollX = mMenuWidth
        close();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mIntercept) {//如果拦截子View的事件，同时不执行自己的onTouchEvent
            return true;
        }
        if (mDetector.onTouchEvent(ev)) {//假如快速滑动执行了，以下代码就不执行
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                offsetX = ev.getX() - startX;
                offsetY = ev.getY() - startY;
                //与按下屏幕的初始位置相比，当纵向滑动大于横向滑动时拦截滑动事件（横向滑动为主时才进行滑动，增加用户体验）
                if (Math.abs(offsetX) < Math.abs(offsetY)) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                //根据手指抬起时的滚动距离判断，要么关闭，要么打开
                int currentScrollX = getScrollX();
                if (currentScrollX > mMenuWidth / 2) {
                    close();
                } else {
                    open();
                }
                return true;
        }

        return super.onTouchEvent(ev);
    }

    //关闭菜单
    public void close() {
        mMenuIsOpen = false;
        smoothScrollTo(mMenuWidth, 0);
    }

    //打开菜单
    public void open() {
        mMenuIsOpen = true;
        smoothScrollTo(0, 0);
    }

    //处理左右滑时的缩放
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = 1f * l / mMenuWidth;//scale从 1 - 0
        //滑动时改变内容页的阴影程度，最大1f，最小0f
        mShadow.setAlpha(1 - scale);
        //滑动时菜单页平移，抽屉效果
        ViewCompat.setTranslationX(mMenuView, l * 0.55f);
    }

    //获取屏幕宽度
    private static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int dip2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
