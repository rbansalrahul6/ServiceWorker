package com.example.serviceworker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.serviceworker.worker.ServiceWorker;
import com.example.serviceworker.worker.Task;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String IMAGE_1 = "";
    public static final String IMAGE_2 = "";

    ServiceWorker serviceWorker1 = new ServiceWorker("service_worker_1");
    ServiceWorker serviceWorker2 = new ServiceWorker("service_worker_2");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void fetchImage1AndSet() {
        serviceWorker1.addTask(new Task<Bitmap>() {
            @Override
            public Bitmap onExecuteTask() {
                // TODO
                Request request = new Request.Builder().url(IMAGE_1).build();
                Bitmap bitmap = null;
                try {
                    Response response = client.newCall(request).execute();
                    bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            public void onTaskComplete(Bitmap result) {
                // TODO: set image bitmap
            }
        });
    }
}
