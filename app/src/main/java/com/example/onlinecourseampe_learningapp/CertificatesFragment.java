package com.example.onlinecourseampe_learningapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CertificatesFragment extends Fragment {

    private int courseId;
    private My_View_Model viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_certificates, container, false);
        ImageView imageView=view.findViewById(R.id.Certificates_image);

        viewModel = new ViewModelProvider(this).get(My_View_Model.class);


        // استلام الـ courseId من الـ Bundle
        if (getArguments() != null) {
            courseId = getArguments().getInt("COURSE_ID", -1);
        }
        viewModel.getAllCoursesById(courseId).observe(getViewLifecycleOwner(), Courses -> {
            byte[] bitmapBytes = Courses.get(0).getProfilePicture();
            if (bitmapBytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
                imageView.setImageBitmap(bitmap);
            }


        });
        // استخدام courseId داخل الفراجمنت
        // يمكنك الآن استخدام courseId لعرض البيانات أو العمليات المناسبة
        return view;
    }
}
