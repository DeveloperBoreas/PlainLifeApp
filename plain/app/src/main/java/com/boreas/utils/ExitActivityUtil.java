package com.boreas.utils;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;
/**
 * 作者 boreas
 * 日期 18-2-24
 * 邮箱 13051089921@163.com
 */

public class ExitActivityUtil extends Activity{
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //2s之内按返回键就会推出
            if((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
