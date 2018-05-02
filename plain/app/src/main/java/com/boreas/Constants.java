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
        public static final int  NEW_MUSIC_LIST = 1;//新歌榜
        public static final int  HOT_MUSIC_LIST = 2;//热歌榜
        public static final int  YAOGUN_MUSIC_LIST = 11;//摇滚榜
        public static final int  JUESHI_MUSIC_LIST = 12;//爵士
        public static final int  LIUXING_MUSIC_LIST = 16;//流行
        public static final int  OUMEI_MUSIC_LIST = 21;//欧美金曲榜
        public static final int  JINGDIAN_MUSIC_LIST = 22;//经典老歌榜
        public static final int  QINGGE_MUSIC_LIST = 23;//情歌对唱榜
        public static final int  YINGSHI_MUSIC_LIST = 24;//影视金曲榜
        public static final int  NET_MUSIC_LIST = 25;//网络歌曲榜

    }
}
