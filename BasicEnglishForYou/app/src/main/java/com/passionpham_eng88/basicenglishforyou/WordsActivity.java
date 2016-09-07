package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class WordsActivity extends AppCompatActivity {
    Button btn_back;
    ListView listword;
    public final int ALL_FOOD = 0;
    public final int HOME = 1;
    public final int HUMAN = 2;
    public final int JOB = 3;
    public final int FISH = 4;
    public final int BIRD = 5;
    public final int INSECT = 6;
    public final int CATTLE = 7;
    public final int WILD_ANIMAL = 8;
    public final int FLOWERS = 9;
    public final int MATERIAL = 10;
    public final int METAL = 11;
    public final int GAS = 12;
    public final int SHAPE = 13;
    public final int PARAM = 14;


    String[] itemname = {
            "Nhóm Đồ Ăn",
            "Đồ vật trong nhà",
            "Cơ thể Người",
            "Nghề nghiệp",
            "Nhóm Loài Cá",
            "Nhóm Chim-Gia Cầm",
            "Nhóm Côn Trùng",
            "Nhóm Gia súc",
            "Nhóm Động Hoang Dã",
            "Các Loài Hoa",
            "Nhóm Vật Liệu",
            "Nhóm Kim Loại",
            "Nhóm Vật Chất Khác",
            "Nhóm Hình Học",
            "Nhóm Đại Lượng",
    };

    Integer[] imgid={
            R.drawable.wfoods,
            R.drawable.home,
            R.drawable.body,
            R.drawable.job,
            R.drawable.wfish,
            R.drawable.wbird,
            R.drawable.wintersect,
            R.drawable.wcattle,
            R.drawable.wwildanimal,
            R.drawable.wflowers,
            R.drawable.wmaterial,
            R.drawable.wmetal,
            R.drawable.wgas,
            R.drawable.wshape,
            R.drawable.wparam,

    };

    public  void MessageMap()
    {
        listword = (ListView)findViewById(R.id.wordListView);
        btn_back = (Button)findViewById(R.id.wordBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        getSupportActionBar().setTitle("English Notebook For You");
        MessageMap();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                Intent menuScreen = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(menuScreen);
            }
        });

        CustomListViewAdapter custom_adapter = new CustomListViewAdapter(this, itemname, imgid);
        listword.setAdapter(custom_adapter);

        listword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case ALL_FOOD:
                        ShowAllFoodGroup(); // private screen
                        break;
                    case HOME:
                        ShowWordGroupScreen(HOME);
                        break;
                    case HUMAN:
                        ShowWordGroupScreen(HUMAN);
                        break;
                    case JOB:
                        ShowWordGroupScreen(JOB);
                        break;
                    case FISH:
                        ShowWordGroupScreen(FISH);
                        break;
                    case BIRD:
                        ShowWordGroupScreen(BIRD);
                        break;
                    case INSECT:
                        ShowWordGroupScreen(INSECT);
                        break;
                    case CATTLE:
                        ShowWordGroupScreen(CATTLE);
                        break;
                    case WILD_ANIMAL:
                        ShowWordGroupScreen(WILD_ANIMAL);
                        break;
                    case FLOWERS:
                        ShowWordGroupScreen(FLOWERS);
                        break;
                    case MATERIAL:
                        ShowWordGroupScreen(MATERIAL);
                        break;
                    case METAL:
                        ShowWordGroupScreen(METAL);
                        break;
                    case GAS:
                        ShowWordGroupScreen(GAS);
                        break;
                    case SHAPE:
                        ShowWordGroupScreen(SHAPE);
                        break;
                    case PARAM:
                        ShowWordGroupScreen(PARAM);
                        break;
                    default:
                        break;
                }
            }

            public  void ShowWordGroupScreen(int type)
            {
                Intent wordGroupScreen = new Intent(WordsActivity.this, WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", type);
                startActivity(wordGroupScreen);
            }

            public  void ShowAllFoodGroup()
            {
                Intent allFood = new Intent(WordsActivity.this, AllFoodsActivity.class);
                startActivity(allFood);
            }
        });
    }
}
