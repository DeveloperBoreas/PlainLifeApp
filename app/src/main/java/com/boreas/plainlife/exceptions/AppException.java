package com.boreas.plainlife.exceptions;

import com.tencent.bugly.crashreport.CrashReport;
import com.boreas.plainlife.App;
import com.boreas.plainlife.R;

import java.util.Map;

public class AppException extends Exception {
    public static String appException = App.getInstance().getResources().getString(R.string.app_name) +  "：";

    public String errorCode;

    public AppException(String message){
        super(appException + message);
    }

    public AppException(Map<String,String> map){
        super(appException + map.toString());
    }

    public AppException(String errorCode, Map<String,String> map){
        this(map);
        this.setErrorCode(errorCode);
    }

    public AppException(String errorCode, String message){
        this(message);
        this.setErrorCode(errorCode);
    }

    public AppException(String errorCode, String message, Throwable cause)
    {
        super(appException + message, cause);
        this.setErrorCode(errorCode);
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void PostException(){
        CrashReport.postCatchedException(this);
    }
}
