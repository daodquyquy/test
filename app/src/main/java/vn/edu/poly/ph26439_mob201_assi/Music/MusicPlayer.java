package vn.edu.poly.ph26439_mob201_assi.Music;

import android.media.MediaPlayer;

public class MusicPlayer {
    static MediaPlayer instance;

    public static MediaPlayer getInstance(){
        if (instance==null){
            instance = new MediaPlayer();
        }
        return instance;
    }
    public  static  int countIndex = -1;

}
