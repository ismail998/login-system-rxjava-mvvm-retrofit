package com.ismailbelgacem.loginsystem.Data;

import com.ismailbelgacem.loginsystem.Model.Catygory;
import com.ismailbelgacem.loginsystem.Model.Login;
import com.ismailbelgacem.loginsystem.Model.ResponceLogin;
import com.ismailbelgacem.loginsystem.Model.User;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SystemInterFace {
    @POST("auth/regester")
    Observable<ResponceLogin> regesterUser(@Body User user);
    @POST("auth/token")
    Observable<ResponceLogin> loginUser(@Body Login login);
    @GET("Categories/index")
    Observable<ArrayList<Catygory>> getAllCatygory();
}
