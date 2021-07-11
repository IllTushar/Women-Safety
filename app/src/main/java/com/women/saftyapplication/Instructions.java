package com.women.saftyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cuberto.liquid_swipe.LiquidPager;

public class Instructions extends AppCompatActivity {
ViewPager viewPager;
LiquidPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_instructions );
        pager= findViewById( R.id.pager );
        viewPager = new ViewPager( getSupportFragmentManager(),1 );
        pager.setAdapter(viewPager);
    }
}