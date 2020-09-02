package com.boreas.plainlife.api;

import com.boreas.plainlife.base.BaseResponse;
import com.boreas.plainlife.mvp.models.login.CaptchatModel;
import com.boreas.plainlife.mvp.models.login.LoginParams;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;
import com.boreas.plainlife.mvp.models.login.UserRegisterParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author: 王秀清
 * des:   项目所用接口管理
 */
public interface ApiService {
    String MULTIPART_DATA = "multipart/form-data";
    String FILE = "file";
                                                        //"手机厂商"                        "手机型号"                          "手机系统版本号"
    String User_Agent = "Android__" + android.os.Build.BRAND + "__" + android.os.Build.MODEL + "__" + android.os.Build.VERSION.RELEASE;

    //获取图形验证码
    @GET("/captchaImage")
    Observable<CaptchatModel> getCaptchaImage();

    //登录
    @POST("/login")
    Observable<LoginReceModel> login(@Header("User-Agent") String userAgent, @Body LoginParams params);

    //注册
    @POST("/system/user/app/addUser")
    Observable<BaseResponse> register(@Body UserRegisterParams params);

    //发送验证码
    @POST("/sendSms")
    @FormUrlEncoded
    Observable<BaseResponse> sendSms(@Field("phoneNum") String phoneNum);

    //根据手机号查询用户
    @GET("/system/user/app/queryUserByPhone")
    Observable<String> queryUserByPhone(@Query("phone") String phoneNum);

}
