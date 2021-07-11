package com.women.saftyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {
ImageView police,message,video,instruction,panic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );
        police = findViewById( R.id.police );
        message= findViewById( R.id.message );
        video = findViewById( R.id.video );
        instruction = findViewById( R.id.instruction );
        panic = findViewById( R.id.panic );
       final MediaPlayer mediaPlayer = MediaPlayer.create( this,R.raw.siren );
        //------------------Police
        police.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Dashboard.this,Police.class);
                startActivity( p );
            }
        });
        //-----------------Message
        message.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Dashboard.this,Message.class);
                startActivity( m );
            }
        } );
        //---------------Video
        video.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent V = new Intent(Dashboard.this,Video.class);
              startActivity( V );
            }
        } );
        //---------------Instructions
        instruction.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent V = new Intent(Dashboard.this,Instructions.class);
                startActivity( V );
            }
        } );
        //---------------Panic
        panic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent V = new Intent(Dashboard.this,Panic.class);
                startActivity( V );
            }
        } );
    }
}