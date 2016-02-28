package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class BuyingActivity extends AppCompatActivity {

    Button btnBack;
    Button btnRunAll;
    Button btnStop;
    ListView listView;
    Thread timer;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    ArrayList<Integer> audio_list;
    MediaPlayer audio_player = new MediaPlayer();
    public void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.btnBack);
        listView = (ListView)findViewById(R.id.listview);
        btnRunAll = (Button)findViewById(R.id.btnRunAll);
        btnStop = (Button)findViewById(R.id.btnStopRunAll);
    }

    public void Init()
    {
        userArray.add(new User("What time are you open?\r\n (Mấy giờ mở cửa)"));
        userArray.add(new User("Do you have any coffee?\r\n (Còn cafe ko)"));
        userArray.add(new User("That's cheap\r\n (Rẻ đấy)"  ));
        userArray.add(new User("How much is this? \r\n (Cái này bao nhiêu)"));

        userArray.add(new User("Can I help you?\r\n (Tôi có thể giúp bạn)"));
        userArray.add(new User("I'm looking for...\r\n (Tôi đang tìm...)"));
        userArray.add(new User("Sorry, we don't have any left\r\n (Xin lỗi, hết hàng rồi)"));
        userArray.add(new User("sorry, we don't sell them.\r\n (Xin lỗi, chúng tôi ko bán)"));

        userArray.add(new User("Does it come with a guarantee?\r\n(Cái này có bảo hành ko)"));
        userArray.add(new User("do you have this item in stock?\r\n(Còn loại này ko)"));
        userArray.add(new User("Have you got anything cheaper?\r\n(có cái rẻ hơn ko)"));
        userArray.add(new User("Do you deliver ?\r\n(Có giao hàng ko)"));

        userArray.add(new User("I need a something\r\n(Tôi cần vai thứ)"));
        userArray.add(new User("May I try this on, please?\r\n(tôi có thể thử)"));
        userArray.add(new User("It doesn’t fit me\r\n(Nó không vừa với tôi)"));
        userArray.add(new User("I need a bigger size?\r\n(Tôi cần size lớn hơn)"));
        userArray.add(new User("Where is the cash desk?\r\n(Nơi thanh toán ở đâu)"));

        userArray.add(new User("Could I get a receipt, please?\r\n(Ghi cho tôi hóa đơn)"));
        userArray.add(new User("Could I get a (plastic) bag, please?\r\n(Cho tôi một cái túi)"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.buying1);
        audio_list.add(R.raw.buying2);
        audio_list.add(R.raw.buying3);
        audio_list.add(R.raw.buying4);
        audio_list.add(R.raw.buying5);
        audio_list.add(R.raw.buying6);
        audio_list.add(R.raw.buying7);
        audio_list.add(R.raw.buying8);
        audio_list.add(R.raw.buying9);
        audio_list.add(R.raw.buying10);
        audio_list.add(R.raw.buying11);
        audio_list.add(R.raw.buying12);
        audio_list.add(R.raw.buying13);
        audio_list.add(R.raw.buying14);
        audio_list.add(R.raw.buying15);
        audio_list.add(R.raw.buying16);
        audio_list.add(R.raw.buying17);
        audio_list.add(R.raw.buying18);
        audio_list.add(R.raw.buying19);
    }
    public void OpenURL(String str)
    {
        Uri uriUrl = Uri.parse(str);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    public  void PlayAudio(int position)
    {
        audio_player = MediaPlayer.create(getApplicationContext(), audio_list.get(position));
        audio_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.stop();
            }
        });
        if (audio_player.isPlaying() == false)
        {
            audio_player.start();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);
        getSupportActionBar().setTitle("Mua đồ (Buy)");
        MessageMap();
        Init();
        userAdapter = new UserCustomListView(BuyingActivity.this, R.layout.row,
                userArray);
        listView = (ListView) findViewById(R.id.listview);
        listView.setItemsCanFocus(false);
        listView.setAdapter(userAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {

                String content = userArray.get(position).GetStence();
                String country_code = getResources().getConfiguration().locale.getCountry();
                // Struct google translate
                // https://translate.google.com/#en/vi/love
                String trans = "https://translate.google.com/#en/" + country_code + "/" + content;
                //OpenURL(trans);
            }
        });

        btnRunAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer = new Thread() {
                    public void run() {
                        try {
                            for (int i = 0; i < audio_list.size(); i++) {
                                PlayAudio(i);
                                sleep(3000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                timer.start();
                timer.isInterrupted();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    if (timer.isInterrupted() == false) {
                        timer.interrupt();
                    } else {
                        audio_player.release();
                    }
                } else {
                    if (audio_player.isPlaying()) {
                        audio_player.stop();
                    }
                }
                Intent menuScreen = new Intent(BuyingActivity.this, MenuActivity.class);
                startActivity(menuScreen);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null)
                {
                    timer.interrupt();
                    audio_player.stop();
                }
            }
        });
    }
}
