package com.example.camfind_lost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.camfind_lost.auth.AuthViewModel;
import com.example.camfind_lost.uitil.UtilFunctions;

public class SignInActivity extends AppCompatActivity {
    private UtilFunctions utilFunctions;
    private AuthViewModel authViewModel;
    Intent intent;
    private TextView signIn, goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        utilFunctions = new UtilFunctions();
        authViewModel = new AuthViewModel();

        signIn = findViewById(R.id.sign_In_TV);
        goBack = findViewById(R.id.back_signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authViewModel.authUserSignIn(v);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilFunctions.gotoMainActivity(getApplicationContext());
            }
        });
    }

    private void setSignIn(){
    }
}
