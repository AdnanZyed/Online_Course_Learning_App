package com.example.onlinecourseampe_learningapp;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {






































    My_View_Model myViewModel;
    ImageView Message;
    TextView description;
    String StudentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);


        String teacherUserName = getArguments().getString("TEACHER_USER_NAME1");
        String CourseDescription = getArguments().getString("courseDescription");
        StudentUser = getArguments().getString("STUDENT_USER");

        Message = view.findViewById(R.id.message_btn);
        description = view.findViewById(R.id.description);

        description.setText(CourseDescription);


        Bundle bundle = new Bundle();

        myViewModel.getAllTeacherByUser(teacherUserName).observe(getViewLifecycleOwner(), teacher -> {

            if (teacher != null) {
                TextView teacherNameTextView = view.findViewById(R.id.teacher_name1);
                TextView educationTextView = view.findViewById(R.id.teacher_magor);
                ImageView teacherImageView = view.findViewById(R.id.imageView4);

                Teacher teacherdata = teacher.get(0);
                Bitmap bitmap = BitmapFactory.decodeByteArray(teacherdata.getImage_teatcher(), 0, teacherdata.getImage_teatcher().length);

                teacherNameTextView.setText(teacherdata.getTeatur_name());
                educationTextView.setText(teacherdata.getEducation());
                teacherImageView.setImageBitmap(bitmap);


                bundle.putString("TEACHER_NAME_TEXT_VIEW", teacherdata.getTeatur_name());
                bundle.putString("TEACHER_USER_NAME_TEXT_VIEW", teacherdata.getTeatur_USER_Name());
                bundle.putString("EDUCATION_TEXT_VIEW", teacherdata.getEducation());
                bundle.putString("STUDENT_USER", StudentUser);

                bundle.putByteArray("BITMAP", teacherdata.getImage_teatcher());
            }
        });


        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), TeacherProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        return view;
    }

}