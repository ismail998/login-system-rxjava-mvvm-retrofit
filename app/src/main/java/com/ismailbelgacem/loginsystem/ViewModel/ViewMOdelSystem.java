package com.ismailbelgacem.loginsystem.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ismailbelgacem.loginsystem.Data.CatygoryClient;
import com.ismailbelgacem.loginsystem.Data.LoginClient;
import com.ismailbelgacem.loginsystem.Data.RegisterClient;
import com.ismailbelgacem.loginsystem.Model.Catygory;
import com.ismailbelgacem.loginsystem.Model.Login;
import com.ismailbelgacem.loginsystem.Model.ResponceLogin;
import com.ismailbelgacem.loginsystem.Model.User;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMOdelSystem extends ViewModel  {
    public MutableLiveData<ResponceLogin> listMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ResponceLogin> listMutableLiveData2 = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Catygory>> catygorylist = new MutableLiveData<>();
    public MutableLiveData<String> logintoken = new MutableLiveData<>();

    public void setListMutableLiveData(MutableLiveData<ResponceLogin> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public void setListMutableLiveData2(MutableLiveData<ResponceLogin> listMutableLiveData2) {
        this.listMutableLiveData2 = listMutableLiveData2;
    }

    public void setLogintoken(MutableLiveData<String> logintoken) {
        this.logintoken = logintoken;
    }

    public MutableLiveData<ResponceLogin> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public MutableLiveData<ResponceLogin> getListMutableLiveData2() {
        return listMutableLiveData2;
    }

    public MutableLiveData<String> getLogintoken() {
        return logintoken;
    }

    public void loginUser(Login login){
        Observable<ResponceLogin> observable= LoginClient.getINSTANCE().loginUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<ResponceLogin> observer =new Observer<ResponceLogin>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("TAG", "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull ResponceLogin responceLogin) {
                listMutableLiveData2.setValue(responceLogin);
                listMutableLiveData.setValue(responceLogin);
                Log.d("TAG", "onNext: "+responceLogin.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG", "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {
            Log.d("TAG","onComplete");
            logintoken.postValue(listMutableLiveData.getValue().getToken());
            }
        };
        observable.subscribe(observer);

    }
    public void regesterUser(User user){
       Observable<ResponceLogin> observable= RegisterClient.getINSTANCE().regesterUser(user)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());
        Observer<ResponceLogin> observer = new Observer<ResponceLogin>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("TAG", "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull ResponceLogin responceLogin) {
                listMutableLiveData2.setValue(responceLogin);
                Log.d("TAG", "onNext: "+responceLogin.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG", "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: ");
            }
        };

       observable.subscribe(observer);
    }
    public void getAllCatygry(){
        Observable<ArrayList<Catygory>> observable= CatygoryClient.getINSTANCE().getAllCatygory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<ArrayList<Catygory>> observer= new Observer<ArrayList<Catygory>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<Catygory> catygories) {
               catygorylist.postValue(catygories);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG", "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}
