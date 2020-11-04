package com.example.zombies_humans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button btnStart;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mediaPlayer=MediaPlayer.create(StartActivity.this,R.raw.start);
        mediaPlayer.start();

        btnStart=findViewById(R.id.btn_Start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(new Intent(StartActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer=MediaPlayer.create(StartActivity.this,R.raw.start);
        mediaPlayer.start();
    }
}