package com.passionpham_eng88.basicenglishforyou;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    public final int LISTENING = 2;
    public final int SITUATION = 3;
    public final int FORKID = 4;
    String[] itemname = {
            "Từ Vựng Cơ Bản.",
            "Ngữ Pháp Cơ Bản",
            "Luyện Nghe",
            "Tình Huống Cơ bản",
            "Tiếng Anh cho Bé"
    };

    Integer[] imgid={
            R.drawable.mnword,
            R.drawable.mngrammar,
            R.drawable.mnlistening,
            R.drawable.mnsituation,
            R.drawable.mnengkid,
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

        // Thiết lập banner quảng cáo trên app
        AdView mAdView = (AdView) findViewById(R.id.adViewMenu);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Thiết lập title cho màn hình menu
        getSupportActionBar().setTitle("English Notebook For You");
        MessageMap();

        // Load item và fill vào list view
        CustomListViewAdapter custom_adapter = new CustomListViewAdapter(this, itemname, imgid);
        list.setAdapter(custom_adapter);


        // Xử lý sự kiện click vào item của list view
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case BASIC_WORD:
                        ShowMenuBasicWord();
                        break;
                    case BASIC_GRAMMAR:
                        ShowGrammarScreen();
                        break;
                    case LISTENING:
                        ShowListeningScreen();
                        break;
                    case SITUATION:
                        ShowSituationScreen();
                        break;
                    case FORKID:
                        ShowEnglishForKid();
                        break;
                    default:
                        break;
                }
            }
        });

        // Xử lý cho sự kiện back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                Intent main_screen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_screen);
            }
        });
    }

    public void ShowMenuBasicWord()
    {
        Intent wordScreen = new Intent(MenuActivity.this, WordsActivity.class);
        startActivity(wordScreen);
    }

    public void ShowGrammarScreen()
    {
        Intent gramamrScreen = new Intent(MenuActivity.this, GrammarActivity.class);
        startActivity(gramamrScreen);
    }

    public void ShowListeningScreen()
    {
        Intent listenSceen = new Intent(MenuActivity.this, ListeningActivity.class);
        startActivity(listenSceen);
    }

    public void ShowSituationScreen()
    {
        Intent situationScreen = new Intent(MenuActivity.this, SituationActivity.class);
        startActivity(situationScreen);
    }

    public void ShowEnglishForKid()
    {
        Intent kidEnglisScreen = new Intent(MenuActivity.this, EnglishForKidActivity.class);
        startActivity(kidEnglisScreen);
    }
}
