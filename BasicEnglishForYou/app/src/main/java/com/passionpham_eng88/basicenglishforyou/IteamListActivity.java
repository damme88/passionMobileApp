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

public class IteamListActivity extends AppCompatActivity {

    Button btnBack;
    //Button btnRunAll;
    //Button btnStop;
    //Thread timer;
    ListView listView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    //ArrayList<Integer> audio_list = new ArrayList<Integer>();
    //MediaPlayer audio_player = new MediaPlayer();

    public final int FEELING = 0;
    public final int LIFE_STATE = 1;

    public void MessageMap()
    {
        //btnRunAll = (Button)findViewById(R.id.btnRunAll);
        //btnStop = (Button)findViewById(R.id.btnStopRunAll);
        btnBack = (Button)findViewById(R.id.btnBack);
        listView = (ListView)findViewById(R.id.listview);
    }

    public void Init(int type)
    {
        if (type == FEELING)
        {
            userArray.add(new User("I am angry! (Tôi thấy tức giận)"));
            userArray.add(new User("I am happy! (Tôi thấy hạnh phúc)"));
            userArray.add(new User("I am sad! (Tôi thấy buồn)"));
            userArray.add(new User("I am lonely! (Tôi thấy cô đơn)"));
            userArray.add(new User("I am bored! (Tôi thấy chán nản)"));
            userArray.add(new User("I am in love!(Tôi đang yêu)"));
            userArray.add(new User("I am jealous! (Tôi đang ghen)"));
            userArray.add(new User("I am lovesick! (Tôi bị thất tình)"));
            userArray.add(new User("I am confused! (Tôi thấy rối rắm)"));
            userArray.add(new User("I am pleased! (Tôi thấy khoái chí)"));
            userArray.add(new User("I am surprised! (Tôi thấy ngạc nhiên)"));
            userArray.add(new User("I am scared! (Tôi thấy sợ)"));
            userArray.add(new User("I am confident! (Tôi thấy tự tin)"));
            userArray.add(new User("I am hopeful! (Tôi thấy hi vọng)"));
            userArray.add(new User("I am shy! (Tôi thấy mắc cỡ)"));
            userArray.add(new User("I am disappointed! (Tôi thấy thất vọng)"));
            userArray.add(new User("I am satisfied! (Tôi thấy mãn nguyện)"));
            userArray.add(new User("I feel silly! (Tôi thấy thật ngỡ ngẩn)"));

/*            audio_list.add(R.raw.fm8);
            audio_list.add(R.raw.fm9);
            audio_list.add(R.raw.fm10);
            audio_list.add(R.raw.fm11);
            audio_list.add(R.raw.fm12);
            audio_list.add(R.raw.fm13);
            audio_list.add(R.raw.fm14);*/
        }
        else if (type == LIFE_STATE)
        {
            userArray.add(new User("Thank God: Cám ơn trời đất"));
            userArray.add(new User("How luck: May mắn thật"));
            userArray.add(new User("Oh, that’s great: ồ, thật tuyệt"));
            userArray.add(new User("Nothing could make me happier: Qúa vui"));
            userArray.add(new User("I have nothing more to desire: Tôi rất vừa ý"));
            userArray.add(new User("Well – done: Làm tốt lắm"));
            userArray.add(new User("Excellent: Tuyệt quá"));
            userArray.add(new User("We are proud of you: chúng tôi tự hào về bạn"));

            userArray.add(new User("The devil take you: Tên chết tiệt"));
            userArray.add(new User("What a bore: Đáng ghét"));
            userArray.add(new User("Dawn you: Đồ tồi"));

            userArray.add(new User("Scram: Cút"));
            userArray.add(new User("Shut up: Câm miệng"));
            userArray.add(new User("Alas: Chao ôi"));
            userArray.add(new User("Oh, my dear: Trời ơi"));

            userArray.add(new User("Ah, poor fellow: Ôi, thật tội nghiệp"));
            userArray.add(new User("what a sad thing it is: Đáng thương thật"));
            userArray.add(new User("What a pity: Đáng tiếc thật"));

/*            audio_list.add(R.raw.fm1);
            audio_list.add(R.raw.fm2);
            audio_list.add(R.raw.fm3);
            audio_list.add(R.raw.fm4);
            audio_list.add(R.raw.fm5);
            audio_list.add(R.raw.fm6);
            audio_list.add(R.raw.fm7);*/
        }
    }

    public void OpenURL(String str)
    {
        Uri uriUrl = Uri.parse(str);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public  void PlayAudio(int position)
    {
/*        if (position < audio_list.size())
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
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteam_list);

        Bundle extras = getIntent().getExtras();
        int type = -1;
        if (extras != null) {
            type  = extras.getInt("Type");
        }

        MessageMap();
        Init(type);
        userAdapter = new UserCustomListView(IteamListActivity.this, R.layout.row, userArray);
        //userAdapter.SetVisbleSpeakButton(false);
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        /*        if (timer != null) {
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
                if (audio_list.size() > 0) {
                    audio_list.clear();
                }*/
                if (userArray.size() > 0) {
                    userArray.clear();
                }
                Intent menuScreen = new Intent(IteamListActivity.this, MenuActivity.class);
                startActivity(menuScreen);
            }
        });
/*        btnRunAll.setOnClickListener(new View.OnClickListener() {
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
                if (timer != null) {
                    timer.interrupt();
                    audio_player.stop();
                }
            }
        });*/
    }
}
