package com.example.davidmautro.subjectsapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.SubjectService;
import com.example.davidmautro.subjectsapp.model.Calification;
import com.example.davidmautro.subjectsapp.model.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListSubjectFragment extends Fragment implements ListView.OnItemClickListener{

    public static final String ID_SUBJECT = "id_subject";
    public static final String NAME_SUBJECT = "name_subject";
    ListView listView;
    List<Subject> subjects;
    public ListSubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_subject, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView)getView().findViewById(R.id.lstSubject);
        listView.setOnItemClickListener(this);
        Intent intent = getActivity().getIntent();

        int idSemester = intent.getIntExtra(SemesterActivity.ID_SEMESTER, 0);

        getSubjects(idSemester);
    }

    private void getSubjects(int idSemester) {
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Fetching Data","Please wait...",false,false);
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
                Toast.makeText(getActivity(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }

        });
    }

    private void showList() {
        listView.setAdapter(new AdapterSubjects(getActivity(), subjects));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), CalificationActivity.class);

        Subject subject = subjects.get(i);
        intent.putExtra(ID_SUBJECT,subject.getIdSubject());
        intent.putExtra(NAME_SUBJECT, subject.getName());
        startActivity(intent);
    }
}
