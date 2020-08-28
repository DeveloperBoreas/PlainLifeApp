package com.boreas.plainlife.utils;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;

public class FrontAndBackView {

    private View faceView, backView;

    private boolean isFront = true;
    private boolean isStart = false;
    private ObjectAnimator toFaceObjectAnimator, toBackObjectAnimator;

    private int duration = 2000;
    private Context context;

    private FrontAndBackViewListener frontAndBackViewListener;

    public FrontAndBackView(Context context, View frontView, View backView) {
        this.context = context;
        this.faceView = frontView;
        this.backView = backView;
        this.faceView.setVisibility(View.VISIBLE);
        this.backView.setVisibility(View.GONE);

        setCameraDistance();

        initAnim();
    }

    private void initAnim() {
        toFaceObjectAnimator = new ObjectAnimator();
        toFaceObjectAnimator.setFloatValues(-90f, 0f);
        toFaceObjectAnimator.setDuration(duration / 2);
        toFaceObjectAnimator.setPropertyName("rotationY");
        toFaceObjectAnimator.setInterpolator(new LinearAnim());

        toBackObjectAnimator = new ObjectAnimator();
        toBackObjectAnimator.setFloatValues(0f, 90f);
        toBackObjectAnimator.setDuration(duration / 2);
        toBackObjectAnimator.setPropertyName("rotationY");
        toBackObjectAnimator.setInterpolator(new LinearAnim());

        toBackObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if ((float) animation.getAnimatedValue() == 90f) {
                    backView.setVisibility(View.VISIBLE);
                    faceView.setVisibility(View.GONE);
                    toFaceObjectAnimator.start();
                }
            }
        });

        toFaceObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if ((float) animation.getAnimatedValue() == 0f) {
                    View view = faceView;
                    faceView = backView;
                    backView = view;            ///交换正反面
                    isFront = !isFront;
                    isStart = false;
                    if (frontAndBackViewListener != null) frontAndBackViewListener.animationEnd();
                }
            }
        });
    }

    public void toggle() {
        if (isStart) return; ///防止多次点击
        isStart = true;
        animStart();
    }


    public void animStart() {

        toFaceObjectAnimator.setTarget(backView);
        toBackObjectAnimator.setTarget(faceView);

        toBackObjectAnimator.start();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setFrontAndBackViewListener(FrontAndBackViewListener frontAndBackViewListener) {
        this.frontAndBackViewListener = frontAndBackViewListener;
    }


    /**
     * rotationY属性变换时需要调整相机距离，否则会影响用户体验
     */
    private void setCameraDistance() {
        int distance = 16000;
        float scale = context.getResources().getDisplayMetrics().density * distance;
        faceView.setCameraDistance(scale);
        backView.setCameraDistance(scale);
    }

    /**
     * 线性值插值器
     */
    class LinearAnim implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            //Log.e("TAG", "getInterpolation: input : " + input );
            return input;
        }
    }

    public interface FrontAndBackViewListener {
        void animationEnd();
    }
}
