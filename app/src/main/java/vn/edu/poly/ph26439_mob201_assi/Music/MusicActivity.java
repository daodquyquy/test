package vn.edu.poly.ph26439_mob201_assi.Music;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import vn.edu.poly.ph26439_mob201_assi.Dto.Music;
import vn.edu.poly.ph26439_mob201_assi.R;

public class MusicActivity extends AppCompatActivity {
    TextView titleTV,currentTimeTV,totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay,nextBtn,previousBtn,musicIcon;
    ArrayList<Music> songList;
    Music curentSong;
    int x =0;
    MediaPlayer mediaPlayer = MusicPlayer.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        titleTV = findViewById(R.id.song_title);
        currentTimeTV = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.musicIcon);

        titleTV.setSelected(true);

        songList = (ArrayList<Music>) getIntent().getSerializableExtra("LIST");

        setResourcesWithMusic();
        MusicActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTV.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));
                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.pause);
                        musicIcon.setRotation(x++);
                    }else{
                        pausePlay.setImageResource(R.drawable.play);
                        musicIcon.setRotation(x);
                    }
                }
                new Handler().postDelayed(this,100);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null&&fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    void setResourcesWithMusic(){
        curentSong = songList.get(MusicPlayer.countIndex);
        titleTV.setText(curentSong.getTitle());
        totalTimeTv.setText(convertToMMSS(curentSong.getDuration()));
        pausePlay.setOnClickListener(v -> pausePlay());
        nextBtn.setOnClickListener(v -> playNextSong());
        previousBtn.setOnClickListener(v -> playPreviousSong());
        playMusic();

    }

    private void playMusic(){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(curentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void playNextSong(){

        if(MusicPlayer.countIndex==songList.size()-1)
            return;
        MusicPlayer.countIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }
    private void playPreviousSong(){
        if(MusicPlayer.countIndex==0)
            return;
        MusicPlayer.countIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }
    private void pausePlay(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else
            mediaPlayer.start();
    }

    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMillis(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }


}