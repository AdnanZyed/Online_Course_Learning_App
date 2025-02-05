package com.example.onlinecourseampe_learningapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Rating;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReviewsTeacherFragment extends Fragment {
    private My_View_Model myViewModel;
    private String student_name;
    private byte[] bytes;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private Spinner ratingSpinner;

    private EditText edit_Comment;
    private ImageView imageSend;

    public ReviewsTeacherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reviwes_teacher, container, false);
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);
        imageView = view.findViewById(R.id.image_student1);
        edit_Comment = view.findViewById(R.id.edit_comment1);

        ratingSpinner = view.findViewById(R.id.rating1);
        imageSend = view.findViewById(R.id.send1);

        recyclerView = view.findViewById(R.id.rv_review1);
        String teacherUserName = getArguments().getString("TEACHER_USER_NAME1");
        String user = getArguments().getString("STUDENT_USER_NAME1");

        myViewModel.getAllStudentByUser(user).observe(getViewLifecycleOwner(), students -> {

            Student student = students.get(0);
            student_name = student.getS_name().toString();


            bytes = student.getS_Image();
            if (bytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.drawable.profile);
            }


        });
        myViewModel.getAllReviewsByCourseIdT(teacherUserName).observe(requireActivity(), reviews -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            ReviewsTeacherAdapter adapter = new ReviewsTeacherAdapter(getContext(), reviews);
            recyclerView.setAdapter(adapter);
            int count = reviews.size();
            int f = 0;

            for (int i = 0; i <= reviews.size() - 1; i++) {
                f += reviews.get(i).getRating();
            }


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

                int rating_spinner = Integer.parseInt(ratingSpinner.getSelectedItem().toString());

                Teacher_Reviews review = new Teacher_Reviews(0, bytes, student_name, user,
                        edit_Comment.getText().toString(),
                        formattedDate, 0, teacherUserName, rating_spinner, false);
                myViewModel.insertReviewT(review);

                edit_Comment.setText(null);
            }
        });


        return view;
    }
}
