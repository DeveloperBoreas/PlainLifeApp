package com.boreas.plainlife.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.boreas.plainlife.R;
import com.boreas.plainlife.databinding.ApplingrecosonDialogBinding;
import com.boreas.plainlife.framwork.ClickProxy;

public class ApplingRecosonDialog extends Dialog {

    private ApplingrecosonDialogBinding binding;

    public ApplingRecosonDialog(Context context) {
        this(context, R.style.BottomFullDialog);
    }

    public ApplingRecosonDialog(Context context, int themeResId) {
        super(context, themeResId);
        View contentView = getLayoutInflater().inflate(R.layout.applingrecoson_dialog, null);
        this.binding = DataBindingUtil.bind(contentView);
        binding.cancle.setOnClickListener(new ClickProxy(v -> this.dismiss()));
        binding.weixiu.setOnClickListener(new ClickProxy(v -> {
            //维修申请
            updateApplingState(0);
        }));
        binding.baofei.setOnClickListener(new ClickProxy(v -> {
            //报废申请
            updateApplingState(1);
        }));
        binding.jianche.setOnClickListener(new ClickProxy(v -> {
            //检测申请
            updateApplingState(2);
        }));
        binding.chuku.setOnClickListener(new ClickProxy(v -> {
            //出库申请
            updateApplingState(3);
        }));
        binding.panku.setOnClickListener(new ClickProxy(v -> {
            updateApplingState(4);
        }));
        super.setContentView(binding.getRoot());
    }
    private void updateApplingState(int index){
        this.resetApplinState();
        switch(index){
            case 0:
                this.binding.weixiuCheckbox.setSelected(true);
                break;
            case 1:
                this.binding.baofeiCheckbox.setSelected(true);
                break;
            case 2:
                this.binding.jiancheCheckbox.setSelected(true);
                break;
            case 3:
                this.binding.chukuCheckbox.setSelected(true);
                break;
            case 4:
                this.binding.pankuCheckbox.setSelected(true);
                break;
        }
    }
    private void  resetApplinState(){
        this.binding.weixiuCheckbox.setSelected(false);
        this.binding.baofeiCheckbox.setSelected(false);
        this.binding.jiancheCheckbox.setSelected(false);
        this.binding.chukuCheckbox.setSelected(false);
        this.binding.pankuCheckbox.setSelected(false);
    }

    protected ApplingRecosonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
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
        setCanceledOnTouchOutside(false);
        getWindow().setAttributes(layoutParams);
    }

}
