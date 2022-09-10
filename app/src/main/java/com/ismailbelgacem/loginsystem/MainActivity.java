package com.ismailbelgacem.loginsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ismailbelgacem.loginsystem.Model.Login;
import com.ismailbelgacem.loginsystem.Model.ResponceLogin;
import com.ismailbelgacem.loginsystem.Model.User;
import com.ismailbelgacem.loginsystem.ViewModel.ViewMOdelSystem;

public class MainActivity extends AppCompatActivity {
   ViewMOdelSystem viewModelLogin,ViewModelRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        viewModelLogin = ViewModelProviders.of(this).get(ViewMOdelSystem.class);
        ViewModelRegister = ViewModelProviders.of(this).get(ViewMOdelSystem.class);
        Login login = new Login("iu@gmail.com","123456789");
        //Button btn = findViewById(R.id.button);

        User user =new User("ismail android","an14@gmail.com","123456789");
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelLogin.loginUser(login);
            }
        });*/
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new LoginFragment()).commit();
        //ViewModelRegister.regesterUser(user);
       viewModelLogin.getListMutableLiveData().observe(this, new Observer<ResponceLogin>() {
           @Override
           public void onChanged(ResponceLogin responceLogin) {
               Log.d("TAG", "onChanged: "+responceLogin.toString());
           }

       });
        ViewModelRegister.getListMutableLiveData2().observe(this, new Observer<ResponceLogin>() {
            @Override
            public void onChanged(ResponceLogin responceLogin) {
                Log.d("TAG", "onChanged: "+responceLogin.toString());
            }
        });
    }
}