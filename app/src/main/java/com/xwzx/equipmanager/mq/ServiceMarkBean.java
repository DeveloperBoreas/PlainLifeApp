package com.xwzx.equipmanager.mq;

public class ServiceMarkBean {

    /**
     * ty : mark
     * ai : AJ-001
     * an : 案件01
     * gps : {"gpsTime":" 2018-12-03T16:00:00.000Z","lat":40.19959858008862,"lng":116.23196686923124,"locType":"W|B"}
     * label : 显示内容
     * publisher : 发布人
     * detail : 详细信息
     * imei : 866716033130570
     * from : service
     * dty : loc
     * time : 2018-12-03T16:00:00.000Z
     */

    private String ty;
    private String ai;
    private String an;
    private GpsBean gps;
    private String label;
    private String publisher;
    private String detail;
    private String imei;
    private String from;
    private String dty;
    private String time;

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
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

    public GpsBean getGps() {
        return gps;
    }

    public void setGps(GpsBean gps) {
        this.gps = gps;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDty() {
        return dty;
    }

    public void setDty(String dty) {
        this.dty = dty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class GpsBean {
        /**
         * gpsTime :  2018-12-03T16:00:00.000Z
         * lat : 40.19959858008862
         * lng : 116.23196686923124
         * locType : W|B
         */

        private String gpsTime;
        private double lat;
        private double lng;
        private String locType;

        public String getGpsTime() {
            return gpsTime;
        }

        public void setGpsTime(String gpsTime) {
            this.gpsTime = gpsTime;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getLocType() {
            return locType;
        }

        public void setLocType(String locType) {
            this.locType = locType;
        }
    }
}
