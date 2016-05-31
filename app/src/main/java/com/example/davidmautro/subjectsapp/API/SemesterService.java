package com.example.davidmautro.subjectsapp.API;

import com.example.davidmautro.subjectsapp.model.Semester;
import com.example.davidmautro.subjectsapp.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Davidmautro on 29/05/2016.
 */
public interface SemesterService {

    @GET("universities/{id_university}/semesters/")
    Call<List<Semester>> getSemestersByUniversity(@Path("id_university") int idUniversity);
}
