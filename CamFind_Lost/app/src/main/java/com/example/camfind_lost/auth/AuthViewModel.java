package com.example.camfind_lost.auth;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.camfind_lost.R;
import com.example.camfind_lost.uitil.UtilFunctions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Objects;

public class AuthViewModel {
    //reference to firebase auth
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser = mAuth.getCurrentUser();
    private String currentUserID;
    //reference to realtime database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myUserReference = database.getReference().child("Users");//.child(currentUserID);
    //reference to firebase storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference myProfileImageReference = storage.getReference().child("Profile Image");
    private StorageReference imageFilePath;
    private UtilFunctions utilFunctions = new UtilFunctions();

    private EditText email;
    private EditText password;
    private String emailStr, passwordStr, confirmPasswordStr, fullNameStr, userNmaeStr, phoneNumber01Str, phoneNumber02Str;
    private String profileImageDownloadUrl;
    private final String TAG = "com.example.camfind_lost.auth.AuthViewModel";

    public void authUserSignUp(@NonNull final View view){
        email = view.getRootView().findViewById(R.id.email_sign_up);
        password = view.getRootView().findViewById(R.id.password_sign_up);
        EditText confirmPassword = view.getRootView().findViewById(R.id.confirmPassword_sign_up);
        EditText fullName = view.getRootView().findViewById(R.id.fullName_sign_up);
        EditText userNmae = view.getRootView().findViewById(R.id.userName_sign_up);
        EditText phoneNumber01 = view.getRootView().findViewById(R.id.phoneNumber1_sign_up);
        EditText phoneNumber02 = view.getRootView().findViewById(R.id.phoneNumber2_sign_up);

        emailStr = email.getText().toString();
        passwordStr = password.getText().toString();
        confirmPasswordStr = confirmPassword.getText().toString();
        fullNameStr = fullName.getText().toString();
        userNmaeStr = userNmae.getText().toString();
        phoneNumber01Str = phoneNumber01.getText().toString();
        phoneNumber02Str = phoneNumber02.getText().toString();

        if(emailStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "enter your email");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            utilFunctions.toastMessage(view.getContext(), "enter a valid email address");
        }else if(fullNameStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "enter your full name");
        }else if(userNmaeStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "enter a username of your choice");
        }else if(phoneNumber01Str.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "enter your contact number");
        }else if(fullNameStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "enter additional contact number");
        }else if(passwordStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "enter your password");
        }else if(passwordStr.length()<5) {
            utilFunctions.toastMessage(view.getContext(), "password length should be at least 6 characters");
        }else if(confirmPasswordStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "confirm your password");
        }else if(!passwordStr.equals(confirmPasswordStr)){
            utilFunctions.toastMessage(view.getContext(), "your passwords do not match");
        }else{
            mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @SuppressLint("LongLogTag")
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //function to setup account info after creating account auth
                        setUpUserAccout(view, fullNameStr, userNmaeStr, emailStr, phoneNumber01Str, phoneNumber02Str);
                        Log.d(TAG, "createUserWithEmailAndPassword : SUCCESS");
                    }else{
                        Log.d(TAG, "createUserWithEmailAndPassword : FAILURE", task.getException());
                        utilFunctions.toastMessage(view.getContext(), "Failed to create account \n" + task.getException().toString());
                    }
                }
            });
        }
    }

    private void setUpUserAccout(final View view, String fullNameStr, String userNmaeStr, String emailStr, String phoneNumber01Str, String phoneNumber02Str) {
        HashMap userHashMapt = new HashMap();
        userHashMapt.put("fullName", fullNameStr);
        userHashMapt.put("userName", userNmaeStr);
        userHashMapt.put("email", emailStr);
        userHashMapt.put("phoneNumber01", phoneNumber01Str);
        userHashMapt.put("phoneNumber02", phoneNumber02Str);
        if(currentUser != null){
            currentUserID = currentUser.getUid();
            myUserReference.child(currentUserID).updateChildren(userHashMapt).addOnCompleteListener(new OnCompleteListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "update children using HashMap : SUCCESS");
                        utilFunctions.toastMessage(view.getContext(), "your account created successfully");
                        utilFunctions.gotoBottomNavigationActivity(view.getContext());
                    }else{
                        Log.d(TAG, "update children using HashMap : FAILURE", task.getException());
                        utilFunctions.toastMessage(view.getContext(), "FAILURE \n" + Objects.requireNonNull(task.getException()).toString());
                    }
                }
            });
        }

    }

    public void authUserSignIn(@NonNull final View view){
        email = view.getRootView().findViewById(R.id.email_signIn);
        password = view.getRootView().findViewById(R.id.password_signIn);

        emailStr = email.getText().toString();
        passwordStr = password.getText().toString();

        if(emailStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "Enter email address");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            utilFunctions.toastMessage(view.getContext(), "Your email address is not correct");
        }else if(passwordStr.trim().isEmpty()){
            utilFunctions.toastMessage(view.getContext(), "Enter your password");
        }else if(passwordStr.length()<5){
            utilFunctions.toastMessage(view.getContext(), "enter correct password");
        }else{
            mAuth.signInWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @SuppressLint("LongLogTag")
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "createUserWithEmailAndPassword : SUCCESS");
                        utilFunctions.toastMessage(view.getContext(),  "You logged In successfully");
                        utilFunctions.gotoBottomNavigationActivity(view.getContext());
                    }else{
                        Log.d(TAG, "createUserWithEmailAndPassword : FAILURE", task.getException());
                        utilFunctions.toastMessage(view.getContext(),  "Failed to logged In\n"+ task.getException().toString());
                    }

                }
            });
        }
    }
}
