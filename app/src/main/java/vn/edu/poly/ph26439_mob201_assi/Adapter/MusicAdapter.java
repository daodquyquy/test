package vn.edu.poly.ph26439_mob201_assi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.ph26439_mob201_assi.Dto.Music;
import vn.edu.poly.ph26439_mob201_assi.Music.MusicActivity;
import vn.edu.poly.ph26439_mob201_assi.Music.MusicFavoriteActivity;
import vn.edu.poly.ph26439_mob201_assi.Music.MusicPlayer;
import vn.edu.poly.ph26439_mob201_assi.R;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.viewholder>{
    ArrayList<Music> list;
    Context context;

    public MusicAdapter(ArrayList<Music> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Music music = list.get(position);
        holder.tv_title.setText(music.getTitle());
        int drawable = R.drawable.favorite;
        int drawable0 = R.drawable.not_favorite;
        holder.img_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        if(MusicPlayer.countIndex==position){
            holder.tv_title.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.tv_title.setTextColor(Color.parseColor("#000000"));
        }
        int index = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigation View
                MusicPlayer.getInstance().reset();
                MusicPlayer.countIndex = index;
                Intent intent = new Intent(context, MusicActivity.class);
                intent.putExtra("LIST",list);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView icon_music,img_favorite;
        TextView tv_title;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            icon_music = itemView.findViewById(R.id.icon_view);
            tv_title = itemView.findViewById(R.id.title_music);
            img_favorite = itemView.findViewById(R.id.img_favorite);
        }

    }
}
