package com.women.saftyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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

public class Panic extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    CircularDotsLoader cz;
EditText allindia;
ImageView helplineimg;
Button callallindia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_panic );
        allindia = findViewById( R.id.allindia );
        helplineimg = findViewById( R.id.helplineimg );
        callallindia = findViewById( R.id.callallindia );
        cz=findViewById( R.id.progressBar);
        cz.setVisibility( View.GONE );
        mAuth = FirebaseAuth.getInstance();
        database =  FirebaseDatabase.getInstance();
        reference = database.getReference("Datils");
        helplineimg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = allindia.getText().toString();
                if (TextUtils.isEmpty(number)){
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
                allIndiaHelpline();
            }
        } );

        callallindia.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                String number = allindia.getText().toString();
                if (number.trim().isEmpty()){
                    Toast.makeText(Panic.this,"Click on Helplinewomen icon !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    i.setData( Uri.parse("tel: "+number));

                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(Panic.this,"Pls grant permission !",Toast.LENGTH_SHORT).show();
                    requestOnPermissions();
                }
                else {
                    startActivity(i);
                }
            }
        } );
    }

    private void requestOnPermissions() {
        ActivityCompat.requestPermissions(Panic.this,new String[]{Manifest.permission.CALL_PHONE},1);


    }

    private void allIndiaHelpline() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String phone = snapshot.child("phone").getValue(String.class);
                allindia.setText(phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Panic.this,"Details cannot show !!",Toast.LENGTH_SHORT).show();


            }
        });

    }

}