package com.women.saftyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;

import static java.lang.Thread.sleep;

public class Message extends AppCompatActivity {
EditText Phonenumber,Text;
Button sendMessage;
    //CircularDotsLoader cz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_message );
        Phonenumber = findViewById( R.id.phone );
        Text = findViewById( R.id.entertext );
        sendMessage = findViewById( R.id.sendmessage );
       // cz=findViewById( R.id.progressBar);
    }

    public void btnsend(View view){
        int permissiongrantend = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        String phonenumber = Phonenumber.getText().toString().trim();
        String Message = Text.getText().toString().trim();

        if (permissiongrantend== PackageManager.PERMISSION_GRANTED){
            MyMessage();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void MyMessage() {
        String phonenumber = Phonenumber.getText().toString().trim();
        String Message = Text.getText().toString().trim();
        if (!Phonenumber.getText().toString().equals("") || !Text.getText().toString().equals("")) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber, null, Message, null, null);
            Toast.makeText(Message.this, "Message Send", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Message.this, "Message Failled", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else {
                    Toast.makeText(Message.this,"Allow Permission",Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
}