package com.example.davidmautro.subjectsapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.UniversityService;
import com.example.davidmautro.subjectsapp.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListUniversityFragment extends Fragment implements ListView.OnItemClickListener{

    public static final String ID_UNIVERSITY = "id_university";
    ListView lstVwUniversities;
    List<University> universities;
    public ListUniversityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_university, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lstVwUniversities = (ListView) getView().findViewById(R.id.lstUniversities);
        lstVwUniversities.setOnItemClickListener(this);
        int idUser;
        SharedPreferences sp = getActivity().getSharedPreferences("PersonalData", getActivity().MODE_PRIVATE);
        idUser = sp.getInt("idUser", 0);
        getUniversities(idUser);
    }

    private void getUniversities(int idUser) {
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Fetching Data","Please wait...",false,false);
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
                Toast.makeText(getActivity(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }

        });
    }

    private void showList(){
        String[] items = new String[universities.size()];

        for(int i=0; i<universities.size(); i++){
            items[i] = universities.get(i).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);

        lstVwUniversities.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), SemesterActivity.class);

        University university = universities.get(i);
        intent.putExtra(ID_UNIVERSITY,university.getIdUniversity());

        startActivity(intent);
    }
}
