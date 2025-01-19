package com.example.onlinecourseampe_learningapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StudentsProfileFragment extends Fragment {

    private String studentName ;
   private Bitmap bitmap;
    private String studentBio ;
    private byte[] studentImage ;

    public StudentsProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students_profile, container, false);


        // ربط عناصر الواجهة
        ImageView imgStudentProfile = view.findViewById(R.id.imgStudentProfile);
        TextView tvStudentName = view.findViewById(R.id.tvStudentName);
        TextView tvStudentBio = view.findViewById(R.id.tvStudentBio);

        if (getArguments() != null) {
            studentName = getArguments().getString("name");
            studentBio = getArguments().getString("bio");
            studentImage = getArguments().getByteArray("image");

            if (studentImage != null) {
                bitmap = BitmapFactory.decodeByteArray(studentImage, 0, studentImage.length);
            }
        }

        // ضبط القيم
        imgStudentProfile.setImageBitmap(bitmap);
        tvStudentName.setText(studentName);
        tvStudentBio.setText(studentBio);
        studentName = getArguments() != null ? getArguments().getString("name", "الاسم الافتراضي") : "الاسم الافتراضي";
        studentBio = getArguments() != null ? getArguments().getString("bio", "النص التعريفي الافتراضي") : "النص التعريفي الافتراضي";
        studentImage = getArguments() != null ? getArguments().getByteArray("image") : null;

        if (studentImage != null) {
            bitmap = BitmapFactory.decodeByteArray(studentImage, 0, studentImage.length);
            imgStudentProfile.setImageBitmap(bitmap);
        } else {
            imgStudentProfile.setImageResource(R.drawable.profile); // صورة افتراضية
        }


        return view;
    }
    public static StudentsProfileFragment newInstance(String name, String bio, byte[] imageResId) {
        StudentsProfileFragment fragment = new StudentsProfileFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("bio", bio);
        args.putByteArray("image", imageResId);
        fragment.setArguments(args);
        return fragment;
    }

}

