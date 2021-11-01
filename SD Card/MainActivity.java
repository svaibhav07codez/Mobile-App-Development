package com.example.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.io.*;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button bt_read;
    Button bt_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_read = findViewById(R.id.bt_read);
        bt_write = findViewById(R.id.bt_write);
        final EditText et_name  = findViewById(R.id.et_name);
        final EditText et_text = findViewById(R.id.et_content);

        bt_read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    String inp,text = "";

                    File file = new File(getFilesDir(),et_name.getEditableText().toString());
                    BufferedReader bf = new BufferedReader(new FileReader(file));
                    while((inp = bf.readLine()) != null)
                        text += inp;

                    et_text.setText(text);
                    Toast.makeText(MainActivity.this, "File Read", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        bt_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    File file = new File(getFilesDir(),et_name.getEditableText().toString());
                    FileOutputStream fp = new FileOutputStream(file);
                    fp.write(et_text.getEditableText().toString().getBytes());
                    fp.close();
                    et_text.setText("");
                    Toast.makeText(MainActivity.this,"File Written", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}