package vn.edu.poly.ph26439_mob201_assi.Frg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import vn.edu.poly.ph26439_mob201_assi.Adapter.MusicAdapter;
import vn.edu.poly.ph26439_mob201_assi.Dto.Music;
import vn.edu.poly.ph26439_mob201_assi.Music.MusicFavoriteActivity;
import vn.edu.poly.ph26439_mob201_assi.R;


public class MusicFragnment extends Fragment {
    RecyclerView rcv_songs;
    TextView noMusic;
    ArrayList<Music> listSong = new ArrayList<>();
    ImageView imgFavorite;

    public MusicFragnment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_fragnment, container, false);
        rcv_songs = view.findViewById(R.id.rcv_song);
        noMusic = view.findViewById(R.id.no_songs_found);
        imgFavorite = view.findViewById(R.id.img_favorite);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MusicFavoriteActivity.class));
            }
        });
        if (checkPermission() == false){
            requestPermission();
        }
        String[] projection ={
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        while (cursor.moveToNext()){
            Music audio = new Music(cursor.getString(1),cursor.getString(0), cursor.getString(2));
            if(new File(audio.getPath()).exists()){
                listSong.add(audio);
            }
            if(listSong.size()==0){
                noMusic.setVisibility(View.VISIBLE);
            }else{
                //Recyclerview
                rcv_songs.setLayoutManager(new LinearLayoutManager(getActivity()));
                rcv_songs.setAdapter(new MusicAdapter(listSong,getActivity()));
            }


        }
        return view;
    }
    boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return  true;
        }else{
            return false;
        }
    }
    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(getActivity(), "Watting", Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(rcv_songs!=null){
            rcv_songs.setAdapter(new MusicAdapter(listSong,getActivity()));
        }
    }
}