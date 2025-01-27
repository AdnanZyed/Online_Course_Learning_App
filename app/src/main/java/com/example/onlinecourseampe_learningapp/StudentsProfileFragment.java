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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

public class StudentsProfileFragment extends Fragment {

    private String studentName;
    private Bitmap bitmap;
    private String studentBio;
    String students_u;
    String BIO;
    private byte[] image;
    private My_View_Model myViewModel;
    String NAME;


    public StudentsProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students_profile, container, false);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        // ربط عناصر الواجهة
        ImageView imgStudentProfile = view.findViewById(R.id.imgStudentProfile);
        TextView tvStudentName = view.findViewById(R.id.tvStudentNameP);
        TextView tvStudentBio = view.findViewById(R.id.tvStudentBio);

//        Bundle args = getArguments();
//        if (args != null) {
//              students_u = args.getString("USER_NAME");
//
//
//        }
//        if (getArguments() != null) {
//            studentName = getArguments().getString("name");
//            studentBio = getArguments().getString("bio");
//            studentImage = getArguments().getByteArray("image");
//
//            if (studentImage != null) {
//                bitmap = BitmapFactory.decodeByteArray(studentImage, 0, studentImage.length);
//            }
//        }


        myViewModel.getAllStudentByUser(getArguments().getString("USER_NAME")).observe((LifecycleOwner) requireContext(), students -> {

            NAME = students.get(0).getS_name();
            BIO = students.get(0).getBio();
            image = students.get(0).getS_Image();


        });
        tvStudentName.setText(NAME);

        if (BIO==null|| BIO.isEmpty()){
            tvStudentBio.setText("Welcom");

        }else {
            tvStudentBio.setText(BIO);

        }
//        studentName =getArguments().getString("name");
//        studentBio = getArguments().getString("bio");
//        studentImage = getArguments().getByteArray("image");

        if (image != null) {
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imgStudentProfile.setImageBitmap(bitmap);
        } else {
            imgStudentProfile.setImageResource(R.drawable.profile);
        }
//       // imgStudentProfile.setImageBitmap(bitmap);


        return view;
    }
//    public static StudentsProfileFragment newInstance(String name, String bio, byte[] imageResId) {
//        StudentsProfileFragment fragment = new StudentsProfileFragment();
//        Bundle args = new Bundle();
//        args.putString("name", name);
//        args.putString("bio", bio);
//        args.putByteArray("image", imageResId);
//        fragment.setArguments(args);
//        return fragment;
//    }

}

