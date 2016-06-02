package com.example.davidmautro.subjectsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.SemesterService;
import com.example.davidmautro.subjectsapp.API.UniversityService;
import com.example.davidmautro.subjectsapp.model.Semester;
import com.example.davidmautro.subjectsapp.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SemesterActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String ID_SEMESTER = "id_semester";
    ListView lstVwSemester;
    List<Semester> semesters;
    Fragment fragmentListSemester, fragmentAddSemester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);
        findViewById(R.id.btnAddSemester).setOnClickListener(this);
        fragmentListSemester = new ListSemesterFragment();
        fragmentAddSemester = new AddSemesterFragment();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.containerSemesters, fragmentListSemester, "listSemesters").commit();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddSemester:
                if(getSupportFragmentManager().findFragmentByTag("listSemesters").isVisible()){
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerSemesters, fragmentAddSemester, "addSemester").addToBackStack(null).commit();
                }
        }
    }
}
