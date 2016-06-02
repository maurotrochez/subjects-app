package com.example.davidmautro.subjectsapp;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.SubjectService;
import com.example.davidmautro.subjectsapp.API.UniversityService;
import com.example.davidmautro.subjectsapp.model.Subject;
import com.example.davidmautro.subjectsapp.model.University;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUniversityFragment extends Fragment implements View.OnClickListener{

    EditText edtTxtUniversityName;
    int idUser;
    public AddUniversityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_university, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.btnSaveUniversity).setOnClickListener(this);
        edtTxtUniversityName = (EditText) getView().findViewById(R.id.edtTxtAddUniversityName);
        SharedPreferences sp = getActivity().getSharedPreferences("PersonalData", getActivity().MODE_PRIVATE);
        idUser = sp.getInt("idUser", 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveUniversity:
                saveUniversity(idUser);
                break;
        }
    }

    private void saveUniversity(int idUser) {
        University university = new University();
        university.setName(edtTxtUniversityName.getText().toString());
        university.setIdUser(idUser);
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Saving Data","Please wait...",false,false);
        UniversityService universityService = ServiceGenerator.createService(UniversityService.class);
        Call<University> call = universityService.save(university);
        call.enqueue(new Callback<University>() {
            @Override
            public void onResponse(Call<University> call, Response<University> response) {
                int status_code = response.code();
                if (status_code == 201) {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Subject saved!", Toast.LENGTH_SHORT).show();
                    edtTxtUniversityName.setText("");
                }
                else{
                    Toast.makeText(getActivity(), "Internal server error", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
                //txtVwExample.setText(user.getName());
            }

            @Override
            public void onFailure(Call<University> call, Throwable t) {
                Toast.makeText(getActivity(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
