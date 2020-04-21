package com.example.camfind_lost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.camfind_lost.uitil.UtilFunctions;

public class MainActivity extends AppCompatActivity {
    private UtilFunctions utilFunctions;
    private TextView gotoSignIn, gotoSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utilFunctions = new UtilFunctions();

        gotoSignIn = findViewById(R.id.go_to_signIn);
        gotoSignUp = findViewById(R.id.go_to_signUp);

        gotoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilFunctions.gotoSignInActitvity(getApplicationContext());
            }
        });

        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilFunctions.gotoSignUpActivity(getApplicationContext());
            }
        });
    }
}
