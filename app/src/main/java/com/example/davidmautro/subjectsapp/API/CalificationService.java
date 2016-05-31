package com.example.davidmautro.subjectsapp.API;

import com.example.davidmautro.subjectsapp.model.Calification;
import com.example.davidmautro.subjectsapp.model.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Davidmautro on 29/05/2016.
 */
public interface CalificationService {
    @GET("subjects/{id_subject}/califications/")
    Call<Calification> getCalificationBySubject(@Path("id_subject") int idSubject);

    @POST("califications")
    Call<Calification> save(@Body Calification calification);
}
