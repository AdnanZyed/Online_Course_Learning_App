package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;
import java.util.List;


public class Most_Popular extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private My_View_Model myViewModel1;
    private ImageView searchIcon1;
    private ImageView backIcon1;
    private ImageView imageFound1;
    private TextView t_sory1;
    private TextView t_found1;
    private TextView textMentor1;
    private EditText eSearsh1;
    private String searchQuery1;
    private SeasonAdapter seasonAdapter1;
    private List<Season> expertList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular);

        recyclerView1 = findViewById(R.id.rv_experts1);
        t_sory1 = findViewById(R.id.sorry1);
        t_found1 = findViewById(R.id.found1);
        imageFound1 = findViewById(R.id.imageView81);
        searchIcon1 = findViewById(R.id.search_icon1);
        backIcon1 = findViewById(R.id.back_icon1);
        eSearsh1 = findViewById(R.id.e_searsh1);
        textMentor1 = findViewById(R.id.text_mentor1);

        String user = getIntent().getStringExtra("STUDENT_USER");

        eSearsh1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchQuery1 = eSearsh1.getText().toString().trim();
                if (searchQuery1 != null && !searchQuery1.isEmpty()) {
                    searchSeasons(searchQuery1);
                } else {
                    loadSeasons();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        backIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        seasonAdapter1 = new SeasonAdapter(this,new ArrayList<>(), user);
        myViewModel1 = new ViewModelProvider(this).get(My_View_Model.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(seasonAdapter1);

        loadSeasons();

        searchIcon1.setOnClickListener(v -> {
            searchIcon1.setVisibility(View.GONE);
            textMentor1.setVisibility(View.GONE);
            backIcon1.setVisibility(View.VISIBLE);
            eSearsh1.setVisibility(View.VISIBLE);
        });

        backIcon1.setOnClickListener(v -> {
            eSearsh1.setVisibility(View.GONE);
            searchIcon1.setVisibility(View.VISIBLE);
            textMentor1.setVisibility(View.VISIBLE);
            loadSeasons();
        });
    }

    private void searchSeasons(String query) {
        myViewModel1.searchSeasons(query).observe(this, seasons -> {
            if (seasons != null && !seasons.isEmpty()) {
                seasonAdapter1.setSeasonList(seasons);
                recyclerView1.setVisibility(View.VISIBLE);
                t_sory1.setVisibility(View.GONE);
                t_found1.setVisibility(View.GONE);
                imageFound1.setVisibility(View.GONE);
            } else {
                recyclerView1.setVisibility(View.GONE);
                t_sory1.setVisibility(View.VISIBLE);
                t_found1.setVisibility(View.VISIBLE);
                imageFound1.setVisibility(View.VISIBLE);
            }
        });
    }

    public void loadSeasons() {
        myViewModel1.getAllSeason().observe(this, seasons -> {
            if (seasons != null && !seasons.isEmpty()) {
                seasonAdapter1.setSeasonList(seasons);
                recyclerView1.setVisibility(View.VISIBLE);
                t_sory1.setVisibility(View.GONE);
                t_found1.setVisibility(View.GONE);
                imageFound1.setVisibility(View.GONE);
            } else {
                recyclerView1.setVisibility(View.GONE);
                t_sory1.setVisibility(View.VISIBLE);
                t_found1.setVisibility(View.VISIBLE);
                imageFound1.setVisibility(View.VISIBLE);
            }
        });
    }
}