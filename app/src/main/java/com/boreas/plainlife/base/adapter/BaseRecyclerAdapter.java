package com.boreas.plainlife.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.framwork.ClickProxy;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {

    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    private Context context;
    private List<T> list;
    private LayoutInflater inflater;
    private int itemLayoutId;
    private boolean isScrolling;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    private RecyclerView recyclerView;
    private boolean isShowFooter = false;
    private int footerHeight = 0;

    //普通布局的type
    private static final int TYPE_ITEM = 1111;
    //脚布局
    private static final int TYPE_FOOTER = 2222;

    public BaseRecyclerAdapter<T> setIsShowFooter(int footerHeight) {
        this.isShowFooter = true;
        this.footerHeight = footerHeight;
        return this;
    }

    /**
     * 在RecyclerView提供数据的时候调用
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    public void insert(T item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void reset(List<T> list) {
        if (list == null) {
            throw new NullPointerException(TAG + "__________list is not null!!!!! ");
        }
        this.list = list;
//        this.list.clear();
//        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException(TAG + "__________list is not null!!!!! ");
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public BaseRecyclerAdapter(Context context, List<T> list, int itemLayoutId) {
        this.context = context;
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        inflater = LayoutInflater.from(context);
    }

    public Context getContext() {
        return this.context;
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(itemLayoutId, parent, false);
            return BaseRecyclerHolder.getRecyclerHolder(context, view);
        } else if (viewType == TYPE_FOOTER) {
            FrameLayout view = new FrameLayout(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, this.footerHeight);
            view.setLayoutParams(layoutParams);
            return BaseRecyclerHolder.getRecyclerHolder(context, view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerHolder holder, int position) {
        int itemType = getItemViewType(position);
        if (itemType == TYPE_ITEM) {
            holder.itemView.setOnClickListener(new ClickProxy(view -> {
                if (listener != null && view != null && recyclerView != null) {
                    int position1 = recyclerView.getChildAdapterPosition(view);
                    listener.onItemClick(recyclerView, view, position1);
                }
            }));
            holder.itemView.setOnLongClickListener(view -> {
                if (longClickListener != null && view != null && recyclerView != null) {
                    int position12 = recyclerView.getChildAdapterPosition(view);
                    longClickListener.onItemLongClick(recyclerView, view, position12);
                    return true;
                }
                return false;
            });
            convert(holder, list.get(position), position, isScrolling);
        }
    }

    @Override
    public int getItemCount() {
        return this.getListItemCount();
    }

    public int getListItemCount(){
        if (this.isShowFooter) {
            return getListData() == null || getListData().isEmpty() ? 0 : getListData().size() + 1;
        }
        return getListData() == null || getListData().isEmpty() ? 0 : getListData().size();
    }

    public int getGridItemCount(){
        if (this.isShowFooter) {
            return this.getListData() == null && this.getListData().isEmpty() ? 0 : (this.getListData().size() % 2 == 0 ? this.getListData().size() + 1 : this.getListData().size() + 2);
        }
        return this.getListData() == null ? 0 : this.getListData().size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.getListItemViewType(position);
    }

    public int getListItemViewType(int position){
        if (this.isShowFooter) {
            return getListData() == null || getListData().isEmpty() ? TYPE_ITEM : this.getItemCount() - 1 > position ? this.TYPE_ITEM : this.TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public int getGridItemViewType(int position){
        if (this.isShowFooter) {
            return this.getListData() == null && this.getListData().isEmpty() ? TYPE_ITEM : this.getListData().size() % 2 == 0 ? getItemCount() - 1 > position ? TYPE_ITEM : TYPE_FOOTER : getItemCount() - 2 > position ? TYPE_ITEM : TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public List<T> getListData() {
        return this.list;
    }

    public BaseRecyclerAdapter<T> setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public BaseRecyclerAdapter<T> setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
        return this;
    }

    /**
     * 填充RecyclerView适配器的方法，子类需要重写
     *
     * @param holder      ViewHolder
     * @param item        子项
     * @param position    位置
     * @param isScrolling 是否在滑动
     */
    public abstract void convert(BaseRecyclerHolder holder, T item, int position, boolean isScrolling);
}
