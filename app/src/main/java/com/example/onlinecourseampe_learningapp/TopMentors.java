package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TopMentors extends AppCompatActivity {
    private RecyclerView recyclerView;
    private My_View_Model myViewModel;
    private ImageView searchIcon;
    private ImageView backIcon;
    private TextView textMentor;
    private EditText eSearsh;
    private String searchQuery;
    private TeacherAdapterMentors teacherAdapter;
    private List<Teacher> teacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_mentors);


        recyclerView = findViewById(R.id.rv_teachers);
        searchIcon = findViewById(R.id.search_icon);
        backIcon = findViewById(R.id.back_icon);
        eSearsh = findViewById(R.id.e_searsh);
        textMentor = findViewById(R.id.text_mentor);


        teacherAdapter = new TeacherAdapterMentors(this, new ArrayList<>());
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
//      Teacher_Dao.getTeacherByName("%" + searchQuery + "%");  // باستخدام الـ "%" للبحث الجزئي
//      // جلب المدرسين من ViewModel
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

      //  loadTeacher1();

        recyclerView.setLayoutManager(layoutManager);

        // قائمة المدرسين (بيانات افتراضية)

        recyclerView.setAdapter(teacherAdapter);




        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchIcon.setVisibility(View.GONE);
                textMentor.setVisibility(View.GONE);
                backIcon.setVisibility(View.GONE);
                eSearsh.setVisibility(View.VISIBLE);


            }
        });

        searchQuery = eSearsh.getText().toString().trim();
        if (!searchQuery.isEmpty()) {
            searchTeachers(searchQuery);
        }


    }
    private void searchTeachers (String query){
        myViewModel.searchTeachers(query).observe(this, teachers -> {
            if (teachers != null) {
                teacherAdapter.setTeacher_MonetorsList(teachers);  // تحديث الـ RecyclerView
            }
        });
       // loadTeacher1();
    }
    public void loadTeacher1() {
        // جلب المدرسين من ViewModel
        myViewModel.getAllTeacher().observe(this, teachers -> {
            teacherAdapter.setTeacher_MonetorsList(teachers);

        });
    }
}