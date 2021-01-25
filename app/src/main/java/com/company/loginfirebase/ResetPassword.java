package com.company.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPassword extends AppCompatActivity {

    Button mForgetPasword;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    EditText resetMailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        mForgetPasword=findViewById(R.id.forgetPasswordButton);
        resetMailText=findViewById(R.id.sendEmailText);

        mForgetPasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String userEmail=resetMailText.getText().toString();
              firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(ResetPassword.this,"Email'nize şifre yolladı ",Toast.LENGTH_LONG).show();
                      }

                  }
              });
              finish();

            }
        });


    }
}
