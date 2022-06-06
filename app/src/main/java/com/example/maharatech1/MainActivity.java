package com.example.maharatech1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nameEt,emailEt;
    Button sendBtn;
    String temp;
    Intent displayIntent;

    public static final String NAME="NAME";
    public static final String EMAIL="EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt=(EditText) findViewById(R.id.et_name);
        emailEt=(EditText) findViewById(R.id.et_email);
        sendBtn=(Button) findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayIntent =new Intent(MainActivity.this,MainActivity2.class);
                temp=nameEt.getText().toString();
                displayIntent.putExtra(NAME,temp);
                temp=emailEt.getText().toString();
                displayIntent.putExtra(EMAIL,temp);
                startActivity(displayIntent);
            }
        });


    }
}