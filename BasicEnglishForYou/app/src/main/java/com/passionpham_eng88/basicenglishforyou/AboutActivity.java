package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class AboutActivity extends AppCompatActivity {

    RelativeLayout about_screen;
    Button btnBack;

    protected void MessageMap()

    {
        btnBack = (Button)findViewById(R.id.btnExit);
        about_screen = (RelativeLayout)findViewById(R.id.aboutScreen);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        MessageMap();
        about_screen.setBackgroundResource(R.drawable.aboutbkgn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainScreen);
            }
        });
    }
}
