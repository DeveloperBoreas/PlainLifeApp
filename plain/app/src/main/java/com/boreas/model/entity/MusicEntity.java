package com.boreas.model.entity;

import java.util.List;

/**
 *
 * @author boreas
 * @date 18-2-23
 */

public class MusicEntity {
    private List<MusicBean>  musicList;

    public List<MusicBean> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<MusicBean> musicList) {
        this.musicList = musicList;
    }

    public static class MusicBean{
        private String songname;   //歌曲名称
        private int seconds;        //歌曲时长
        private int songid;        //歌曲id
        private int singerid;       //歌手id
        private String albumpic_big;     //歌手大图
        private String albumpic_small; //歌手小图
        private String downUrl;          //下载地址
        private String url;              //播放地址
        private String singername;         //歌手名称

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getSongid() {
            return songid;
        }

        public void setSongid(int songid) {
            this.songid = songid;
        }

        public int getSingerid() {
            return singerid;
        }

        public void setSingerid(int singerid) {
            this.singerid = singerid;
        }

        public String getAlbumpic_big() {
            return albumpic_big;
        }

        public void setAlbumpic_big(String albumpic_big) {
            this.albumpic_big = albumpic_big;
        }

        public String getAlbumpic_small() {
            return albumpic_small;
        }

        public void setAlbumpic_small(String albumpic_small) {
            this.albumpic_small = albumpic_small;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSingername() {
            return singername;
        }

        public void setSingername(String singername) {
            this.singername = singername;
        }

        @Override
        public String toString() {
            return "MusicBean{" +
                    "songname='" + songname + '\'' +
                    ", seconds=" + seconds +
                    ", songid=" + songid +
                    ", singerid=" + singerid +
                    ", albumpic_big='" + albumpic_big + '\'' +
                    ", albumpic_small='" + albumpic_small + '\'' +
                    ", downUrl='" + downUrl + '\'' +
                    ", url='" + url + '\'' +
                    ", singername='" + singername + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "MusicEntity{" +
                "musicList=" + musicList +
                '}';
    }
}
