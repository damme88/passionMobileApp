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

public class WordGroupActivity extends AppCompatActivity {
    Button btnBack;
    Button btnRunAll;
    Button btnStop;
    Thread timer;
    ListView listView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    ArrayList<Integer> audio_list = new ArrayList<Integer>();
    MediaPlayer audio_player = new MediaPlayer();

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

    public final int FOODS_MEET = 20;
    public final int FOODS_MILK = 21;
    public final int FOODS_FRUITS = 22;

    public final int FOODS_VEGETABLE = 23;
    public final int FOODS_DRINKING = 24;
    public final int FOODS_CAKE = 25;
    public final int COOK_TOOL = 26;
    public final int COOK_SPICES= 27;
    public final int COOK_TYPE = 28;

    public void MessageMap()
    {
        btnRunAll = (Button)findViewById(R.id.wgPlayAll);
        btnStop = (Button)findViewById(R.id.wgStopAll);
        btnBack = (Button)findViewById(R.id.wgBack);
        listView = (ListView)findViewById(R.id.wgListView);
    }

    public void Init(int type)
    {
        switch (type)
        {
            case HOME:
            {
                Home();
                break;
            }
            case HUMAN:
            {
                BodyHuman();
                break;
            }
            case JOB:
            {
                InitJob();
                break;
            }
            case FISH:
            {
                InitFish();
                break;
            }
            case BIRD:
            {
                InitBird();
                break;
            }
            case INSECT:
            {
                InitInsect();
                break;
            }
            case CATTLE:
            {
                InitCattle();
                break;
            }
            case WILD_ANIMAL:
            {
                InitWildAnimal();
                break;
            }
            case FLOWERS:
            {
                InitFlowers();
                break;
            }
            case MATERIAL:
            {
                InitMaterial();
                break;
            }
            case METAL:
            {
                InitMetal();
                break;
            }
            case GAS:
            {
                InitGas();
                break;
            }
            case SHAPE:
            {
                InitShape();
                break;
            }
            case PARAM:
            {
                InitParam();
                break;
            }
            case FOODS_MEET:
            {
                InitFoodMeet();
                break;
            }
            case FOODS_MILK:
            {
                InitFoodMilk();
                break;
            }
            case FOODS_FRUITS:
            {
                InitFoodFruits();
                break;
            }
            case FOODS_VEGETABLE:
            {
                InitVegetable();
                break;
            }
            case FOODS_DRINKING:
            {
                InitDrinking();
                break;
            }
            case FOODS_CAKE:
            {
                InitCake();
                break;
            }
            case COOK_TOOL:
            {
                InitCookTool();
                break;
            }
            case COOK_SPICES:
            {
                InitCookingSpices();
                break;
            }
            case COOK_TYPE:
            {
                InitCookingType();
                break;
            }
            default:
            {
                InitFish();
                break;
            }
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
            if (audio_player.isPlaying() == true)
            {
                audio_player.stop();
                audio_player.release();
            }
            audio_player = MediaPlayer.create(getApplicationContext(), audio_list.get(position));
            audio_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.stop();
                }
            });
            audio_player.start();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_group);
        AdView mAdView = (AdView) findViewById(R.id.adViewWg);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Bundle extras = getIntent().getExtras();
        int type = -1;
        if (extras != null) {
            type  = extras.getInt("Type");
        }
        MessageMap();
        Init(type);
        userAdapter = new UserCustomListView(WordGroupActivity.this, R.layout.row, userArray);
        //userAdapter.SetVisbleSpeakButton(false);
        listView = (ListView) findViewById(R.id.wgListView);
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

                Bundle extras = getIntent().getExtras();
                int type = -1;
                if (extras != null) {
                    type  = extras.getInt("Type");
                }

                finishAffinity();
                Intent wordGroup = new Intent(getApplicationContext(), WordsActivity.class);
                Intent foodGroup = new Intent(getApplicationContext(), AllFoodsActivity.class);
                if (type >= FOODS_MEET && type <= COOK_TYPE)
                {
                    startActivity(foodGroup);
                }
                else
                {
                    startActivity(wordGroup);
                }
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
                if (timer != null) {
                    timer.interrupt();
                    audio_player.stop();
                }
            }
        });
    }

    public void Home()
    {
        getSupportActionBar().setTitle("ĐỒ VẬT TRONG NHÀ");
        userArray.add(new User("Calendar\nLịch"));
        userArray.add(new User("Chair\nGhế"));
        userArray.add(new User("Clock\nĐồng hồ treo tường"));
        userArray.add(new User("Cup\nCái chén"));
        userArray.add(new User("Curtain\nRèm cửa"));
        userArray.add(new User("Door\nCưa ra vào"));
        userArray.add(new User("Fan\nQuật"));
        userArray.add(new User("Fence\nHàng rào"));
        userArray.add(new User("Flat\nCăn hộ"));
        userArray.add(new User("Floor\nSàn nhà"));
        userArray.add(new User("Garden\nVườn"));
        userArray.add(new User("House\nNhà"));
        userArray.add(new User("Lamp\nCái đèn"));
        userArray.add(new User("Pillow\nCái gối"));
        userArray.add(new User("Rung\nThảm nhà"));
        userArray.add(new User("Shelf\nKệ sách"));
        userArray.add(new User("Sink\nBồn rửa bát"));
        userArray.add(new User("Sofa\nGhê sofa"));
        userArray.add(new User("Stair\nCầu thang"));
        userArray.add(new User("Table\nCái bàn"));
        userArray.add(new User("Terrace\nSân thượng"));
        userArray.add(new User("Toilet\nNhà vệ sinh"));
        userArray.add(new User("Towel\nKhăn tắm"));
        userArray.add(new User("Wall\nBức tường"));
        userArray.add(new User("Window\nCửa sổ"));

        audio_list.add(R.raw.hmcalendar);
        audio_list.add(R.raw.hmchair);
        audio_list.add(R.raw.hmclock);
        audio_list.add(R.raw.hmcup);
        audio_list.add(R.raw.hmcurtain);
        audio_list.add(R.raw.hmdoor);
        audio_list.add(R.raw.hmfan);
        audio_list.add(R.raw.hmfence);
        audio_list.add(R.raw.hmflat);
        audio_list.add(R.raw.hmfloor);
        audio_list.add(R.raw.hmgarden);
        audio_list.add(R.raw.hmhouse);
        audio_list.add(R.raw.hmlamp);
        audio_list.add(R.raw.hmpillow);
        audio_list.add(R.raw.hmrung);
        audio_list.add(R.raw.hmshelf);
        audio_list.add(R.raw.hmsink);
        audio_list.add(R.raw.hmsofa);
        audio_list.add(R.raw.hmstair);
        audio_list.add(R.raw.hmtable);
        audio_list.add(R.raw.hmterrace);
        audio_list.add(R.raw.hmtoilet);
        audio_list.add(R.raw.hmtowel);
        audio_list.add(R.raw.hmwall);
        audio_list.add(R.raw.hmwindow);
    }
    public void BodyHuman()
    {
        getSupportActionBar().setTitle("CƠ THỂ NGƯỜI");
        userArray.add(new User("Beard\nrâu"));
        userArray.add(new User("Blood\nmáu"));
        userArray.add(new User("Body\ncơ thể"));
        userArray.add(new User("Bone\nxương"));
        userArray.add(new User("Bowel\nRuột"));
        userArray.add(new User("Breast\nNgực"));
        userArray.add(new User("Buttoks\nMông"));
        userArray.add(new User("Chin\nCằm"));
        userArray.add(new User("Ear\nTai"));
        userArray.add(new User("Eye\nMắt"));
        userArray.add(new User("Face\nMặt"));
        userArray.add(new User("Fingger\nNgón tay"));
        userArray.add(new User("Flesh\nDa - Thịt"));
        userArray.add(new User("Foot\nBàn Chân"));
        userArray.add(new User("Hair\nTóc"));
        userArray.add(new User("Hand\nBàn tay"));
        userArray.add(new User("Head\nCái đầu"));
        userArray.add(new User("Heart\nTrái tim"));
        userArray.add(new User("Jaw\nQuai hàm"));
        userArray.add(new User("Lip\nMôi"));
        userArray.add(new User("Moustache\nRâu mép"));
        userArray.add(new User("Mouth\nCái miệng"));
        userArray.add(new User("Nose\nMũi"));
        userArray.add(new User("Nostril\nlỗi mũi"));
        userArray.add(new User("Tear\nNước mắt"));
        userArray.add(new User("Thigh\nĐùi"));
        userArray.add(new User("Throat\nCổ họng"));
        userArray.add(new User("Tongue\nLưỡi"));
        userArray.add(new User("Tooth - Teeth\nRăng - Hàm răng"));

        audio_list.add(R.raw.bobeard);
        audio_list.add(R.raw.boblood);
        audio_list.add(R.raw.bobody);
        audio_list.add(R.raw.bobone);
        audio_list.add(R.raw.bobowel);
        audio_list.add(R.raw.bobreast);
        audio_list.add(R.raw.bobuttoks);
        audio_list.add(R.raw.bochin);
        audio_list.add(R.raw.boear);
        audio_list.add(R.raw.boeye);
        audio_list.add(R.raw.boface);
        audio_list.add(R.raw.bofingger);
        audio_list.add(R.raw.boflesh);
        audio_list.add(R.raw.bofoot);
        audio_list.add(R.raw.bohair);
        audio_list.add(R.raw.bohand);
        audio_list.add(R.raw.bohead);
        audio_list.add(R.raw.boheart);
        audio_list.add(R.raw.bojaw);
        audio_list.add(R.raw.bolip);
        audio_list.add(R.raw.bomoustache);
        audio_list.add(R.raw.bomous);
        audio_list.add(R.raw.bonose);
        audio_list.add(R.raw.bonostril);
        audio_list.add(R.raw.botear);
        audio_list.add(R.raw.bothigh);
        audio_list.add(R.raw.bothroat);
        audio_list.add(R.raw.botongue);
        audio_list.add(R.raw.botoothteeth);
    }

    public void InitJob()
    {
        getSupportActionBar().setTitle("NGHỀ NGHIỆP");
        userArray.add(new User("Actor\nNam diễn viên"));
        userArray.add(new User("Actress\nNữ diễn viên"));
        userArray.add(new User("Athlete\nVận động viên"));
        userArray.add(new User("Chef\nBếp trưởng"));
        userArray.add(new User("Fish man\nNgư dân"));
        userArray.add(new User("Librarian\nThủ thư"));
        userArray.add(new User("Musician\nNhạc sỹ"));
        userArray.add(new User("Pilot\nPhi công"));
        userArray.add(new User("Policeman\nCảnh sát"));
        userArray.add(new User("Post man\nNgười đưa thư"));
        userArray.add(new User("Queen\nnữ hoàng"));
        userArray.add(new User("Sales man\nNgười bán hàng"));
        userArray.add(new User("Secretary\nThư ký"));
        userArray.add(new User("Nurse\ny tá"));
        userArray.add(new User("Programmer\nLập trình viên"));

        audio_list.add(R.raw.joactor);
        audio_list.add(R.raw.joactress);
        audio_list.add(R.raw.joathlete);
        audio_list.add(R.raw.jochef);
        audio_list.add(R.raw.jofishman);
        audio_list.add(R.raw.jolibrarian);
        audio_list.add(R.raw.jomusician);
        audio_list.add(R.raw.jopilot);
        audio_list.add(R.raw.jopoliceman);
        audio_list.add(R.raw.jopostman);
        audio_list.add(R.raw.joqueen);
        audio_list.add(R.raw.josalesman);
        audio_list.add(R.raw.josecretary);
        audio_list.add(R.raw.jonurse);
        audio_list.add(R.raw.joprogrammer);
    }
    public void InitFish()
    {
        getSupportActionBar().setTitle("CÁC LOÀI CÁ");
        userArray.add(new User("Octopus\nBạch tuộc"));
        userArray.add(new User("Carp\nCá chép"));
        userArray.add(new User("Dolphin\nCá heo"));
        userArray.add(new User("Shark\nCá mập"));
        userArray.add(new User("Tuna\nCá ngừ"));
        userArray.add(new User("Mackerel\nCá thu"));
        userArray.add(new User("Catfish\nCá trê"));
        userArray.add(new User("Goldfish\nCá vàng"));
        userArray.add(new User("Whale\nCá voi"));
        userArray.add(new User("Eel\nCon lươn"));
        userArray.add(new User("Jellyfish\nCon sứa"));
        userArray.add(new User("Crab\nCua"));
        userArray.add(new User("Seal\nHải cẩu"));
        userArray.add(new User("Squid\nMực"));
        userArray.add(new User("Starfish\nSao biển"));
        userArray.add(new User("Mussel\nTrai"));
        userArray.add(new User("Shrimp\nTôm"));
        userArray.add(new User("Lobster\nTôm hùm"));


        audio_list.add(R.raw.foctopus);
        audio_list.add(R.raw.fcrap);
        audio_list.add(R.raw.fdolphin);
        audio_list.add(R.raw.fshark);
        audio_list.add(R.raw.ftuna);
        audio_list.add(R.raw.fmackerel);
        audio_list.add(R.raw.fcatfish);
        audio_list.add(R.raw.fgoldfish);
        audio_list.add(R.raw.fwhale);
        audio_list.add(R.raw.ffeel);
        audio_list.add(R.raw.fjellyfish);
        audio_list.add(R.raw.fcrab);
        audio_list.add(R.raw.fseal);
        audio_list.add(R.raw.fsquid);
        audio_list.add(R.raw.fstarfish);
        audio_list.add(R.raw.fmussel);
        audio_list.add(R.raw.fshrimp);
        audio_list.add(R.raw.flobster);
    }

    public void InitBird()
    {
        getSupportActionBar().setTitle("CÁC LOÀI CHIM");
        userArray.add(new User("Crow\nQuạ"));
        userArray.add(new User("Dove\nBồ câu"));
        userArray.add(new User("Duck\nVịt"));
        userArray.add(new User("Eagle\nĐại bàng"));
        userArray.add(new User("Finch\nChim sẻ"));
        userArray.add(new User("Hawk\nChim diều hâu"));
        userArray.add(new User("Kingfisher\nChim bói cá"));
        userArray.add(new User("Owl\nChim cú"));
        userArray.add(new User("Seagull\nChim hải âu"));
        userArray.add(new User("Swan\nThiên nga"));
        userArray.add(new User("Woodpecker\nChim gõ kiến"));
        userArray.add(new User("Chick\nGà con"));
        userArray.add(new User("Duckling\nVịt con"));
        userArray.add(new User("Parrot\nVẹt"));

        audio_list.add(R.raw.bcrow);
        audio_list.add(R.raw.bdove);
        audio_list.add(R.raw.bduck);
        audio_list.add(R.raw.beagle);
        audio_list.add(R.raw.bfinch);
        audio_list.add(R.raw.bhawk);
        audio_list.add(R.raw.bkingfisher);
        audio_list.add(R.raw.bowl);
        audio_list.add(R.raw.bseagull);
        audio_list.add(R.raw.bswan);
        audio_list.add(R.raw.bwoodpecke);
        audio_list.add(R.raw.bchick);
        audio_list.add(R.raw.bduckling);
        audio_list.add(R.raw.bparrot);
    }
    public void InitInsect()
    {
        getSupportActionBar().setTitle("CÁC LOÀI CÔN TRÙNG");
        userArray.add(new User("Ant\nKiến"));
        userArray.add(new User("Bee\nOng"));
        userArray.add(new User("Butterfly\nBướm"));
        userArray.add(new User("Centipede\nCon rết"));
        userArray.add(new User("Cockroach\nGián"));
        userArray.add(new User("Cricket\nDế"));
        userArray.add(new User("Fly\nRuồi"));
        userArray.add(new User("Mosquito\nMuỗi"));
        userArray.add(new User("Scorpion\nBọ cạp"));
        userArray.add(new User("Snail\nỐc"));
        userArray.add(new User("Spider\nNhện"));
        userArray.add(new User("Termite\nCon mối"));
        userArray.add(new User("Worm\nSâu"));
        userArray.add(new User("Grasshopper\nChâu chấu"));

        audio_list.add(R.raw.itant);
        audio_list.add(R.raw.itbee);
        audio_list.add(R.raw.itbutterfly);
        audio_list.add(R.raw.itcentipede);
        audio_list.add(R.raw.itcockroach);
        audio_list.add(R.raw.itcricket);
        audio_list.add(R.raw.itfly);
        audio_list.add(R.raw.itmosquito);
        audio_list.add(R.raw.itscorpion);
        audio_list.add(R.raw.itsnail);
        audio_list.add(R.raw.itspider);
        audio_list.add(R.raw.ittermite);
        audio_list.add(R.raw.itworm);
        audio_list.add(R.raw.itgrasshopper);
    }
    public void InitCattle()
    {
        getSupportActionBar().setTitle("NHÓM VẬT NUÔI");
        userArray.add(new User("Cattle\nGia súc"));
        userArray.add(new User("Bull\nBò đực"));
        userArray.add(new User("Bullock\nBò đực con"));
        userArray.add(new User("Cow\nBò"));
        userArray.add(new User("Calf\nBê"));
        userArray.add(new User("Cock\nGà trống"));
        userArray.add(new User("Hen\nGà mái"));
        userArray.add(new User("Donkey\nCon lừa"));
        userArray.add(new User("Goat\nDê"));
        userArray.add(new User("Goose\nNgỗng"));
        userArray.add(new User("Turkey\nGà tây"));
        userArray.add(new User("Pig\nLợn"));

        audio_list.add(R.raw.ctcattle);
        audio_list.add(R.raw.ctbull);
        audio_list.add(R.raw.ctbullock);
        audio_list.add(R.raw.ctcow);
        audio_list.add(R.raw.ctcalf);
        audio_list.add(R.raw.ctcock);
        audio_list.add(R.raw.cthen);
        audio_list.add(R.raw.ctdonkey);
        audio_list.add(R.raw.ctgoat);
        audio_list.add(R.raw.ctgoose);
        audio_list.add(R.raw.ctturkey);
        audio_list.add(R.raw.ctpig);
    }
    public void InitWildAnimal()
    {
        getSupportActionBar().setTitle("CÁC LOÀI HOANG DÃ");
        userArray.add(new User("Bat\nRơi"));
        userArray.add(new User("Deer\nHươu"));
        userArray.add(new User("Frog\nẾch"));
        userArray.add(new User("Fox\nCáo"));
        userArray.add(new User("Hare\nThỏ rừng"));
        userArray.add(new User("Hedgehog\nNhím"));
        userArray.add(new User("Lizard\nThằn lằn"));
        userArray.add(new User("Reindeer\nTuần lộc"));
        userArray.add(new User("Snake\nRắn"));
        userArray.add(new User("Squirrel\nSóc"));
        userArray.add(new User("Toad\nCóc"));
        userArray.add(new User("Antelope\nLinh dương"));
        userArray.add(new User("Bear\nGấu"));
        userArray.add(new User("Camel\nLạc đà"));
        userArray.add(new User("Crocodile\nCá sấu"));
        userArray.add(new User("Elephant\nVoi"));
        userArray.add(new User("Giraffe\nHươu cao cổ"));
        userArray.add(new User("Gorilla\nKhỉ đột"));
        userArray.add(new User("Kangaroo\nChuột túi"));
        userArray.add(new User("Leopard\nBáo"));
        userArray.add(new User("Lion\nSư tử"));
        userArray.add(new User("Monkey\nKhỉ"));
        userArray.add(new User("Ostrich\nĐà điểu"));
        userArray.add(new User("Panda\nGấu trúc"));
        userArray.add(new User("Penguin\nChím cánh cụt"));
        userArray.add(new User("Polar bear\nGấu bắc cực"));
        userArray.add(new User("Tiger\nHổ"));
        userArray.add(new User("Zebra\nNgựa vằn"));
        userArray.add(new User("Wolf\nSói"));

        audio_list.add(R.raw.anibat);
        audio_list.add(R.raw.anideer);
        audio_list.add(R.raw.anifrog);
        audio_list.add(R.raw.anifox);
        audio_list.add(R.raw.anihare);
        audio_list.add(R.raw.anihedgehog);
        audio_list.add(R.raw.anilizard);
        audio_list.add(R.raw.anireindeer);
        audio_list.add(R.raw.anisnake);
        audio_list.add(R.raw.anisquirrel);
        audio_list.add(R.raw.anitoad);
        audio_list.add(R.raw.aniantelope);
        audio_list.add(R.raw.anibear);
        audio_list.add(R.raw.anicamel);
        audio_list.add(R.raw.anicrocodile);
        audio_list.add(R.raw.anielephant);
        audio_list.add(R.raw.anigiraffe);
        audio_list.add(R.raw.anigorilla);
        audio_list.add(R.raw.anikangaroo);
        audio_list.add(R.raw.anileopard);
        audio_list.add(R.raw.anilion);
        audio_list.add(R.raw.animonkey);
        audio_list.add(R.raw.aniostrich);
        audio_list.add(R.raw.anipanda);
        audio_list.add(R.raw.anipenguin);
        audio_list.add(R.raw.anipolarbear);
        audio_list.add(R.raw.anitiger);
        audio_list.add(R.raw.anizebra);
        audio_list.add(R.raw.aniwolf);
    }
    public void InitFlowers()
    {
        getSupportActionBar().setTitle("CÁC LOÀI HOA");
        userArray.add(new User("Rose\nHoa hồng"));
        userArray.add(new User("Orchid\nHoa lan"));
        userArray.add(new User("Lily\nHoa loa kèn"));
        userArray.add(new User("Daisy\nHoa cúc"));
        userArray.add(new User("Dandelion\nHoa bồ công anh"));
        userArray.add(new User("Carnation\nHoa cẩm chướng"));
        userArray.add(new User("Poppy\nHoa anh túc"));
        userArray.add(new User("Sunflower\nHoa hướng dương"));
        userArray.add(new User("Tulip\nHoa tulip"));
        userArray.add(new User("Waterlily\nHoa súng"));
        userArray.add(new User("Forget-me-not\nHoa lưu ly"));

        audio_list.add(R.raw.flrose);
        audio_list.add(R.raw.florchid);
        audio_list.add(R.raw.fllily);
        audio_list.add(R.raw.fldaisy);
        audio_list.add(R.raw.fldandelion);
        audio_list.add(R.raw.flcarnation);
        audio_list.add(R.raw.flpoppy);
        audio_list.add(R.raw.flsunflower);
        audio_list.add(R.raw.fltulip);
        audio_list.add(R.raw.flwaterlily);
        audio_list.add(R.raw.flforgetmenot);
    }
    public void InitMaterial()
    {
        getSupportActionBar().setTitle("CÁC LOẠI VẬT LIỆU");
        userArray.add(new User("Brick\nGạch"));
        userArray.add(new User("Cement\nXi măng"));
        userArray.add(new User("Concrete\nBê tông"));
        userArray.add(new User("Glass\nThủy tinh"));
        userArray.add(new User("Gravel\nSỏi"));
        userArray.add(new User("Marble\nĐá hoa"));
        userArray.add(new User("Metal\nKim loại"));
        userArray.add(new User("Plastic\nNhựa"));
        userArray.add(new User("Sand\nCát"));
        userArray.add(new User("Slate\nNgói"));
        userArray.add(new User("Stone\nĐá cục"));
        userArray.add(new User("Wood\nGỗ"));
        userArray.add(new User("Cloth\nVải"));
        userArray.add(new User("Cotton\nCotton"));
        userArray.add(new User("Lace\nRen"));
        userArray.add(new User("Leather\nDa"));
        userArray.add(new User("Nylon\nNi-lông"));
        userArray.add(new User("Silk\nLụa"));
        userArray.add(new User("Wool\nLen"));

        audio_list.add(R.raw.matbrick);
        audio_list.add(R.raw.matcement);
        audio_list.add(R.raw.matconcrete);
        audio_list.add(R.raw.matglass);

        audio_list.add(R.raw.matgravel);
        audio_list.add(R.raw.matmarble);
        audio_list.add(R.raw.matmetal);
        audio_list.add(R.raw.matplastic);
        audio_list.add(R.raw.matsand);
        audio_list.add(R.raw.matslate);

        audio_list.add(R.raw.matstone);
        audio_list.add(R.raw.matwood);
        audio_list.add(R.raw.matcloth);
        audio_list.add(R.raw.matcotton);
        audio_list.add(R.raw.matlace);
        audio_list.add(R.raw.matleather);
        audio_list.add(R.raw.matnylon);
        audio_list.add(R.raw.matsilk);
        audio_list.add(R.raw.matwool);
    }
    public void InitMetal()
    {
        getSupportActionBar().setTitle("CÁC DẠNG KIM LOẠI");
        userArray.add(new User("Aluminium\n Nhôm"));
        userArray.add(new User("Brass\nĐồng thau"));
        userArray.add(new User("Gold\nVàng"));
        userArray.add(new User("Iron\nSắt"));
        userArray.add(new User("Lead\nChì"));
        userArray.add(new User("Mercury\nThủy ngân"));
        userArray.add(new User("Magnesium\nMa-giê"));
        userArray.add(new User("Nickel\nKền"));
        userArray.add(new User("Platinum\nBạch kim"));
        userArray.add(new User("Silver\nBạc"));
        userArray.add(new User("Steel\nThép"));
        userArray.add(new User("Tin\nThiếc"));
        userArray.add(new User("Zinc\nKẽm"));

        audio_list.add(R.raw.memealuminium);
        audio_list.add(R.raw.mebrass);
        audio_list.add(R.raw.megold);
        audio_list.add(R.raw.meiron);
        audio_list.add(R.raw.melead);
        audio_list.add(R.raw.memercury);
        audio_list.add(R.raw.memagnesium);
        audio_list.add(R.raw.menickel);
        audio_list.add(R.raw.meplatinum);
        audio_list.add(R.raw.mesilver);
        audio_list.add(R.raw.mesteel);
        audio_list.add(R.raw.metin);
        audio_list.add(R.raw.mezinc);
    }
    public void InitGas()
    {
        getSupportActionBar().setTitle("CÁC KHÍ VÀ DẠNG VẬT CHẤT");
        userArray.add(new User("Charcoal\nThan củi"));
        userArray.add(new User("Coal\nThan đá"));
        userArray.add(new User("Gas\nGa"));
        userArray.add(new User("Oil\nDầu"));
        userArray.add(new User("Petrol\nXăng"));
        userArray.add(new User("Clay\nĐất sét"));
        userArray.add(new User("Dust\nBụi"));
        userArray.add(new User("Mud\nBùn"));
        userArray.add(new User("Paper\nGiấy"));
        userArray.add(new User("Rubber\nCao su"));
        userArray.add(new User("Smoke\nKhói"));
        userArray.add(new User("Soil\nĐất"));
        userArray.add(new User("Ice\nĐá băng"));
        userArray.add(new User("Steam\nHơi nước"));
        userArray.add(new User("Water\nNước"));

        audio_list.add(R.raw.gacharcoal);
        audio_list.add(R.raw.gacoal);
        audio_list.add(R.raw.gagas);
        audio_list.add(R.raw.gaoil);
        audio_list.add(R.raw.gapetrol);
        audio_list.add(R.raw.gaclay);
        audio_list.add(R.raw.gadust);
        audio_list.add(R.raw.gamud);
        audio_list.add(R.raw.gapaper);
        audio_list.add(R.raw.garubber);
        audio_list.add(R.raw.gasmoke);
        audio_list.add(R.raw.gasoil);
        audio_list.add(R.raw.gaice);
        audio_list.add(R.raw.gasteam);
        audio_list.add(R.raw.gawater);
    }
    public void InitShape()
    {
        getSupportActionBar().setTitle("CÁC DẠNG HÌNH HỌC");
        userArray.add(new User("Circle      : Hình tròn"));
        userArray.add(new User("Triangle    : Hình tam giác"));
        userArray.add(new User("Square      : Hình vuông"));
        userArray.add(new User("Rectangle   : Hình chữ nhật"));
        userArray.add(new User("Pentagon    : Hình ngũ giác"));
        userArray.add(new User("Hexagon     : Hình lục giác"));
        userArray.add(new User("Octagon     : Hình bát giác"));
        userArray.add(new User("Oval        : Hình bầu dục"));
        userArray.add(new User("Star        : Hình sao"));
        userArray.add(new User("Polygon     : Hình đa giác"));
        userArray.add(new User("Cone        : Hình nón"));
        userArray.add(new User("Cube        : Hình lập phương"));
        userArray.add(new User("Cylinder    : Hình trụ"));
        userArray.add(new User("Pyramid     : Hình chóp"));
        userArray.add(new User("Sphere      : Hình cầu"));

        audio_list.add(R.raw.shcircle);
        audio_list.add(R.raw.shtriangle);
        audio_list.add(R.raw.shsquare);
        audio_list.add(R.raw.shrectangle);
        audio_list.add(R.raw.shpentagon);
        audio_list.add(R.raw.shhexagon);
        audio_list.add(R.raw.shoctagon);
        audio_list.add(R.raw.shoval);
        audio_list.add(R.raw.shstar);
        audio_list.add(R.raw.shpolygon);
        audio_list.add(R.raw.shcone);
        audio_list.add(R.raw.shcube);
        audio_list.add(R.raw.shcylinder);
        audio_list.add(R.raw.shpyramid);
        audio_list.add(R.raw.shsphere);
    }
    public void InitParam()
    {
        getSupportActionBar().setTitle("CÁC ĐẠI LƯỢNG VẬT LÝ");
        userArray.add(new User("Area\nDiện tích"));
        userArray.add(new User("Circumference\nChu vi đường tròn"));
        userArray.add(new User("Diameter\nĐường kính"));
        userArray.add(new User("Radius\nBán kính"));
        userArray.add(new User("Length\nChiều dài"));
        userArray.add(new User("Height\nChiều cao"));
        userArray.add(new User("Width\nChiều rộng"));
        userArray.add(new User("Perimeter\nChu vi"));
        userArray.add(new User("Angle\nGóc"));
        userArray.add(new User("Right angle\nGóc vuông"));
        userArray.add(new User("Line\nĐường"));
        userArray.add(new User("Straight line\nĐường thẳng"));
        userArray.add(new User("Curve\nĐường cong"));
        userArray.add(new User("Volume\nThể tích"));

        audio_list.add(R.raw.prarea);
        audio_list.add(R.raw.prcircumference);
        audio_list.add(R.raw.prdiameter);
        audio_list.add(R.raw.prradius);
        audio_list.add(R.raw.prlength);
        audio_list.add(R.raw.prheight);
        audio_list.add(R.raw.prwidth);
        audio_list.add(R.raw.prperimeter);
        audio_list.add(R.raw.prangle);
        audio_list.add(R.raw.prrightangle);
        audio_list.add(R.raw.prline);
        audio_list.add(R.raw.prstraightline);
        audio_list.add(R.raw.prcurve);
        audio_list.add(R.raw.prvolume);
    }
    public void InitFoodMeet()
    {
        getSupportActionBar().setTitle("CÁC LOẠI THỊT");
        userArray.add(new User("Liver\nGan"));
        userArray.add(new User("Ham\nGiăm Bông"));
        userArray.add(new User("Pate\nPa tê"));
        userArray.add(new User("Veal\nThịt Bê"));
        userArray.add(new User("Beef\nThị Bò"));
        userArray.add(new User("Lamb\nThịt Cừu"));
        userArray.add(new User("Chicken\nThị Gà"));
        userArray.add(new User("Turkey\nThịt Gà tây"));
        userArray.add(new User("Pork\nThịt Lợn"));
        userArray.add(new User("Bacon\nThịt Muối"));
        userArray.add(new User("Cooked meat\nThị nấu chín"));
        userArray.add(new User("Duck\nThịt Vịt"));
        userArray.add(new User("Sausages\nXúc xíc"));

        audio_list.add(R.raw.foliver);
        audio_list.add(R.raw.foham);
        audio_list.add(R.raw.fopate);
        audio_list.add(R.raw.foveal);
        audio_list.add(R.raw.fobeef);
        audio_list.add(R.raw.folamb);
        audio_list.add(R.raw.fochicken);
        audio_list.add(R.raw.foturkey);
        audio_list.add(R.raw.fopork);
        audio_list.add(R.raw.fobacon);
        audio_list.add(R.raw.focookedmeat);
        audio_list.add(R.raw.foduck);
        audio_list.add(R.raw.fosausages);

    }
    public void InitFoodMilk()
    {
        getSupportActionBar().setTitle("CÁC LOẠI SỮA - ĐỒ UỐNG");
        userArray.add(new User("Butter\nBơ"));
        userArray.add(new User("Cream\nKem"));
        userArray.add(new User("Sour cream\nKem chua"));
        userArray.add(new User("Cheese\nPhô mai"));
        userArray.add(new User("Egg\nTrứng"));
        userArray.add(new User("Milk\nSữa"));
        userArray.add(new User("Yoghurt\nSữa chua"));

        audio_list.add(R.raw.dributter);
        audio_list.add(R.raw.dricream);
        audio_list.add(R.raw.drisourcream);
        audio_list.add(R.raw.dricheese);
        audio_list.add(R.raw.driegg);
        audio_list.add(R.raw.drimilk);
        audio_list.add(R.raw.driyoghurt);
    }
    public void InitFoodFruits()
    {
        getSupportActionBar().setTitle("CÁC LOẠI TRÁI CÂY");
        userArray.add(new User("Cherry\nAnh đào"));
        userArray.add(new User("Grapefruit\nBưởi"));
        userArray.add(new User("Orange\nCam"));
        userArray.add(new User("Banana\nChuối"));
        userArray.add(new User("Melon\nDưa"));
        userArray.add(new User("Coconut\nDừa"));
        userArray.add(new User("Pineapple\nDứa"));
        userArray.add(new User("Peach\nĐào"));
        userArray.add(new User("Pear\nLê"));
        userArray.add(new User("Plum\nMận"));
        userArray.add(new User("Apricot\nMơ"));
        userArray.add(new User("Grape\nNho"));
        userArray.add(new User("Fig\nSung"));
        userArray.add(new User("Apple\nTáo"));
        userArray.add(new User("Mango\nXoài"));
        userArray.add(new User("Kiwi fruit\nQuả Kiwi"));
        userArray.add(new User("Lemon\nQuả Chanh tây"));
        userArray.add(new User("Lime\nQuả Chanh ta"));
        userArray.add(new User("Pomegranate\nQuả Lựu"));
        userArray.add(new User("Raspberry\nQuả mâm xôi đỏ"));
        userArray.add(new User("Blackberry\nQuả mâm xôi đen"));
        userArray.add(new User("Strawberry\nQuả Dâu"));

        audio_list.add(R.raw.frtcherry);
        audio_list.add(R.raw.frtgrapefruit);
        audio_list.add(R.raw.frtorange);
        audio_list.add(R.raw.frtbanana);
        audio_list.add(R.raw.frtmelon);
        audio_list.add(R.raw.frtcoconut);
        audio_list.add(R.raw.frtpineapple);
        audio_list.add(R.raw.frtpeach);
        audio_list.add(R.raw.frtpear);
        audio_list.add(R.raw.frtplum);
        audio_list.add(R.raw.frtapricot);
        audio_list.add(R.raw.frtgrape);
        audio_list.add(R.raw.frtfig);
        audio_list.add(R.raw.frtapple);
        audio_list.add(R.raw.frtmango);
        audio_list.add(R.raw.frtkiwifruit);
        audio_list.add(R.raw.frtlemon);
        audio_list.add(R.raw.frtlime);
        audio_list.add(R.raw.frtpomegranate);
        audio_list.add(R.raw.frtraspberry);
        audio_list.add(R.raw.frtblackberry);
        audio_list.add(R.raw.frtstrawberry);
    }

    public void InitVegetable()
    {
        getSupportActionBar().setTitle("CÁC LOẠI RAU - CỦ- HẠT");
        userArray.add(new User("Cabbage\nBắp cải"));
        userArray.add(new User("Cacao\nCây ca cao"));
        userArray.add(new User("Cauliflower\nHoa cải, hoa lơ"));
        userArray.add(new User("Celery\nCần tây"));
        userArray.add(new User("Chayote\nSu su"));
        userArray.add(new User("Chestnut\nHạt dẻ"));
        userArray.add(new User("Coleslaw\nXà lách cải bắp"));
        userArray.add(new User("Cucumber\nDưa leo"));
        userArray.add(new User("Eggplant\nCà tím"));
        userArray.add(new User("Galangal\nCủ Giềng"));
        userArray.add(new User("Garlic\nTỏi"));
        userArray.add(new User("Kale\nCải ngọt"));
        userArray.add(new User("Knotgrass\nRau răm"));
        userArray.add(new User("Kohlrabi\nSu hào"));
        userArray.add(new User("Lettuce\nRau diếp"));
        userArray.add(new User("Ginger, Ginger root\nGừng"));
        userArray.add(new User("Gourd\nQuả bầu"));
        userArray.add(new User("Tomato\nCà chua"));
        userArray.add(new User("Dill\nCây thì là"));
        userArray.add(new User("Water morning glory\nRau Muống"));
        userArray.add(new User("Wild Betel leave\nLá Lốt"));
        userArray.add(new User("White Radish\nCủ Cải Trắng"));
        userArray.add(new User("Yam\nKhoai lang mỹ"));

        audio_list.add(R.raw.vecabbage);
        audio_list.add(R.raw.vecacao);
        audio_list.add(R.raw.vecauliflower);
        audio_list.add(R.raw.vecelery);
        audio_list.add(R.raw.vechayote);
        audio_list.add(R.raw.vechestnut);
        audio_list.add(R.raw.vecoleslaw);
        audio_list.add(R.raw.vecucumber);
        audio_list.add(R.raw.veeggplant);
        audio_list.add(R.raw.vegalangal);
        audio_list.add(R.raw.vegarlic);
        audio_list.add(R.raw.vekale);
        audio_list.add(R.raw.veknotgrass);
        audio_list.add(R.raw.vekohlrabi);
        audio_list.add(R.raw.velettuce);
        audio_list.add(R.raw.veginger);
        audio_list.add(R.raw.vegourd);
        audio_list.add(R.raw.vetomato);
        audio_list.add(R.raw.vedill);
        audio_list.add(R.raw.vewatermorningglory);
        audio_list.add(R.raw.vewildbetelleave);
        audio_list.add(R.raw.vewhiteradish);
        audio_list.add(R.raw.veyam);
    }

    public void InitDrinking()
    {
        getSupportActionBar().setTitle("CÁC LOẠI ĐỒ UỐNG");
        userArray.add(new User("Mineral water\nnước khoáng"));
        userArray.add(new User("Fruit juice\nnước trái cây\n"));
        userArray.add(new User("Orange juice\nnước cam"));
        userArray.add(new User("Pineapple juice\nnước dứa"));
        userArray.add(new User("Tomato juice\nnước cà chua"));
        userArray.add(new User("Smoothies\nsinh tố"));
        userArray.add(new User("Lemonade\nnước chanh"));
        userArray.add(new User("Squash\nnước ép"));
        userArray.add(new User("Iced tea\ntrà đá"));
        userArray.add(new User("Tea\nTrà"));
        userArray.add(new User("Coffee\ncà phê"));
        userArray.add(new User("Cocoa\nca cao"));
        userArray.add(new User("Beer\nBia"));
        userArray.add(new User("Wine\nRượu"));
        userArray.add(new User("Bitter\nRượu Đắng"));
        userArray.add(new User("Champagne\nSâm banh"));
        userArray.add(new User("Alcohol\nRượu bia nói chung"));
        userArray.add(new User("Spirits\nRượu mạnh"));

        audio_list.add(R.raw.tomineralwater);
        audio_list.add(R.raw.tofruitjuice);
        audio_list.add(R.raw.toorangejuice);
        audio_list.add(R.raw.topineapplejuice);
        audio_list.add(R.raw.totomatojuice);
        audio_list.add(R.raw.tosmoothies);
        audio_list.add(R.raw.tolemonade);
        audio_list.add(R.raw.tosquash);
        audio_list.add(R.raw.toicedtea);
        audio_list.add(R.raw.tootea);
        audio_list.add(R.raw.tocoffee);
        audio_list.add(R.raw.tococoa);
        audio_list.add(R.raw.tobeer);
        audio_list.add(R.raw.towine);
        audio_list.add(R.raw.tobitter);
        audio_list.add(R.raw.tochampagne);
        audio_list.add(R.raw.toalcohol);
        audio_list.add(R.raw.tospirits);
    }

    public void InitCake()
    {
        getSupportActionBar().setTitle("CÁC LOẠI BÁNH-BỘT");
        userArray.add(new User("Bread\nBánh mì"));
        userArray.add(new User("Quick bread\nBánh mì nhanh"));
        userArray.add(new User("Stuffed pancake\nBánh cuốn"));
        userArray.add(new User("Round sticky rice cake\nBánh dầy"));
        userArray.add(new User("Girdle-cake\nBánh tráng"));
        userArray.add(new User("Shrimp in batter\nBánh tôm"));
        userArray.add(new User("Young rice cake\nBánh cốm "));
        userArray.add(new User("Stuffed sticky rice balls\nBánh trôi"));
        userArray.add(new User("Soya cake\nBánh đậu"));
        userArray.add(new User("Steamed wheat flour cake\nBánh bao"));
        userArray.add(new User("Pancako\nBánh xèo"));
        userArray.add(new User("Stuffed sticky rice cake\nBánh chưng"));

        audio_list.add(R.raw.ckbread);
        audio_list.add(R.raw.ckquickbread);
        audio_list.add(R.raw.ckstuffedpancake);
        audio_list.add(R.raw.ckroundstickyricecake);
        audio_list.add(R.raw.ckgirdlecake);
        audio_list.add(R.raw.ckshrimpinbatter);
        audio_list.add(R.raw.ckyoungricecake);
        audio_list.add(R.raw.ckstuffedstickyriceballs);
        audio_list.add(R.raw.cksoyacake);
        audio_list.add(R.raw.cksteamedwheatflourcake);
        audio_list.add(R.raw.ckpancako);
        audio_list.add(R.raw.ckstuffedstickyricecake);
    }

    public void InitCookTool()
    {
        getSupportActionBar().setTitle("CÁC LOẠI DỤNG CỤ LÀM BẾP");
        userArray.add(new User("Apron\ntạp dề"));
        userArray.add(new User("Baking pan/ baking sheet\nkhay nướng bánh"));
        userArray.add(new User("Barbecue grill\nvỉ nướng"));
        userArray.add(new User("Blender\nmáy xay sinh tố"));
        userArray.add(new User("Charcoal grill\nlò nướng bằng than"));
        userArray.add(new User("Cheese cloth\nvải lọc"));
        userArray.add(new User("Cookbook\nsách hướng dẫn nấu ăn"));
        userArray.add(new User("Cutlery\nbộ dao kéo"));
        userArray.add(new User("Cutting board\ncái thớt"));
        userArray.add(new User("Egg beater\nmáy đánh trứng"));
        userArray.add(new User("Fondue pot\nnồi lẩu"));
        userArray.add(new User("Fork\nnĩa"));
        userArray.add(new User("Frying pan\nchảo rán"));
        userArray.add(new User("Grater\nđồ bào, mài"));
        userArray.add(new User("Griddle\nvỉ nướng"));
        userArray.add(new User("Grill pan\nchảo nướng"));
        userArray.add(new User("Grinder\ncối xay"));
        userArray.add(new User("Ice bucket\nxô đựng đá"));
        userArray.add(new User("Juicer\nmáy ép trái cây"));
        userArray.add(new User("Kettle\nấm nước"));
        userArray.add(new User("Knife\ndao"));
        userArray.add(new User("Ladle\nmuôi, vá"));
        userArray.add(new User("Lid\nnắp"));
        userArray.add(new User("Microwave oven\nlò vi sóng"));
        userArray.add(new User("Platter\nđĩa"));
        userArray.add(new User("Pot holder\ngăng tay lót nồi"));
        userArray.add(new User("Pressure cooker\nnồi áp suất"));
        userArray.add(new User("Refrigerator\ntủ lạnh"));
        userArray.add(new User("Rice cooker\nnồi cơm điện"));
        userArray.add(new User("Roaster/ roasting pan\nlò quay"));
        userArray.add(new User("Skewer\ncây xiên thịt"));
        userArray.add(new User("Stove\nbếp"));
        userArray.add(new User("Teakettle\nấm trà"));
        userArray.add(new User("Toaster\nmáy nướng bánh"));
        userArray.add(new User("Utensils\nđồ dùng nhà bếp"));
        userArray.add(new User("Water filter\nmáy lọc nước"));

        audio_list.add(R.raw.tlapron);
        audio_list.add(R.raw.tlbakingpan);
        audio_list.add(R.raw.tlbarbecuegrill);
        audio_list.add(R.raw.tlblender);
        audio_list.add(R.raw.tlcharcoalgrill);
        audio_list.add(R.raw.tlcheesecloth);
        audio_list.add(R.raw.tlcookbook);
        audio_list.add(R.raw.tlcutlery);
        audio_list.add(R.raw.tlcuttingboard);
        audio_list.add(R.raw.tleggbeater);
        audio_list.add(R.raw.tlfonduepot);
        audio_list.add(R.raw.tlfork);
        audio_list.add(R.raw.tlfryingpan);
        audio_list.add(R.raw.tlgrater);
        audio_list.add(R.raw.tlgriddle);
        audio_list.add(R.raw.tlgrillpan);
        audio_list.add(R.raw.tlgrinder);
        audio_list.add(R.raw.tlicebucket);
        audio_list.add(R.raw.tljuicer);
        audio_list.add(R.raw.tlkettle);
        audio_list.add(R.raw.tlknife);
        audio_list.add(R.raw.tlladle);
        audio_list.add(R.raw.tllid);
        audio_list.add(R.raw.tlmicrowaveoven);
        audio_list.add(R.raw.tlplatter);
        audio_list.add(R.raw.tlpotholder);
        audio_list.add(R.raw.tlpressurecooker);
        audio_list.add(R.raw.tlrefrigerator);
        audio_list.add(R.raw.tlricecooker);
        audio_list.add(R.raw.tlroaster);
        audio_list.add(R.raw.tlskewer);
        audio_list.add(R.raw.tlstove);
        audio_list.add(R.raw.tlteakettle);
        audio_list.add(R.raw.tltoaster);
        audio_list.add(R.raw.tlutensils);
        audio_list.add(R.raw.tlwaterfilter);
    }

    public void InitCookingType()
    {
        getSupportActionBar().setTitle("CÁC KIỂU NẤU.");
        userArray.add(new User("Cook with sauce\nKho"));
        userArray.add(new User("Grill\nNướng"));
        userArray.add(new User("Roast\nQuay"));
        userArray.add(new User("Fry\nRán ,chiên"));
        userArray.add(new User("Saute\nXào ,áp chảo"));
        userArray.add(new User("Stew\nHầm, ninh"));
        userArray.add(new User("Steam\nHấp"));
        userArray.add(new User("Sweet and sour salad\nNộm"));

        audio_list.add(R.raw.ctcookwithsauce);
        audio_list.add(R.raw.ctgrill);
        audio_list.add(R.raw.ctroast);
        audio_list.add(R.raw.ctfry);
        audio_list.add(R.raw.ctsaute);
        audio_list.add(R.raw.ctstew);
        audio_list.add(R.raw.ctsteam);
        audio_list.add(R.raw.ctsweetandsoursalad);
    }

    public void InitCookingSpices()
    {
        getSupportActionBar().setTitle("CÁC LOẠI GIA VỊ - BỘT");
        userArray.add(new User("Spices\ngia vị"));
        userArray.add(new User("Chilli sauce\ntương ớt"));
        userArray.add(new User("Chili powder\nớt bột"));
        userArray.add(new User("Chili oil\ndầu ớt"));
        userArray.add(new User("Chili paste\nớt sa tế"));
        userArray.add(new User("Curry powder\nbột cà ri"));
        userArray.add(new User("Pasta sauce\nsốt cà chua nấu mì Ý"));
        userArray.add(new User("Cooking oil\ndầu ăn"));
        userArray.add(new User("Olive oil\ndầu ô liu"));
        userArray.add(new User("Sugar\nđường"));
        userArray.add(new User("Salt\nmuối"));
        userArray.add(new User("Fish sauce\nnước mắm"));
        userArray.add(new User("Soy sauce\nnước tương"));
        userArray.add(new User("Pepper\nhạt tiêu"));
        userArray.add(new User("Vinegar\ngiấm"));

        audio_list.add(R.raw.spspices);
        audio_list.add(R.raw.spchillisauce);
        audio_list.add(R.raw.spchilipowder);
        audio_list.add(R.raw.spchilioil);
        audio_list.add(R.raw.spchilipaste);
        audio_list.add(R.raw.spcurrypowder);
        audio_list.add(R.raw.sppastasauce);
        audio_list.add(R.raw.spcookingoil);

        audio_list.add(R.raw.spoliveoil);
        audio_list.add(R.raw.spsugar);
        audio_list.add(R.raw.spsalt);

        audio_list.add(R.raw.spfishsauce);
        audio_list.add(R.raw.spsoysauce);
        audio_list.add(R.raw.sppepper);
        audio_list.add(R.raw.spvinegar);
    }
}
