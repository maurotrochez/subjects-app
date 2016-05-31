package com.example.davidmautro.subjectsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidmautro.subjectsapp.model.Subject;

import java.util.List;

/**
 * Created by Davidmautro on 30/05/2016.
 */
public class AdapterSubjects extends BaseAdapter {

    private final List<Subject> lista;
    private final Activity actividad;

    public AdapterSubjects(Activity activity, List<Subject> lista){
        super();
        this.lista = lista;
        this.actividad = activity;
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_subject_list, null, true);

        //Nombre de la persona
        TextView textView  = (TextView)view.findViewById(R.id.txtVwSubjectName);
        textView.setText(lista.get(i).getName());

        //Edad de la persona
        TextView textViewTeacher = (TextView)view.findViewById(R.id.txtVwSubjectTeacher);
        textViewTeacher.setText(lista.get(i).getTeacher());

        return view;
    }
}
