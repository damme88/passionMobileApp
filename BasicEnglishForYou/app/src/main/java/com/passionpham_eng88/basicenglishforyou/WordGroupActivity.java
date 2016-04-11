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
        userArray.add(new User("Calendar : Lịch"));
        userArray.add(new User("Chair    : Ghế"));
        userArray.add(new User("Clock    : Đồng hồ treo tường"));
        userArray.add(new User("Cup      : Cái chén"));
        userArray.add(new User("Curtain  : Rèm cửa"));
        userArray.add(new User("Door     : Cưa ra vào"));
        userArray.add(new User("Fan      : Quật"));
        userArray.add(new User("Fence    : Hàng rào"));
        userArray.add(new User("Flat     : Căn hộ"));
        userArray.add(new User("Floor    : Sàn nhà"));
        userArray.add(new User("Garden   : Vườn"));
        userArray.add(new User("House    : Nhà"));
        userArray.add(new User("Lamp     : Cái đèn"));
        userArray.add(new User("Pillow   : Cái gối"));
        userArray.add(new User("Rung     : Thảm nhà"));
        userArray.add(new User("Shelf    : Kệ sách"));
        userArray.add(new User("Sink     : Bồn rửa bát"));
        userArray.add(new User("Sofa     : Ghê sofa"));
        userArray.add(new User("Stair    : Cầu thang"));
        userArray.add(new User("Table    : Cái bàn"));
        userArray.add(new User("Terrace  : Sân thượng"));
        userArray.add(new User("Toilet   : Nhà vệ sinh"));
        userArray.add(new User("Towel    : Khăn tắm"));
        userArray.add(new User("Wall     : Bức tường"));
        userArray.add(new User("Window   : Cửa sổ"));

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
        userArray.add(new User("beard : râu"));
        userArray.add(new User("blood : máu"));
        userArray.add(new User("body  : cơ thể"));
        userArray.add(new User("bone  : xương"));
        userArray.add(new User("bowel : Ruột"));
        userArray.add(new User("breast : Ngực"));
        userArray.add(new User("Buttoks: Mông"));
        userArray.add(new User("Chin   : Cằm"));
        userArray.add(new User("Ear    : Tai"));
        userArray.add(new User("Eye	: Mắt"));
        userArray.add(new User("Face   : Mặt"));
        userArray.add(new User("Fingger: Ngón tay"));
        userArray.add(new User("Flesh  : Da - Thịt"));
        userArray.add(new User("Foot   : Bàn Chân"));
        userArray.add(new User("Hair   : Tóc"));
        userArray.add(new User("Hand   : Bàn tay"));
        userArray.add(new User("Head   : Cái đầu"));
        userArray.add(new User("Heart  : Trái tim"));
        userArray.add(new User("Jaw    : Quai hàm"));
        userArray.add(new User("Lip    : Môi"));
        userArray.add(new User("Moustache : Râu mép"));
        userArray.add(new User("Mouth  : Cái miệng"));
        userArray.add(new User("Nose   : Mũi"));
        userArray.add(new User("Nostril : lỗi mũi"));
        userArray.add(new User("Tear    : Nước mắt"));
        userArray.add(new User("Thigh   : Đùi"));
        userArray.add(new User("Throat  : Cổ họng"));
        userArray.add(new User("Tongue  : Lưỡi"));
        userArray.add(new User("Tooth - Teeth : Răng - Hàm răng"));

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
        userArray.add(new User("Actor   : Nam diễn viên"));
        userArray.add(new User("Actress : Nữ diễn viên"));
        userArray.add(new User("Athlete : Vận động viên"));
        userArray.add(new User("Chef    : Bếp trưởng"));
        userArray.add(new User("Fish man : Ngư dân"));
        //userArray.add(new User("Lady      : Qúy bà"));
        userArray.add(new User("Librarian : Thủ thư"));
        userArray.add(new User("Musician  : Nhạc sỹ"));
        userArray.add(new User("Pilot     : Phi công"));
        userArray.add(new User("Policeman : Cảnh sát"));
        userArray.add(new User("Post man  : Người đưa thư"));
        userArray.add(new User("Queen     : nữ hoàng"));
        userArray.add(new User("Sales man : Người bán hàng"));
        userArray.add(new User("Secretary : Thư ký"));
        userArray.add(new User("Nurse     : y tá"));
        userArray.add(new User("Programmer : Lập trình viên"));

        audio_list.add(R.raw.joactor);
        audio_list.add(R.raw.joactress);
        audio_list.add(R.raw.joathlete);
        audio_list.add(R.raw.jochef);
        audio_list.add(R.raw.jofishman);
        //audio_list.add(R.raw.jolady);
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
        userArray.add(new User("Octopus     : Bạch tuộc"));
        userArray.add(new User("Carp        : Cá chép"));
        userArray.add(new User("Dolphin     : Cá heo"));
        userArray.add(new User("Shark       : Cá mập"));
        userArray.add(new User("Tuna        : Cá ngừ"));
        userArray.add(new User("Mackerel    : Cá thu"));
        userArray.add(new User("Catfish     : Cá trê"));
        userArray.add(new User("Goldfish    : Cá vàng"));
        userArray.add(new User("Whale       : Cá voi"));
        userArray.add(new User("Eel         : Con lươn"));
        userArray.add(new User("Jellyfish   : Con sứa"));
        userArray.add(new User("Crab        : Cua"));
        userArray.add(new User("Seal        : Hải cẩu"));
        userArray.add(new User("Squid       : Mực"));
        userArray.add(new User("Starfish    : Sao biển"));
        userArray.add(new User("Mussel      : Trai"));
        userArray.add(new User("Shrimp      : Tôm"));
        userArray.add(new User("Lobster     : Tôm hùm"));


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
        userArray.add(new User("Crow        : Quạ"));
        userArray.add(new User("Dove        : Bồ câu"));
        userArray.add(new User("Duck        : Vịt"));
        userArray.add(new User("Eagle       : Đại bàng"));
        userArray.add(new User("Finch       : Chim sẻ"));
        userArray.add(new User("Hawk        : Chim diều hâu"));
        userArray.add(new User("Kingfisher  : Chim bói cá"));
        userArray.add(new User("Owl         : Chim cú"));
        userArray.add(new User("Seagull     : Chim hải âu"));
        userArray.add(new User("Swan        : Thiên nga"));
        userArray.add(new User("Woodpecker  : Chim gõ kiến"));
        userArray.add(new User("Chick       : Gà con"));
        userArray.add(new User("Duckling    : Vịt con"));
        userArray.add(new User("Parrot      : Vẹt"));

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
        userArray.add(new User("Ant         : Kiến"));
        userArray.add(new User("Bee         : Ong"));
        userArray.add(new User("Butterfly   : Bướm"));
        userArray.add(new User("Centipede   : Con rết"));
        userArray.add(new User("Cockroach   : Gián"));
        userArray.add(new User("Cricket     : Dế"));
        userArray.add(new User("Fly         : Ruồi"));
        userArray.add(new User("Mosquito    : Muỗi"));
        userArray.add(new User("Scorpion    : Bọ cạp"));
        userArray.add(new User("Snail       : Ốc"));
        userArray.add(new User("Spider      : Nhện"));
        userArray.add(new User("Termite     : Con mối"));
        userArray.add(new User("Worm        : Sâu"));
        userArray.add(new User("Grasshopper : Châu chấu"));

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
        userArray.add(new User("Cattle  : Gia súc"));
        userArray.add(new User("Bull    : Bò đực"));
        userArray.add(new User("Bullock : Bò đực con"));
        userArray.add(new User("Cow     : Bò"));
        userArray.add(new User("Calf    : Bê"));
        userArray.add(new User("Cock    : Gà trống"));
        userArray.add(new User("Hen     : Gà mái"));
        userArray.add(new User("Donkey  : Con lừa"));
        userArray.add(new User("Goat    : Dê"));
        userArray.add(new User("Goose   : Ngỗng"));
        userArray.add(new User("Turkey  : Gà tây"));
        userArray.add(new User("Pig     : Lợn"));

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
        userArray.add(new User("Bat         : Rơi"));
        userArray.add(new User("Deer        : Hươu"));
        userArray.add(new User("Frog        : Ếch"));
        userArray.add(new User("Fox         : Cáo"));
        userArray.add(new User("Hare        : Thỏ rừng"));
        userArray.add(new User("Hedgehog    : Nhím"));
        userArray.add(new User("Lizard      : Thằn lằn"));
        userArray.add(new User("Reindeer    : Tuần lộc"));
        userArray.add(new User("Snake       : Rắn"));
        userArray.add(new User("Squirrel    : Sóc"));
        userArray.add(new User("Toad        : Cóc"));
        userArray.add(new User("Antelope    : Linh dương"));
        userArray.add(new User("Bear        : Gấu"));
        userArray.add(new User("Camel       : Lạc đà"));
        userArray.add(new User("Crocodile   : Cá sấu"));
        userArray.add(new User("Elephant    : Voi"));
        userArray.add(new User("Giraffe     : Hươu cao cổ"));
        userArray.add(new User("Gorilla     : Khỉ đột"));
        userArray.add(new User("Kangaroo    : Chuột túi"));
        userArray.add(new User("Leopard     : Báo"));
        userArray.add(new User("Lion        : Sư tử"));
        userArray.add(new User("Monkey      : Khỉ"));
        userArray.add(new User("Ostrich     : Đà điểu"));
        userArray.add(new User("Panda       : Gấu trúc"));
        userArray.add(new User("Penguin     : Chím cánh cụt"));
        userArray.add(new User("Polar bear  : Gấu bắc cực"));
        userArray.add(new User("Tiger       : Hổ"));
        userArray.add(new User("Zebra       : Ngựa vằn"));
        userArray.add(new User("Wolf        : Sói"));

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
        userArray.add(new User("Rose        :Hoa hồng"));
        userArray.add(new User("Orchid      :Hoa lan"));
        userArray.add(new User("Lily        :Hoa loa kèn"));
        userArray.add(new User("Daisy       :Hoa cúc"));
        userArray.add(new User("Dandelion   :Hoa bồ công anh"));
        userArray.add(new User("Carnation   :Hoa cẩm chướng"));
        userArray.add(new User("Poppy       :Hoa anh túc"));
        userArray.add(new User("Sunflower   :Hoa hướng dương"));
        userArray.add(new User("Tulip       :Hoa tulip"));
        userArray.add(new User("Waterlily   :Hoa súng"));
        userArray.add(new User("Forget-me-not:  Hoa lưu ly"));

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
        userArray.add(new User("Brick:      Gạch"));
        userArray.add(new User("Cement:     Xi măng"));
        userArray.add(new User("Concrete:   Bê tông"));
        userArray.add(new User("Glass:      Thủy tinh"));
        userArray.add(new User("Gravel:     Sỏi"));
        userArray.add(new User("Marble:     Đá hoa"));
        userArray.add(new User("Metal:      Kim loại"));
        userArray.add(new User("Plastic:    Nhựa"));
        userArray.add(new User("Sand:       Cát"));
        userArray.add(new User("Slate:      Ngói"));
        userArray.add(new User("Stone:      Đá cục"));
        userArray.add(new User("Wood:       Gỗ"));
        userArray.add(new User("Cloth:      Vải"));
        userArray.add(new User("Cotton:     Cotton"));
        userArray.add(new User("Lace:       Ren"));
        userArray.add(new User("Leather:    Da"));
        userArray.add(new User("Nylon:      Ni-lông"));
        userArray.add(new User("Silk:       Lụa"));
        userArray.add(new User("Wool:       Len"));

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
        userArray.add(new User("Aluminium   : Nhôm"));
        userArray.add(new User("Brass       : Đồng thau"));
        userArray.add(new User("Gold        : Vàng"));
        userArray.add(new User("Iron        : Sắt"));
        userArray.add(new User("Lead        : Chì"));
        userArray.add(new User("Mercury     : Thủy ngân"));
        userArray.add(new User("Magnesium   : Ma-giê"));
        userArray.add(new User("Nickel      : Kền"));
        userArray.add(new User("Platinum    : Bạch kim"));
        userArray.add(new User("Silver      : Bạc"));
        userArray.add(new User("Steel       : Thép"));
        userArray.add(new User("Tin         : Thiếc"));
        userArray.add(new User("Zinc        : Kẽm"));

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
        userArray.add(new User("Charcoal    : Than củi"));
        userArray.add(new User("Coal        : Than đá"));
        userArray.add(new User("Gas         : Ga"));
        userArray.add(new User("Oil         : Dầu"));
        userArray.add(new User("Petrol      : Xăng"));
        userArray.add(new User("Clay        : Đất sét"));
        userArray.add(new User("Dust        : Bụi"));
        userArray.add(new User("Mud         : Bùn"));
        userArray.add(new User("Paper       : Giấy"));
        userArray.add(new User("Rubber      : Cao su"));
        userArray.add(new User("Smoke       : Khói"));
        userArray.add(new User("Soil        : Đất"));
        userArray.add(new User("Ice         : Đá băng"));
        userArray.add(new User("Steam       : Hơi nước"));
        userArray.add(new User("Water       : Nước"));

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
        userArray.add(new User("Area            : Diện tích"));
        userArray.add(new User("Circumference   : Chu vi đường tròn"));
        userArray.add(new User("Diameter        : Đường kính"));
        userArray.add(new User("Radius          : Bán kính"));
        userArray.add(new User("Length          : Chiều dài"));
        userArray.add(new User("Height          : Chiều cao"));
        userArray.add(new User("Width           : Chiều rộng"));
        userArray.add(new User("Perimeter       : Chu vi"));
        userArray.add(new User("Angle           : Góc"));
        userArray.add(new User("Right angle     : Góc vuông"));
        userArray.add(new User("Line            : Đường"));
        userArray.add(new User("Straight line   : Đường thẳng"));
        userArray.add(new User("Curve           : Đường cong"));
        userArray.add(new User("Volume          : Thể tích"));

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
        userArray.add(new User("Liver       : Gan"));
        userArray.add(new User("Ham         : Giăm Bông"));
        userArray.add(new User("Pate        : Pa tê"));
        userArray.add(new User("veal        : Thịt Bê"));
        userArray.add(new User("Beef        : Thị Bò"));
        userArray.add(new User("Lamb        : Thịt Cừu"));
        userArray.add(new User("Chicken     : Thị Gà"));
        userArray.add(new User("turkey      : Thịt gà tây"));
        userArray.add(new User("Pork        : Thịt Lợn"));
        userArray.add(new User("Bacon       : Thịt Muối"));
        userArray.add(new User("Cooked meat : Thị nấu chín"));
        userArray.add(new User("Duck        : Thịt Vịt"));
        userArray.add(new User("Sausages    : Xúc xíc"));

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
        audio_list.add(R.raw.fosausages);

    }
    public void InitFoodMilk()
    {
        getSupportActionBar().setTitle("CÁC LOẠI SỮA - ĐỒ UỐNG");
        userArray.add(new User("Butter     : Bơ"));
        userArray.add(new User("Cream      : Kem"));
        userArray.add(new User("Cheese     : Phô mai"));
        userArray.add(new User("Egg        : Trứng"));
        userArray.add(new User("Milk       : Sữa"));
        userArray.add(new User("Sour cream : Kem chua"));
        userArray.add(new User("Yoghurt    : Sữa chua"));

        audio_list.add(R.raw.dributter);
        audio_list.add(R.raw.dricream);
        audio_list.add(R.raw.dricheese);
        audio_list.add(R.raw.driegg);
        audio_list.add(R.raw.drimilk);
        audio_list.add(R.raw.drisourcream);
        audio_list.add(R.raw.driyoghurt);
    }
    public void InitFoodFruits()
    {
        getSupportActionBar().setTitle("CÁC LOẠI TRÁI CÂY");
        userArray.add(new User("Cherry      : Anh đào"));
        userArray.add(new User("Grapefruit  : Bưởi"));
        userArray.add(new User("Orange 	    : Cam"));
        userArray.add(new User("Banana 	    : Chuối"));
        userArray.add(new User("Melon 	    : Dưa"));
        userArray.add(new User("Coconut     : Dừa"));
        userArray.add(new User("Pineapple   : Dứa"));
        userArray.add(new User("Peach 	    : Đào"));
        userArray.add(new User("Pear 	    : Lê"));
        userArray.add(new User("Plum 	    : Mận"));
        userArray.add(new User("Apricot     : Mơ"));
        userArray.add(new User("Grape 	    : Nho"));
        userArray.add(new User("Fig 	    : Sung"));
        userArray.add(new User("Apple       : Táo"));
        userArray.add(new User("Mango 	    : Xoài"));
        userArray.add(new User("Kiwi fruit  : Quả Kiwi"));
        userArray.add(new User("Lemon 	    : Quả Chanh tây"));
        userArray.add(new User("Lime 	    : Quả Chanh ta"));
        userArray.add(new User("Pomegranate : Quả Lựu"));
        userArray.add(new User("Raspberry 	: Quả mâm xôi đỏ"));
        userArray.add(new User("Blackberry  : Quả mâm xôi đen"));
        userArray.add(new User("Strawberry 	: Quả Dâu"));
    }

    public void InitVegetable()
    {
        getSupportActionBar().setTitle("CÁC LOẠI RAU - CỦ- HẠT");
        userArray.add(new User("Cabbage: Bắp cải"));
        userArray.add(new User("Cacao: Cây ca cao"));
        userArray.add(new User("Catawissa: Hành ta"));
        userArray.add(new User("Cauliflower: Hoa cải, hoa lơ"));
        userArray.add(new User("Celery: Cần tây"));
        userArray.add(new User("Chayote : Su su"));
        userArray.add(new User("Chestnut: Hạt dẻ"));
        userArray.add(new User("Coleslaw: Xà lách cải bắp"));
        userArray.add(new User("Cucumber: Dưa leo"));
        userArray.add(new User("Eggplant: Cà tím"));
        userArray.add(new User("Galangal: Củ Giềng"));
        userArray.add(new User("Garlic: Tỏi"));
        userArray.add(new User("Kale: Cải ngọt"));
        userArray.add(new User("Knotgrass: Rau răm"));
        userArray.add(new User("Kohlrabi: Su hào"));
        userArray.add(new User("Lettuce: Rau diếp"));
        userArray.add(new User("Ginger, Ginger root: Gừng"));
        userArray.add(new User("Gourd: Quả bầu"));
        userArray.add(new User("Tomato: Cà chua"));
        userArray.add(new User("Dill: Cây thì là"));
        userArray.add(new User("Water morning glory: Rau Muống"));
        userArray.add(new User("Wild Betel leave: Lá Lốt"));
        userArray.add(new User("White Radish: Củ Cải Trắng"));
        userArray.add(new User("Yam: (Mỹ) Khoai lang"));
    }

    public void InitDrinking()
    {
        getSupportActionBar().setTitle("CÁC LOẠI ĐỒ UỐNG");
        userArray.add(new User("mineral water: nước khoáng"));
        userArray.add(new User("fruit juice: nước trái cây\n"));
        userArray.add(new User("orange juice: nước cam"));
        userArray.add(new User("pineapple juice: nước dứa"));
        userArray.add(new User("tomato juice: nước cà chua"));
        userArray.add(new User("smoothies: sinh tố"));
        userArray.add(new User("lemonade: nước chanh"));
        userArray.add(new User("squash: nước ép"));
        userArray.add(new User("iced tea: trà đá"));
        userArray.add(new User("Tea: Trà"));
        userArray.add(new User("coffee: cà phê"));
        userArray.add(new User("cocoa: ca cao"));
        userArray.add(new User("beer: Bia"));
        userArray.add(new User("Wine: Rượu"));
        userArray.add(new User("Bitter     : Rượu Đắng"));
        userArray.add(new User("Champagne  : Sâm banh"));
        userArray.add(new User("Alcohol    : Rượu bia nói chung"));
        userArray.add(new User("Spirits    : Rượu mạnh"));

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
        userArray.add(new User("Bread – Bánh mì"));
        userArray.add(new User("Quick bread – Bánh mì nhanh"));
        userArray.add(new User("stuffed pancake: Bánh cuốn"));
        userArray.add(new User("round sticky rice cake: Bánh dầy"));
        userArray.add(new User("girdle-cake: Bánh tráng"));
        userArray.add(new User("shrimp in batter: Bánh tôm"));
        userArray.add(new User("young rice cake: Bánh cốm "));
        userArray.add(new User("stuffed sticky rice balls: Bánh trôi"));
        userArray.add(new User("soya cake: Bánh đậu"));
        userArray.add(new User("steamed wheat flour cake: Bánh bao"));
        userArray.add(new User("pancako: Bánh xèo"));
        userArray.add(new User("stuffed sticky rice cake: Bánh chưng"));

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
        audio_list.add(R.raw.ckstuffedpancake);
    }

    public void InitCookTool()
    {
        getSupportActionBar().setTitle("CÁC LOẠI DỤNG CỤ LÀM BẾP");
        userArray.add(new User("apron: tạp dề"));
        userArray.add(new User("baking pan/ baking sheet: khay nướng bánh"));
        userArray.add(new User("barbecue grill: vỉ nướng"));
        userArray.add(new User("blender: máy xay sinh tố"));
        userArray.add(new User("charcoal grill: lò nướng bằng than"));
        userArray.add(new User("cheese cloth: vải lọc"));
        userArray.add(new User("cookbook: sách hướng dẫn nấu ăn"));
        userArray.add(new User("cutlery: bộ dao kéo"));
        userArray.add(new User("cutting board: cái thớt"));
        userArray.add(new User("egg beater: máy đánh trứng"));
        userArray.add(new User("fondue pot: nồi lẩu"));
        userArray.add(new User("fork: nĩa"));
        userArray.add(new User("frying pan: chảo rán"));
        userArray.add(new User("grater: đồ bào, mài"));
        userArray.add(new User("griddle: vỉ nướng"));
        userArray.add(new User("grill pan: chảo nướng"));
        userArray.add(new User("grinder: cối xay"));
        userArray.add(new User("ice bucket: xô đựng đá"));
        userArray.add(new User("juicer: máy ép trái cây"));
        userArray.add(new User("kettle: ấm nước"));
        userArray.add(new User("knife: dao"));
        userArray.add(new User("ladle: muôi, vá"));
        userArray.add(new User("lid: nắp"));
        userArray.add(new User("microwave oven: lò vi sóng"));
        userArray.add(new User("platter: đĩa"));
        userArray.add(new User("pot holder: găng tay lót nồi"));
        userArray.add(new User("pressure cooker: nồi áp suất"));
        userArray.add(new User("refrigerator: tủ lạnh"));
        userArray.add(new User("rice cooker: nồi cơm điện"));
        userArray.add(new User("roaster/ roasting pan: lò quay"));
        userArray.add(new User("skewer: cây xiên thịt"));
        userArray.add(new User("stove: bếp"));
        userArray.add(new User("teakettle: ấm trà"));
        userArray.add(new User("toaster: máy nướng bánh"));
        userArray.add(new User("utensils: đồ dùng nhà bếp"));
        userArray.add(new User("water filter: máy lọc nước"));

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
        audio_list.add(R.raw.tltoaster);
        audio_list.add(R.raw.tlutensils);
        audio_list.add(R.raw.tlwaterfilter);
    }

    public void InitCookingType()
    {
        getSupportActionBar().setTitle("CÁC KIỂU NẤU.");
        userArray.add(new User("cook with sauce: Kho"));
        userArray.add(new User("grill: Nướng"));
        userArray.add(new User("roast: Quay"));
        userArray.add(new User("fry: Rán ,chiên"));
        userArray.add(new User("Saute: Xào ,áp chảo"));
        userArray.add(new User("stew: Hầm, ninh"));
        userArray.add(new User("steam: Hấp"));
        userArray.add(new User("Sweet and sour salad: Nộm"));

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
        userArray.add(new User("Spices       : gia vị"));
        userArray.add(new User("Chilli sauce : tương ớt"));
        userArray.add(new User("Chili powder : ớt bột"));
        userArray.add(new User("Chili oil    : dầu ớt"));
        userArray.add(new User("Chili paste  : ớt sa tế"));
        userArray.add(new User("curry powder : bột cà ri"));
        userArray.add(new User("pasta sauce  : sốt cà chua nấu mì Ý"));
        userArray.add(new User("cooking oil  : dầu ăn"));
        userArray.add(new User("olive oil    : dầu ô liu"));
        userArray.add(new User("Sugar        : đường"));
        userArray.add(new User("Salt         : muối"));
        userArray.add(new User("Fish sauce   : nước mắm"));
        userArray.add(new User("Soy sauce    : nước tương"));
        userArray.add(new User("Pepper       : hạt tiêu"));
        userArray.add(new User("Vinegar      : giấm"));

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
