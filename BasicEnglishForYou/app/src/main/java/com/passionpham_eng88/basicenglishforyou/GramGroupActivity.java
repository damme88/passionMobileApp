package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class GramGroupActivity extends AppCompatActivity {

    Button btnBack;
    RelativeLayout selfDesScreen;
    public final int SIM_PRESENT = 0;
    public final int CONTINOUS_PRESENT = 1;
    public final int PERFECT_PRESENT = 2;
    public final int PERFECT_CONTINUOUS_PRESENT = 3;
    public final int SIM_PAST = 4;
    public final int CONTINOUS_PAST = 5;
    public final int PERFECT_PAST = 6;
    public final int SIM_FUTURE = 7;
    public final int PEFECT_FUTURE = 8;
    public final  int CONTINOUS_FUTURE = 9;

    Integer[] selfImg={
            R.drawable.grasimpre,
            R.drawable.graconpre,
            R.drawable.graperpre,
            R.drawable.graperconpre,
            R.drawable.grasimpast,
            R.drawable.graconpast,
            R.drawable.graperpast,
            R.drawable.grasimfuture,
            R.drawable.graperfuture,
            R.drawable.graconfuture,
    };

    public void Init(int type)
    {
        selfDesScreen.setBackgroundResource(selfImg[type]);
    }
    public  void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.gramGroupBack);
        selfDesScreen = (RelativeLayout)findViewById(R.id.grammarGroup);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gram_group);

        getSupportActionBar().setTitle("Ngữ pháp cơ bản");
        MessageMap();

        Bundle extras = getIntent().getExtras();
        int type = -1;
        if (extras != null) {
            type  = extras.getInt("Type");
        }
        Init(type);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuScreen = new Intent(getApplicationContext(), GrammarActivity.class);
                startActivity(menuScreen);
            }
        });
    }
}
