package com.boreas.plainlife.mvp.views.activitys;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.view.View;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.MainProcessingCaseAdapter;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityMainProcessingCaseBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;

import java.util.ArrayList;

public class MainProcessingCaseActivity extends BaseActivity<ActivityMainProcessingCaseBinding> {
    private ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> taskBeans;
    @Override
    public void handlerJumpData(Intent intent) {
        super.handlerJumpData(intent);
        this.taskBeans = (ArrayList<TaskModel.DataBeanX.DataBean.TasksBean>) intent.getSerializableExtra("taskBeans");
    }
    @Override
    public int setContentView() {
        return R.layout.activity_main_processing_case;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.processing_case));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        if(this.taskBeans != null && !this.taskBeans.isEmpty()){
            MainProcessingCaseAdapter adapter = new MainProcessingCaseAdapter(this,taskBeans,R.layout.recycle_process_case_item_layout);
            this.binding.recycleProcessingCase.setHasFixedSize(false);
            this.binding.recycleProcessingCase.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            this.binding.recycleProcessingCase.setAdapter(adapter);
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
