package com.example.davidmautro.subjectsapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.SubjectService;
import com.example.davidmautro.subjectsapp.API.UserService;
import com.example.davidmautro.subjectsapp.model.Subject;
import com.example.davidmautro.subjectsapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSubjectFragment extends Fragment implements View.OnClickListener {

    int idSemester;
    EditText edtTxtCode, edtTxtName, edtTxtTeacher;
    public AddSubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_subject, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.btnSaveSubject).setOnClickListener(this);
        edtTxtCode = (EditText) getView().findViewById(R.id.edtTxtSubjectCode);
        edtTxtName = (EditText) getView().findViewById(R.id.edtTxtAddSubjectName);
        edtTxtTeacher = (EditText) getView().findViewById(R.id.edtTxtAddSubjectTeacher);

        Intent intent = getActivity().getIntent();

        idSemester = intent.getIntExtra(SemesterActivity.ID_SEMESTER, 0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveSubject:
                saveSubject(idSemester);
        }
    }

    private void saveSubject(int idSemester) {
        Subject subject = new Subject();
        subject.setCode(edtTxtCode.getText().toString());
        subject.setName(edtTxtName.getText().toString());
        subject.setTeacher(edtTxtTeacher.getText().toString());
        subject.setIdSemester(idSemester);
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Saving Data","Please wait...",false,false);
        SubjectService subjectService = ServiceGenerator.createService(SubjectService.class);
        Call<Subject> call = subjectService.save(subject);
        call.enqueue(new Callback<Subject>() {
            @Override
            public void onResponse(Call<Subject> call, Response<Subject> response) {
                int status_code = response.code();
                if (status_code == 201) {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Subject saved!", Toast.LENGTH_SHORT).show();
                    edtTxtName.setText("");
                    edtTxtCode.setText("");
                    edtTxtTeacher.setText("");
                }
                else{
                    Toast.makeText(getActivity(), "Internal server error", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
                //txtVwExample.setText(user.getName());
            }

            @Override
            public void onFailure(Call<Subject> call, Throwable t) {
                Toast.makeText(getActivity(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
