package com.boreas.plainlife.receiver;

public class BatteryInfo {

    private String level;  ///电池剩余电量
    private int scal;   /////获取电池满电量数值
    private double voltage;  ///获取电池电压
    private double temp ; //电池温度

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScal() {
        return scal;
    }

    public void setScal(int scal) {
        this.scal = scal;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
