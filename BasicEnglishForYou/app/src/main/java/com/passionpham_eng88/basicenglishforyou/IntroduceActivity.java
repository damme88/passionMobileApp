package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IntroduceActivity extends AppCompatActivity {
    Button btnBack;
    Button btnNext;
    RelativeLayout selfDesScreen;
    int index = 0;

    Integer[] selfImg={
            R.drawable.selfdes1,
            R.drawable.selfdes2,
            R.drawable.selfdes3,
            R.drawable.selfdes4,
            R.drawable.selfdes5,
    };

    public  void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.btnBack);
        selfDesScreen = (RelativeLayout)findViewById(R.id.seflDescription);
        btnNext = (Button)findViewById(R.id.btnNext);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        getSupportActionBar().setTitle("Mẫu Tự Giới Thiệu Bản Thân");
        MessageMap();

        selfDesScreen.setBackgroundResource(R.drawable.selfdes1);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index < selfImg.length && index >= 0) {
                    selfDesScreen.setBackgroundResource(selfImg[index]);
                } else {
                    index = 0;
                    selfDesScreen.setBackgroundResource(selfImg[index]);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuScreen = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(menuScreen);
            }
        });
    }
}
