package com.boreas.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.boreas.service.MusicService;
import com.boreas.ui.activity.MainActivity;

import static com.boreas.service.MusicService.MUSIC_ACTION_PLAY;

/**
 * Created by admin on 2018/4/11.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public static final String ACTION_MUSIC_PLAY = "com.aidlmusicplayer.www.action.music.play";
    public static final String ACTION_MUSIC_NEXT = "com.aidlmusicplayer.www.action.music.next";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        if(MainActivity.context.getMusicPlayer() == null){
            return;
        }
        String action = intent.getAction();
        try {
            switch (action){
                case ACTION_MUSIC_PLAY:
                    if(MusicService.MUSIC_CURRENT_ACTION == MUSIC_ACTION_PLAY){
                        MainActivity.context.getMusicPlayer().action(MusicService.MUSIC_ACTION_PAUSE,"");
                    }else{
                        MainActivity.context.getMusicPlayer().action(MusicService.MUSIC_ACTION_CONTINUE_PLAY,"");
                    }
                    break;
                case ACTION_MUSIC_NEXT:
                    MainActivity.context.getMusicPlayer().action(MusicService.MUSIC_ACTION_NEXT,"");
                    break;
                    default:break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
