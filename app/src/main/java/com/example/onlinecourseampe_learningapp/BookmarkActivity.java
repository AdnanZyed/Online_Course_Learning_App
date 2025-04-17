package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;
import java.util.List;
public class BookmarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SeasonsAdapter adapter;
    String user;
    private List<SeasonStep> farmersList1 = new ArrayList<>();

    My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.recycler_view);
        ImageView imageView = findViewById(R.id.back_icon_enrollB);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        user = getIntent().getStringExtra("USER");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        myViewModel.getBookmarkedSeasonByFarmer(user).observe(this, bookmarkedSeasons -> {
            if (bookmarkedSeasons != null && !bookmarkedSeasons.isEmpty()) {
                farmersList1.clear();

                for (Farmer_Seasons farmerSeasons : bookmarkedSeasons) {
                    myViewModel.getStepsBySeasonId(farmerSeasons.getSeason_ID()).observe(this, seasons -> {
                        if (seasons != null && !seasons.isEmpty()) {
                            farmersList1.addAll(seasons);

                            runOnUiThread(() -> {
                                adapter = new SeasonsAdapter((List<Season>) this, (Context) farmersList1, user);
                                recyclerView.setAdapter(adapter);
                            });
                        }
                    });
                }

            } else {
                Toast.makeText(this, "No saved season!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
