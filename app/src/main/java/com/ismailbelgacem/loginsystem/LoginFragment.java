package com.ismailbelgacem.loginsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ismailbelgacem.loginsystem.Model.Login;
import com.ismailbelgacem.loginsystem.Model.ResponceLogin;
import com.ismailbelgacem.loginsystem.ViewModel.ViewMOdelSystem;


public class LoginFragment extends Fragment {
    TextView signup;
    Button login;
    EditText email,pass;
    ViewMOdelSystem viewModelLogin;
    public LoginFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_login, container, false);
        email=view.findViewById(R.id.email);
        login=view.findViewById(R.id.btnlogin);
        pass=view.findViewById(R.id.pass);
        signup=view.findViewById(R.id.signin);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new RegisterFragment()).commit();
            }
        });
        viewModelLogin = ViewModelProviders.of(this).get(ViewMOdelSystem.class);
        viewModelLogin.getListMutableLiveData().observe(getActivity(), new Observer<ResponceLogin>() {
            @Override
            public void onChanged(ResponceLogin responceLogin) {
                Intent intent = new Intent(getActivity(),MainApp.class);
                intent.putExtra("token",responceLogin.getToken());
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekinginput();
            }
        });
        return view;
    }
    private void chekinginput() {
        if(TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(pass.getText().toString())){
            Toast.makeText(getActivity(),"text is empty email or password",Toast.LENGTH_SHORT).show();
        }else{
           if(email.getText().toString().contains("@")){
               viewModelLogin.loginUser(new Login(email.getText().toString(),pass.getText().toString()));

           }else {
               Toast.makeText(getActivity(),"please entre email",Toast.LENGTH_SHORT).show();
           }
        }
    }
}