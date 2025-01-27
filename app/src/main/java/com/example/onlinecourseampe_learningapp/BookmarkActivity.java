package com.example.onlinecourseampe_learningapp;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CourseAdapter adapter;
    String user;
    private List<Course> studentsList1 = new ArrayList<>();

    My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        user = getIntent().getStringExtra("USER");

        // مراقبة الـ LiveData للكورسات المحفوظة
        myViewModel.getBookmarkedCoursesByStudent(user).observe(this, bookmarkedCourses -> {
            if (bookmarkedCourses != null && !bookmarkedCourses.isEmpty()) {
                studentsList1.clear(); // تأكد من مسح القائمة قبل إضافة العناصر الجديدة

                // عملية غير متزامنة للحصول على جميع الكورسات المرتبطة بـ Student_Course
                for (Student_Course studentCourse : bookmarkedCourses) {
                    myViewModel.getAllCoursesById(studentCourse.getCourse_ID()).observe(this, courses -> {
                        if (courses != null && !courses.isEmpty()) {
                            studentsList1.addAll(courses); // إضافة الكورسات إلى القائمة

                            // بعد تحديث الـ studentsList1، تأكد من أن واجهة المستخدم تتحدث
                            runOnUiThread(() -> {
                                adapter = new CourseAdapter(this, studentsList1, user);
                                recyclerView.setAdapter(adapter); // تعيين الـ Adapter
                            });
                        }
                    });
                }

            } else {
                // عرض رسالة في حال عدم وجود كورسات محفوظة
                Toast.makeText(this, "لا توجد كورسات محفوظة!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
