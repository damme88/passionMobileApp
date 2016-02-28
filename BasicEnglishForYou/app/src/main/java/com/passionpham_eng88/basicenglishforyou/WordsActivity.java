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
    public final int FISH = 0;
    public final int BIRD = 1;
    public final int INSECT = 2;
    public final int CATTLE = 3;
    public final int WILD_ANIMAL = 4;
    public final int FLOWERS = 5;
    public final int MATERIAL = 6;
    public final int METAL = 7;
    public final int GAS = 8;
    public final int SHAPE = 9;
    public final int PARAM = 10;

    String[] itemname = {
            "Nhóm Loài Cá",
            "Nhóm Chim-Gia Cầm",
            "Nhóm Côn Trùng",
            "Nhóm Gia súc",
            "Nhóm Động Hoang Dã",
            "Các Loài Hoa",
            "Nhóm Vật Liệu",
            "Nhóm Kim Loại",
            "Nhóm Khí Đốt",
            "Nhóm Hình Học",
            "Nhóm Đại Lượng"
    };

    Integer[] imgid={
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
                Intent wordGroupScreen = new Intent(getApplicationContext(), WordGroupActivity.class);
                wordGroupScreen.putExtra("Type", type);
                startActivity(wordGroupScreen);
            }
        });
    }
}
