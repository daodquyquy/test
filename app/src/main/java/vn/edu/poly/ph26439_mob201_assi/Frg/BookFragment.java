package vn.edu.poly.ph26439_mob201_assi.Frg;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import vn.edu.poly.ph26439_mob201_assi.R;

public class BookFragment extends Fragment {
    BottomNavigationView bottom_nav_book;

    public BookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        replateFragment(new Book_SucKhoe_Frg());
        View view =  inflater.inflate(R.layout.fragment_book, container, false);
        bottom_nav_book = view.findViewById(R.id.bottom_nav_book);
        bottom_nav_book.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_contact:
                        replateFragment(new Book_SucKhoe_Frg());
                        break;
                    case R.id.nav_bookmark:
                        replateFragment(new Book_TheThao_Frg());
                        break;
                    case R.id.nav_calllogs:
                        replateFragment(new Book_DuLich_Frg());
                        break;
                    case R.id.nav_media:
                        replateFragment(new Book_GiaoDuc_Frg());
                        break;

                }
                return true;
            }
        });

        return view;
    }
    public void replateFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_book,fragment);
        transaction.commit();
    }


}