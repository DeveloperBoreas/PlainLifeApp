
package com.boreas.plainlife.widget.loading;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.boreas.plainlife.R;


public class LoadingDialog {

    public static Dialog createLoadingDialog(Activity activity, String msg) {
//        //加载View
//        LVFinePoiStar lvFinePoiStar = new LVFinePoiStar(activity);
//        lvFinePoiStar.setViewColor(Color.WHITE);
//        lvFinePoiStar.setCircleColor(Color.YELLOW);
//        lvFinePoiStar.setDrawPath(true);
//
//        Dialog loadingDialog = new Dialog(activity, R.style.MyDialogStyle);
//        loadingDialog.setCancelable(true);
//        loadingDialog.setCanceledOnTouchOutside(false);
//        loadingDialog.setContentView(lvFinePoiStar, new LinearLayout.LayoutParams(
//                (int)activity.getResources().getDimension(R.dimen.loading_size_50),
//                (int)activity.getResources().getDimension(R.dimen.loading_size_50)));
//        Window window = loadingDialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setGravity(Gravity.CENTER);
//        window.setAttributes(lp);
//        window.setWindowAnimations(R.style.PopWindowAnimStyle);
//        lvFinePoiStar.startAnim();
//        loadingDialog.show();
//        return loadingDialog;
        return null;
    }


    public static void closeDialog(Dialog mDialog) {
        new Handler().postDelayed(() -> {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }, 100);
    }
}
