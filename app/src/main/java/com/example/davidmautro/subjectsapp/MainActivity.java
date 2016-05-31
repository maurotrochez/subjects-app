package com.example.davidmautro.subjectsapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.UserService;
import com.example.davidmautro.subjectsapp.model.User;

import org.xml.sax.Parser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://10.0.2.2:5000/api/v1/";
    TextView txtVwExample;
//    UserService userService;
//    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwExample = (TextView) findViewById(R.id.txtEmail);
        String token;
        int idUser;
        SharedPreferences sp = getSharedPreferences("PersonalData", MODE_PRIVATE);

        token = sp.getString("token", "");
        idUser = sp.getInt("idUser", 0);
        txtVwExample.setText(String.valueOf(idUser));
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        userService = retrofit.create(UserService.class);
    }

    public void onClick(View v){
        UserService userService = ServiceGenerator.createService(UserService.class);
        int id = 1;
        Call<User> call = userService.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int status_code = response.code();
                User user = response.body();
                if (user != null)
                    txtVwExample.setText(user.getName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String a = "";
            }
        });
    }

}
