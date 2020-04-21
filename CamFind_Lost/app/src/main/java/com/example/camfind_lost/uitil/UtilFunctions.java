package com.example.camfind_lost.uitil;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.textclassifier.ConversationActions;
import android.widget.Toast;

import com.example.camfind_lost.BottomNavigationActivity;
import com.example.camfind_lost.MainActivity;
import com.example.camfind_lost.SignInActivity;
import com.example.camfind_lost.SignUpActivity;

public class UtilFunctions {
    Intent intent;

    public UtilFunctions(){

    }

    public void toastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void gotoMainActivity(Context context){
        intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void gotoSignInActitvity(Context context){
        intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    public void gotoSignUpActivity(Context context){
        intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public void gotoBottomNavigationActivity(Context context){
        intent = new Intent(context, BottomNavigationActivity.class);
        context.startActivity(intent);
    }

    public void ProgessBar(Context context, String string){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle(string);
    }
}
