package com.example.serviceworker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.serviceworker.worker.ServiceWorker;
import com.example.serviceworker.worker.Task;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView1, imageView2;
    private Button button1, button2;
    public static final String IMAGE_1 = "https://i.picsum.photos/id/237/200/300.jpg";
    public static final String IMAGE_2 = "https://i.picsum.photos/id/866/200/300.jpg";

    ServiceWorker serviceWorker1 = new ServiceWorker("service_worker_1");
    ServiceWorker serviceWorker2 = new ServiceWorker("service_worker_2");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchImage1AndSet();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchImage2AndSet();
            }
        });
    }

    private void initViews() {
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
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
                if (result != null) {
                    imageView1.setImageBitmap(result);
                }
            }
        });
    }

    private void fetchImage2AndSet() {
        serviceWorker1.addTask(new Task<Bitmap>() {
            @Override
            public Bitmap onExecuteTask() {
                // TODO
                Request request = new Request.Builder().url(IMAGE_2).build();
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
                if (result != null) {
                    imageView2.setImageBitmap(result);
                }
            }
        });
    }
}
