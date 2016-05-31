package com.example.davidmautro.subjectsapp.API;

import com.example.davidmautro.subjectsapp.model.Subject;
import com.example.davidmautro.subjectsapp.model.Token;
import com.example.davidmautro.subjectsapp.model.University;
import com.example.davidmautro.subjectsapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Davidmautro on 29/05/2016.
 */
public interface SubjectService {

    @GET("semesters/{id_semester}/subjects/")
    Call<List<Subject>> getSubjectsBySemester(@Path("id_semester") int idSemester);

    @POST("subjects")
    Call<Subject> save(@Body Subject subject);
}
