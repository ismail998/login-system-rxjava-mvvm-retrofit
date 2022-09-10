package com.ismailbelgacem.loginsystem.Data;

import com.ismailbelgacem.loginsystem.Model.ResponceLogin;
import com.ismailbelgacem.loginsystem.Model.User;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterClient {
    private static final String BaseUrl="http://192.168.1.102/loginsystem/public/api/";
    private SystemInterFace systemInterFace;
    private static RegisterClient INSTANCE;
    public RegisterClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        systemInterFace = retrofit.create(SystemInterFace.class);
    }
    public static RegisterClient getINSTANCE() {
        if (INSTANCE==null){
            INSTANCE =new RegisterClient();
        }
        return INSTANCE;
    }
    public Observable<ResponceLogin> regesterUser(User user){
        return systemInterFace.regesterUser(user);
    }
}
