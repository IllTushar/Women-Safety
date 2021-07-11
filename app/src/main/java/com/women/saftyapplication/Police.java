package com.women.saftyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Thread.sleep;

public class Police extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
ImageView image;
Button call;
EditText policenumber;
    CircularDotsLoader cz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_police );
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Datils");
        call = findViewById( R.id.call );
        policenumber = findViewById( R.id.policnumber );
        image = findViewById( R.id.policeman );
        final MediaPlayer mediaPlayer = MediaPlayer.create( this,R.raw.siren );
        cz=findViewById( R.id.progressBar);
        cz.setVisibility( View.GONE );
       image.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mediaPlayer.start();
               String number = policenumber.getText().toString();

               if (TextUtils.isEmpty(  number)){
                   try {

                       cz.setVisibility( View.VISIBLE );
                       sleep( 1000 );
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               else {
                   cz.setVisibility( View.GONE );
               }

               reference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       String phone = snapshot.child("username").getValue(String.class);
                       policenumber.setText(phone);

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                       Toast.makeText(Police.this,"Details cannot show !!",Toast.LENGTH_SHORT).show();


                   }
               });


           }
       } );
       call.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Intent.ACTION_CALL);
               String number = policenumber.getText().toString();
               if (number.trim().isEmpty()){
                   Toast.makeText(Police.this,"Click on Policeman icon !!",Toast.LENGTH_SHORT).show();
                  return;
               }
               else {
                   i.setData( Uri.parse("tel: "+number));

               }
               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                   Toast.makeText(Police.this,"Pls grant permission !",Toast.LENGTH_SHORT).show();
                   requestOnPermissions();
               }
               else {
                   startActivity(i);
               }
           }
       } );
    }

    private void requestOnPermissions() {
        ActivityCompat.requestPermissions(Police.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
}
