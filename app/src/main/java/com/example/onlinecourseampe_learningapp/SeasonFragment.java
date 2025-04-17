package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;

public class SeasonFragment extends Fragment {

    private My_View_Model myViewModel;
    private SeasonAdapter seasonAdapter;
    private String user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_season, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_Seasons);

        user = getArguments().getString("USER_NAME_R");

        seasonAdapter = new SeasonAdapter(requireContext(), new ArrayList<>(), user);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(seasonAdapter);

        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);
        loadSeasons();

        return view;
    }


    public void loadSeasons() {


        myViewModel.getAllSeason().observe(getViewLifecycleOwner(), seasons -> {
            seasonAdapter.setSeasonList(seasons);
            seasonAdapter.setOnSeasonClickListener(season -> {

                Intent intent = new Intent(requireContext(), SeasonDetailsActivity.class);

                intent.putExtra("COURSE_ID", season.getSeason_ID());
                intent.putExtra("USER", user);
                intent.putExtra("TEACHER_USER_NAME", season.getExpert_USER_Name());
                intent.putExtra("COURSE_NAME", season.getSeason_NAME());
                intent.putExtra("COURSE_DESCRIPTION", season.getDescription());
                intent.putExtra("COURSE_CATEGORIES", season.getCategorie());
                intent.putExtra("COURSE_PRICE", season.getPrice());
                intent.putExtra("COURSE_IMAGE", season.getImage());
                intent.putExtra("TEACHER_NAME", season.getExpert_name());


                startActivity(intent);
            });

        });
    }

    public void loadSeasonsByUserExpert(String string) {


        myViewModel.getAllseasonsByExpert_USER_Name(string).observe(getViewLifecycleOwner(), seasons -> {
            seasonAdapter.setSeasonList(seasons);
            seasonAdapter.setOnSeasonClickListener(season -> {

                Intent intent = new Intent(requireContext(), SeasonDetailsActivity.class);

                intent.putExtra("COURSE_ID", season.getSeason_ID());
                intent.putExtra("TEACHER_USER_NAME", season.getExpert_USER_Name());
                intent.putExtra("COURSE_NAME", season.getSeason_NAME());
                intent.putExtra("USER", user);
                intent.putExtra("COURSE_DESCRIPTION", season.getDescription());

                intent.putExtra("COURSE_PRICE", season.getPrice());
                intent.putExtra("COURSE_IMAGE", season.getImage());
                intent.putExtra("TEACHER_NAME", season.getExpert_name());


                startActivity(intent);
            });

        });
    }

    public void loadSeasons_Categorie_Art() {


        myViewModel.getSeasonsByCategory("Art").observe(getViewLifecycleOwner(), seasons -> {

            seasonAdapter.setSeasonList(seasons);

            seasonAdapter.setOnSeasonClickListener(season -> {

                Intent intent = new Intent(requireContext(), SeasonDetailsActivity.class);

                intent.putExtra("COURSE_ID", season.getSeason_ID());
                intent.putExtra("TEACHER_USER_NAME", season.getExpert_USER_Name());
                intent.putExtra("COURSE_NAME", season.getSeason_NAME());
                intent.putExtra("COURSE_DESCRIPTION", season.getDescription());
                intent.putExtra("COURSE_PRICE", season.getPrice());
                intent.putExtra("COURSE_IMAGE", season.getImage());
                intent.putExtra("USER", user);

                intent.putExtra("TEACHER_NAME", season.getExpert_name());


                startActivity(intent);
            });

        });
    }

    public void loadSeasons_Categorie_Programming() {


        myViewModel.getSeasonsByCategory("Programming").observe(getViewLifecycleOwner(), seasons -> {

            seasonAdapter.setSeasonList(seasons);

            seasonAdapter.setOnSeasonClickListener(season -> {

                Intent intent = new Intent(requireContext(), SeasonDetailsActivity.class);

                intent.putExtra("COURSE_ID", season.getSeason_ID());
                intent.putExtra("TEACHER_USER_NAME", season.getExpert_USER_Name());
                intent.putExtra("COURSE_NAME", season.getSeason_NAME());
                intent.putExtra("COURSE_PRICE", season.getPrice());
                intent.putExtra("COURSE_DESCRIPTION", season.getDescription());
                intent.putExtra("USER", user);
                intent.putExtra("COURSE_IMAGE", season.getImage());
                intent.putExtra("TEACHER_NAME", season.getExpert_name());


                startActivity(intent);
            });

        });
    }

    public void loadSeasons_Categorie_Business() {


        myViewModel.getSeasonsByCategory("Business").observe(getViewLifecycleOwner(), seasons -> {

            seasonAdapter.setSeasonList(seasons);

            seasonAdapter.setOnSeasonClickListener(season -> {

                Intent intent = new Intent(requireContext(), SeasonDetailsActivity.class);

                intent.putExtra("COURSE_ID", season.getSeason_ID());
                intent.putExtra("TEACHER_USER_NAME", season.getExpert_USER_Name());
                intent.putExtra("COURSE_NAME", season.getSeason_NAME());
                intent.putExtra("COURSE_PRICE", season.getPrice());
                intent.putExtra("COURSE_DESCRIPTION", season.getDescription());

                intent.putExtra("COURSE_IMAGE", season.getImage());
                intent.putExtra("TEACHER_NAME", season.getExpert_name());


                startActivity(intent);
            });

        });
    }

    public void loadSeasons_Categorie_3D_Design() {


        myViewModel.getSeasonsByCategory("3D Design").observe(getViewLifecycleOwner(), seasons -> {

            seasonAdapter.setSeasonList(seasons);

            seasonAdapter.setOnSeasonClickListener(season -> {

                Intent intent = new Intent(requireContext(), SeasonDetailsActivity.class);

                intent.putExtra("COURSE_ID", season.getSeason_ID());
                intent.putExtra("TEACHER_USER_NAME", season.getExpert_USER_Name());
                intent.putExtra("COURSE_NAME", season.getSeason_NAME());
                intent.putExtra("COURSE_PRICE", season.getPrice());
                intent.putExtra("COURSE_DESCRIPTION", season.getDescription());

                intent.putExtra("COURSE_IMAGE", season.getImage());
                intent.putExtra("TEACHER_NAME", season.getExpert_name());


                startActivity(intent);
            });

        });
    }
}
