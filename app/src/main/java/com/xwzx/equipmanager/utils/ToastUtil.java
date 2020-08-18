package com.xwzx.equipmanager.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xwzx.equipmanager.R;


public class ToastUtil {

    private static Toast toast;

    public static void show(Context context, String msg) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
        TextView tvMessage = view.findViewById(R.id.toast_text);
        tvMessage.setText(msg);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
    public static void showBottom(Context context, String msg) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 180);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
        TextView tvMessage = view.findViewById(R.id.toast_text);
        tvMessage.setText(msg);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
