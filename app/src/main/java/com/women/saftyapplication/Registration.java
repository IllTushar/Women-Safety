package com.women.saftyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    TextView login;
    Button register;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseDatabase database;
    EditText username,password,confirmpassword,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );
        register =findViewById( R.id.Register );
        login = findViewById( R.id.login );
        username = findViewById( R.id.username );
        password = findViewById( R.id.password );
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Datils");
        confirmpassword = findViewById( R.id.confirmpassword);
        email = findViewById( R.id.remail );
        phone = findViewById( R.id.phone );
        /*-------------Perform Action Login Activity--------------*/
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lg = new Intent(Registration.this,Login.class);
                startActivity( lg );
            }
        });
        /*---------------------Register------------------------------*/
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  Username = username.getText().toString();
                String  Email = email.getText().toString();
                String   Phone = phone.getText().toString();
                String  Password = password.getText().toString();
                String Confirmpassword = confirmpassword.getText().toString();
                boolean check = validations( Username,Email,Phone,Password,Confirmpassword );
                if (check==true){
                    if (Password.equals( Confirmpassword )){
                        mAuth.createUserWithEmailAndPassword(Email,Password)
                                .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Userhelper userhelper = new Userhelper(Username,Phone,Email,Password,Confirmpassword);
                                            reference.child( Password ).setValue(userhelper);
                                            Intent in = new Intent(Registration.this,Dashboard.class);
                                            startActivity(in);
                                        } else {
                                            Toast.makeText( Registration.this,"Registration Failed !!",Toast.LENGTH_SHORT ).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });

    }
    /*--------------------Validation for Register--------------------*/
    private boolean validations(String Username, String Email, String Phone, String Password, String Confirmpassword) {
        if (TextUtils.isEmpty( Username )){
            username.requestFocus();
            username.setError( "Please enter username !!" );
            return false;
        }
        else if (TextUtils.isEmpty( Email )){
            email.requestFocus();
            email.setError( "Please enter email !!" );
            return false;
        }
        else if (TextUtils.isEmpty( Phone )){
            phone.requestFocus();
            phone.setError( "Enter phone number !!" );
            return false;
        }
        else if (TextUtils.isEmpty( Password )){
            password.requestFocus();
            password.setError( "Enter the password using at least special character: @!#$%^&*" );
            return false;
        }
       else if (!Password.matches( "^"+
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$"
        )){
            password.requestFocus();
            password.setError( "Enter at least one special charater" );
            return false;
        }


        else if (TextUtils.isEmpty( Confirmpassword )) {
            confirmpassword.requestFocus();
            confirmpassword.setError( "Enter Confirm Password" );
            return false;
        }
        else if (!Password.equals( Confirmpassword )){
            Toast.makeText( Registration.this,"Your password and confirmPassword not matched !!",Toast.LENGTH_SHORT ).show();
            return false;
        }
        else {
            Toast.makeText( Registration.this,"Your data successfully saved !!",Toast.LENGTH_SHORT ).show();
        }
        return true;
    }
}