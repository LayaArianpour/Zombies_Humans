package com.example.zombies_humans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView img_human1Left,img_human2Left,img_human3Left,img_human1Right,img_human2Right,img_human3Right;
    private ImageView img_zombie1Left,img_zombie2Left,img_zombie3Left,img_zombie1Right,img_zombie2Right,img_zombie3Right;
    private ImageView img_passenger1Left,img_passenger2Left,img_passenger1Right,img_passenger2Right,img_boatLeft,img_boatRight;
    private RelativeLayout container_boatLeft,container_boatRight,container_gameOver,container_gameWin;
    private Button btn_go,btn_playAgain_gameOver,btn_playAgain_win;
    private boolean isHuman=false;
    private boolean boatInLeft=true;
    HashMap<Object,Object> existenceOfBoatPassengers=new HashMap<>();
    HashMap<Object,String> nameOfBoatPassengers=new HashMap<>();
    HashMap<String,Boolean> typeOfBoatPassengers=new HashMap<>();
    HashMap<Object,Boolean> passengers_IsBoarding=new HashMap<>();
    int countOfHumansInLeft=3;
    int countOfHumansInRight=0;
    int countOfZombiesInLeft=3;
    int countOfZombiesInRight=0;
    int countOfPassengers=0;
    private ImageView passenger1,passenger2;


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
        container_gameOver=findViewById(R.id.container_gameOver);
        container_gameWin=findViewById(R.id.container_gameWin);

        btn_go=findViewById(R.id.btn_go);
        btn_playAgain_gameOver=findViewById(R.id.btn_playAgain_Gameover);
        btn_playAgain_win=findViewById(R.id.btn_playAgain_win);

        existenceOfBoatPassengers.put(img_passenger1Left,null);
        existenceOfBoatPassengers.put(img_passenger2Left,null);
        existenceOfBoatPassengers.put(img_passenger1Right,null);
        existenceOfBoatPassengers.put(img_passenger2Right,null);

        nameOfBoatPassengers.put(img_passenger1Left,null);
        nameOfBoatPassengers.put(img_passenger2Left,null);
        nameOfBoatPassengers.put(img_passenger1Right,null);
        nameOfBoatPassengers.put(img_passenger2Right,null);

        typeOfBoatPassengers.put("passengers1_isHuman",false);
        typeOfBoatPassengers.put("passengers2_isHuman",false);

        img_human1Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = true;
                if (!passengers_IsBoarding.get(img_human1Left)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_human1Left,img_human1Right,isHuman,"human1",true);
                }
            }
        });

        img_human2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = true;
                if (!passengers_IsBoarding.get(img_human2Left)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_human2Left,img_human2Right,isHuman,"human2",true);
                }
            }
        });

        img_human3Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = true;
                if (!passengers_IsBoarding.get(img_human3Left)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_human3Left,img_human3Right,isHuman,"human3",true);
                }
            }
        });

        img_human1Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = true;
                if (!passengers_IsBoarding.get(img_human1Right)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_human1Right,img_human1Left,isHuman,"human1",false);
                }
            }
        });

        img_human2Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = true;
                if (!passengers_IsBoarding.get(img_human2Right)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_human2Right,img_human2Left,isHuman,"human2",false);
                }
            }
        });

        img_human3Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = true;
                if (!passengers_IsBoarding.get(img_human3Right)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_human3Right,img_human3Left,isHuman,"human3",false);
                }
            }
        });


        img_zombie1Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = false;
                if (!passengers_IsBoarding.get(img_zombie1Left)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_zombie1Left,img_zombie1Right,isHuman,"zombie1",true);
                }
            }
        });

        img_zombie2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = false;
                if (!passengers_IsBoarding.get(img_zombie2Left)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_zombie2Left,img_zombie2Right,isHuman,"zombie2",true);
                }
            }
        });

        img_zombie3Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = false;
                if (!passengers_IsBoarding.get(img_zombie3Left)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_zombie3Left,img_zombie3Right,isHuman,"zombie3",true);
                }
            }
        });

        img_zombie1Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = false;
                if (!passengers_IsBoarding.get(img_zombie1Right)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_zombie1Right,img_zombie1Left,isHuman,"zombie1",false);
                }
            }
        });

        img_zombie2Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = false;
                if (!passengers_IsBoarding.get(img_zombie2Right)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_zombie2Right,img_zombie2Left,isHuman,"zombie2",false);
                }
            }
        });

        img_zombie3Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHuman = false;
                if (!passengers_IsBoarding.get(img_zombie3Right)) { //if human not in boat
                    checkBeforeGettingOnBoat(img_zombie3Right,img_zombie3Left,isHuman,"zombie3",false);
                }
            }
        });

        img_passenger1Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOffBoat(img_passenger1Left, boatInLeft);
            }
        });

        img_passenger2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOffBoat(img_passenger2Left, boatInLeft);
            }
        });

        img_passenger1Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOffBoat(img_passenger1Right, boatInLeft);
            }
        });

        img_passenger2Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOffBoat(img_passenger2Right, boatInLeft);
            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveBoat(boatInLeft);
            }
        });
    }

    private void moveBoat(boolean isBoatInLeft) {
        if(countOfPassengers>0){
            if(isBoatInLeft){
                if(passenger1!=null){
                    img_passenger1Right.setVisibility(View.VISIBLE);
                    img_passenger1Left.setVisibility(View.INVISIBLE);
                    img_passenger1Right.setImageDrawable(passenger1.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger1Right);
                    existenceOfBoatPassengers.put(img_passenger1Right,passenger1);
                    existenceOfBoatPassengers.remove(img_passenger1Left);
                    existenceOfBoatPassengers.put(img_passenger1Left,null);
                }
                if(passenger2!=null){
                    img_passenger2Right.setVisibility(View.VISIBLE);
                    img_passenger2Left.setVisibility(View.INVISIBLE);
                    img_passenger2Right.setImageDrawable(passenger2.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger2Right);
                    existenceOfBoatPassengers.put(img_passenger2Right,passenger2);
                    existenceOfBoatPassengers.remove(img_passenger2Left);
                    existenceOfBoatPassengers.put(img_passenger2Left,null);
                }
                container_boatLeft.setVisibility(View.INVISIBLE);
                container_boatRight.setVisibility(View.VISIBLE);
                boatInLeft=false;
            }else{
                if(passenger1!=null){
                    img_passenger1Left.setVisibility(View.VISIBLE);
                    img_passenger1Right.setVisibility(View.INVISIBLE);
                    img_passenger1Left.setImageDrawable(passenger1.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger1Left);
                    existenceOfBoatPassengers.put(img_passenger1Left,passenger1);
                    existenceOfBoatPassengers.remove(img_passenger1Right);
                    existenceOfBoatPassengers.put(img_passenger1Right,null);
                }
                if(passenger2!=null){
                    img_passenger2Left.setVisibility(View.VISIBLE);
                    img_passenger2Right.setVisibility(View.INVISIBLE);
                    img_passenger2Left.setImageDrawable(passenger2.getDrawable());
                    existenceOfBoatPassengers.remove(img_passenger2Left);
                    existenceOfBoatPassengers.put(img_passenger2Left,passenger2);
                    existenceOfBoatPassengers.remove(img_passenger2Right);
                    existenceOfBoatPassengers.put(img_passenger2Right,null);
                }
                container_boatRight.setVisibility(View.INVISIBLE);
                container_boatLeft.setVisibility(View.VISIBLE);
                boatInLeft=true;
            }
        }else{
            Toast.makeText(MainActivity.this,"Boat is Empty!!\nplease getting on boat passenger",Toast.LENGTH_SHORT).show();
        }
    }

    private void getOffBoat(ImageView imgPassenger, boolean boatInLeft) {
        ImageView nameOfObjectPassenger= (ImageView) existenceOfBoatPassengers.get(imgPassenger);
        if(boatInLeft) {
            if (img_zombie1Left.equals(nameOfObjectPassenger)|| img_zombie1Right.equals(nameOfObjectPassenger)) {
                img_zombie1Left.setVisibility(View.VISIBLE);
                img_zombie1Right.setVisibility(View.INVISIBLE);
            } else if (img_zombie2Left.equals(nameOfObjectPassenger)||img_zombie2Right.equals(nameOfObjectPassenger)) {
                img_zombie2Left.setVisibility(View.VISIBLE);
                img_zombie2Right.setVisibility(View.INVISIBLE);
            } else if (img_zombie3Left.equals(nameOfObjectPassenger)||img_zombie3Right.equals(nameOfObjectPassenger)) {
                img_zombie3Left.setVisibility(View.VISIBLE);
                img_zombie3Right.setVisibility(View.INVISIBLE);
            } else if (img_human1Left.equals(nameOfObjectPassenger)||img_human1Right.equals(nameOfObjectPassenger)) {
                img_human1Left.setVisibility(View.VISIBLE);
                img_human1Right.setVisibility(View.INVISIBLE);
            } else if (img_human2Left.equals(nameOfObjectPassenger)||img_human2Right.equals(nameOfObjectPassenger)) {
                img_human2Left.setVisibility(View.VISIBLE);
                img_human2Right.setVisibility(View.INVISIBLE);
            } else if (img_human3Left.equals(nameOfObjectPassenger)||img_human3Right.equals(nameOfObjectPassenger)) {
                img_human3Left.setVisibility(View.VISIBLE);
                img_human3Right.setVisibility(View.INVISIBLE);
            }

        }else{
            if (img_zombie1Right.equals(nameOfObjectPassenger)||img_zombie1Left.equals(nameOfObjectPassenger)) {
                img_zombie1Left.setVisibility(View.INVISIBLE);
                img_zombie1Right.setVisibility(View.VISIBLE);
            } else if (img_zombie2Right.equals(nameOfObjectPassenger)||img_zombie2Left.equals(nameOfObjectPassenger)) {
                img_zombie2Left.setVisibility(View.INVISIBLE);
                img_zombie2Right.setVisibility(View.VISIBLE);
            } else if (img_zombie3Right.equals(nameOfObjectPassenger)||img_zombie3Left.equals(nameOfObjectPassenger)) {
                img_zombie3Left.setVisibility(View.INVISIBLE);
                img_zombie3Right.setVisibility(View.VISIBLE);
            } else if (img_human1Right.equals(nameOfObjectPassenger)||img_human1Left.equals(nameOfObjectPassenger)) {
                img_human1Left.setVisibility(View.INVISIBLE);
                img_human1Right.setVisibility(View.VISIBLE);
            } else if (img_human2Right.equals(nameOfObjectPassenger)||img_human2Left.equals(nameOfObjectPassenger)) {
                img_human2Left.setVisibility(View.INVISIBLE);
                img_human2Right.setVisibility(View.VISIBLE);
            } else if (img_human3Right.equals(nameOfObjectPassenger)||img_human3Left.equals(nameOfObjectPassenger)) {
                img_human3Left.setVisibility(View.INVISIBLE);
                img_human3Right.setVisibility(View.VISIBLE);
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



}