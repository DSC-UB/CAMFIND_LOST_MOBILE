package com.example.camfind_lost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    Intent intent;
    private TextView signUp, goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = findViewById(R.id.sign_Up_TV);
        goBack = findViewById(R.id.back_signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSignUp();
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGoBack();
            }
        });
    }

    private void setGoBack() {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setSignUp() {
        intent = new Intent(this, BottomNavigationActivity.class);
        startActivity(intent);
    }
}
