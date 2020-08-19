package com.boreas.plainlife.mq;

import java.util.List;

public class PushVipData {

    private String ty;
    private String ai;
    private String an;
    private LocBean loc;
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

    public LocBean getLoc() {
        return loc;
    }

    public void setLoc(LocBean loc) {
        this.loc = loc;
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

    public static class LocBean {
        /**
         * loc :
         * note :
         * lng : 113.132146
         * lc : ["",""]
         * time : 2019-07-09T08:41:12.000Z
         * radis :
         * lat : 46.132131
         * obj : {"imsi":"460010051462293","imei":""}
         */

        private String loc;
        private String note;
        private String lng;
        private String time;
        private String radis;
        private String lat;
        private ObjBean obj;
        private List<String> lc;
        private String stamp;
        private int level;
        private String pci;
        private String freq;
        private List<String> cdma;

        public String getPci() {
            return pci;
        }

        public void setPci(String pci) {
            this.pci = pci;
        }

        public String getFreq() {
            return freq;
        }

        public void setFreq(String freq) {
            this.freq = freq;
        }

        public String getStamp() {
            return stamp;
        }

        public void setStamp(String stamp) {
            this.stamp = stamp;
        }

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRadis() {
            return radis;
        }

        public void setRadis(String radis) {
            this.radis = radis;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public ObjBean getObj() {
            return obj;
        }

        public void setObj(ObjBean obj) {
            this.obj = obj;
        }

        public List<String> getLc() {
            return lc;
        }

        public void setLc(List<String> lc) {
            this.lc = lc;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<String> getCdma() {
            return cdma;
        }

        public void setCdma(List<String> cdma) {
            this.cdma = cdma;
        }

        public static class ObjBean {
            /**
             * imsi : 460010051462293
             * code :
             */

            private String imsi;
            private String imei;
            private String code;
            public String getImsi() {
                return imsi;
            }

            public void setImsi(String imsi) {
                this.imsi = imsi;
            }

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}

