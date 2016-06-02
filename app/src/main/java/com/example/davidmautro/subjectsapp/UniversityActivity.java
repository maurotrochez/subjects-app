package com.example.davidmautro.subjectsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.UniversityService;
import com.example.davidmautro.subjectsapp.API.UserService;
import com.example.davidmautro.subjectsapp.model.Semester;
import com.example.davidmautro.subjectsapp.model.University;
import com.example.davidmautro.subjectsapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UniversityActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String ID_UNIVERSITY = "id_university";
    ListView lstVwUniversities;
    List<University> universities;
    Fragment fragmentListUniversities, fragmentAddUniversity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        findViewById(R.id.btnAddUniversity).setOnClickListener(this);
        fragmentListUniversities = new ListUniversityFragment();
        fragmentAddUniversity = new AddUniversityFragment();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.containerUniversitites, fragmentListUniversities, "listUniversities").commit();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddUniversity:
                if(getSupportFragmentManager().findFragmentByTag("listUniversities").isVisible()){
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerUniversitites, fragmentAddUniversity, "addUniversity").addToBackStack(null).commit();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }
}
