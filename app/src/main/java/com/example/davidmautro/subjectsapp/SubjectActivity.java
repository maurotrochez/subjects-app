package com.example.davidmautro.subjectsapp;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.SemesterService;
import com.example.davidmautro.subjectsapp.API.SubjectService;
import com.example.davidmautro.subjectsapp.model.Calification;
import com.example.davidmautro.subjectsapp.model.Semester;
import com.example.davidmautro.subjectsapp.model.Subject;
import com.example.davidmautro.subjectsapp.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubjectActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String ID_SUBJECT = "id_subject";
    ListView lstVwSubjects;
    List<Subject> subjects;
    Fragment fragmentListSubject, fragmentAddSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        findViewById(R.id.btnAddSubject).setOnClickListener(this);
        fragmentListSubject = new ListSubjectFragment();
        fragmentAddSubject = new AddSubjectFragment();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.containerSubjects, fragmentListSubject, "listSubjects").commit();
        }
//        Intent intent = getIntent();
//
//        int idSemester = intent.getIntExtra(SemesterActivity.ID_SEMESTER, 0);
//
//        lstVwSubjects= (ListView)findViewById(R.id.lstSubject);
//        lstVwSubjects.setOnItemClickListener(this);
//
//        getSubjects(idSemester);

        //lstVwSubjects.setAdapter(new AdapterSubjects(this, getSubjects(idSemester)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddSubject:
                if(getSupportFragmentManager().findFragmentByTag("listSubjects").isVisible()){
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerSubjects, fragmentAddSubject, "addSubject").addToBackStack(null).commit();
                }
        }
    }

    /*private void getSubjects(int idSemester) {
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
        SubjectService subjectService = ServiceGenerator.createService(SubjectService.class);
        Call<List<Subject>> call = subjectService.getSubjectsBySemester(idSemester);
        call.enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                loading.dismiss();
                subjects = response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }

        });
    }

    private void showList() {
        lstVwSubjects.setAdapter(new AdapterSubjects(this, subjects));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, Calification.class);

        Subject subject = subjects.get(i);
        intent.putExtra(ID_SUBJECT,subject.getIdSubject());

        startActivity(intent);
    }*/
}
