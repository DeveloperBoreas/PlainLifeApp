package com.xwzx.equipmanager.mvp.views.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.MainHistoryCaseAdapter;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.databinding.ActivityMainHistoryCaseBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;

import java.io.Serializable;
import java.util.ArrayList;

public class MainHistoryCaseActivity extends BaseActivity<ActivityMainHistoryCaseBinding> {

    private ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> taskBeans;

    @Override
    public void handlerJumpData(Intent intent) {
        super.handlerJumpData(intent);
        this.taskBeans = (ArrayList<TaskModel.DataBeanX.DataBean.TasksBean>) intent.getSerializableExtra("taskBeans");
    }

    @Override
    public int setContentView() {
        return R.layout.activity_main_history_case;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.history_case));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        if(this.taskBeans != null && !this.taskBeans.isEmpty()){
            MainHistoryCaseAdapter adapter = new MainHistoryCaseAdapter(this,taskBeans,R.layout.recycle_history_case_item_layout);
            this.binding.recycleHistoryCase.setHasFixedSize(false);
            this.binding.recycleHistoryCase.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            this.binding.recycleHistoryCase.setAdapter(adapter);
        }
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
            finish();
        }));
    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
