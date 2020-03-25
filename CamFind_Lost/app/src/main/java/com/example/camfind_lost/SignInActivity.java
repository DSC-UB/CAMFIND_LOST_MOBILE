package com.example.camfind_lost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    Intent intent;
    private TextView signIn, goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.sign_In_TV);
        goBack = findViewById(R.id.back_signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSignIn();
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGoBack();
            }
        });
    }

    public void setGoBack(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void setSignIn(){
        intent = new Intent(this, BottomNavigationActivity.class);
        startActivity(intent);
    }
}
