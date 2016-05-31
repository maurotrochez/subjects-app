package com.example.davidmautro.subjectsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.CalificationService;
import com.example.davidmautro.subjectsapp.API.SubjectService;
import com.example.davidmautro.subjectsapp.model.Calification;
import com.example.davidmautro.subjectsapp.model.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalificationActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtTxtNote1,edtTxtNote2,edtTxtNote3,edtTxtNoteAdd;
    TextView txtVwFinalNote, txtVwNoteToCalificate;
    Calification calification;
    int idSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calification);

        findViewById(R.id.saveCalification).setOnClickListener(this);

        edtTxtNote1 = (EditText)findViewById(R.id.edtTxtNote1);
        edtTxtNote2 = (EditText)findViewById(R.id.edtTxtNote2);
        edtTxtNote3 = (EditText)findViewById(R.id.edtTxtNote3);
        edtTxtNoteAdd = (EditText)findViewById(R.id.edtTxtNoteAdd);
        txtVwFinalNote = (TextView) findViewById(R.id.txtVwFinalNote);
        txtVwNoteToCalificate = (TextView) findViewById(R.id.txtVwSubjectToCalificate);

        Intent intent = getIntent();
        idSubject = intent.getIntExtra(ListSubjectFragment.ID_SUBJECT, 0);
        String nameSubject = intent.getStringExtra(ListSubjectFragment.NAME_SUBJECT);
        txtVwNoteToCalificate.setText(nameSubject);
        getCalification(idSubject);
    }

    private void getCalification(int idSubject) {
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
        CalificationService calificationService = ServiceGenerator.createService(CalificationService.class);
        Call<Calification> call = calificationService.getCalificationBySubject(idSubject);
        call.enqueue(new Callback<Calification>() {
            @Override
            public void onResponse(Call<Calification> call, Response<Calification> response) {
                loading.dismiss();
                calification = response.body();
                if (calification != null){
                    edtTxtNote1.setText(calification.getNote1().toString(), TextView.BufferType.EDITABLE);
                    edtTxtNote2.setText(calification.getNote2().toString(), TextView.BufferType.EDITABLE);
                    edtTxtNote3.setText(calification.getNote3().toString(), TextView.BufferType.EDITABLE);
                    edtTxtNoteAdd.setText(calification.getNoteAdd().toString(), TextView.BufferType.EDITABLE);
                    txtVwFinalNote.setText(calification.getFinalNote().toString(), TextView.BufferType.EDITABLE);
                }
            }

            @Override
            public void onFailure(Call<Calification> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveCalification:
                saveCalification(idSubject);
        }
    }

    private void saveCalification(int idSubject) {
        Calification calification= new Calification();
        calification.setNote1(Double.parseDouble(edtTxtNote1.getText().toString()));
        calification.setNote2(Double.parseDouble(edtTxtNote2.getText().toString()));
        calification.setNote3(Double.parseDouble(edtTxtNote3.getText().toString()));
        calification.setNoteAdd(Double.parseDouble(edtTxtNoteAdd.getText().toString()));
        calification.setFinalNote(Double.parseDouble(txtVwFinalNote.getText().toString()));
        calification.setIdSubject(idSubject);

        final ProgressDialog loading = ProgressDialog.show(this,"Saving Data","Please wait...",false,false);

        CalificationService calificationService = ServiceGenerator.createService(CalificationService.class);
        Call<Calification> call = calificationService.save(calification);
        call.enqueue(new Callback<Calification>() {
            @Override
            public void onResponse(Call<Calification> call, Response<Calification> response) {
                int status_code = response.code();
                if (status_code == 201) {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Subject saved!", Toast.LENGTH_SHORT).show();
                    edtTxtNote1.setText("0",TextView.BufferType.EDITABLE);
                    edtTxtNote2.setText("0",TextView.BufferType.EDITABLE);
                    edtTxtNote3.setText("0",TextView.BufferType.EDITABLE);
                    edtTxtNoteAdd.setText("0",TextView.BufferType.EDITABLE);
                    txtVwFinalNote.setText("0",TextView.BufferType.EDITABLE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Internal server error", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
                //txtVwExample.setText(user.getName());
            }

            @Override
            public void onFailure(Call<Calification> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error try to connect to server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
