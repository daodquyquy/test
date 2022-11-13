package vn.edu.poly.ph26439_mob201_assi.Book;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import vn.edu.poly.ph26439_mob201_assi.Adapter.BookAdapter;
import vn.edu.poly.ph26439_mob201_assi.Dto.Book;
import vn.edu.poly.ph26439_mob201_assi.R;

public class DownloadBook extends AsyncTask<String,Void, List<Book>> {
    String TAG = "zzzzzzzzzzzzzzz";
    Activity activity = null;

    public DownloadBook(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<Book> doInBackground(String... strings) {
        BookLoader bookLoader = new BookLoader();
        List<Book> list =new ArrayList<>();

        String ulRss = strings[0];
        Log.d(TAG,"1");
        try {
            URL url = new URL(ulRss);
            Log.d(TAG,"2");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            Log.d(TAG,"3");
            connection.connect();
            Log.d(TAG,"4");
            if(connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                Log.d(TAG,"5");
                list = bookLoader.getlistBook(inputStream);
                Log.d(TAG,"6");
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        super.onPostExecute(books);
        Log.d("zzzzz", "onPostExecute : số lượng bài viết : " + books.size());
        for (int i = 0; i < books.size(); i++) {
            Log.d("zzz", "Tiêu đề bài viết : " + books.get(i).getTitle());
        }
        BookAdapter adapter = new BookAdapter(books, activity);
        ListView lv = activity.findViewById(R.id.lv);
        lv.setAdapter(adapter);
    }
}
