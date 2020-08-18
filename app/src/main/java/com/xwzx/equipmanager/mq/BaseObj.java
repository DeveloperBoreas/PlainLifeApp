package com.xwzx.equipmanager.mq;


import com.xwzx.equipmanager.utils.TimeUtils;

/****/
public class BaseObj {
    /**类型   --- 事件**/
    private String ty;
    /** 事件下面对应的子类型**/
    private String sty;
    /**案件编号**/
    private String ai;
    /**案件名称**/
    private String an;
    /** 任务组 ID 后台分配**/
//    private String tskGroupId;
    /** 任务ID **/
    private String tskId;
    /** 操作手机ID**/
//    private String optPhoneId;
    /** 设备名称  设备组名称 +  手机当前网络环境 2G 3G 4G **/
    private String deivceName;
    /** 车牌号 **/
    private String plate;
    /**设备类型**/
    private String dty;
    /**手机imei**/
    private String imei;
    /****/
    private String time;
    /****/
    private String tskName;

    private String datasource;

    public BaseObj() {
    }
    public BaseObj(String ty, String sty, String ai, String an, String tskId, String deivceName, String plate, String imei, String tskName, String datasource) {
        this.ty = ty;
        this.sty = sty;
        this.ai = ai;
        this.an = an;
        this.tskId = tskId;
        this.deivceName = deivceName;
        this.plate = plate;
        this.dty = "loc";
        this.imei = imei;
        this.time = TimeUtils.getUTCTimeDef();
        this.tskName = tskName;
        this.datasource = datasource;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public String getSty() {
        return sty;
    }

    public void setSty(String sty) {
        this.sty = sty;
    }

    public String getAi() {
        return ai;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getTskId() {
        return tskId;
    }

    public void setTskId(String tskId) {
        this.tskId = tskId;
    }

    public String getDeivceName() {
        return deivceName;
    }

    public void setDeivceName(String deivceName) {
        this.deivceName = deivceName;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDty() {
        return dty;
    }

    public void setDty(String dty) {
        this.dty = dty;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTskName() {
        return tskName;
    }

    public void setTskName(String tskName) {
        this.tskName = tskName;
    }
}
