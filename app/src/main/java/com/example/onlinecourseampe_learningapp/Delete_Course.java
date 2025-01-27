package com.example.onlinecourseampe_learningapp;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Delete_Course extends AppCompatActivity {
    RecyclerView recyclerView;
    CourseAdapter courseAdapter;
    My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);


        // إعداد RecyclerView
        recyclerView = findViewById(R.id.rv_courses_delete);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // إعداد الـ Adapter
        courseAdapter = new CourseAdapter(Delete_Course.this, new ArrayList<>(), "");
        recyclerView.setAdapter(courseAdapter);

        // إعداد ViewModel
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        // استرجاع البيانات من LiveData
        myViewModel.getAllCourse().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                // تحديث الـ Adapter بالبيانات الجديدة
                courseAdapter.setCourseList(courses);
            }
        });


// إعداد ItemTouchHelper للسحب
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // عند سحب العنصر إلى اليمين، قم بفتح أو تنفيذ أمر السلة
                CourseAdapter.CourseViewHolder courseViewHolder = (CourseAdapter.CourseViewHolder) viewHolder;
                courseViewHolder.cartIcon.setVisibility(View.VISIBLE); // عرض أيقونة السل

                // يمكنك تنفيذ الأمر هنا مثل إضافة العنصر إلى السلة
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (isCurrentlyActive) {
                    // يمكنك تخصيص تصميم السحب مثل إضافة لون الخلفية أو مؤثرات خاصة هنا
                }
            }
        };

// إضافة ItemTouchHelper إلى RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

}