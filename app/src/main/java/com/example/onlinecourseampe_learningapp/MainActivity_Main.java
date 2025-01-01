package com.example.onlinecourseampe_learningapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Main extends AppCompatActivity

        implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private List<Button> buttons = new ArrayList<>();
    private Button activeButton = null;
    Button btnAll;
    Button btn3DDesign;
    Button btnProgramming;
    Button btnBusiness;
    Button btnArt;
    TextView tv_seeall;
    HomeFragment homeFragment = new HomeFragment();
    Prophile_Fragment prophileFragment = new Prophile_Fragment();
    CoursesFragment coursesFragment = new CoursesFragment();
    CartFragment cartFragment = new CartFragment();
    InboxFragment inboxFragment = new InboxFragment();
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        // إعداد ViewModel
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        //A
//adnan

        Course course=new Course();
        My_Database.databaseWriteExecutor.execute(() -> {

        myViewModel.insertCourse(course);
        });
        //  addSampleTeacher();
        // إضافة بيانات أولية (إذا أردت)
        //   addSampleCourses();
        initializeButtons();
        // إضافة مستمع النقر لكل زر
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClicked(button);
                }
            });
        }

        // تحميل الفراجمنت داخل FrameLayout
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new CourseFragment())
                    .commit();
        }


        // استدعاء الكورسات عند الضغط على الزر
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CourseFragment fragment = (CourseFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.flFragment);

                if (fragment != null) {
                    fragment.loadCourses(); // استدعاء الدالة داخل الفراجمنت
                }
            }
        });
    }

    private void initializeButtons() {
        // زر عرض الكورسات


        btnAll = findViewById(R.id.btn_all);
        // ربط الأزرار

        btn3DDesign = findViewById(R.id.btn_3d_design);
        btnProgramming = findViewById(R.id.btn_programming);
        btnBusiness = findViewById(R.id.btn_business);
        btnArt = findViewById(R.id.btn_art);
        // إضافة الأزرار إلى القائمة

        buttons.add(btnAll);
        buttons.add(btn3DDesign);
        buttons.add(btnProgramming);
        buttons.add(btnBusiness);
        buttons.add(btnArt);

        for (Button button : buttons) {
            button.setOnClickListener(v -> onButtonClicked(button));
        }
    }

    private void addSampleCourses() {
        Course course1 = new Course(0, "Programming Basics", null, 100, "Programming", "Learn the basics of programming", "John Doe", null);
        Course course2 = new Course(0, "3D Design", null, 150, "3D Design", "Create amazing 3D models", "Jane Smith", null);

        myViewModel.insertCourse(course1);
        myViewModel.insertCourse(course2);
    }

    private void addSampleTeacher() {
        try {
//            Teacher teacher = new Teacher("Adnan", null);
//            Teacher teacher1 = new Teacher("Adnan", null);
//            Teacher teacher2 = new Teacher("Adnan", null);
            Teacher teacher2 = new Teacher("Adnan");

            My_Database.databaseWriteExecutor.execute(() -> {

//                myViewModel.insertTeacher(teacher);
//                myViewModel.insertTeacher(teacher1);
                myViewModel.insertTeacher(teacher2);


            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//        if(savedInstanceState ==null)
//
//    {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.flFragment, new CourseFragment())
//                .commit();
//    }


    private void onButtonClicked(Button clickedButton) {
        // إعادة لون الزر النشط السابق إلى اللون الافتراضي
        if (activeButton != null) {
            activeButton.setBackgroundColor(Color.parseColor("#008577")); // اللون الافتراضي (Teal)
        }

        // تغيير لون الزر الذي تم النقر عليه
        clickedButton.setBackgroundColor(Color.parseColor("#FF6200EE")); // اللون الجديد (Purple)

        // تحديث الزر النشط
        activeButton = clickedButton;


//        Today's Special
//
//        Get a discount for every course ordert
//        Only volid for today!
//
//


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.prophile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, prophileFragment)
                        .commit();
                return true;

            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeFragment)
                        .commit();
                return true;

            case R.id.courses:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, coursesFragment)
                        .commit();
                return true;
            case R.id.cart:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, cartFragment)
                        .commit();
                return true;
            case R.id.inbox:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, inboxFragment)
                        .commit();
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}
