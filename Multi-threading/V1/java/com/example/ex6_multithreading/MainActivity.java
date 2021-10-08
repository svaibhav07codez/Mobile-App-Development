package com.example.ex6_multithreading;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbar;
    Handler handler;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_sleep = findViewById(R.id.bt_sleep);
        final Button bt_start = findViewById(R.id.bt_start);

        pbar = findViewById(R.id.pbar);
        handler = new Handler();
        bt_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setTitle("Sleep in Progress");
                pd.setMessage("Started");
                pd.show();
                EditText et_time = findViewById(R.id.et_time);
                int count = Integer.parseInt(et_time.getEditableText().toString());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                },count*1000);
            }
        });
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (i=0; i<=100; i+=10) {
                            if (i==100) {
                                handler.post(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {
                                        pbar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
                                        bt_start.setText("FINISH");
                                    }
                                });
                            }
                            pbar.setProgress(i);
                            try {
                                Thread.sleep(100);
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}