package com.xwzx.equipmanager.mvp.views.activitys;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.keeperDisposedTodoAdapter;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.databinding.ActivityDisposeTodoBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.mvp.models.DisposedModel;
import com.xwzx.equipmanager.widget.dialog.TrunDownDialog;

import java.util.ArrayList;

public class DisposeTodoActivity extends BaseActivity<ActivityDisposeTodoBinding> {


    @Override
    public int setContentView() {
        return R.layout.activity_dispose_todo;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.dispseTodo));
        this.binding.list.setLayoutManager(new LinearLayoutManager(this));
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setAdapter(new keeperDisposedTodoAdapter(this,getChildDisposedModel(),R.layout.disposetodo_item_child));
    }
    private ArrayList<DisposedModel.DisposedChildModel> getChildDisposedModel() {
        ArrayList<DisposedModel.DisposedChildModel> disposedChildModels = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            DisposedModel.DisposedChildModel disposedChildModel = new DisposedModel.DisposedChildModel(i, null, "外带设备", "一架3列", i == 0 ? 0 : 1);
            disposedChildModels.add(disposedChildModel);
        }
        return disposedChildModels;
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
            finish();
        }));
        this.binding.trunDown.setOnClickListener(new ClickProxy(v -> {
            TrunDownDialog trunDownDialog = new TrunDownDialog(this);
            trunDownDialog.show();
        }));
        this.binding.agree.setOnClickListener(new ClickProxy(v -> {
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
