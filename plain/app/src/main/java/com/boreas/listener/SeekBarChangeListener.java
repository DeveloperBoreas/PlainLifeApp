package com.boreas.listener;

import android.os.Build;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.widget.SeekBar;

import com.boreas.App;
import com.boreas.IMusicPlayer;
import com.boreas.service.MusicService;
import com.boreas.ui.activity.MainActivity;

/**
 * Created by admin on 2018/4/11.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private IMusicPlayer musicPlayerService;
    public SeekBarChangeListener(IMusicPlayer musicPlayerService){
        this.musicPlayerService = musicPlayerService;
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        try {
            seekBar.setProgress(seekBar.getProgress());
            if (musicPlayerService!=null) {
                musicPlayerService.action(MusicService.MUSIC_ACTION_SEEK_PLAY, String.valueOf(seekBar.getProgress()));
            }else{
                musicPlayerService = App.app.getMusicPlayerService();
                musicPlayerService.action(MusicService.MUSIC_ACTION_SEEK_PLAY, String.valueOf(seekBar.getProgress()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
