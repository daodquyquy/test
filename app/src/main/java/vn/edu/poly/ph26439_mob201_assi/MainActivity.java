package vn.edu.poly.ph26439_mob201_assi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import vn.edu.poly.ph26439_mob201_assi.Frg.BookFragment;
import vn.edu.poly.ph26439_mob201_assi.Frg.ChangeImg_Frg;
import vn.edu.poly.ph26439_mob201_assi.Frg.HomeFragment;
import vn.edu.poly.ph26439_mob201_assi.Frg.MusicFragnment;
import vn.edu.poly.ph26439_mob201_assi.Frg.QuanLyTaiKhoanFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView nav =findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navgationdrawer_open,R.string.navgationdrawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.home :
                        drawerLayout.closeDrawer(nav);
                        replateFragment(new HomeFragment());
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.book:
                        drawerLayout.closeDrawer(nav);
                        replateFragment(new BookFragment());
                        Toast.makeText(MainActivity.this, "Đọc báo", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.music:
                        drawerLayout.closeDrawer(nav);
                        replateFragment(new MusicFragnment());
                        Toast.makeText(MainActivity.this, "Nghe nhạc", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.quanlytaikhoan:
                        drawerLayout.closeDrawer(nav);
                        replateFragment(new QuanLyTaiKhoanFragment());
                        Toast.makeText(MainActivity.this, "Tài khoản", Toast.LENGTH_SHORT).show();
                        break;
//                    case R.id.changeimg:
//                        drawerLayout.closeDrawer(nav);
//                        replateFragment(new ChangeImg_Frg());
//                        Toast.makeText(MainActivity.this, "Đổi ảnh ", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.thoat :
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        break;
                }

                return false;
            }
        });
        replateFragment(new HomeFragment());
    }
    public void replateFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framer,fragment);
        transaction.commit();
    }



}