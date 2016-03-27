package com.passionpham_eng88.basicenglishforyou;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    Button btn_back;
    ListView list;

    public final int BASIC_WORD = 0;
    public final int BASIC_GRAMMAR = 1;
    public final int FIRST_MEET = 2;
    public final int TOURISM = 3;
    public final int HELP_ME =4;
    public final int BUYING = 5;
    public final int PAYMENT = 6;
    public final int FEELING = 7;
    public final int LIFE_STATE = 8;
    public final int INTRODUCEME = 9;
    String[] itemname = {
            "Nhóm Từ Vựng Cơ Bản.",
            "Ngữ Pháp Cơ Bản",
            "Lần đầu gặp",
            "Đi du lich",
            "Cần Giúp Đỡ",
            "Mua Bán",
            "Thanh Toán",
            "Mẫu câu: Tôi cảm thấy.",
            "Mẫu câu: Cảm xúc!",
            "Giới Thiệu Bản Thân",
    };

    Integer[] imgid={
            R.drawable.word,
            R.drawable.gram,
            R.drawable.fistmeet,
            R.drawable.tourism,
            R.drawable.helpme,
            R.drawable.shopping,
            R.drawable.payment,
            R.drawable.feeling,
            R.drawable.relationship,
            R.drawable.aboutme,
    };

    public  void MessageMap()
    {
        list = (ListView)findViewById(R.id.menuListview);
        btn_back = (Button)findViewById(R.id.menuBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getSupportActionBar().setTitle("English Notebook For You");
        MessageMap();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainScreen);
            }
        });


        CustomListViewAdapter custom_adapter = new CustomListViewAdapter(this, itemname, imgid);
        list.setAdapter(custom_adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case BASIC_WORD:
                        ShowMenuBasicWord();
                        break;
                    case BASIC_GRAMMAR:
                        ShowGrammarScreen();
                        break;
                    case FIRST_MEET:
                        ShowItemList(FIRST_MEET);
                        break;
                    case TOURISM:
                        ShowItemList(TOURISM);
                        break;
                    case BUYING:
                        ShowItemList(BUYING);
                        break;
                    case HELP_ME:
                        ShowItemList(HELP_ME);
                        break;
                    case PAYMENT:
                        ShowItemList(PAYMENT);
                        break;
                    case FEELING:
                        ShowItemList(FEELING);
                        break;
                    case LIFE_STATE:
                        ShowItemList(LIFE_STATE);
                        break;
                    case INTRODUCEME:
                        ShowIntroduceMe();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void ShowItemList(int type)
    {
        Intent itemListScreen = new Intent(getApplicationContext(), IteamListActivity.class);
        itemListScreen.putExtra("Type", type);
        startActivity(itemListScreen);
    }
    public void ShowIntroduceMe()
    {
        Intent introduceScreen = new Intent(getApplicationContext(), IntroduceActivity.class);
        startActivity(introduceScreen);
    }

    public void ShowMenuBasicWord()
    {
        Intent wordScreen = new Intent(getApplicationContext(), WordsActivity.class);
        startActivity(wordScreen);
    }

    public void ShowGrammarScreen()
    {
        Intent gramamrScreen = new Intent(getApplicationContext(), GrammarActivity.class);
        startActivity(gramamrScreen);
    }
}
