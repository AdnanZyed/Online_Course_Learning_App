package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Button> buttons = new ArrayList<>();
    private Button activeButton = null;
    Button btnAll;
    ViewPager2 viewPager;
    Button btn3DDesign;
    Bundle args;
    Bundle bundle;
    RecyclerView teacherRecyclerView;
    TeacherAdapter teacherAdapter;
    Button btnProgramming;
    Button btnBusiness;
    Button btnArt;
    TextView tv_Seeall;


    TextView tv_Seeall2;
    TextView tv_Welcom;
    TextView tv_Name;
    ImageView Iv_notification;
    ImageView Iv_Bookmark;
    String students_u;
    ImageView iv_S;
    String bio;

    private My_View_Model myViewModel;

    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);

        args = getArguments();
        if (args != null) {
            students_u = args.getString("USER_NAME");


        }


        ViewPager2 viewPager2 = rootView.findViewById(R.id.viewPager2);
        if (viewPager2 == null) {
            Log.e("HomeFragment", "viewPager2 is null!");
        }

        List<Integer> images = Arrays.asList(R.drawable.ad1, R.drawable.ad2, R.drawable.ad3);
        ImageAdapter adapter = new ImageAdapter(images);

        viewPager2.setAdapter(adapter);

        bundle = new Bundle();
        bundle.putString("USER_NAME_R", students_u);

        Fragment targetFragment = new ReviewsFragment();
        Fragment fragment2 = new CourseFragment();
        fragment2.setArguments(bundle);
        targetFragment.setArguments(bundle);

        btnAll = rootView.findViewById(R.id.btn_all);
        Iv_notification = rootView.findViewById(R.id.iv_notification);
        iv_S = rootView.findViewById(R.id.iv_s);
        Iv_Bookmark = rootView.findViewById(R.id.iv_Bookmark);
        tv_Seeall = rootView.findViewById(R.id.tv_seeall);
        tv_Seeall2 = rootView.findViewById(R.id.tv_seeall2);
        tv_Name = rootView.findViewById(R.id.tv_name);
        tv_Welcom = rootView.findViewById(R.id.tv_welcom);
        btn3DDesign = rootView.findViewById(R.id.btn_3d_design);
        btnProgramming = rootView.findViewById(R.id.btn_programming);
        teacherRecyclerView = rootView.findViewById(R.id.rv_t);
        btnBusiness = rootView.findViewById(R.id.btn_business);
        btnArt = rootView.findViewById(R.id.btn_art);
        CourseFragment fragment = (CourseFragment) getParentFragmentManager()
                .findFragmentById(R.id.fram_corse);


        if (fragment != null) {
            fragment.loadCourses();
            onButtonClicked(btnAll);
        }


        buttons.add(btnAll);
        buttons.add(btnProgramming);
        buttons.add(btn3DDesign);
        buttons.add(btnBusiness);
        buttons.add(btnArt);
        Button defaultButton = btnAll;
        defaultButton.setBackgroundResource(R.drawable.catigories_btn_selected);
        defaultButton.setTextColor(R.color.white);
        for (Button button : buttons) {
            button.setOnClickListener(v -> onButtonClicked(button));
        }

        myViewModel.getAllStudentByUser(students_u).observe(getViewLifecycleOwner(), students -> {
            if (students != null && !students.isEmpty()) {

                Student student = students.get(0);
                String student_name = student.getS_name().toString();
                bio = student.getBio().toString();
                if (bio.isEmpty()) {
                    tv_Welcom.setText("Welcom");


                } else {
                    tv_Welcom.setText(bio);
                }
                byte[] bytes = student.getS_Image();
                if (bytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    iv_S.setImageBitmap(bitmap);
                } else {
                    iv_S.setImageResource(R.drawable.profile);
                }

                tv_Name.setText(student_name);
            }
        });


        iv_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentsProfileFragment fragment = new StudentsProfileFragment();
                Bundle args = new Bundle();
                args.putString("USER_NAME_R", students_u);
                fragment.setArguments(args);


                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Iv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });


        Iv_Bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireContext(), BookmarkActivity.class);
                intent.putExtra("USER", students_u);

                startActivity(intent);
            }
        });


        if (savedInstanceState == null) {
            CourseFragment courseFragment = new CourseFragment();
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME_R", students_u);
            courseFragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fram_corse, courseFragment)
                    .commit();
            onButtonClicked(btnAll);

        }

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses();
                    onButtonClicked(btnAll);


                }
            }
        });
        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_Art();
                    onButtonClicked(btnArt);


                }

            }
        });

        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_Business();
                    onButtonClicked(btnBusiness);


                }

            }
        });

        btnProgramming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_Programming();
                    onButtonClicked(btnProgramming);


                }

            }
        });

        btn3DDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_3D_Design();
                    onButtonClicked(btn3DDesign);


                }

            }
        });


        tv_Seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), TopMentors.class);
                intent.putExtra("STUDENT_USER", students_u);
                startActivity(intent);
            }
        });
        tv_Seeall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), Most_Popular.class);
                intent.putExtra("USER", students_u);

                startActivity(intent);
            }
        });


        loadTeacher();


        return rootView;
    }

    public void loadTeacher() {
        myViewModel.getAllTeacher().observe(getViewLifecycleOwner(), teachers -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);


            teacherAdapter = new TeacherAdapter(requireContext(), new ArrayList<>(), students_u);

            teacherRecyclerView.setLayoutManager(layoutManager);


            teacherAdapter.setTeacherList(teachers);
            teacherRecyclerView.setAdapter(teacherAdapter);

        });
    }


    @SuppressLint("ResourceAsColor")
    private void onButtonClicked(Button clickedButton) {
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.catigories_btn);
            button.setTextColor(R.color.blue);
        }


        clickedButton.setBackgroundResource(R.drawable.catigories_btn_selected);
        clickedButton.setTextColor(R.color.white);

        activeButton = clickedButton;


    }
}