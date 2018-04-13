package com.boreas;

/**
 *
 * @author boreas
 * @date 18-2-24
 * 全局变量
 */

public class Constants {

    private static final String TAG = Constants.class.getSimpleName();

    public static final String MUSIC = "MUSIC";
    public static final String PIC = "PIC";
    public static final String WEATHER = "WEATHER";
    public static final String SHARE = "SHARE";


    public static final String TAG_FLAG_1 = "TAG_FLAG_1";
    public static final String TAG_FLAG_2 = "TAG_FLAG_2";
    public static final String TAG_FLAG_3 = "TAG_FLAG_3";
    public static final String TAG_FLAG_4 = "TAG_FLAG_4";
    public static final String TAG_FLAG_5 = "TAG_FLAG_5";

    public static final int DEBUG_LEVEL = 10;

    public static final String BASE_URL = "http://127.0.0.1:8080/";


    public static class MusicType{
        // 3=欧美  5=内地  6=港台  16=韩国  17=日本  18=民谣  19=摇滚  23=销量  26=热歌
        //中文取名
        public static final int OUM_EI = 3;
        public static final int NEI_DI = 5;
        public static final int GUANG_TAI = 6;
        public static final int HAN_GUO = 16;
        public static final int RI_BEN = 17;
        public static final int MIN_YAO = 18;
        public static final int YAO_GUN = 19;
        public static final int XIAO_LIANG = 23;
        public static final int RE_GE = 26;

    }
}
