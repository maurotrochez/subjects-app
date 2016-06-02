package com.example.davidmautro.subjectsapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.SemesterService;
import com.example.davidmautro.subjectsapp.API.SubjectService;
import com.example.davidmautro.subjectsapp.model.Semester;
import com.example.davidmautro.subjectsapp.model.Subject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSemesterFragment extends Fragment implements View.OnClickListener{

    int idUniversity;
    EditText edtTxtSemesterCode, edtTxtSemesterName;
    public AddSemesterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_semester, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.btnSaveSemester).setOnClickListener(this);

        edtTxtSemesterCode = (EditText) getView().findViewById(R.id.edtTxtAddSemesterCode);
        edtTxtSemesterName = (EditText) getView().findViewById(R.id.edtTxtAddSemesterName);

        Intent intent = getActivity().getIntent();

        idUniversity = intent.getIntExtra(UniversityActivity.ID_UNIVERSITY, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveSemester:
                saveSemester(idUniversity);
                break;
        }
    }

    private void saveSemester(int idUniversity) {
        Semester semester = new Semester();
        semester.setCode(edtTxtSemesterCode.getText().toString());
        semester.setName(edtTxtSemesterName.getText().toString());
        semester.setIdUniversity(idUniversity);
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Saving Data","Please wait...",false,false);
        SemesterService semesterService = ServiceGenerator.createService(SemesterService.class);
        Call<Semester> call = semesterService.save(semester);
        call.enqueue(new Callback<Semester>() {
            @Override
            public void onResponse(Call<Semester> call, Response<Semester> response) {
                int status_code = response.code();
                if (status_code == 201) {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Subject saved!", Toast.LENGTH_SHORT).show();
                    edtTxtSemesterCode.setText("");
                    edtTxtSemesterName.setText("");
                }
                else{
                    Toast.makeText(getActivity(), "Internal server error", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
                //txtVwExample.setText(user.getName());
            }

            @Override
            public void onFailure(Call<Semester> call, Throwable t) {
                Toast.makeText(getActivity(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
