package com.example.davidmautro.subjectsapp.API;

import com.example.davidmautro.subjectsapp.model.University;
import com.example.davidmautro.subjectsapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Davidmautro on 29/05/2016.
 */
public interface UniversityService {

    @GET("users/{id_user}/universities/")
    Call<List<University>> getUniversitiesByUserId(@Path("id_user") int idUser);
}
