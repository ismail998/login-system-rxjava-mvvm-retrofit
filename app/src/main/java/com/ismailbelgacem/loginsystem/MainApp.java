package com.ismailbelgacem.loginsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ismailbelgacem.loginsystem.Model.Catygory;
import com.ismailbelgacem.loginsystem.ViewModel.ViewMOdelSystem;

import java.util.ArrayList;

public class MainApp extends AppCompatActivity {
  TextView textView;
  ViewMOdelSystem viewMOdelSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        textView=findViewById(R.id.textView);
        viewMOdelSystem= ViewModelProviders.of(this).get(ViewMOdelSystem.class);
        viewMOdelSystem.getAllCatygry();
        viewMOdelSystem.catygorylist.observe(this, new Observer<ArrayList<Catygory>>() {
            @Override
            public void onChanged(ArrayList<Catygory> catygories) {
                Log.d("TAG", "onChanged: "+catygories.size());
            }
        });
        textView.setText(getIntent().getStringExtra("token"));
    }
}