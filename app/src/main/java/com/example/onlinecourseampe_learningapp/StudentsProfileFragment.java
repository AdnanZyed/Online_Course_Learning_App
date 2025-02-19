package com.example.onlinecourseampe_learningapp;


import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentsProfileFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;

    private String studentName;
    private Bitmap bitmap;
    int CARD;
    byte[] imageBytes;
    int PHONE;
    private String studentBio;
    String students_u;
    Button Save;
    String BIO;
    private byte[] image;
    private My_View_Model myViewModel;
    String NAME;
    String PASSWORD;
    TextView tvStudentName;

    ImageView imgStudentProfile;
    TextView tvStudentBio;
    ImageView BtnEditName;
    ImageView BtnEditBio;
    String user;
    private CoursesAdapter adapter;

    EditText etStudentName;
    EditText etStudentBio;
    private RecyclerView recyclerView;


    public StudentsProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students_profile, container, false);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        etStudentName = view.findViewById(R.id.etStudentName);

        recyclerView = view.findViewById(R.id.rv_st);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        etStudentBio = view.findViewById(R.id.etStudentBio);
        imgStudentProfile = view.findViewById(R.id.imgStudentProfile);
        tvStudentName = view.findViewById(R.id.tvStudentNameP);
        tvStudentBio = view.findViewById(R.id.tvStudentBio);
        Save = view.findViewById(R.id.save);
        BtnEditName = view.findViewById(R.id.btnEditName);
        BtnEditBio = view.findViewById(R.id.btnEditBio);
        user = getArguments().getString("USER_NAME");

        ImageView signOutButton = view.findViewById(R.id.sign_out);
        signOutButton.setOnClickListener(v -> {
            SharedPreferences preferences = requireActivity().getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(requireActivity(), ActivityMainSignIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        myViewModel.getisRegisterCoursesByStudent1(user).observe(getViewLifecycleOwner(), studentCourses -> {
            List<Integer> courseIds = new ArrayList<>();
            for (Student_Course sc : studentCourses) {
                courseIds.add(sc.getCourse_ID());
            }

            myViewModel.getAllCoursesByIds(courseIds).observe(getViewLifecycleOwner(), courses -> {
                adapter = new CoursesAdapter(courses, requireContext(), user);
                recyclerView.setAdapter(adapter);

            });
        });


        myViewModel.getAllStudentByUser(user).observe((LifecycleOwner) requireContext(), students -> {
            if (!students.isEmpty()) {
                NAME = students.get(0).getS_name();
                BIO = students.get(0).getBio();
                image = students.get(0).getS_Image();
                PASSWORD = students.get(0).getStudent_Password();
                PHONE = students.get(0).getPhone_nomber();
                CARD = students.get(0).getCard_Number();

                tvStudentName.setText(NAME);
                tvStudentBio.setText((BIO == null || BIO.isEmpty()) ? "Welcome" : BIO);

                if (image != null) {
                    bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    imgStudentProfile.setImageBitmap(bitmap);
                } else {
                    imgStudentProfile.setImageResource(R.drawable.profile);
                }
            }
        });
        imgStudentProfile.setOnClickListener(v -> openGallery());

        BtnEditName.setOnClickListener(v -> {
            tvStudentName.setVisibility(View.GONE);
            etStudentName.setVisibility(View.VISIBLE);
            etStudentName.setText(tvStudentName.getText().toString());
            Save.setVisibility(View.VISIBLE);
        });

        BtnEditBio.setOnClickListener(v -> {
            tvStudentBio.setVisibility(View.GONE);
            etStudentBio.setVisibility(View.VISIBLE);
            etStudentBio.setText(tvStudentBio.getText().toString());
            Save.setVisibility(View.VISIBLE);
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(user, PASSWORD, PHONE, CARD, etStudentName.getText().toString(), imageBytes, etStudentBio.getText().toString());
                myViewModel.updateStudent(student);
                tvStudentBio.setVisibility(View.VISIBLE);
                etStudentBio.setVisibility(View.GONE);
                Save.setVisibility(View.GONE);
                tvStudentName.setVisibility(View.VISIBLE);
                etStudentName.setVisibility(View.GONE);
                Save.setVisibility(View.GONE);
            }
        });


        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public static StudentsProfileFragment newInstance(String username) {
        StudentsProfileFragment fragment = new StudentsProfileFragment();
        Bundle args = new Bundle();
        args.putString("USER_NAME_R", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                imgStudentProfile.setImageBitmap(bitmap);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageBytes = stream.toByteArray();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

