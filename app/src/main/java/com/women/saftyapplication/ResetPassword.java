package com.women.saftyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    EditText email;
    FirebaseAuth mAuth;
    Button btnreset;
    CircularDotsLoader cz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reset_password );

        email = findViewById( R.id.resemail );
        btnreset = findViewById( R.id.btnreset );
        mAuth = FirebaseAuth.getInstance();
        cz=findViewById( R.id.progressBar);
        cz.setVisibility( View.GONE );
        btnreset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                boolean check = validation(Email);
                if (validation( Email )==true){
                    cz.setVisibility(View.VISIBLE);
                }
                if (check==true){
                    mAuth.sendPasswordResetEmail(Email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent l = new Intent(ResetPassword.this,Login.class);
                                        startActivity( l );
                                        Toast.makeText( ResetPassword.this,"Please check your email !!",Toast.LENGTH_SHORT ).show();
                                        return;
                                    } else {
                                        Toast.makeText( ResetPassword.this,"Enter valid email !!",Toast.LENGTH_SHORT ).show();
                                        return;
                                    }
                                }
                            });
                }
            }
        } );

    }

    private boolean validation(String Email) {
        if (TextUtils.isEmpty( Email )) {
            email.requestFocus();
            email.setError( "Enter email !!" );
            return false;
        } else {
            return true;
        }
    }

}