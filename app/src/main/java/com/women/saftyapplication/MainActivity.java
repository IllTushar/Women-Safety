package com.women.saftyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.agrawalsuneet.dotsloader.loaders.LazyLoader;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

Animation topAlpha;
ImageView women,sheild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
//Animation
        topAlpha = AnimationUtils.loadAnimation(this,R.anim.topanim);
        women = findViewById( R.id.women );
        sheild = findViewById( R.id.sheilds );
        AllianceLoader allianceLoader = new AllianceLoader(
                this,
                40,
                6,
                true,
                10,
                ContextCompat.getColor(this, R.color.white),
                ContextCompat.getColor(this, R.color.white),
                ContextCompat.getColor(this, R.color.white));

        allianceLoader.setAnimDuration(500);
        //Animation set
        women.setAnimation( topAlpha );
        sheild.setAnimation( topAlpha );
        Thread myThead = new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity( i );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } );
        myThead.start();
    }
}