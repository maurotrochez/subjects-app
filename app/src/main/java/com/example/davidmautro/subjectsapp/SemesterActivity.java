package com.example.davidmautro.subjectsapp;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class SemesterActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    public static final String ID_SEMESTER = "id_semester";
    ListView lstVwSemester;
    List<Semester> semesters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        lstVwSemester = (ListView) findViewById(R.id.lstSemesters);
        lstVwSemester.setOnItemClickListener(this);
        Intent intent = getIntent();

        int idUniversity = intent.getIntExtra(UniversityActivity.ID_UNIVERSITY, 0);

        getSemesters(idUniversity);
    }

    private void getSemesters(int idUniversity) {
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
        SemesterService semesterService = ServiceGenerator.createService(SemesterService.class);
        Call<List<Semester>> call = semesterService.getSemestersByUniversity(idUniversity);
        call.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                loading.dismiss();
                semesters = response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }

        });
    }

    private void showList(){
        String[] items = new String[semesters.size()];

        for(int i=0; i<semesters.size(); i++){
            items[i] = semesters.get(i).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);

        lstVwSemester.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, SubjectActivity.class);

        Semester semester= semesters.get(i);
        intent.putExtra(ID_SEMESTER,semester.getIdSemester());

        startActivity(intent);
    }
}
