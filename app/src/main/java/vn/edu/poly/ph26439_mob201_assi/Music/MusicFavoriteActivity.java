package vn.edu.poly.ph26439_mob201_assi.Music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import vn.edu.poly.ph26439_mob201_assi.Adapter.MusicAdapter;
import vn.edu.poly.ph26439_mob201_assi.Adapter.MusicFavoriteAdapter;
import vn.edu.poly.ph26439_mob201_assi.Dto.Music;
import vn.edu.poly.ph26439_mob201_assi.R;

public class MusicFavoriteActivity extends AppCompatActivity {
    RecyclerView rcv_songs;
    TextView noMusic;
    ArrayList<Music> listSong = new ArrayList<>();
    MusicFavoriteAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_favorite);
        rcv_songs = findViewById(R.id.rcv_favorite_song);
        noMusic = findViewById(R.id.no_songs_found);
        adapter = new MusicFavoriteAdapter(listSong,this);
        if (checkPermission() == false){
            requestPermission();
        }
        String[] projection ={
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        while (cursor.moveToNext()){
            Music audio = new Music(cursor.getString(1),cursor.getString(0), cursor.getString(2));
            if(new File(audio.getPath()).exists()){
                listSong.add(audio);
            }
            if(listSong.size()==0){
                noMusic.setVisibility(View.VISIBLE);
            }else{
                //Recyclerview
                rcv_songs.setLayoutManager(new LinearLayoutManager(MusicFavoriteActivity.this));
                rcv_songs.setAdapter(new MusicAdapter(listSong,MusicFavoriteActivity.this));
            }


        }

    }
    boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MusicFavoriteActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return  true;
        }else{
            return false;
        }
    }
    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MusicFavoriteActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MusicFavoriteActivity.this, "Watting", Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MusicFavoriteActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rcv_songs!=null){
            rcv_songs.setAdapter(new MusicAdapter(listSong,MusicFavoriteActivity.this));
        }
    }
}