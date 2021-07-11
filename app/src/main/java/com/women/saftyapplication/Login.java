package com.women.saftyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView forgetpassword,registration;
    EditText email,password;
    FirebaseAuth mAuth;
    Button login;
    CircularDotsLoader cz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        forgetpassword = findViewById( R.id.forgetpassword );
        mAuth = FirebaseAuth.getInstance();
        registration= findViewById( R.id.registration );
        email = findViewById( R.id.lemail );
        password = findViewById( R.id.lpassword );
        login = findViewById( R.id.llogin );
        cz=findViewById( R.id.progressBar);

        cz.setVisibility( View.GONE );
        /*------------Forget Password----------------------*/
        forgetpassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp = new Intent(Login.this,ResetPassword.class);
                startActivity( fp );
            }
        } );
        registration.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rs = new Intent(Login.this,Registration.class);
                startActivity( rs );
            }
        });
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lemail = email.getText().toString();
                String lpassword = password.getText().toString();
                boolean check =   validations(lemail,lpassword);
                if (validations( lemail,lpassword )==true){
                    cz.setVisibility(View.VISIBLE);
                }
                if (check==true){
                    mAuth.signInWithEmailAndPassword(lemail,lpassword)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //loader.setVisibility( View.GONE );
                                    if (task.isSuccessful()) {
                                        Intent m = new Intent(Login.this,Dashboard.class);
                                        startActivity( m );
                                        Toast.makeText( Login.this,"Login Successful !!",Toast.LENGTH_SHORT ).show();
                                        return;
                                    } else {
                                        Toast.makeText( Login.this,"Login Failed !!",Toast.LENGTH_SHORT ).show();
                                        return;
                                    }
                                }
                            });
                }
            }
        } );
    }
    private boolean validations(String lemail, String lpassword) {
        if (TextUtils.isEmpty( lemail )){
            email.requestFocus();
            email.setError( "Enter your email !!" );
            return false;
        }else if(TextUtils.isEmpty( lpassword )){
            password.requestFocus();
            password.setError( "Enter your password !!" );
            return false;
        }
        else {
            return true;
        }
    }
}