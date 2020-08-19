package com.boreas.plainlife.mq;

import java.util.List;

public class HBMessageBean {

    private String ty;
    private String ai;
    private String an;
    private String tskGroupId;
    private String tskId;
    private GpsBean gps;
    private String optPhoneId;
    private String from;
    private String dty;
    private String imei;
    private String plate;
    private String device_name;
    private String time;
    private CellBean cell;
    private String mac;
    private int tem;
    private double vol;
    private String swr;
    private String power;
    private int signal;
    private int amp;

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

    public String getTskGroupId() {
        return tskGroupId;
    }

    public void setTskGroupId(String tskGroupId) {
        this.tskGroupId = tskGroupId;
    }

    public String getTskId() {
        return tskId;
    }

    public void setTskId(String tskId) {
        this.tskId = tskId;
    }

    public GpsBean getGps() {
        return gps;
    }

    public void setGps(GpsBean gps) {
        this.gps = gps;
    }

    public String getOptPhoneId() {
        return optPhoneId;
    }

    public void setOptPhoneId(String optPhoneId) {
        this.optPhoneId = optPhoneId;
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CellBean getCell() {
        return cell;
    }

    public void setCell(CellBean cell) {
        this.cell = cell;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getTem() {
        return tem;
    }

    public void setTem(int tem) {
        this.tem = tem;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public String getSwr() {
        return swr;
    }

    public void setSwr(String swr) {
        this.swr = swr;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public int getAmp() {
        return amp;
    }

    public void setAmp(int amp) {
        this.amp = amp;
    }

    public static class GpsBean {

        private String gpsTime;
        private String lng;
        private String lat;
        private String locType;

        public String getGpsTime() {
            return gpsTime;
        }

        public void setGpsTime(String gpsTime) {
            this.gpsTime = gpsTime;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLocType() {
            return locType;
        }

        public void setLocType(String locType) {
            this.locType = locType;
        }
    }

    public static class CellBean {
        private List<?> cdma;
        private List<?> wcdma;
        private List<?> gsm;
        private List<LteBean> lte;

        public List<?> getCdma() {
            return cdma;
        }

        public void setCdma(List<?> cdma) {
            this.cdma = cdma;
        }

        public List<?> getWcdma() {
            return wcdma;
        }

        public void setWcdma(List<?> wcdma) {
            this.wcdma = wcdma;
        }

        public List<?> getGsm() {
            return gsm;
        }

        public void setGsm(List<?> gsm) {
            this.gsm = gsm;
        }

        public List<LteBean> getLte() {
            return lte;
        }

        public void setLte(List<LteBean> lte) {
            this.lte = lte;
        }

        public static class LteBean {
            private int dbm;
            private int mcc;
            private int pci;
            private int mnc;
            private int ci;
            private int ch;
            private String cellTime;
            private int tac;

            public int getDbm() {
                return dbm;
            }

            public void setDbm(int dbm) {
                this.dbm = dbm;
            }

            public int getMcc() {
                return mcc;
            }

            public void setMcc(int mcc) {
                this.mcc = mcc;
            }

            public int getPci() {
                return pci;
            }

            public void setPci(int pci) {
                this.pci = pci;
            }

            public int getMnc() {
                return mnc;
            }

            public void setMnc(int mnc) {
                this.mnc = mnc;
            }

            public int getCi() {
                return ci;
            }

            public void setCi(int ci) {
                this.ci = ci;
            }

            public int getCh() {
                return ch;
            }

            public void setCh(int ch) {
                this.ch = ch;
            }

            public String getCellTime() {
                return cellTime;
            }

            public void setCellTime(String cellTime) {
                this.cellTime = cellTime;
            }

            public int getTac() {
                return tac;
            }

            public void setTac(int tac) {
                this.tac = tac;
            }
        }
    }
}
