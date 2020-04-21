package com.example.camfind_lost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.camfind_lost.auth.AuthViewModel;
import com.example.camfind_lost.uitil.UtilFunctions;

public class SignUpActivity extends AppCompatActivity {
    private UtilFunctions utilFunctions;
    private AuthViewModel authViewModel;
    private TextView signUp, goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        utilFunctions = new UtilFunctions();
        authViewModel = new AuthViewModel();

        signUp = findViewById(R.id.sign_Up_TV);
        goBack = findViewById(R.id.back_signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authViewModel.authUserSignUp(v);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilFunctions.gotoMainActivity(getApplicationContext());
            }
        });
    }
}
