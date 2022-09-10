package com.ismailbelgacem.loginsystem.Data;

import com.ismailbelgacem.loginsystem.Model.Login;
import com.ismailbelgacem.loginsystem.Model.ResponceLogin;
import com.ismailbelgacem.loginsystem.Model.User;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginClient {
    private static final String BaseUrl="http://192.168.1.102/loginsystem/public/api/";
    private SystemInterFace systemInterFace;
    private static LoginClient INSTANCE;
    public LoginClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        systemInterFace = retrofit.create(SystemInterFace.class);
    }
    public static LoginClient getINSTANCE() {
        if (INSTANCE==null){
            INSTANCE =new LoginClient();
        }
        return INSTANCE;
    }
    public Observable<ResponceLogin> loginUser(Login login){
        return systemInterFace.loginUser(login);
    }
}
