package com.example.taskplanner2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taskplanner2.Login.LoginWrapper;
import com.example.taskplanner2.Login.Token;
import com.example.taskplanner2.Service.AuthService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private AuthService authService;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:8080/taskPlanner/") //localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(AuthService.class);
    }


    public void onLoginAction(View view) throws IOException {
        final EditText userText = (EditText) findViewById(R.id.User2);
        final String userInfo = userText.getText().toString().trim();
        final EditText passText = (EditText) findViewById(R.id.passID);
        final String passInfo = passText.getText().toString().trim();

        if (userInfo.equals("")) {
            userText.setError("You must enter your email.");
            return;
        }
        if (passInfo.equals("")) {
            passText.setError("You must enter your password.");
            return;
        }
        final LoginActivity loginActivity = this;

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(loginActivity,MainActivity.class);
                Response<Token> response = null;
                try {
                    response = authService.loginUser(new LoginWrapper(userInfo,passInfo)).execute();

                    Token authToken = response.body();
                    if (authToken == null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView textViewErrorMessage = findViewById(R.id.User2);
                                textViewErrorMessage.setText("you must verify the credentials");
                            }
                        });
                        return;
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.TOKEN_KEY), authToken.getToken());
                    editor.commit();

                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}

