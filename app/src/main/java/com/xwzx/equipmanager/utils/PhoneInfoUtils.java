package com.xwzx.equipmanager.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.App;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class PhoneInfoUtils {

    /**
     * 获取失败默认返回值
     */
    public static final String ERROR_MAC_STR = "02:00:00:00:00:00";
    public static final int SLOT_1 = 0;
    public static final int SLOT_2 = 1;

    /**\
     * @return
     */
    public static String getImeiNum(Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getMethod("getImei", int.class);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            String imei = manager.getDeviceId();
//            String imei = (String) method.invoke(manager, slotId);
            return imei;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 获取imsi卡
     *
     * @param context
     * @return
     */
    public static String getImsiNum(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMSI号
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            String imsi = telephonyManager.getSubscriberId();

            if (null == imsi) {
                imsi = "";
            }
            return imsi;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 获取手机的制式
     *
     * @param context
     * @return
     */
    public static String getPhoneType(Context context) {
        TelephonyManager telephoneManger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        int phoneType = telephoneManger.getPhoneType();
        if (TelephonyManager.PHONE_TYPE_CDMA == phoneType) {
            return "CDMA";
        } else if (TelephonyManager.PHONE_TYPE_GSM == phoneType) {
            return "GSM";
        } else {
            return "未知";
        }

    }


    /**
     * 获取手机设备MAC地址
     * MAC地址：物理地址、硬件地址，用来定义网络设备的位置
     * modify by heliquan at 2018年1月17日
     *
     * @param context
     * @return
     */
    public static String getMobileMAC(Context context) {

        WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getAndroidVersion7MAC();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getAndroidHighVersionMac();
        } else { // 当前设备在6.0以下
            return getAndroidLowVersionMac(mWifiManager);
        }
    }

    /**
     * 获取手机电池的电压 ，温度信息
     *
     * @return
     */

//    public static BatteryInfo getBatteryInfo() {
//        return App.info;
//    }


    /**
     * 获取信号强度
     *
     * @param context
     * @return
     */

//    public static int getSignalInfo(Context context) {
//        return ((App) context.getApplicationContext()).getmDbm();
//
//    }

    /**
     * Android 6.0 设备兼容获取mac
     * 兼容原因：从Android 6.0之后，Android 移除了通过WiFi和蓝牙API来在应用程序中可编程的访问本地硬件标示符。
     * 现在WifiInfo.getMacAddress()和BluetoothAdapter.getAddress()方法都将返回：02:00:00:00:00:00
     *
     * @return
     */
    private static String getAndroidHighVersionMac() {
        String str = "";
        String macSerial = "";
        try {
            // 由于Android底层基于Linux系统 可以根据shell获取
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (macSerial == null || "".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();
                macSerial = getAndroidVersion7MAC();
            }
        }
        return macSerial;
    }

    /**
     * Android 6.0 以下设备获取mac地址 获取失败默认返回：02:00:00:00:00:00
     *
     * @param wifiManager
     * @return
     */
    @NonNull
    private static String getAndroidLowVersionMac(WifiManager wifiManager) {
        try {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String mac = wifiInfo.getMacAddress();
            if (TextUtils.isEmpty(mac)) {
                return ERROR_MAC_STR;
            } else {
                return mac;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("mac", "get android low version mac error:" + e.getMessage());
            return ERROR_MAC_STR;
        }
    }

    /**
     * 兼容7.0获取不到的问题
     *
     * @return
     */
    public static String getAndroidVersion7MAC() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            Log.e("mac", "get android version 7.0 mac error:" + e.getMessage());
        }
        return ERROR_MAC_STR;
    }

    public static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }

    public static String GetNetworkType(Context context) {
        String strNetworkType = "";
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                Log.e("cocos2d-x", "Network getSubtypeName : " + _strSubTypeName);

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }
                        break;
                }
                Logger.e("Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        }
        Logger.e("Network Type : " + strNetworkType);
        return strNetworkType;
    }

}
