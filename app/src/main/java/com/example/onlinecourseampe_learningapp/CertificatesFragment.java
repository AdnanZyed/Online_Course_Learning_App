package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CertificatesFragment extends Fragment {

    private int courseId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // استلام الـ courseId من الـ Bundle
        if (getArguments() != null) {
            courseId = getArguments().getInt("COURSE_ID", -1);
        }

        // استخدام courseId داخل الفراجمنت
        // يمكنك الآن استخدام courseId لعرض البيانات أو العمليات المناسبة

        return inflater.inflate(R.layout.fragment_certificates, container, false);
    }
}
