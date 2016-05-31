package com.example.davidmautro.subjectsapp.API;

import com.example.davidmautro.subjectsapp.model.Token;
import com.example.davidmautro.subjectsapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Davidmautro on 27/05/2016.
 */
public interface UserService {

    @GET("users/{id_user}")
    Call<User> getUser(@Path("id_user") int idUser);

    @POST("users/")
    Call<User> createUser(@Body User user);

    @POST("validate_token")
    Call<User> validateToken(@Body Token token);
}
