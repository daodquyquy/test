package vn.edu.poly.ph26439_mob201_assi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class BookWebActivity extends AppCompatActivity {
    WebView web;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_web);
        back = findViewById(R.id.back);
        web = findViewById(R.id.web);
        Intent intent =getIntent();
        String link = intent.getStringExtra("link");
        web.loadUrl(link);
        web.setWebViewClient(new WebViewClient());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}