package com.example.onlinecourseampe_learningapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.onlineSeasonampe_learningapp.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReviewsFragment extends Fragment {
    private My_View_Model myViewModel;
    String farmer_name;
    byte[] bytes;
    ImageView imageView;
    RecyclerView recyclerView;


    Bundle args;
    int rating;
    String userString;
    TextView total_Comments;
    TextView total_Rating;
    EditText edit_Comment;
    ImageView imageSend;
    private RatingBar ratingBar;


    public ReviewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);
        imageView = view.findViewById(R.id.image_farmer);
        edit_Comment = view.findViewById(R.id.edit_comment);
        total_Comments = view.findViewById(R.id.total_comments);
        total_Rating = view.findViewById(R.id.total_rating);
        imageSend = view.findViewById(R.id.send);
        recyclerView = view.findViewById(R.id.rv_review);
        ratingBar = view.findViewById(R.id.ratingBar);


        Bundle bundle1 = getArguments();
        int seasonInt = bundle1.getInt("COURSE_ID1");
        String user = bundle1.getString("USER_ST");


        myViewModel.getAllFarmerByUser(user).observe(getViewLifecycleOwner(), farmers -> {

            if (!farmers.isEmpty() && farmers != null) {
                Farmer farmer = farmers.get(0);
                farmer_name = farmer.getS_name().toString();


                bytes = farmer.getS_Image();
                if (bytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bitmap);
                } else {
                    imageView.setImageResource(R.drawable.profile);
                }
            }

        });
        myViewModel.getFarmersBySeasonAndFarmer(user, seasonInt).observe((LifecycleOwner) requireContext(), farmerSeasons -> {
            if (!farmerSeasons.isEmpty() && farmerSeasons != null) {

                rating = farmerSeasons.get(0).getRating();
            }

        });
        myViewModel.getAllReviewsBySeasonId(seasonInt).observe(requireActivity(), reviews -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


            ReviewsAdapter adapter = new ReviewsAdapter(getContext(), reviews);
            recyclerView.setAdapter(adapter);
            int count = reviews.size();
            int f = 0;

            for (int i = 0; i <= reviews.size() - 1; i++) {
                f = +reviews.get(i).getRating();
            }

            total_Comments.setText("" + count);
            total_Rating.setText("" + f);
            ratingBar.setProgress(f);
        });


        edit_Comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    imageSend.setImageResource(R.drawable.send);
                    imageSend.setEnabled(false);
                } else {
                    imageSend.setImageResource(R.drawable.send_b);
                    imageSend.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        imageSend.setOnClickListener(v -> {
            if (!edit_Comment.getText().toString().trim().isEmpty()) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = now.format(formatter);


                Season_Reviews review = new Season_Reviews(0, bytes, farmer_name, user,
                        edit_Comment.getText().toString(),
                        formattedDate, 0, seasonInt, rating, false);
                myViewModel.insertReview(review);

                edit_Comment.setText(null);
            }
        });


        return view;
    }
}
