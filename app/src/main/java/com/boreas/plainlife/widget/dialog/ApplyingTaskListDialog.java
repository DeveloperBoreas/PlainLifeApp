package com.boreas.plainlife.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.ApplyTaskListAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.databinding.ApplyTasklistDialogBinding;
import com.boreas.plainlife.mvp.models.submit.FindCaseByUserModel;

import java.util.ArrayList;
import java.util.List;

public class ApplyingTaskListDialog extends Dialog implements BaseRecyclerAdapter.OnItemClickListener {

    private ApplyTasklistDialogBinding binding;
    private ArrayList<FindCaseByUserModel.CaseItemModel> dataList;
    private ApplyTaskListAdapter adapter;

    public ApplyingTaskListDialog(@NonNull Context context) {
        super(context, R.style.BottomFullDialog);
    }
    protected ApplyingTaskListDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ApplyingTaskListDialog(Context context, int themeResId) {
        super(context, themeResId);
        View viewLayout = getLayoutInflater().inflate(R.layout.apply_tasklist_dialog,null);
        this.binding = DataBindingUtil.bind(viewLayout);
        this.binding.recycleTaskList.setHasFixedSize(true);
        this.binding.recycleTaskList.setLayoutManager(new LinearLayoutManager(getContext()));
        dataList = new ArrayList<>();
//        adapter = new ApplyTaskListAdapter(getContext(),dataList,R.layout.recycle_item_apply_task_list);
        this.binding.recycleTaskList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        super.setContentView(binding.getRoot());
    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int position) {

    }

    public ApplyingTaskListDialog upData(List<FindCaseByUserModel.CaseItemModel> data){
        dataList.clear();
        dataList.addAll(data);
        adapter.notifyDataSetChanged();
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);//设置显示在底部
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = display.getWidth();//设置Dialog的宽度为屏幕宽度
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setAttributes(layoutParams);
    }



}
