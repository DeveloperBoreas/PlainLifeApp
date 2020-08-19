package com.boreas.plainlife.api;

import com.boreas.plainlife.base.BaseResponse;
import com.boreas.plainlife.mvp.models.equipLib.EquipLibraryModel;
import com.boreas.plainlife.mvp.models.equipLib.EquipLibraryReceModel;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;
import com.boreas.plainlife.mvp.models.login.AccessModel;
import com.boreas.plainlife.mvp.models.login.LoginModel;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;
import com.boreas.plainlife.mvp.models.precenter.KeeperUsedDevicesModel;
import com.boreas.plainlife.mvp.models.precenter.OperatorAllRecordModel;
import com.boreas.plainlife.mvp.models.precenter.OperatorApplicationModel;
import com.boreas.plainlife.mvp.models.precenter.PersonalBaseMsgModel;
import com.boreas.plainlife.mvp.models.shopping.AddShoppingCartModel;
import com.boreas.plainlife.mvp.models.shopping.DeleteShoppingCartModel;
import com.boreas.plainlife.mvp.models.shopping.ShoppingCartModel;
import com.boreas.plainlife.mvp.models.submit.FindCaseByUserModel;
import com.boreas.plainlife.mvp.models.submit.SubmitApprovalModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * author: 王秀清
 * des:   项目所用接口管理
 */
public interface ApiService {
    String MULTIPART_DATA = "multipart/form-data";
    String FILE = "file";

    //登录
    @POST("/service/api/login")
    Observable<LoginReceModel> login(@Body LoginModel params);

    //权限查看
    @GET("/service/role/findMyAccess")
    Observable<AccessModel> findMyAccess();

    // 操作员 获取购物车
    @POST("/service/shopCart/getCart")
    Observable<ShoppingCartModel> getCart();

    // 操作员 添加到购物车
    @POST("/service/shopCart/insert")
    Observable<BaseResponse<String>> insert(@Body AddShoppingCartModel addShoppingCartModel);

    // 操作员  批量添加到购物车
    @POST("/service/shopCart/inserts")
    Observable<BaseResponse<String>> inserts(@Body List<AddShoppingCartModel> addShoppingCartModels);

    // 操作员 批量删除购物车
    @POST("/service/shopCart/delBatch")
    Observable<ShoppingCartModel> delBatch(@Body DeleteShoppingCartModel deleteShoppingCartModel);

    //查询装备库
    @POST("/service/Equipment/findEquipment")
    Observable<EquipLibraryReceModel> findEquipment(@Body EquipLibraryModel params);

    //操作员提交申请
    @POST("/service/shopCart/submitApproval")
    Observable<BaseResponse<String>> submitApproval(@Body SubmitApprovalModel submitApproval);

    //基础信息
    @GET("/service/personal/base")
    Observable<PersonalBaseMsgModel> personalBase();

    //  操作员  常用装备和最近归还装备
    @GET("/service/personal/device")
    Observable<KeeperUsedDevicesModel> personalDevices();

    // 操作员  根据案件状态查询在办以及历史案件  status 1：在办 0：关闭（历史）
    @POST("/service/caseManager/findCaseTaskByStatus")
    @FormUrlEncoded
    Observable<TaskModel> findCaseTaskByStatus(@Field("status") int status);

    @POST("/service/caseManager/findCaseTaskByUser")
    Observable<FindCaseByUserModel> findCaseTaskByUser();

    // 操作员  查看我的消息
    @GET("/service/Apply/findMyApplication")
    Observable<OperatorApplicationModel> findMyApplication();

    // 操作员 查询所有 我的记录
    @GET("/service/shopCart/getAppAll")
    Observable<OperatorAllRecordModel> findAllRecord();
}
