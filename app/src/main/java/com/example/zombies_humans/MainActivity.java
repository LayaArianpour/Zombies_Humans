package com.example.zombies_humans;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView img_human1Left,img_human2Left,img_human3Left,img_human1Right,img_human2Right,img_human3Right;
    private ImageView img_zombie1Left,img_zombie2Left,img_zombie3Left,img_zombie1Right,img_zombie2Right,img_zombie3Right;
    private ImageView img_passenger1Left,img_passenger2Left,img_passenger1Right,img_passenger2Right,img_boatLeft,img_boatRight;
    private RelativeLayout container_boatLeft,container_boatRight,container_gameOver,container_gameWin,container_timer;
    private TextView txt_sec;
    private Button btn_go,btn_playAgain_gameOver,btn_playAgain_win;
    private boolean isHuman=false;
    private boolean boatInLeft=true;
    private boolean activeGame=true;
    private boolean startBeep=false;
    private boolean isMoveBoat=false;

    HashMap<Object,Object> existenceOfBoatPassengers=new HashMap<>();
    HashMap<Object,Boolean> passengers_IsBoarding=new HashMap<>();
    int countOfHumansInLeft=3;
    int countOfHumansInRight=0;
    int countOfZombiesInLeft=3;
    int countOfZombiesInRight=0;
    int countOfPassengers=0;
    private ImageView passenger1,passenger2;
    private MediaPlayer mediaPlayer;
    private ObjectAnimator animator;
    private int resoursSong=R.raw.play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(MainActivity.this,resoursSong);
        mediaPlayer.start();

        txt_sec=findViewById(R.id.txt_sec);

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
        container_gameOver=findViewById(R.id.container_gameOver);
        container_gameWin=findViewById(R.id.container_gameWin);
        container_timer=findViewById(R.id.container_timer);

        btn_go=findViewById(R.id.btn_go);
        btn_playAgain_gameOver=findViewById(R.id.btn_playAgain_Gameover);
        btn_playAgain_win=findViewById(R.id.btn_playAgain_win);


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


        new CountDownTimer(110000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (activeGame) {
                    if (millisUntilFinished / 1000 <=10) {
                        txt_sec.setTextColor(Color.RED);
                    }
                    if (millisUntilFinished / 1000 < 10) {
                        if(!startBeep){
                            mediaPlayer.stop();
                            resoursSong=R.raw.beep;
                            mediaPlayer=MediaPlayer.create(MainActivity.this,resoursSong);
                            mediaPlayer.start();
                            startBeep=true;
                        }
                        txt_sec.setText("00" + millisUntilFinished / 1000);
                    } else if (millisUntilFinished / 1000 < 100) {
                        txt_sec.setText("0" + millisUntilFinished / 1000);
                    } else {
                        txt_sec.setText("" + millisUntilFinished / 1000);
                    }
                }
            }
            public void onFinish() {
                if(startBeep){
                    mediaPlayer.stop();
                    Toast.makeText(MainActivity.this,"Time Out...",Toast.LENGTH_SHORT).show();
                }
                if(activeGame) {
                    gameOver();
                }
            }
        }.start();

        img_human1Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = true;
                        if (!passengers_IsBoarding.get(img_human1Left)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_human1Left, img_human1Right, isHuman, "human1", true);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_human2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat){
                    if (activeGame) {
                        isHuman = true;
                        if (!passengers_IsBoarding.get(img_human2Left)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_human2Left, img_human2Right, isHuman, "human2", true);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_human3Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = true;
                        if (!passengers_IsBoarding.get(img_human3Left)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_human3Left, img_human3Right, isHuman, "human3", true);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_human1Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = true;
                        if (!passengers_IsBoarding.get(img_human1Right)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_human1Right, img_human1Left, isHuman, "human1", false);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_human2Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = true;
                        if (!passengers_IsBoarding.get(img_human2Right)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_human2Right, img_human2Left, isHuman, "human2", false);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_human3Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = true;
                        if (!passengers_IsBoarding.get(img_human3Right)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_human3Right, img_human3Left, isHuman, "human3", false);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });


        img_zombie1Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = false;
                        if (!passengers_IsBoarding.get(img_zombie1Left)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_zombie1Left, img_zombie1Right, isHuman, "zombie1", true);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_zombie2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = false;
                        if (!passengers_IsBoarding.get(img_zombie2Left)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_zombie2Left, img_zombie2Right, isHuman, "zombie2", true);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_zombie3Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = false;
                        if (!passengers_IsBoarding.get(img_zombie3Left)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_zombie3Left, img_zombie3Right, isHuman, "zombie3", true);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_zombie1Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = false;
                        if (!passengers_IsBoarding.get(img_zombie1Right)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_zombie1Right, img_zombie1Left, isHuman, "zombie1", false);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_zombie2Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = false;
                        if (!passengers_IsBoarding.get(img_zombie2Right)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_zombie2Right, img_zombie2Left, isHuman, "zombie2", false);
                        }
                    }
                }
            }
        });

        img_zombie3Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        isHuman = false;
                        if (!passengers_IsBoarding.get(img_zombie3Right)) { //if human not in boat
                            checkBeforeGettingOnBoat(img_zombie3Right, img_zombie3Left, isHuman, "zombie3", false);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_passenger1Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        getOffBoat(img_passenger1Left, boatInLeft);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_passenger2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        getOffBoat(img_passenger2Left, boatInLeft);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_passenger1Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        getOffBoat(img_passenger1Right, boatInLeft);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_passenger2Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    if (activeGame) {
                        getOffBoat(img_passenger2Right, boatInLeft);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't pick up or drop off passengers",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMoveBoat) {
                    moveBoat(boatInLeft);
                }else {
                    Toast.makeText(MainActivity.this,"The boat is moving\nYou can't move Boat",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_playAgain_win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        btn_playAgain_gameOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void moveBoat(boolean isBoatInLeft) {
        if(countOfPassengers>0){
            if(isBoatInLeft){
                if(passenger1!=null){
                    img_passenger1Right.setVisibility(View.VISIBLE);
                    img_passenger1Right.setImageDrawable(passenger1.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger1Right);
                    existenceOfBoatPassengers.put(img_passenger1Right,passenger1);
                    existenceOfBoatPassengers.remove(img_passenger1Left);
                    existenceOfBoatPassengers.put(img_passenger1Left,null);
                }
                if(passenger2!=null){
                    img_passenger2Right.setVisibility(View.VISIBLE);
                    img_passenger2Right.setImageDrawable(passenger2.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger2Right);
                    existenceOfBoatPassengers.put(img_passenger2Right,passenger2);
                    existenceOfBoatPassengers.remove(img_passenger2Left);
                    existenceOfBoatPassengers.put(img_passenger2Left,null);
                }
                animationOfMoveBoat(container_boatLeft,500f,5000);
                new CountDownTimer(5500, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        img_passenger1Left.setVisibility(View.INVISIBLE);
                        img_passenger2Left.setVisibility(View.INVISIBLE);
                        container_boatLeft.setVisibility(View.INVISIBLE);
                        container_boatLeft.setTranslationX(-20f);
                        boatInLeft=false;
                        computingCountOfItems(passenger1,passenger2,true);
                        container_boatRight.setVisibility(View.VISIBLE);
                        isMoveBoat=false;

                    }
                }.start();
            }else{
                if(passenger1!=null){
                    img_passenger1Left.setVisibility(View.VISIBLE);
                    img_passenger1Left.setImageDrawable(passenger1.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger1Left);
                    existenceOfBoatPassengers.put(img_passenger1Left,passenger1);
                    existenceOfBoatPassengers.remove(img_passenger1Right);
                    existenceOfBoatPassengers.put(img_passenger1Right,null);
                }
                if(passenger2!=null){
                    img_passenger2Left.setVisibility(View.VISIBLE);
                    img_passenger2Left.setImageDrawable(passenger2.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger2Left);
                    existenceOfBoatPassengers.put(img_passenger2Left,passenger2);
                    existenceOfBoatPassengers.remove(img_passenger2Right);
                    existenceOfBoatPassengers.put(img_passenger2Right,null);
                }
                animationOfMoveBoat(container_boatRight,-400f,5000);
                new CountDownTimer(5500, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        img_passenger1Right.setVisibility(View.INVISIBLE);
                        img_passenger2Right.setVisibility(View.INVISIBLE);
                        container_boatRight.setVisibility(View.INVISIBLE);
                        container_boatRight.setTranslationX(50f);
                        boatInLeft=true;
                        computingCountOfItems(passenger1,passenger2,false);
                        container_boatLeft.setVisibility(View.VISIBLE);
                        isMoveBoat=false;
                    }
                }.start();
            }
        }else{
            Toast.makeText(MainActivity.this,"Boat is Empty!!\nplease getting on boat passenger",Toast.LENGTH_SHORT).show();
        }
    }

    private void animationOfMoveBoat(RelativeLayout container,float lenght,long duration) {
        isMoveBoat=true;
        animator=ObjectAnimator.ofFloat(container,"translationX",lenght);
        animator.setDuration(duration);
        animator.start();
    }

    private void getOffBoat(ImageView imgPassenger, boolean boatInLeft) {
        ImageView nameOfObjectPassenger= (ImageView) existenceOfBoatPassengers.get(imgPassenger);
        boolean isObjectHuman=false;
        if(boatInLeft) {
            if (img_zombie1Left.equals(nameOfObjectPassenger)|| img_zombie1Right.equals(nameOfObjectPassenger)) {
                img_zombie1Left.setVisibility(View.VISIBLE);
                img_zombie1Right.setVisibility(View.INVISIBLE);
                isObjectHuman=false;
            } else if (img_zombie2Left.equals(nameOfObjectPassenger)||img_zombie2Right.equals(nameOfObjectPassenger)) {
                img_zombie2Left.setVisibility(View.VISIBLE);
                img_zombie2Right.setVisibility(View.INVISIBLE);
                isObjectHuman=false;
            } else if (img_zombie3Left.equals(nameOfObjectPassenger)||img_zombie3Right.equals(nameOfObjectPassenger)) {
                img_zombie3Left.setVisibility(View.VISIBLE);
                img_zombie3Right.setVisibility(View.INVISIBLE);
                isObjectHuman=false;
            } else if (img_human1Left.equals(nameOfObjectPassenger)||img_human1Right.equals(nameOfObjectPassenger)) {
                img_human1Left.setVisibility(View.VISIBLE);
                img_human1Right.setVisibility(View.INVISIBLE);
                isObjectHuman=true;
            } else if (img_human2Left.equals(nameOfObjectPassenger)||img_human2Right.equals(nameOfObjectPassenger)) {
                img_human2Left.setVisibility(View.VISIBLE);
                img_human2Right.setVisibility(View.INVISIBLE);
                isObjectHuman=true;
            } else if (img_human3Left.equals(nameOfObjectPassenger)||img_human3Right.equals(nameOfObjectPassenger)) {
                img_human3Left.setVisibility(View.VISIBLE);
                img_human3Right.setVisibility(View.INVISIBLE);
                isObjectHuman=true;
            }
        }else{
            if (img_zombie1Right.equals(nameOfObjectPassenger)||img_zombie1Left.equals(nameOfObjectPassenger)) {
                img_zombie1Left.setVisibility(View.INVISIBLE);
                img_zombie1Right.setVisibility(View.VISIBLE);
                isObjectHuman=false;
            } else if (img_zombie2Right.equals(nameOfObjectPassenger)||img_zombie2Left.equals(nameOfObjectPassenger)) {
                img_zombie2Left.setVisibility(View.INVISIBLE);
                img_zombie2Right.setVisibility(View.VISIBLE);
                isObjectHuman=false;
            } else if (img_zombie3Right.equals(nameOfObjectPassenger)||img_zombie3Left.equals(nameOfObjectPassenger)) {
                img_zombie3Left.setVisibility(View.INVISIBLE);
                img_zombie3Right.setVisibility(View.VISIBLE);
                isObjectHuman=false;
            } else if (img_human1Right.equals(nameOfObjectPassenger)||img_human1Left.equals(nameOfObjectPassenger)) {
                img_human1Left.setVisibility(View.INVISIBLE);
                img_human1Right.setVisibility(View.VISIBLE);
                isObjectHuman=true;
            } else if (img_human2Right.equals(nameOfObjectPassenger)||img_human2Left.equals(nameOfObjectPassenger)) {
                img_human2Left.setVisibility(View.INVISIBLE);
                img_human2Right.setVisibility(View.VISIBLE);
                isObjectHuman=true;
            } else if (img_human3Right.equals(nameOfObjectPassenger)||img_human3Left.equals(nameOfObjectPassenger)) {
                img_human3Left.setVisibility(View.INVISIBLE);
                img_human3Right.setVisibility(View.VISIBLE);
                isObjectHuman=true;
            }
        }
        if(imgPassenger==img_passenger1Left || imgPassenger==img_passenger1Right){
            passenger1=null;
        }
        if(imgPassenger==img_passenger2Left || imgPassenger==img_passenger2Right){
            passenger2=null;
        }
        countOfPassengers--;
        imgPassenger.setVisibility(View.INVISIBLE);
        existenceOfBoatPassengers.remove(imgPassenger);
        existenceOfBoatPassengers.put(imgPassenger,null);
    }

    private void checkBeforeGettingOnBoat(ImageView imgInBoat, ImageView imgAdverse, boolean isHuman,String nameOfPassenger,boolean isLeftGettinOn) {

        if(isLeftGettinOn){
            if(boatInLeft){
                gettingOnBoat(imgInBoat,imgAdverse,isHuman,true,nameOfPassenger);
            }else {
                Toast.makeText(MainActivity.this, "Opsss boat in right!!\n can not move!!", Toast.LENGTH_SHORT).show();
            }
        }else if(!isLeftGettinOn){
            if(!boatInLeft){
                gettingOnBoat(imgInBoat,imgAdverse,isHuman,false,nameOfPassenger);
            }else {
                Toast.makeText(MainActivity.this, "Opsss boat in left!!\n can not move!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void gettingOnBoat(ImageView imgInBoat, ImageView imgAdverse, boolean isHuman, boolean toRightBoat,String nameOfPassenger) {

        if(toRightBoat){ //boat in left
            if(existenceOfBoatPassengers.get(img_passenger1Left)==null) { //if boat Passengers1 is empty
                imgInBoat.setVisibility(View.INVISIBLE);
                imgAdverse.setVisibility(View.INVISIBLE);
                img_passenger1Left.setImageDrawable(imgInBoat.getDrawable());
                img_passenger1Left.setVisibility(View.VISIBLE);
                existenceOfBoatPassengers.remove(img_passenger1Left);
                existenceOfBoatPassengers.put(img_passenger1Left,imgInBoat);
                passenger1=imgInBoat;
                countOfPassengers++;
            }
            else if(existenceOfBoatPassengers.get(img_passenger2Left)==null){//if boat Passengers1 is empty
                imgInBoat.setVisibility(View.INVISIBLE);
                imgAdverse.setVisibility(View.INVISIBLE);
                img_passenger2Left.setImageDrawable(imgInBoat.getDrawable());
                img_passenger2Left.setVisibility(View.VISIBLE);
                existenceOfBoatPassengers.remove(img_passenger2Left);
                existenceOfBoatPassengers.put(img_passenger2Left,imgInBoat);
                passenger2=imgInBoat;
                countOfPassengers++;
            }
            else{
                Toast.makeText(MainActivity.this,"boat is full!!!!",Toast.LENGTH_SHORT).show();
            }
        }else{// boat in right
            if(existenceOfBoatPassengers.get(img_passenger1Right)==null) { //if boat Passengers1 is empty
                imgInBoat.setVisibility(View.INVISIBLE);
                imgAdverse.setVisibility(View.INVISIBLE);
                img_passenger1Right.setImageDrawable(imgInBoat.getDrawable());
                img_passenger1Right.setVisibility(View.VISIBLE);
                existenceOfBoatPassengers.remove(img_passenger1Right);
                existenceOfBoatPassengers.put(img_passenger1Right,imgInBoat);
                passenger1=imgInBoat;
                countOfPassengers++;
            }
            else if(existenceOfBoatPassengers.get(img_passenger2Right)==null){ //if boat Passengers2 is empty
                imgInBoat.setVisibility(View.INVISIBLE);
                imgAdverse.setVisibility(View.INVISIBLE);
                img_passenger2Right.setImageDrawable(imgInBoat.getDrawable());
                img_passenger2Right.setVisibility(View.VISIBLE);
                existenceOfBoatPassengers.remove(img_passenger2Right);
                existenceOfBoatPassengers.put(img_passenger2Right,imgInBoat);
                passenger2=imgInBoat;
                countOfPassengers++;
            }
            else{
                Toast.makeText(MainActivity.this,"boat is full!!!!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void computingCountOfItems(ImageView passenger1Type,ImageView passenger2Type,boolean BoatToRight) {
        if(BoatToRight){
            if (img_zombie1Left.equals(passenger1Type) || img_zombie2Left.equals(passenger1Type) || img_zombie3Left.equals(passenger1Type) || img_zombie1Right.equals(passenger1Type) || img_zombie2Right.equals(passenger1Type) || img_zombie3Right.equals(passenger1Type)) {
                countOfZombiesInLeft--;
                countOfZombiesInRight++;
            } else if (img_human1Left.equals(passenger1Type) || img_human2Left.equals(passenger1Type) || img_human3Left.equals(passenger1Type) || img_human1Right.equals(passenger1Type) || img_human2Right.equals(passenger1Type) || img_human3Right.equals(passenger1Type)) {
                countOfHumansInLeft--;
                countOfHumansInRight++;
            }
            if (img_zombie1Left.equals(passenger2Type) || img_zombie2Left.equals(passenger2Type) || img_zombie3Left.equals(passenger2Type) || img_zombie1Right.equals(passenger2Type) || img_zombie2Right.equals(passenger2Type) || img_zombie3Right.equals(passenger2Type)) {
                countOfZombiesInLeft--;
                countOfZombiesInRight++;
            } else if (img_human1Left.equals(passenger2Type) || img_human2Left.equals(passenger2Type) || img_human3Left.equals(passenger2Type) || img_human1Right.equals(passenger2Type) || img_human2Right.equals(passenger2Type) || img_human3Right.equals(passenger2Type)) {
                countOfHumansInLeft--;
                countOfHumansInRight++;
            }
        }else {
            if (img_zombie1Left.equals(passenger1Type) || img_zombie2Left.equals(passenger1Type) || img_zombie3Left.equals(passenger1Type) || img_zombie1Right.equals(passenger1Type) || img_zombie2Right.equals(passenger1Type) || img_zombie3Right.equals(passenger1Type)) {
                countOfZombiesInLeft++;
                countOfZombiesInRight--;
            } else if (img_human1Left.equals(passenger1Type) || img_human2Left.equals(passenger1Type) || img_human3Left.equals(passenger1Type) || img_human1Right.equals(passenger1Type) || img_human2Right.equals(passenger1Type) || img_human3Right.equals(passenger1Type)) {
                countOfHumansInLeft++;
                countOfHumansInRight--;
            }
            if (img_zombie1Left.equals(passenger2Type) || img_zombie2Left.equals(passenger2Type) || img_zombie3Left.equals(passenger2Type) || img_zombie1Right.equals(passenger2Type) || img_zombie2Right.equals(passenger2Type) || img_zombie3Right.equals(passenger2Type)) {
                countOfZombiesInLeft++;
                countOfZombiesInRight--;
            } else if (img_human1Left.equals(passenger2Type) || img_human2Left.equals(passenger2Type) || img_human3Left.equals(passenger2Type) || img_human1Right.equals(passenger2Type) || img_human2Right.equals(passenger2Type) || img_human3Right.equals(passenger2Type)) {
                countOfHumansInLeft++;
                countOfHumansInRight--;
            }
        }

        if((countOfHumansInLeft<countOfZombiesInLeft&&countOfHumansInLeft>=1) || (countOfHumansInRight<countOfZombiesInRight&&countOfHumansInRight>=1)){
            gameOver();
        }
        else if(countOfZombiesInLeft==0 && countOfHumansInLeft==0 && countOfZombiesInRight==3 && countOfHumansInRight==3){
            mediaPlayer.stop();
            resoursSong=R.raw.win;
            mediaPlayer=MediaPlayer.create(MainActivity.this,resoursSong);
            mediaPlayer.start();
            btn_go.setVisibility(View.GONE);
            container_gameWin.setVisibility(View.VISIBLE);
            activeGame=false;
        }
    }

    private void gameOver() {
        mediaPlayer.stop();
        //mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.loser);
        resoursSong=R.raw.loser;
        mediaPlayer=MediaPlayer.create(MainActivity.this,resoursSong);
        mediaPlayer.start();
        btn_go.setVisibility(View.GONE);
        container_gameOver.setVisibility(View.VISIBLE);
        activeGame=false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer=MediaPlayer.create(MainActivity.this,resoursSong);
        mediaPlayer.start();
    }
}