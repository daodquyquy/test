package vn.edu.poly.ph26439_mob201_assi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.poly.ph26439_mob201_assi.BookWebActivity;
import vn.edu.poly.ph26439_mob201_assi.Dto.Book;
import vn.edu.poly.ph26439_mob201_assi.R;

public class BookAdapter extends BaseAdapter {
    List<Book> list;
    Context context;

    public BookAdapter(List<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.item_book,null);
        Book book = list.get(position);
        TextView tv_tilte = view.findViewById(R.id.tv_title);

        tv_tilte.setText(book.getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookWebActivity.class);
                String link =String.valueOf(list.get(position).getLink());
                intent.putExtra("link",link);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
