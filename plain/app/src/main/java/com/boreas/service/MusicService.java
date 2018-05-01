package com.boreas.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.boreas.IMusicPlayer;
import com.boreas.IMusicPlayerListener;
import com.boreas.model.entity.MusicEntity;
import com.boreas.utils.GsonHelper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者 boreas
 * 日期 18-4-10
 * 邮箱 13051089921@163.com
 */

public class MusicService extends Service  implements
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        AudioManager.OnAudioFocusChangeListener {

    private static final String TAG = MusicService.class.getSimpleName();

    /******************************************************************/
    public static final int MUSIC_ACTION_INIT = 253;
    public static final int MUSIC_ACTION_PLAY = 255;
    public static final int MUSIC_ACTION_PREVIOUS = 256;
    public static final int MUSIC_ACTION_NEXT = 257;
    public static final int MUSIC_ACTION_PAUSE = 259;
    public static final int MUSIC_ACTION_STOP = 260;
    public static final int MUSIC_ACTION_CONTINUE_PLAY = 280;
    public static final int MUSIC_ACTION_SEEK_PLAY = 270;
    public static final int MUSIC_ACTION_MUTE = 258;

    public static int MUSIC_CURRENT_ACTION = -1;

    /**循环模式**/
    public static final int MUSIC_PLAY_MODE_RANDOM = 2001;//random
    public static final int MUSIC_PLAY_MODE_REPEAT = 2002;//sequence
    public static final int MUSIC_PLAY_MODE_SINGLE = 2003;//single

    public static int MUSIC_CURRENT_MODE = MUSIC_PLAY_MODE_RANDOM;

    public static final int PLAYER_LISTENER_ACTION_NORMAL = 1001;
    public static final int PLAYER_LISTENER_ACTION_DANGER = 1005;
    /******************************************************************/

    private MediaPlayer mMediaPlayer;
    private RemoteCallbackList<IMusicPlayerListener> remoteCallbackList = new RemoteCallbackList<>();
    private Timer mTimer;
    private ArrayList<MusicEntity.MusicBean> mSong_list = new ArrayList<>();
    private int currentPosition;

    //地图定位
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    @Override
    public void onCreate() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        ((AudioManager) getSystemService(Context.AUDIO_SERVICE)).
                requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        initMapLocation();
        super.onCreate();
    }

    private void initMapLocation() {
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        mLocationOption.setOnceLocation(false);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setInterval(5000);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setMockEnable(true);
        mLocationOption.setHttpTimeOut(10000);
        mLocationOption.setLocationCacheEnable(false);


        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Logger.d("定位:" + aMapLocation.getAddress());
//可在其中解析amapLocation获取相应内容。
                    }else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }

            }
        });

        if(null != mLocationClient){
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    Binder mBinder = new IMusicPlayer.Stub() {
        @Override
        public void action(int action, String datum) throws RemoteException {
            abloutPlay(action,datum);
        }

        @Override
        public void registerListener(IMusicPlayerListener listener) throws RemoteException {
            if(listener != null){
                remoteCallbackList.register(listener);
            }

        }

        @Override
        public void unregisterListener(IMusicPlayerListener listener) throws RemoteException {
            if(listener != null){
                remoteCallbackList.unregister(listener);
            }
        }

        @Override
        public Message getCurrentSongInfo() throws RemoteException {
            Message msg = Message.obtain();
            if (mSong_list != null && mSong_list.size() > 0) {
                msg.obj = mSong_list.get(currentPosition);
            }
            return msg;
        }
    };
    private void matchPlayMode(int action) {
        switch (action) {
            case MUSIC_PLAY_MODE_RANDOM:
                MUSIC_CURRENT_MODE = MUSIC_PLAY_MODE_RANDOM;
                break;
            case MUSIC_PLAY_MODE_REPEAT:
                MUSIC_CURRENT_MODE = MUSIC_PLAY_MODE_REPEAT;
                break;
            case MUSIC_PLAY_MODE_SINGLE:
                MUSIC_CURRENT_MODE = MUSIC_PLAY_MODE_SINGLE;
                break;
        }
    }
    private void abloutPlay(int action, String datum) {
        switch (action) {
            case MUSIC_ACTION_INIT://数据只能通过init来传输
                initSongData(datum);
                break;
            ///*******************about play***********************************************/
            case MUSIC_ACTION_PAUSE:
                pauseSong();
                break;
            case MUSIC_ACTION_STOP:
                stopSong();
                break;
            case MUSIC_ACTION_SEEK_PLAY:
                seekPlaySong(Integer.parseInt(datum));
                break;
            case MUSIC_ACTION_PLAY:
                onActionPlay(datum);//原来的初始化位置
                break;
            case MUSIC_ACTION_CONTINUE_PLAY: //暂停位置继续播放
                continuePlaySong();
                break;
            case MUSIC_ACTION_PREVIOUS://上一首
                previousSong();
                break;
            case MUSIC_ACTION_NEXT://下一首
                modePlay();
                break;
            case MUSIC_ACTION_MUTE:
                mMediaPlayer.setVolume(0f, 0f);
                break;
            default:
                ///*******************about play mode***********************************************/
                matchPlayMode(action);
                break;
        }
    }
    /**传入音乐数据**/
    private void initSongData(String datum) {
        if (TextUtils.isEmpty(datum)) {
            return;
        }
        Logger.d("--------MusicService initSongData------- "+datum);
        MusicEntity musicEntity = GsonHelper.getGson().fromJson(datum, MusicEntity.class);
        currentPosition = 0;
        mSong_list.clear();
        mSong_list.addAll(musicEntity.getMusicList());
    }

    /******************************************************************/
    private void modePlay() {
        switch (MUSIC_CURRENT_MODE) {
            case MUSIC_PLAY_MODE_RANDOM:
                if (mSong_list != null && mSong_list.size() > 0) {
                    Random random = new Random();
                    currentPosition = random.nextInt(mSong_list.size());
                    play();
                }
                break;
            case MUSIC_PLAY_MODE_REPEAT:
                nextSong();
                break;
            case MUSIC_PLAY_MODE_SINGLE:
                play();
                break;
            default:break;
        }
    }

    private void nextSong() {
        if (++currentPosition >= mSong_list.size()) {
            currentPosition = 0;
        }
        play();
    }
    private void previousSong() {
        if (currentPosition > 0) {
            currentPosition--;
        } else {
            currentPosition = mSong_list.size() - 1;
        }
        play();
    }

    /**  播放音乐  */
    public void playSong(String path) {
        try {
            stopSong();
            mMediaPlayer.reset();//idle
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            MUSIC_CURRENT_ACTION = MUSIC_ACTION_PLAY;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void pauseSong() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            MUSIC_CURRENT_ACTION = MUSIC_ACTION_PAUSE;
            onPausePlay();
        }
    }
    public void onPausePlay() {
        Message msg = Message.obtain();
        msg.what = MUSIC_ACTION_PLAY;
        msg.arg1 = 1;
        sendMessage(MUSIC_ACTION_PLAY, msg);
    }
    private void onStartPlay() {
        Message msg = Message.obtain();
        msg.what = MUSIC_ACTION_PLAY;
        msg.arg1 = 1;
        sendMessage(MUSIC_ACTION_PLAY, msg);
    }

    private void onStopPlay() {
        Message msg = Message.obtain();
        msg.what = MUSIC_ACTION_STOP;
        msg.arg1 = 1;
        sendMessage(MUSIC_ACTION_PLAY, msg);
    }

    public void continuePlaySong() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            MUSIC_CURRENT_ACTION = MUSIC_ACTION_PLAY;
            onStartPlay();
        }
    }


    public void stopSong() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            MUSIC_CURRENT_ACTION = MUSIC_ACTION_STOP;
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
            }
        }
    }

    public void seekPlaySong(int progress) {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.seekTo(progress);
        }
    }

    private void onActionPlay(String itemBean) {
        if (TextUtils.isEmpty(itemBean)) {
            play();
            return;
        }
        Logger.d("--------MusicService initSongData------- "+itemBean);
        MusicEntity.MusicBean bean = GsonHelper.getGson().fromJson(itemBean,MusicEntity.MusicBean.class);
        for (int i = 0; i < mSong_list.size(); i++) {
            MusicEntity.MusicBean tempBean = mSong_list.get(i);
            if(tempBean.getSongid() == bean.getSongid()){
                currentPosition = mSong_list.indexOf(bean);
                break;
            }
        }
        play();
    }

    /**
     * 待修改
     */
    private void play() {
        MusicEntity.MusicBean musicBean = mSong_list.get(currentPosition);
        if(musicBean == null){
            MusicEntity.MusicBean lastMusicBean = mSong_list.get(mSong_list.size()-1);
            currentPosition = mSong_list.size()-1;
            onStartPlay();
            playSong(lastMusicBean.getUrl());
            return;
        }else if (musicBean != null && musicBean.getUrl() != null){
            onStartPlay();
            playSong(musicBean.getUrl());
        }else {
            Toast.makeText(MusicService.this, "Music playback error", Toast.LENGTH_LONG).show();
        }
    }

    private void onPaying() {
        int currentPosition = mMediaPlayer.getCurrentPosition();
        int totalDuration = mMediaPlayer.getDuration();
        Message msg = Message.obtain();
        msg.what = MUSIC_ACTION_SEEK_PLAY;
        msg.arg1 = currentPosition;
        msg.arg2 = totalDuration;
        sendMessage(PLAYER_LISTENER_ACTION_NORMAL, msg);
    }


    /** 发送通知到注册  IMusicPlayerListener 的回调中**/
    private void sendMessage(int action, Message msg) {
        try {
            final int N = remoteCallbackList.beginBroadcast();
            for (int i = 0; i < N; i++) {
                IMusicPlayerListener broadcastItem = remoteCallbackList.getBroadcastItem(i);
                if (broadcastItem != null) {
                    broadcastItem.action(action, msg);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            try {
                remoteCallbackList.finishBroadcast();
            } catch (IllegalArgumentException illegalArgumentException) {
                Logger.e("Error while diffusing message to listener  finishBroadcast ", illegalArgumentException);
            }
        }

    }
    /******************************************************************/




    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN://获得了Audio Focus；
                // resume playback
                mMediaPlayer.start();
                mMediaPlayer.setVolume(1.0f, 1.0f);
                break;
            case AudioManager.AUDIOFOCUS_LOSS:  //失去了Audio Focus，并将会持续很长的时间。
                // Lost focus for an unbounded amount of time: stopSong playback and release media player
                if (mMediaPlayer.isPlaying())
                    mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT://暂时失去Audio Focus，并会很快再次获得。
                // Lost focus for a short time, but we have to stopSong
                // playback. We don't release the media player because playback
                // is likely to resume
                if (mMediaPlayer.isPlaying())
                    mMediaPlayer.pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK://暂时失去AudioFocus，但是可以继续播放，不过要在降低音量。
                // Lost focus for a short time, but it's ok to keep playing
                // at an attenuated level
                if (mMediaPlayer.isPlaying())
                    mMediaPlayer.setVolume(0.1f, 0.1f);
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        modePlay();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this, "播放失败", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onPaying();
            }
        }, 0, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mLocationClient != null){
            mLocationClient.onDestroy();
            mLocationClient = null;

        }
    }
}
