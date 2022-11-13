package vn.edu.poly.ph26439_mob201_assi.Frg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.poly.ph26439_mob201_assi.Book.DownloadBook;
import vn.edu.poly.ph26439_mob201_assi.R;

public class Book_DuLich_Frg extends Fragment {

    String ulRss = "https://vnexpress.net/rss/du-lich.rss";
    public Book_DuLich_Frg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book__giao_duc__frg, container, false);
        DownloadBook downloadBook = new DownloadBook(getActivity());
        downloadBook.execute(ulRss);
        return view;
    }
}