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

    public final int FISH = 0;
    public final int BIRD = 1;
    public final int INSECT = 2;
    public final int CATTLE = 3;
    public final int WILD_ANIMAL = 4;
    public final int FLOWERS = 5;
    public final int MATERIAL = 6;
    public final int METAL = 7;
    public final int GAS = 8;
    public final int SHAPE = 9;
    public final int PARAM = 10;

    public void MessageMap()
    {
        btnRunAll = (Button)findViewById(R.id.btnRunAll);
        btnStop = (Button)findViewById(R.id.btnStopRunAll);
        btnBack = (Button)findViewById(R.id.btnBack);
        listView = (ListView)findViewById(R.id.listview);
    }

    public void Init(int type)
    {
        switch (type)
        {
            case FISH:
            {
                InitFish();
                break;
            }
            case BIRD:
            {
                Bird();
                break;
            }
            case INSECT:
            {
                Insect();
                break;
            }
            case CATTLE:
            {
                Cattle();
                break;
            }
            case WILD_ANIMAL:
            {
                WildAnimal();
                break;
            }
            case FLOWERS:
            {
                Flowers();
                break;
            }
            case MATERIAL:
            {
                Material();
                break;
            }
            case METAL:
            {
                Metal();
                break;
            }
            case GAS:
            {
                Gas();
                break;
            }
            case SHAPE:
            {
                Shape();
                break;
            }
            case PARAM:
            {
                Param();
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
        setContentView(R.layout.activity_word_group);
        Bundle extras = getIntent().getExtras();
        int type = -1;
        if (extras != null) {
            type  = extras.getInt("Type");
        }
        MessageMap();
        Init(type);
        userAdapter = new UserCustomListView(WordGroupActivity.this, R.layout.row, userArray);
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
                Intent wordGroup = new Intent(getApplicationContext(), WordsActivity.class);
                startActivity(wordGroup);
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

    public void InitFish()
    {
        userArray.add(new User("carp: Cá Chép"));
        userArray.add(new User("catfish: Cá trê"));
        userArray.add(new User("dolphin: Cá heo"));
        userArray.add(new User("eel: Con lươn"));
        userArray.add(new User("jellyfish  sứa"));
        userArray.add(new User("mackerel cá thu"));
        userArray.add(new User("octopus  bạch tuộc"));
        userArray.add(new User("seal  hải cẩu"));
        userArray.add(new User("shark  cá mập"));
        userArray.add(new User("squid  mực"));
        userArray.add(new User("tuna cá ngừ"));
        userArray.add(new User("whale  cá voi"));
        userArray.add(new User("crab  cua"));
        userArray.add(new User("lobster  tôm hùm"));
        userArray.add(new User("mussel  trai"));
        userArray.add(new User("starfish  sao biển"));
        userArray.add(new User("shrimp  tôm nói chung"));
        userArray.add(new User("goldfish cá vàng"));

        audio_list.add(R.raw.fcrap);
        audio_list.add(R.raw.fcatfish);
        audio_list.add(R.raw.fdolphin);
        audio_list.add(R.raw.ffeel);
        audio_list.add(R.raw.fjellyfish);
        audio_list.add(R.raw.fmackerel);
        audio_list.add(R.raw.foctopus);
        audio_list.add(R.raw.fseal);
        audio_list.add(R.raw.fshark);
        audio_list.add(R.raw.fsquid);
        audio_list.add(R.raw.ftuna);
        audio_list.add(R.raw.fwhale);
        audio_list.add(R.raw.fcrab);
        audio_list.add(R.raw.flobster);
        audio_list.add(R.raw.fmussel);
        audio_list.add(R.raw.fstarfish);
        audio_list.add(R.raw.fshrimp);
        audio_list.add(R.raw.fgoldfish);
    }

    public void Bird()
    {
        userArray.add(new User("crow quạ"));
        userArray.add(new User("dove  chim bồ câu"));
        userArray.add(new User("duck  vịt"));
        userArray.add(new User("eagle  chim đại bàng"));
        userArray.add(new User("finch  chim sẻ"));
        userArray.add(new User("hawk  chim diều hâu"));
        userArray.add(new User("kingfisher  chim bói cá"));
        userArray.add(new User("owl  con cú"));
        userArray.add(new User("seagull  chim hải âu"));
        userArray.add(new User("swan  thiên nga"));
        userArray.add(new User("woodpecker  chim gõ kiến"));
        userArray.add(new User("chick  gà con"));
        userArray.add(new User("duckling  vịt con"));
        userArray.add(new User("parrot  vẹt"));

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
    public void Insect()
    {
        userArray.add(new User("ant kiến"));
        userArray.add(new User("bee ong"));
        userArray.add(new User("butterfly  bướm"));
        userArray.add(new User("centipede  con rết"));
        userArray.add(new User("cockroach  gián"));
        userArray.add(new User("cricket  dế"));
        userArray.add(new User("fly  ruồi"));
        userArray.add(new User("mosquito  muỗi"));
        userArray.add(new User("scorpion  bọ cạp"));
        userArray.add(new User("snail  ốc"));
        userArray.add(new User("spider  nhện"));
        userArray.add(new User("termite  con mối"));
        userArray.add(new User("worm  sâu"));
        userArray.add(new User("grasshopper  châu chấu"));

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
    public void Cattle()
    {
        userArray.add(new User("cattle  gia súc"));
        userArray.add(new User("bull  bò đực"));
        userArray.add(new User("bullock  bò đực con"));
        userArray.add(new User("cow  bò"));
        userArray.add(new User("calf  bê"));
        userArray.add(new User("cock  gà trống"));
        userArray.add(new User("hen  gà mái"));
        userArray.add(new User("donkey  con lừa"));
        userArray.add(new User("goat  dê"));
        userArray.add(new User("goose-geese ngỗng"));
        userArray.add(new User("turkey  gà tây"));
        userArray.add(new User("pig  lợn"));

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
    public void WildAnimal()
    {
        userArray.add(new User("bat  rơi"));
        userArray.add(new User("deer hươu"));
        userArray.add(new User("frog  ếch"));
        userArray.add(new User("fox  cáo"));
        userArray.add(new User("hare  thỏ rừng"));
        userArray.add(new User("hedgehog  nhím"));
        userArray.add(new User("lizard  thằn lằn"));
        userArray.add(new User("reindeer tuần lộc"));
        userArray.add(new User("snake  rắn"));
        userArray.add(new User("squirrel  sóc"));
        userArray.add(new User("toad  cóc"));
        userArray.add(new User("antelope  linh dương"));
        userArray.add(new User("bear  gấu"));
        userArray.add(new User("camel  lạc đà"));
        userArray.add(new User("crocodile  cá sấu"));
        userArray.add(new User("elephant  voi"));
        userArray.add(new User("giraffe  hươu cao cổ"));
        userArray.add(new User("gorilla  khỉ đột"));

        userArray.add(new User("kangaroo"));
        userArray.add(new User("leopard  báo"));
        userArray.add(new User("lion  sư tử"));
        userArray.add(new User("monkey  khỉ"));
        userArray.add(new User("ostrich  đà điểu"));

        userArray.add(new User("panda Gau truct"));
        userArray.add(new User("penguin"));
        userArray.add(new User("polar bear"));
        userArray.add(new User("tiger  hổ"));
        userArray.add(new User("zebra  ngựa vằn"));
        userArray.add(new User("wolf  sói"));

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
    public void Flowers()
    {
        userArray.add(new User("rose  hoa hồng"));
        userArray.add(new User("orchid  hoa lan"));
        userArray.add(new User("lily  hoa loa kèn"));
        userArray.add(new User("daisy  hoa cúc"));
        userArray.add(new User("dandelion  hoa bồ công anh"));
        userArray.add(new User("carnation  hoa cẩm chướng"));
        userArray.add(new User("poppy  hoa anh túc"));
        userArray.add(new User("sunflower  hoa hướng dương"));
        userArray.add(new User("tulip  hoa tulip"));
        userArray.add(new User("waterlily  hoa súng"));
        userArray.add(new User("forget-me-not  hoa lưu ly"));

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
    public void Material()
    {
        userArray.add(new User("brick  gạch"));
        userArray.add(new User("cement  xi măng"));
        userArray.add(new User("concrete  bê tông"));
        userArray.add(new User("glass  thủy tinh"));
        userArray.add(new User("gravel  sỏi"));
        userArray.add(new User("marble  đá hoa"));
        userArray.add(new User("metal  kim loại"));
        userArray.add(new User("plastic  nhựa"));
        userArray.add(new User("sand  cát"));
        userArray.add(new User("slate  ngói"));
        userArray.add(new User("stone  đá cục"));
        userArray.add(new User("wood  gỗ"));
        userArray.add(new User("cloth  vải"));
        userArray.add(new User("cotton  cotton"));
        userArray.add(new User("lace  ren"));
        userArray.add(new User("leather  da"));
        userArray.add(new User("nylon  ni-lông"));
        userArray.add(new User("silk  lụa"));
        userArray.add(new User("wool  len"));

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
    public void Metal()
    {

        userArray.add(new User("aluminium  nhôm"));
        userArray.add(new User("brass  đồng thau"));
        userArray.add(new User("gold  vàng"));
        userArray.add(new User("iron  sắt"));
        userArray.add(new User("lead  chì"));
        userArray.add(new User("mercury  thủy ngân"));
        userArray.add(new User("magnesium  ma-giê"));
        userArray.add(new User("nickel  mạ kền"));
        userArray.add(new User("platinum  bạch kim"));
        userArray.add(new User("silver  bạc"));
        userArray.add(new User("steel  thép"));
        userArray.add(new User("tin  thiếc"));
        userArray.add(new User("zinc  kẽm"));

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
    public void Gas()
    {
        userArray.add(new User("charcoal  than củi"));
        userArray.add(new User("coal  than đá"));
        userArray.add(new User("gas  ga"));
        userArray.add(new User("oil  dầu"));
        userArray.add(new User("petrol  xăng"));
        userArray.add(new User("clay  đất sét"));
        userArray.add(new User("dust  bụi"));
        userArray.add(new User("mud  bùn"));
        userArray.add(new User("paper  giấy"));
        userArray.add(new User("rubber  cao su"));
        userArray.add(new User("smoke  khói"));
        userArray.add(new User("soil  đất"));
        userArray.add(new User("ice  đá băng"));
        userArray.add(new User("steam  hơi nước"));
        userArray.add(new User("water  nước"));

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
    public  void Shape()
    {
        userArray.add(new User("circle  hình tròn"));
        userArray.add(new User("triangle  hình tam giác"));
        userArray.add(new User("square  hình vuông"));
        userArray.add(new User("rectangle  hình chữ nhật"));
        userArray.add(new User("pentagon  hình ngũ giác"));
        userArray.add(new User("hexagon  hình lục giác"));
        userArray.add(new User("octagon  hình bát giác"));
        userArray.add(new User("oval  hình bầu dục"));
        userArray.add(new User("star  hình sao"));
        userArray.add(new User("polygon  hình đa giác"));
        userArray.add(new User("cone  hình nón"));
        userArray.add(new User("cube  hình lập phương"));
        userArray.add(new User("cylinder  hình trụ"));
        userArray.add(new User("pyramid  hình chóp"));
        userArray.add(new User("sphere  hình cầu"));

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
    public void Param()
    {
        userArray.add(new User("area  diện tích"));
        userArray.add(new User("circumference  chu vi đường tròn"));
        userArray.add(new User("diameter  đường kính"));
        userArray.add(new User("radius  bán kính"));
        userArray.add(new User("length  chiều dài"));
        userArray.add(new User("height  chiều cao"));
        userArray.add(new User("width  chiều rộng"));
        userArray.add(new User("perimeter  chu vi"));
        userArray.add(new User("angle  góc"));
        userArray.add(new User("right angle  góc vuông"));
        userArray.add(new User("line  đường"));
        userArray.add(new User("straight line  đường thẳng"));
        userArray.add(new User("curve  đường cong"));
        userArray.add(new User("volume  thể tích"));

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
}
