package com.boreas.plainlife.mvp.views.activitys;


import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.SubmitApplyAdapter;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.base.BaseResponse;
import com.boreas.plainlife.databinding.ActivitySubmitApplyBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerSubmitApplyActivityComponent;
import com.boreas.plainlife.mvp.models.ShoppingModel;
import com.boreas.plainlife.mvp.views.viewinterfaces.SubmitApplyActivityInterface;
import com.boreas.plainlife.widget.dialog.ApplingRecosonDialog;

import java.util.ArrayList;

public class SubmitApplyActivity extends BaseActivity<ActivitySubmitApplyBinding> implements SubmitApplyActivityInterface {

    private ArrayList<ShoppingModel> shoppingModels;
    private boolean isSubmitSuccess = false;

    @Override
    public int setContentView() {
        return R.layout.activity_submit_apply;
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.submitapply));
        this.binding.shoppingList.setHasFixedSize(false);
        this.binding.shoppingList.setLayoutManager(new LinearLayoutManager(this));
        this.binding.shoppingList.setAdapter(new SubmitApplyAdapter(this, this.shoppingModels, R.layout.activity_shopping_item_parent));

    }

    @Override
    public void handlerJumpData(Intent intent) {
        super.handlerJumpData(intent);
        this.shoppingModels = (ArrayList<ShoppingModel>) intent.getSerializableExtra("applyList");
    }

    private boolean backIndex() {
        //返回首页
        if (isSubmitSuccess) {
            //返回首页
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
            if (!this.backIndex()) {
                finish();
            }
        }));
        this.binding.applying.setOnClickListener(new ClickProxy(v -> {
            ApplingRecosonDialog applingRecoson = new ApplingRecosonDialog(this);
            applingRecoson.show();
        }));
        this.binding.submit.setOnClickListener(new ClickProxy(v -> {
            isSubmitSuccess = true;
            this.showCompleteView();
        }));
        this.binding.backIndex.setOnClickListener(new ClickProxy(v -> {
            if (!this.backIndex()) {
                finish();
            }
        }));
        this.binding.lookRecord.setOnClickListener(new ClickProxy(v -> {
            if (!this.backIndex()) {
                finish();
            }
        }));
    }

    private void showCompleteView() {
        this.binding.submit.setVisibility(View.GONE);
        this.binding.contentView.setVisibility(View.GONE);
        this.binding.headLayout.headTitle.setVisibility(View.INVISIBLE);
        this.binding.headLayout.rightTitle.setVisibility(View.INVISIBLE);
        this.binding.headLayout.rightBtn.setVisibility(View.INVISIBLE);
        this.binding.complete.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initComponent() {
        DaggerSubmitApplyActivityComponent.builder()
                .appComponent(App.getInstance().getmAppComponent())
                .netComponent(App.getInstance().getmNetComponent())
                .build().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(BaseResponse<String> s) {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void noNetWork() {

    }

    @Override
    public void noData() {

    }

    @Override
    public void onDisLoadingDialog() {

    }

    /**
     * 显示LoadingDialog
     */
    @Override
    public void onShowLoadingDialog() {

    }
}
