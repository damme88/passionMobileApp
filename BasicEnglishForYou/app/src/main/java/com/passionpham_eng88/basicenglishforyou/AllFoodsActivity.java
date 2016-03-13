package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AllFoodsActivity extends AppCompatActivity {
    ImageView imgFood;
    ImageView imgMilk;
    ImageView imgFruits;
    ImageView imgVeget;
    ImageView imgDrinking;
    ImageView imgCake;
    ImageView imgCookTool;
    ImageView imgSpices;
    ImageView imgCookType;
    Button btnBack;
    public final int FOODS_MEET = 20;
    public final int FOODS_MILK = 21;
    public final int FOODS_FRUITS = 22;
    public final int FOODS_VEGETABLE = 23;
    public final int FOODS_DRINKING = 24;
    public final int FOODS_CAKE = 25;
    public final int COOK_TOOL = 26;
    public final int COOK_SPICES= 27;
    public final int COOK_TYPE = 28;
    public void OnMessageMap()
    {
        imgFood = (ImageView)findViewById(R.id.imgFood);
        imgMilk = (ImageView)findViewById(R.id.imgMilk);
        imgFruits = (ImageView)findViewById(R.id.imgFruits);

        imgVeget = (ImageView)findViewById(R.id.imgVegetable);
        imgDrinking = (ImageView)findViewById(R.id.imgDrinking);
        imgCake = (ImageView)findViewById(R.id.imgCake);

        imgCookTool = (ImageView)findViewById(R.id.imgToolCooking);
        imgSpices = (ImageView)findViewById(R.id.imgSpices);
        imgCookType = (ImageView)findViewById(R.id.imgCookType);

        btnBack = (Button)findViewById(R.id.allFoodBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_foods);
        OnMessageMap();
        getSupportActionBar().setTitle("NHÓM THỰC PHẨM ĐỒ ĂN UỐNG");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wgWord = new Intent(getApplicationContext(), WordsActivity.class);
                startActivity(wgWord);
            }
        });

        imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", FOODS_MEET);
                startActivity(wordGroupScreen);
            }
        });

        imgMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", FOODS_MILK);
                startActivity(wordGroupScreen);
            }
        });

        imgFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", FOODS_FRUITS);
                startActivity(wordGroupScreen);
            }
        });

        imgVeget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", FOODS_VEGETABLE);
                startActivity(wordGroupScreen);
            }
        });

        imgDrinking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", FOODS_DRINKING);
                startActivity(wordGroupScreen);
            }
        });

        imgCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", FOODS_CAKE);
                startActivity(wordGroupScreen);
            }
        });

        imgCookTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", COOK_TOOL);
                startActivity(wordGroupScreen);
            }
        });

        imgSpices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", COOK_SPICES);
                startActivity(wordGroupScreen);
            }
        });

        imgCookType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", COOK_TYPE);
                startActivity(wordGroupScreen);
            }
        });
    }
}
