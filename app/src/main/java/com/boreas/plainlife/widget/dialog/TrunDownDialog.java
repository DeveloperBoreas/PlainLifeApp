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
import com.boreas.plainlife.databinding.TrundownDialogBinding;
import com.boreas.plainlife.framwork.ClickProxy;

public class TrunDownDialog extends Dialog {

    private TrundownDialogBinding binding;

    public TrunDownDialog(Context context) {
        this(context, R.style.BottomFullDialog);
    }

    public TrunDownDialog(Context context, int themeResId) {
        super(context, themeResId);
        View contentView = getLayoutInflater().inflate(R.layout.trundown_dialog, null);
        this.binding = DataBindingUtil.bind(contentView);
        binding.cancle.setOnClickListener(new ClickProxy(v -> this.dismiss()));
        binding.sure.setOnClickListener(new ClickProxy(v -> this.dismiss()));
        super.setContentView(binding.getRoot());
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
