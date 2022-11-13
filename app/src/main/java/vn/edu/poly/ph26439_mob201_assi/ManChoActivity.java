package vn.edu.poly.ph26439_mob201_assi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class ManChoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Ẩn tên ứng dụng
        //getSupportActionBar().hide(); // Ẩn luôn thanh tiêu đề
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //bật chế độ toàn màn hình
        setContentView(R.layout.activity_man_cho);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Công việc sẽ thực hiện sau 5s = 5000ms
                Toast.makeText(ManChoActivity.this, "Hết 3s đợi rồi, stop", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(ManChoActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}