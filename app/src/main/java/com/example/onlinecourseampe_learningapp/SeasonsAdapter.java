package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.List;

public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.SeasonViewHolder> {

    public Season  season1;
    boolean isCompleted;

    private final List<Season > seasonList;
    private final Context context;
    private final My_View_Model viewModel;
    private final String user;

    public SeasonsAdapter(List<Season > seasonsList, Context context, String user) {
        this.seasonList = seasonsList;
        this.context = context;
        this.user = user;
        this.viewModel = new ViewModelProvider((AppCompatActivity) context).get(My_View_Model.class);

    }


    @NonNull
    @Override
    public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_regestered, parent, false);
        return new SeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonViewHolder holder, int position) {
        Season season = seasonList.get(position);

        holder.bind(season);

        season1 = season;

    }

    @Override
    public int getItemCount() {
        return seasonList != null ? seasonList.size() : 0;
    }

    public boolean completed() {

        viewModel.getTotalStepsCountBySeasonId(season1.getSeason_ID()).observe((AppCompatActivity) context, totalSteps1 -> {
            int total = totalSteps1;


            viewModel.getCompletedStepsCountBySeasonId(season1.getSeason_ID()).observe((AppCompatActivity) context, completedSteps1 -> {
                int complete = completedSteps1;


                if (total == complete) {


                    isCompleted = true;

                } else {
                    isCompleted = false;


                }


            });
        });
        return isCompleted;

    }

    class SeasonViewHolder extends RecyclerView.ViewHolder {

        private final ImageView seasonImage;
        private final TextView seasonName;
        private final TextView seasonTime;
        private final ProgressBar progressBar;
        private final TextView stepsNumR;
        private final TextView numStepR;

        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);

            seasonImage = itemView.findViewById(R.id.iv_Season_imageR);
            seasonName = itemView.findViewById(R.id.tv_Season_nameR);
            seasonTime = itemView.findViewById(R.id.time_SeasonR);
            progressBar = itemView.findViewById(R.id.progressBarR);
            stepsNumR = itemView.findViewById(R.id.steps_numR);
            numStepR = itemView.findViewById(R.id.numstepR);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Season selectedSeason = seasonList.get(position);
                    Intent intent = new Intent(context, StepsActivity.class);
                    intent.putExtra("COURSE_ID", selectedSeason.getSeason_ID());
                    intent.putExtra("USER", user);
                    context.startActivity(intent);

                }

            });

        }


        public void bind(Season season) {


            seasonName.setText(season.getSeason_NAME());
            if (season.getImage() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(season.getImage(), 0, season.getImage().length);
                seasonImage.setImageBitmap(bitmap);
            } else {
                seasonImage.setImageResource(R.drawable.unnamed);
            }
            viewModel.getTotalStepsCountBySeasonId(season.getSeason_ID()).observe((AppCompatActivity) context, totalSteps -> {
                numStepR.setText(String.valueOf(totalSteps));


            });

            viewModel.getCompletedStepsCountBySeasonId(season.getSeason_ID()).observe((AppCompatActivity) context, completedSteps -> {
                stepsNumR.setText(String.valueOf(completedSteps));

            });

            viewModel.getTotalStepsTimeBySeasonId(season.getSeason_ID()).observe((AppCompatActivity) context, totalTime -> {
                seasonTime.setText(String.format("%d min", totalTime));
            });

            viewModel.getTotalStepsCountBySeasonId(season.getSeason_ID()).observe((AppCompatActivity) context, totalSteps -> {
                viewModel.getCompletedStepsCountBySeasonId(season.getSeason_ID()).observe((AppCompatActivity) context, completedSteps -> {
                    int progress = (totalSteps > 0) ? (completedSteps * 100 / totalSteps) : 0;
                    progressBar.setProgress(progress);

                    if (totalSteps == completedSteps) {
                        season.setCompleted(true);


                    }
                });
            });


        }
    }
}

