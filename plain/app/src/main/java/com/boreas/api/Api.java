package com.boreas.api;

/**
 *
 * @author boreas
 * @date 18-2-24
 */

public class Api {
    /**
     * 图片
     * type 暂时未用到
     */
//    public static final String GET_PICS = "http://192.168.8.138:8080/pic/pics?type=1";
    public static final String GET_PICS = "http://10.10.16.82:8080/pic/pics?type=1";
    
    public static String MUSIC_LIST = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&callback=&from=webapp_music&method=baidu.ting.billboard.billList&type=@&size=100&offset=0";


}
