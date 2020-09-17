package com.boreas.plainlife.api;

import com.boreas.plainlife.base.BaseResponse;
import com.boreas.plainlife.mvp.models.location.LocationUserListModel;
import com.boreas.plainlife.mvp.models.location.LocationUserModel;
import com.boreas.plainlife.mvp.models.login.CaptchatModel;
import com.boreas.plainlife.mvp.models.login.LoginParams;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;
import com.boreas.plainlife.mvp.models.login.UserRegisterParams;

import java.util.ArrayList;

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
    ArrayList<String> notAllowInterceptPath = new ArrayList<String>(){{
        add("captchaImage");
        add("login");
        add("system/user/app/addUser");
        add("sendSms");
    }};
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

    //获取用户信息
    @GET("/getInfo")
    Observable<BaseResponse> getInfo();

    //根据手机号查询用户
    @GET("/app/location/queryUserByPhone")
    Observable<LocationUserModel> queryUserByPhone(@Query("phone") String phoneNum);

    //绑定用户
    @POST("/app/location/bindUserForPhone")
    @FormUrlEncoded
    Observable<BaseResponse> bindUserForPhone(@Field("phone") String phone);

    //绑定用户列表
    @GET("/app/location/queryBindUsers")
    Observable<LocationUserListModel> queryBindUsers();

}
