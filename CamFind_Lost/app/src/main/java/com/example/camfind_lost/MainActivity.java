package com.example.camfind_lost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView gotoSignIn, gotoSignUp;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoSignIn = findViewById(R.id.go_to_signIn);
        gotoSignUp = findViewById(R.id.go_to_signUp);

        gotoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGotoSignIn();
            }
        });

        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGotoSignUp();
            }
        });
    }

    public void setGotoSignIn(){
        intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
    public void setGotoSignUp(){
        intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}
