package com.example.keyboardapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button button;
private InputConnection inputConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText3);
        editText.setTextIsSelectable(true);
        inputConnection = editText.onCreateInputConnection(new EditorInfo());
    }

    public void btnchar(View view) {
        button = (Button)findViewById(view.getId());
        inputConnection.commitText(button.getText(),1);
    }

    public void btnsp(View view) {
        switch (view.getId()) {
            case R.id.button40: if(editText.length() != 0)inputConnection.deleteSurroundingText(1,0);
                                else inputConnection.commitText("",1); break;
            case R.id.button43: inputConnection.commitText(" ",1); break;
            case R.id.button44: inputConnection.commitText("\n",1); break;
            default:
        }
    }
}