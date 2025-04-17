package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;
import java.util.List;


public class CompletedFragment extends Fragment {
    private RecyclerView recyclerView;
    private My_View_Model viewModel;
    private String user;
    private int coursId;
    private List<Season> ongoingSeasons = new ArrayList<>();
    private SeasonsAdapter adapter;

    public CompletedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        recyclerView = view.findViewById(R.id.rv_ongoing);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        if (getArguments() != null) {
            coursId = getArguments().getInt("COURSE_ID");
            user = getArguments().getString("USER");
        }

        viewModel = new ViewModelProvider(this).get(My_View_Model.class);

        adapter = new SeasonsAdapter(ongoingSeasons, requireContext(), user);
        recyclerView.setAdapter(adapter);

        loadOngoingSeasons();

        return view;
    }

    private void loadOngoingSeasons() {
        viewModel.getisRegisterSeasonsByFarmer1(user).observe(getViewLifecycleOwner(), farmerSeasons -> {
            List<Integer> seasonIds = new ArrayList<>();
            for (Farmer_Seasons sc : farmerSeasons) {
                seasonIds.add(sc.getSeason_ID());
            }

            viewModel.getAllSeasonsByIds(seasonIds).observe(getViewLifecycleOwner(), seasons -> {
                List<Season> tempOngoingSeasons = new ArrayList<>();

                for (Season season : seasons) {
                    viewModel.getTotalStepsCountBySeasonId(season.getSeason_ID()).observe(getViewLifecycleOwner(), totalSteps -> {
                        viewModel.getCompletedStepsCountBySeasonId(season.getSeason_ID()).observe(getViewLifecycleOwner(), completedSteps -> {
                            if (completedSteps == totalSteps) {
                                tempOngoingSeasons.add(season);
                            }

                            if (tempOngoingSeasons.size() > 0) {
                                ongoingSeasons.clear();
                                ongoingSeasons.addAll(tempOngoingSeasons);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    });
                }
            });
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadOngoingSeasons();
    }
}
