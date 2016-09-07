package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RunSingleAudioActivity extends AppCompatActivity {

    EditText fileText;
    Button btnBack;
    CheckBox cbShowFile;
    Button btnPlay;
    Button btnStop;
    RelativeLayout selfDesScreen;
    Integer audioLesson;
    Thread timer;
    MediaPlayer audio_player = new MediaPlayer();
    Integer[] selfImg={
            R.drawable.kdgoingtozoo,
            R.drawable.kdchristmas,
            R.drawable.kdnurse,
            R.drawable.kdwaiting,
            R.drawable.kdchores,
            R.drawable.kdfirstsong,
            R.drawable.kdgotodoctor,
            R.drawable.kddifferentfood,
            R.drawable.kdalotofants,
            R.drawable.kdpettingzoo,
    };

    public void Init(int type)
    {
        selfDesScreen.setBackgroundResource(selfImg[type]);
        fileText.setFocusable(false);
        cbShowFile.setChecked(false);
        fileText.setVisibility(View.INVISIBLE);
        switch (type)
        {
            case 0:
                audioLesson = R.raw.kdgoingtothezoo;
                getSupportActionBar().setTitle("Going To The Zoo");
                fileText.setText("She goes to the zoo. \n" +
                                 "She sees a lion. \n" +
                                 "The lion roars. She sees an elephant. \n" +
                                 "The elephant has a long trunk. \n" +
                                 "She sees a turtle. \n" +
                                 "The turtle is slow. She sees a rabbit. \n" +
                                 "The rabbit has soft fur. She sees a gorilla. The gorilla is eating a banana.");
                break;
            case 1:
                audioLesson = R.raw.kdchristmastime;
                getSupportActionBar().setTitle("Christmas Time");
                fileText.setText("It is Christmas. \n" +
                        "Dad gives Tim a toy. The toy is in the box. \n" +
                        "Tim takes off the lid. He sees the toy. It is a car. \n" +
                        "The car is red. The car makes noises. \n" +
                        "The car moves fast. Tim likes the gift. He hugs his dad. Dad smiles. ");
                break;
            case 2:
                audioLesson = R.raw.kdshegoestothenurse;
                getSupportActionBar().setTitle("She goes to the Nurse");
                fileText.setText("She goes out to play. She runs around. She falls down. It hurts. She cries. \n" +
                        "She gets up. She goes to the nurse. The nurse is nice. She looks friendly. \n" +
                        "The nurse gives her a lollipop. It tastes good. \n" +
                        "The nurse gives her a bandage. She is okay now. She walks back to class.");
                break;
            case 3:
                audioLesson = R.raw.kdwaiting;
                getSupportActionBar().setTitle("Waiting");
                fileText.setText("The kids are in class. The teacher is teaching math. The kids are tired. \n" +
                        "They want to play. They want to see the sun. They like slides. \n" +
                        "They also like swings. They don't like math. They stare at the clock. \n" +
                        "The clock moves slowly. The kids continue to wait. ");
                break;
            case 4:
                audioLesson = R.raw.kdchores;
                getSupportActionBar().setTitle("Chores");
                fileText.setText("He is playing video games. Mom gets mad. She thinks he is lazy. \n" +
                        "She gives him a list of chores. He frowns. He looks at the list. \n" +
                        "He has to clean the floors. Then, he has to wash the dishes. \n" +
                        "Finally, he has to feed his dog. He starts doing his chores. ");
                break;
            case 5:
                audioLesson = R.raw.kdthefirstsong;
                getSupportActionBar().setTitle("The First Song");
                fileText.setText("She sits in the car. Her dad turns on the radio. A song plays. \n" +
                        "She taps her feet. She sways her head. Her dad laughs at her. \n" +
                        "He likes the song too. The song is over. The radio plays a different song. \n" +
                        "She does not like the new song. She sits quietly. ");
                break;
            case 6:
                audioLesson = R.raw.kdvisitingthedoctor;
                getSupportActionBar().setTitle("Visiting the Doctor");
                fileText.setText("Haley feels hot. Her mom touches her forehead. Haley has a fever. \n" +
                        "The mom takes Haley to a doctor. The doctor is kind. He gives her a sticker. \n" +
                        "He tells her to take a pill. He tells her to drink a lot of water.\n" +
                        " Haley goes home. She takes the pill and drinks water. \n" +
                        "She does this for three days. She is healthy again. ");
                break;
            case 7:
                audioLesson = R.raw.kddifferentfoods;
                getSupportActionBar().setTitle("Different Foods");
                fileText.setText("Sara is hungry. She goes to the kitchen. She opens the cabinet. \n" +
                        "There are a lot of snacks. The marshmallows are too sweet. \n" +
                        "The potato chips are too salty. The ice cream is too watery. \n" +
                        "The kiwis are too sour. The cereal is too bland. Her dad comes home. \n" +
                        "He gives her crackers. The crackers are perfect.");
                break;
            case 8:
                audioLesson = R.raw.kdalotofants;
                getSupportActionBar().setTitle("A Lot of Ants");
                fileText.setText("She eats a slice of cake. She drops a crumb." +
                        " The ants can smell it. They crawl towards the crumb." +
                        " She notices the ants." +
                        " She does not want to kill them." +
                        " She gets a cup." +
                        " She puts the ants inside." +
                        " She opens the window. She lets the ants go. ");
                break;
            case 9:
                audioLesson = R.raw.kdpettingzoo;
                getSupportActionBar().setTitle("Petting Zoo");
                fileText.setText("He goes to the petting zoo." +
                        " There are many different animals." +
                        " He pets the turtles." +
                        " The turtles feel rough." +
                        " He pets the sheep. The sheep feel wooly." +
                        " He pets the cows. The cows feel smooth." +
                        " He pets the bunnies." +
                        " The bunnies feel fluffy." +
                        " He tells his mom he wants a pet." +
                        " His mom says he can get one tomorrow. ");
                break;
            default:
                break;
        }

    }
    public  void MessageMap()
    {
        fileText = (EditText)findViewById(R.id.editText);
        cbShowFile = (CheckBox)findViewById(R.id.cbShowText);
        btnStop = (Button)findViewById(R.id.singleAudioStop);
        btnPlay = (Button)findViewById(R.id.singleAudioPlay);
        btnBack = (Button)findViewById(R.id.singleAudioBack);
        selfDesScreen = (RelativeLayout)findViewById(R.id.singleAudio);

    }

    public  void PlayAudio()
    {
            if (audio_player.isPlaying())
            {
                audio_player.stop();
            }
            audio_player = MediaPlayer.create(getApplicationContext(), audioLesson);
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
        setContentView(R.layout.activity_run_single_audio);

        MessageMap();
        Bundle extras = getIntent().getExtras();
        int type = -1;
        if (extras != null) {
            type  = extras.getInt("Type");
        }
        Init(type);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio_player.isPlaying()) {
                    audio_player.stop();
                }

                finishAffinity();
                Intent englishForScreen = new Intent(getApplicationContext(), EnglishForKidActivity.class);
                startActivity(englishForScreen);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayAudio();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (audio_player.isPlaying())
                {
                    audio_player.stop();
                }
            }
        });

        cbShowFile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    fileText.setVisibility(View.VISIBLE);
                }
                else
                {
                    fileText.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
