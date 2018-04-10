package com.boreas;
import com.boreas.IMusicPlayerListener;

interface IMusicPlayer {

     void action(in int action ,in String datum);
     void registerListener(IMusicPlayerListener listener);
     void unregisterListener(IMusicPlayerListener listener);
     Message getCurrentSongInfo();
}
