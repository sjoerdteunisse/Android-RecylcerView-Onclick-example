package com.axr.sjoerd.contactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.axr.sjoerd.ApplicationLogic.RandomPersonApi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d("main", "initRecyclerView: init recyclerview.");

        RandomPersonApi randomPersonApi = new RandomPersonApi();
        final ArrayList<com.axr.sjoerd.contactcard.DomainLayer.Person> personArrayList = randomPersonApi.jsonObject();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, personArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
