
package com.boreas.plainlife.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boreas.plainlife.R;


public class LoadingDialog {

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = v.findViewById(R.id.dialog_loading_view);
        TextView tipTextView = v.findViewById(R.id.tipTextView);
        ProgressBar progressBar = v.findViewById(R.id.progressBar);
//        LayerDrawable layerDrawable = (LayerDrawable) progressBar.getIndeterminateDrawable();
//        layerDrawable.
        tipTextView.setText(msg);

        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }

    public static void closeDialog(Dialog mDialogUtils) {
        new Handler().postDelayed(() -> {
            if (mDialogUtils != null && mDialogUtils.isShowing()) {
                mDialogUtils.dismiss();
            }
        }, 500);

    }
}
