package com.company.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button loginButton;
    TextView mCreateButton,mForgetPasword;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    // public void  logout (View view){
       // FirebaseAuth .getInstance().signOut();
       //  startActivity(new Intent(getApplicationContext(),LoginActivity.class));
         //finish();  }


        mEmail= findViewById(R.id.emailLogin);
        mPassword=findViewById(R.id.passwordLogin);
        loginButton=findViewById(R.id.buttonLogin);
        mCreateButton=findViewById(R.id.cerateTextLogin);
        progressBar=findViewById(R.id.progressBarLogin);
        firebaseAuth=FirebaseAuth.getInstance();
        mForgetPasword=findViewById(R.id.forgetPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password= mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password id Required");
                    return;
                }

                if(password.length()< 6 ){
                    mPassword.setError("Pasword must be > = 6");
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this,"Logged in Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }

                        else {

                            Toast.makeText(MainActivity.this,"ERROR"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


        mCreateButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               FirebaseAuth .getInstance().signOut();
             startActivity(new Intent(getApplicationContext(),RegisterActiviity.class));
            }
       });



       // mCreateButton.setOnClickListener((v) -> {
         //           startActivity(new Intent(getApplicationContext(),RegisterActiviity.class));
            //    });

        mForgetPasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth .getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),ResetPassword.class));

            }
        });

    }

}