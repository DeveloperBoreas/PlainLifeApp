package com.boreas.plainlife.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;

import com.boreas.plainlife.App;

import java.util.List;

/**
 * 监听电池电量
 */
public class BatteryBroadCastReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_BATTERY_CHANGED==action){

            int level=intent.getIntExtra("level", 0);    ///电池剩余电量
            int scal=intent.getIntExtra("scale", 0);  ///获取电池满电量数值
            intent.getStringExtra("technology");  ///获取电池技术支持
            intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN); ///获取电池状态
            intent.getIntExtra("plugged", 0);  ///获取电源信息
            intent.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN);  ///获取电池健康度
            int voltage=intent.getIntExtra("voltage", 0);  ///获取电池电压
            int temp= intent.getIntExtra("temperature", 0);  ///获取电池温度
            BatteryInfo info = App.getInstance().getBatteryInfo();
            synchronized (info){
                info.setLevel(level+"%");
                info.setScal(scal);
                info.setTemp((int) (temp-273.15));
                info.setVoltage((double) (voltage/10)/100);
            }
        }
    }
}
