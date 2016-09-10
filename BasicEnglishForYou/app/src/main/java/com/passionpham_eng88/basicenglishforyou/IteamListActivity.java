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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class IteamListActivity extends AppCompatActivity {

    Button btnBack;
    Button btnRunAll;
    Button btnStop;
    Thread timer;
    ListView listView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    ArrayList<Integer> audio_list = new ArrayList<Integer>();
    MediaPlayer audio_player = new MediaPlayer();

    public final int FIRST_MEET = 0;
    public final int TOURISM = 1;
    public final int HELP_ME = 2;
    public final int BUYING = 3;
    public final int PAYMENT = 4;
    public final int FEELING = 5;
    public final int LIFE_STATE = 6;

    public void MessageMap()
    {
        btnRunAll = (Button)findViewById(R.id.commonPlayAll);
        btnStop = (Button)findViewById(R.id.commonStopAll);
        btnBack = (Button)findViewById(R.id.commonBack);
        listView = (ListView)findViewById(R.id.commonListview);
    }

    public void Init(int type)
    {
        if (type == FIRST_MEET)
        {
            InitFistMeet();
        }
        if (type == TOURISM)
        {
            InitTourism();
        }
        if (type == HELP_ME)
        {
            InitHelp();
        }
        if (type == BUYING)
        {
            InitBuying();
        }
        if (type == PAYMENT)
        {
            InitPayment();
        }
        if (type == FEELING)
        {
            InitFeeling();
        }
        else if (type == LIFE_STATE)
        {
            InitLifeState();
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
        if (position < audio_list.size())
        {
            if (audio_player.isPlaying())
            {
                audio_player.stop();
            }
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteam_list);
        AdView mAdView = (AdView) findViewById(R.id.adViewItemList);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Bundle extras = getIntent().getExtras();
        int type = -1;
        if (extras != null) {
            type  = extras.getInt("Type");
        }

        MessageMap();
        Init(type);
        userAdapter = new UserCustomListView(IteamListActivity.this, R.layout.row, userArray);
        listView = (ListView) findViewById(R.id.commonListview);
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
                if (audio_list.size() > 0) {
                    audio_list.clear();
                }
                if (userArray.size() > 0) {
                    userArray.clear();
                }
                finishAffinity();
                Intent menuScreen = new Intent(getApplicationContext(), SituationActivity.class);
                startActivity(menuScreen);
            }
        });
       btnRunAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio_player.isPlaying())
                {
                    audio_player.stop();
                }
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
        });
    }

    public  void InitFistMeet()
    {
        getSupportActionBar().setTitle("NHÓM CÂU LẦN ĐẦU GẶP GỠ.");
        userArray.add(new User("Hello, Hi, Good morning.\r\n(Xin chào)"));
        userArray.add(new User("My name is David.\r\n(Tôi tên là David)"));
        userArray.add(new User("Are you American?\r\n(Bạn là người mỹ phải ko)"));
        userArray.add(new User("Where are you from?\r\n(Bạn từ đâu đến)"));
        userArray.add(new User("Do you speak English?\r\n(Bạn có biết nói tiếng anh ko)"));
        userArray.add(new User("How are you?\r\n(Bạn khỏe chứ)"));
        userArray.add(new User("What is your job?\r\n(Bạn làm nghề gì)"));
        userArray.add(new User("Do you like it here\r\n(Bạn thick nơi đây chứ)"));
        userArray.add(new User("I'd like you to meet Marry\r\n(Tối muốn bạn gặp gỡ Marry)"));
        userArray.add(new User("Nice to meet you?\r\n(Rất vui được gặp bạn)"));
        userArray.add(new User("Please sit down? \r\n(Mời ngồi)"));
        userArray.add(new User("Sorry, I did not hear clearly \r\n(Xin lỗi, tôi không nghe rõ)"));
        userArray.add(new User("Please, Speak more slowly \r\n(Vui lòng nói chậm hơn)"));
        userArray.add(new User("What does this mean?\r\n(Điều đó nghĩa là gì)"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.fm1);
        audio_list.add(R.raw.fm2);
        audio_list.add(R.raw.fm3);
        audio_list.add(R.raw.fm4);
        audio_list.add(R.raw.fm5);
        audio_list.add(R.raw.fm6);
        audio_list.add(R.raw.fm7);
        audio_list.add(R.raw.fm8);
        audio_list.add(R.raw.fm9);
        audio_list.add(R.raw.fm10);
        audio_list.add(R.raw.fm11);
        audio_list.add(R.raw.fm12);
        audio_list.add(R.raw.fm13);
        audio_list.add(R.raw.fm14);
    }
    public void InitTourism()
    {
        getSupportActionBar().setTitle("NHÓM CÂU KHI ĐI DU LỊCH");
        userArray.add(new User("Do you speak English?\n Bạn có biết tiếng anh ko"));
        userArray.add(new User("Where can I find a taxi/train?\n Tôi có thể tìm taxi(tàu hỏa) ở đâu"));
        userArray.add(new User("Can you take me to the airport please?\n Bạn có thể chi cho tôi sân bay?"));
        userArray.add(new User("Can you take me a photograph?\nBạn có thể chụp cho tôi một tấm ảnh"));
        userArray.add(new User("Where is the bank/restaurant?\nNgân hàng(khách sạn ở đâu)"));
        userArray.add(new User("Where can I get something to eat?\nTôi có thể tìm đồ ăn ở đâu"));
        userArray.add(new User("Where are you from\nBạn từ đâu tới"));

        userArray.add(new User("where’s the ticket office?\nPhòng bán vẻ ở đâu"));
        userArray.add(new User("where are the ticket machines?\nMáy bán vé ở đâu"));
        userArray.add(new User("what time’s the next bus to London?\nChuyến xe bus kế tiếp đến Luân đôn là mấy giờ"));
        userArray.add(new User("can I buy a ticket on the bus?\nTôi có thể mua vé trên xe buýt không"));
        userArray.add(new User("how much is a (single/return)to London?\nVé một chiều (Khứ hồi) đến Luân Đôn là bao nhiêu"));
        userArray.add(new User("I’d like a single to Bristol\n Tôi muốn mua 1 vé 1 chiều tới Bristol"));
        userArray.add(new User("which platform do I need for London?\n Tôi cần tới ga nào để đi Luân Đôn"));
        userArray.add(new User("Is this the right platform for London\n Đây có phải là ga để đi Luân Đôn"));
        userArray.add(new User("Can I have a timetable, please?\n Tôi có thể xin lịch tàu chạy"));
        userArray.add(new User("How often do the buses run to London\n Bao lâu thì có một chuyến xe buýt đi Luân Đôn"));
        userArray.add(new User("I’d like to renew my season ticket, please\n Tôi muốn gia hạn vé dài kỳ"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.t1);
        audio_list.add(R.raw.t2);
        audio_list.add(R.raw.t3);
        audio_list.add(R.raw.t4);
        audio_list.add(R.raw.t5);
        audio_list.add(R.raw.t6);
        audio_list.add(R.raw.t7);

        audio_list.add(R.raw.t8);
        audio_list.add(R.raw.t9);
        audio_list.add(R.raw.t10);
        audio_list.add(R.raw.t11);
        audio_list.add(R.raw.t12);
        audio_list.add(R.raw.t13);
        audio_list.add(R.raw.t14);

        audio_list.add(R.raw.t15);
        audio_list.add(R.raw.t16);
        audio_list.add(R.raw.t17);
        audio_list.add(R.raw.t18);
    }
    public void InitHelp()
    {
        getSupportActionBar().setTitle("NHÓM CÂU CẦN SỰ GIÚP ĐỠ");
        userArray.add(new User("Can you help me?\r\n(Giúp tôi)"));
        userArray.add(new User("I need a doctor?\r\n(Tôi cần bác sĩ)"));
        userArray.add(new User("Can you do me a favor? \r\n(Bạn có thể giúp tôi một việc ko)"));
        userArray.add(new User("Can you please say that again\r\n(Bạn có thể nói lại điều đó ko)"));
        userArray.add(new User("Does anyone here speak English ?\r\n(Ở đây có ai nói tiếng anh ko)"));
        userArray.add(new User("Please call the police\r\n(Hãy gọi công an)"));
        userArray.add(new User("Please speak more slowly\r\n(Hãy nói chậm hơn)"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.h1);
        audio_list.add(R.raw.h2);
        audio_list.add(R.raw.h3);
        audio_list.add(R.raw.h4);
        audio_list.add(R.raw.h5);
        audio_list.add(R.raw.h6);
        audio_list.add(R.raw.h7);
    }
    public void InitBuying()
    {
        getSupportActionBar().setTitle("NHÓM CÂU MUA BÁN");
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
    public void InitPayment()
    {
        getSupportActionBar().setTitle("NHÓM CÂU THANH TOÁN HÀNG HÓA");
        userArray.add(new User("Could we have the bill, please?\r\n(Mang hóa đơn cho chúng tôi dc ko?)"));
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
    public void InitFeeling()
    {
        getSupportActionBar().setTitle("NHÓM CÂU TÔI CẢM THẤY...");
        userArray.add(new User("I am angry!\n(Tôi thấy tức giận)"));
        userArray.add(new User("I am happy!\n(Tôi thấy hạnh phúc)"));
        userArray.add(new User("I am sad!\n(Tôi thấy buồn)"));
        userArray.add(new User("I am lonely!\n(Tôi thấy cô đơn)"));
        userArray.add(new User("I am bored!\n(Tôi thấy chán nản)"));
        userArray.add(new User("I am in love!\n(Tôi đang yêu)"));
        userArray.add(new User("I am jealous!\n(Tôi đang ghen)"));
        userArray.add(new User("I am confused!\n(Tôi thấy rối rắm)"));
        userArray.add(new User("I am pleased!\n(Tôi thấy khoái chí)"));
        userArray.add(new User("I am surprised!\n(Tôi thấy ngạc nhiên)"));
        userArray.add(new User("I am scared!\n(Tôi thấy sợ)"));
        userArray.add(new User("I am confident!\n(Tôi thấy tự tin)"));
        userArray.add(new User("I am hopeful!\n(Tôi thấy hi vọng)"));
        userArray.add(new User("I am shy!\n(Tôi thấy mắc cỡ)"));
        userArray.add(new User("I am disappointed!\n(Tôi thấy thất vọng)"));
        userArray.add(new User("I am satisfied!\n(Tôi thấy mãn nguyện)"));
        userArray.add(new User("I feel silly!\n(Tôi thấy thật ngỡ ngẩn)"));
        userArray.add(new User("I am lovesick!\n(Tôi bị thất tình)"));

        audio_list.add(R.raw.flangry);
        audio_list.add(R.raw.flhappy);
        audio_list.add(R.raw.flsad);
        audio_list.add(R.raw.fllonely);
        audio_list.add(R.raw.flbored);
        audio_list.add(R.raw.fllove);
        audio_list.add(R.raw.fljealous);
        audio_list.add(R.raw.flconfused);
        audio_list.add(R.raw.flpleased);
        audio_list.add(R.raw.flsurprised);
        audio_list.add(R.raw.flscared);
        audio_list.add(R.raw.flconfident);
        audio_list.add(R.raw.flhopeful);
        audio_list.add(R.raw.flshy);
        audio_list.add(R.raw.fldisappointed);
        audio_list.add(R.raw.flsatisfied);
        audio_list.add(R.raw.flsilly);
        audio_list.add(R.raw.fllovesick);
    }
    public void InitLifeState()
    {
        getSupportActionBar().setTitle("NHÓM CÂU CẢM XÚC ");
        userArray.add(new User("Thank God\nCám ơn trời đất"));
        userArray.add(new User("How luck\nMay mắn thật"));
        userArray.add(new User("Oh, that’s great\nỒ, thật tuyệt"));
        userArray.add(new User("Nothing could make me happier\nQúa vui"));
        userArray.add(new User("I have nothing more to desire\nTôi rất vừa ý"));
        userArray.add(new User("Well – done\nLàm tốt lắm"));
        userArray.add(new User("Excellent\nTuyệt quá"));
        userArray.add(new User("What a bore\nĐáng ghét"));
        userArray.add(new User("Oh, my dear\nTrời ơi"));

        userArray.add(new User("Ah, poor fellow\nÔi, thật tội nghiệp"));
        userArray.add(new User("What a pity\nĐáng tiếc thật"));

        audio_list.add(R.raw.lsthankgod);
        audio_list.add(R.raw.lshowluck);
        audio_list.add(R.raw.lsthatgreate);
        audio_list.add(R.raw.lsnothinghappiner);
        audio_list.add(R.raw.lsnothingdesire);
        audio_list.add(R.raw.lswelldone);
        audio_list.add(R.raw.lsexcellent);
        audio_list.add(R.raw.lswhatabore);
        audio_list.add(R.raw.lsmydear);
        audio_list.add(R.raw.lspoorfellow);
        audio_list.add(R.raw.lswhatapity);
    }
}

