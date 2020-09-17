
package com.boreas.plainlife.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.boreas.plainlife.R;
import com.wang.avi.AVLoadingIndicatorView;


public class LoadingDialog {

    public static Dialog createLoadingDialog(Context context, String msg) {
        InnerLoadingDialog loadingDialog = new InnerLoadingDialog(context);
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.setNavigationBarColor(context.getColor(R.color.transparent));
        loadingDialog.show();
        return loadingDialog;
    }


    public static void closeDialog(Dialog mDialogUtils) {
        new Handler().postDelayed(() -> {
            if (mDialogUtils != null && mDialogUtils.isShowing()) {
                mDialogUtils.dismiss();
            }
        }, 100);
    }
    private static class InnerLoadingDialog extends Dialog{

        private AVLoadingIndicatorView loading;

        public InnerLoadingDialog(@NonNull Context context) {
            super(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.dialog_loading, null);
            LinearLayout layout = v.findViewById(R.id.dialog_loading_view);
            loading = v.findViewById(R.id.loading);
            this.setCancelable(true);
            this.setCanceledOnTouchOutside(false);
            super.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        }

        public InnerLoadingDialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
        }

        protected InnerLoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        @Override
        public void show() {
            loading.show();
            super.show();
        }

        @Override
        public void dismiss() {
            loading.hide();
            super.dismiss();
        }
    }
}
