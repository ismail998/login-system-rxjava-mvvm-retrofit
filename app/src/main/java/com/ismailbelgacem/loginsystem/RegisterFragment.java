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
import com.ismailbelgacem.loginsystem.Model.User;
import com.ismailbelgacem.loginsystem.ViewModel.ViewMOdelSystem;

public class RegisterFragment extends Fragment {
    ViewMOdelSystem ViewModelRegister;
    TextView signIn;
    Button login;
    EditText email,pass,name;
    public RegisterFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register, container, false);
        email=view.findViewById(R.id.email);
        login=view.findViewById(R.id.btnlogin);
        pass=view.findViewById(R.id.pass);
        name=view.findViewById(R.id.name);
        signIn=view.findViewById(R.id.signin);
        ViewModelRegister = ViewModelProviders.of(this).get(ViewMOdelSystem.class);
        ViewModelRegister.getListMutableLiveData2().observe(getActivity(), new Observer<ResponceLogin>() {
            @Override
            public void onChanged(ResponceLogin responceLogin) {
                Log.d("TAG", "onChangedre: "+responceLogin.toString());
                Intent intent = new Intent(getActivity(),MainApp.class);
                intent.putExtra("token",responceLogin.getToken());
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new LoginFragment()).commit();
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
        if(TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(pass.getText().toString())||
                TextUtils.isEmpty(name.getText().toString())){
            Toast.makeText(getActivity(),"text is empty email or password",Toast.LENGTH_SHORT).show();
        }else{
            if(email.getText().toString().contains("@")&& pass.getText().toString().length()>=8){
               // .loginUser(new Login(email.getText().toString(),pass.getText().toString()));
                ViewModelRegister.regesterUser(new User(name.getText().toString(),
                        email.getText().toString(),pass.getText().toString()));
            }else {
                Toast.makeText(getActivity(),"please entre email ",Toast.LENGTH_SHORT).show();
            }
        }
    }
}