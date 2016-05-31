package com.example.davidmautro.subjectsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class UniversityActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    public static final String ID_UNIVERSITY = "id_university";
    ListView lstVwUniversities;
    List<University> universities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        lstVwUniversities = (ListView) findViewById(R.id.lstUniversities);
        lstVwUniversities.setOnItemClickListener(this);
        int idUser;
        SharedPreferences sp = getSharedPreferences("PersonalData", MODE_PRIVATE);
        idUser = sp.getInt("idUser", 0);
        getUniversities(idUser);
    }

    private void getUniversities(int idUser) {
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
        UniversityService universityService = ServiceGenerator.createService(UniversityService.class);
        Call<List<University>> call = universityService.getUniversitiesByUserId(idUser);
        call.enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {
                loading.dismiss();
                universities = response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }

        });
    }

    private void showList(){
        String[] items = new String[universities.size()];

        for(int i=0; i<universities.size(); i++){
            items[i] = universities.get(i).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);

        lstVwUniversities.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Intent intent = new Intent(this, SemesterActivity.class);

        University university = universities.get(i);
        intent.putExtra(ID_UNIVERSITY,university.getIdUniversity());

        startActivity(intent);
    }
}
