package vn.edu.poly.ph26439_mob201_assi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.ph26439_mob201_assi.Dto.Music;
import vn.edu.poly.ph26439_mob201_assi.R;

public class MusicFavoriteAdapter extends RecyclerView.Adapter<MusicFavoriteAdapter.viewholder>{
    ArrayList<Music> list;
    Context context;

    public MusicFavoriteAdapter(ArrayList<Music> list, Context context) {
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
        SharedPreferences sdf = context.getSharedPreferences("LIST_FAVORITE", Context.MODE_PRIVATE);
        String title = sdf.getString("TITLE","");
        holder.tv_title.setText(title.toString());


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
