package com.boreas.plainlife.mvp.views.activitys;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.keeperDisposedTodoAdapter;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityDisposeTodoBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.mvp.models.DisposedModel;
import com.boreas.plainlife.widget.dialog.TrunDownDialog;

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
