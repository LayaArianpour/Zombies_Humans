package com.example.zombies_humans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView img_human1Left,img_human2Left,img_human3Left,img_human1Right,img_human2Right,img_human3Right;
    private ImageView img_zombie1Left,img_zombie2Left,img_zombie3Left,img_zombie1Right,img_zombie2Right,img_zombie3Right;
    private ImageView img_passenger1Left,img_passenger2Left,img_passenger1Right,img_passenger2Right,img_boatLeft,img_boatRight;
    private RelativeLayout container_boatLeft,container_boatRight,container_gameOver,container_gameWin;
    private Button btn_go,btn_playAgain_gameOver,btn_playAgain_win;
    private boolean isHuman=false;
    private boolean boatInLeft=true;
    HashMap<Object,String> existenceOfBoatPassengers=new HashMap<>();
    HashMap<String,Boolean> typeOfBoatPassengers=new HashMap<>();
    HashMap<Object,Boolean> passengers_IsBoarding=new HashMap<>();
    ImageView[] listOfPasspassengers=new ImageView[2];
    int countOfHumansInLeft=3;
    int countOfHumansInRight=0;
    int countOfZombiesInLeft=3;
    int countOfZombiesInRight=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_human1Left=findViewById(R.id.img_human1Left);
        img_human2Left=findViewById(R.id.img_human2Left);
        img_human3Left=findViewById(R.id.img_human3Left);
        img_human1Right=findViewById(R.id.img_human1Right);
        img_human2Right=findViewById(R.id.img_human2Right);
        img_human3Right=findViewById(R.id.img_human3Right);

        img_zombie1Left=findViewById(R.id.img_zombie1Left);
        img_zombie2Left=findViewById(R.id.img_zombie2Left);
        img_zombie3Left=findViewById(R.id.img_zombie3Left);
        img_zombie1Right=findViewById(R.id.img_zombie1Right);
        img_zombie2Right=findViewById(R.id.img_zombie2Right);
        img_zombie3Right=findViewById(R.id.img_zombie3Right);

        img_passenger1Left=findViewById(R.id.img_passenger1Left);
        img_passenger2Left=findViewById(R.id.img_passenger2Left);
        img_passenger1Right=findViewById(R.id.img_passenger1Right);
        img_passenger2Right=findViewById(R.id.img_passenger2Right);

        img_boatLeft=findViewById(R.id.img_boatLeft);
        img_boatRight=findViewById(R.id.img_boatRight);

        container_boatLeft=findViewById(R.id.container_boatLeft);
        container_boatRight=findViewById(R.id.container_boatRight);

        passengers_IsBoarding.put(img_human1Left,false);
        passengers_IsBoarding.put(img_human2Left,false);
        passengers_IsBoarding.put(img_human3Left,false);
        passengers_IsBoarding.put(img_human1Right,false);
        passengers_IsBoarding.put(img_human2Right,false);
        passengers_IsBoarding.put(img_human3Right,false);
        passengers_IsBoarding.put(img_zombie1Left,false);
        passengers_IsBoarding.put(img_zombie2Left,false);
        passengers_IsBoarding.put(img_zombie3Left,false);
        passengers_IsBoarding.put(img_zombie1Right,false);
        passengers_IsBoarding.put(img_zombie2Right,false);
        passengers_IsBoarding.put(img_zombie3Right,false);

        existenceOfBoatPassengers.put(img_passenger1Left,null);
        existenceOfBoatPassengers.put(img_passenger2Left,null);
        existenceOfBoatPassengers.put(img_passenger1Right,null);
        existenceOfBoatPassengers.put(img_passenger2Right,null);

        typeOfBoatPassengers.put("passengers1_isHuman",false);
        typeOfBoatPassengers.put("passengers2_isHuman",false);
    }
}