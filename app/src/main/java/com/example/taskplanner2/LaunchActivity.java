package com.example.taskplanner2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {
    public static final String TOKEN_KEY = "TOKEN_KEY";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if(sharedPreferences.contains(TOKEN_KEY)){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
