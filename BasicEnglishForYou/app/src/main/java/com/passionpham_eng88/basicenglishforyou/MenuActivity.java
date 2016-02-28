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

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    Button btn_back;
    ListView list;

    public final int FIRST_MEET = 0;
    public final int TOURISM = 1;
    public final int HELP_ME = 2;
    public final int BUYING = 3;
    public final int PAYMENT = 4;
    public final int INTRODUCEME = 5;
    public final int FEELING = 6;
    public final int LIFE_STATE = 7;
    public final int BASIC_WORD = 8;
    String[] itemname = {
            "Lần đầu gặp",
            "Đi du lich",
            "Cần Giúp đỡ",
            "Mua Bán",
            "Thanh Toán",
            "Giới Thiệu Mình.(Phỏng Vấn)",
            "Mẫu câu: Tôi cảm thấy.",
            "Mẫu câu: Cảm xúc!",
            "Nhóm Từ Vựng Cơ Bản.",

    };

    Integer[] imgid={
            R.drawable.fistmeet,
            R.drawable.tourism,
            R.drawable.helpme,
            R.drawable.shopping,
            R.drawable.payment,
            R.drawable.aboutme,
            R.drawable.feeling,
            R.drawable.relationship,
            R.drawable.word,
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
                    case FIRST_MEET:
                        ShowFirstMeetScreen();
                        break;
                    case TOURISM:
                        ShowTourismScreen();
                        break;
                    case BUYING:
                        ShowBuyingScreen();
                        break;
                    case HELP_ME:
                        ShowHelpMeScreen();
                        break;
                    case PAYMENT:
                        ShowPaymentScreen();
                        break;
                    case INTRODUCEME:
                        ShowIntroduceMe();
                        break;
                    case FEELING:
                        ShowFeelingScreen();
                        break;
                    case LIFE_STATE:
                        ShowRelationShipScreen();
                        break;
                    case BASIC_WORD:
                        ShowMenuBasicWord();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void ShowFirstMeetScreen()
    {
        Intent firstMeetScreen = new Intent(getApplicationContext(), FirstMeetActivity.class);
        startActivity(firstMeetScreen);
    }

    public void ShowTourismScreen()
    {
        Intent TourScreen = new Intent(getApplicationContext(), TourismActitvity.class);
        startActivity(TourScreen);
    }
    public  void ShowBuyingScreen()
    {
        Intent buyingScreen = new Intent(getApplicationContext(), BuyingActivity.class);
        startActivity(buyingScreen);
    }
    public void ShowHelpMeScreen()
    {
        Intent helpScreen = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(helpScreen);
    }
    public void ShowPaymentScreen()
    {
        Intent paymentScreen = new Intent(getApplicationContext(), PaymentActivity.class);
        startActivity(paymentScreen);
    }
    public void ShowIntroduceMe()
    {
        Intent introduceScreen = new Intent(getApplicationContext(), IntroduceActivity.class);
        startActivity(introduceScreen);
    }

    public void ShowFeelingScreen()
    {
        Intent itemListScreen = new Intent(getApplicationContext(), IteamListActivity.class);
        int type = 0;
        itemListScreen.putExtra("Type", type);
        startActivity(itemListScreen);
    }

    public void ShowRelationShipScreen()
    {
        Intent itemListScreen = new Intent(getApplicationContext(), IteamListActivity.class);
        int type = 1;
        itemListScreen.putExtra("Type", type);
        startActivity(itemListScreen);
    }
    public void ShowMenuBasicWord()
    {
        Intent wordScreen = new Intent(getApplicationContext(), WordsActivity.class);
        startActivity(wordScreen);
    }
}
