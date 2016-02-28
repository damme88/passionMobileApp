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

public class PaymentActivity extends AppCompatActivity {

    Button btnBack;
    ListView listView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    ArrayList<Integer> audio_list;
    MediaPlayer audio_player = new MediaPlayer();
    Button btnRunAll;
    Button btnStop;
    Thread timer;
    public void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.payBack);
        listView = (ListView)findViewById(R.id.payListview);
        btnRunAll = (Button)findViewById(R.id.payPlayAll);
        btnStop = (Button)findViewById(R.id.payStopAll);
    }

    public void Init() {
        userArray.add(new User("Could we have the bill, please?\r\n(Mang hóa đơn cho chung tôi dc ko?)"));
        userArray.add(new User("Can I pay by card?\r\n(Tôi trả bằng thẻ dc ko?)"));
        userArray.add(new User("Is service included?\r\n(Đã có phí dịch vụ chưa ?)"));
        userArray.add(new User("I'll get this? \r\n(Tôi sẽ thanh toán)"));
        userArray.add(new User("Let's split it? \r\n(Chúng ta chia nhau nhé)"));
        userArray.add(new User("Would you be able to gift wrap it for me?\r\n(Gói quà hộ tôi nhé)"));


        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.pay1);
        audio_list.add(R.raw.pay2);
        audio_list.add(R.raw.pay3);
        audio_list.add(R.raw.pay4);
        audio_list.add(R.raw.pay5);
        audio_list.add(R.raw.pay6);
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
    public void OpenURL(String str)
    {
        Uri uriUrl = Uri.parse(str);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setTitle("Mẫu Câu Thanh Toán(Payment)");
        MessageMap();
        Init();
        userAdapter = new UserCustomListView(PaymentActivity.this, R.layout.row,
                userArray);
        listView = (ListView) findViewById(R.id.payListview);
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
                Intent menuScreen = new Intent(PaymentActivity.this, MenuActivity.class);
                startActivity(menuScreen);
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
