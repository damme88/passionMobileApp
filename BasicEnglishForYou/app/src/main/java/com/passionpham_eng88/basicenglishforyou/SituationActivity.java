package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class SituationActivity extends AppCompatActivity {

    Button btn_back;
    ListView list;
    public final int FIRST_MEET = 0;
    public final int TOURISM = 1;
    public final int HELP_ME =2;
    public final int BUYING = 3;
    public final int PAYMENT = 4;
    public final int FEELING = 5;
    public final int LIFE_STATE = 6;
    public final int INTRODUCEME = 10;
    String[] itemname = {
            "Lần đầu gặp",
            "Đi du lich",
            "Cần Giúp Đỡ",
            "Mua Bán",
            "Thanh Toán",
            "Mẫu câu: Tôi cảm thấy.",
            "Mẫu câu: Cảm xúc!"
    };

    Integer[] imgid={
            R.drawable.fistmeet,
            R.drawable.tourism,
            R.drawable.helpme,
            R.drawable.shopping,
            R.drawable.payment,
            R.drawable.feeling,
            R.drawable.relationship,
    };

    public  void MessageMap()
    {
        list = (ListView)findViewById(R.id.siListView);
        btn_back = (Button)findViewById(R.id.siBack);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        // Thiết lập banner quảng cáo trên app
        AdView mAdView = (AdView) findViewById(R.id.adViewsi);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getSupportActionBar().setTitle("Tình Huống Cơ bản");
        MessageMap();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                Intent main_screen = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(main_screen);
            }
        });


        CustomListViewAdapter custom_adapter = new CustomListViewAdapter(this, itemname, imgid);
        list.setAdapter(custom_adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
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
                    default:
                        break;
                }
            }
        });
    }

    public void ShowItemList(int type)
    {
        Intent itemListScreen = new Intent(SituationActivity.this, IteamListActivity.class);
        itemListScreen.putExtra("Type", type);
        startActivity(itemListScreen);
    }

    public void ShowIntroduceMe()
    {
        Intent introduceScreen = new Intent(getApplicationContext(), IntroduceActivity.class);
        startActivity(introduceScreen);
    }
}
