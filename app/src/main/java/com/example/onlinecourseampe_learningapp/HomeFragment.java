package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Button> buttons = new ArrayList<>();
    private Button activeButton = null;
    Button btnAll;
    Button btn3DDesign;
    RecyclerView teacherRecyclerView;
    TeacherAdapter teacherAdapter;
    Button btnProgramming;
    Button btnBusiness;
    Button btnArt;
    TextView tv_Seeall;
    TextView tv_Seeall2;
    RecyclerView recyclerView;
    ImageView Iv_notification;
    ImageView Iv_Bookmark;
    private My_View_Model myViewModel;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // قم بتحميل واجهة المستخدم الخاصة بالـ Fragment (layout)
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);


        btnAll = rootView.findViewById(R.id.btn_all);
        Iv_notification = rootView.findViewById(R.id.iv_notification);
        Iv_Bookmark = rootView.findViewById(R.id.iv_Bookmark);
        tv_Seeall = rootView.findViewById(R.id.tv_seeall);
        tv_Seeall2 = rootView.findViewById(R.id.tv_seeall2);
        btn3DDesign = rootView.findViewById(R.id.btn_3d_design);
        btnProgramming = rootView.findViewById(R.id.btn_programming);
        teacherRecyclerView = rootView.findViewById(R.id.rv_t);

        btnBusiness = rootView.findViewById(R.id.btn_business);
        btnArt = rootView.findViewById(R.id.btn_art);
        // إضافة الأزرار إلى القائمة

        buttons.add(btnAll);
        buttons.add(btn3DDesign);
        buttons.add(btnProgramming);
        buttons.add(btnBusiness);
        buttons.add(btnArt);
        // تحديد الزر الافتراضي (على سبيل المثال btnAll)
        Button defaultButton = btnAll;
        defaultButton.setBackgroundResource(R.drawable.catigories_btn_selected);
        defaultButton.setTextColor(R.color.white);
        // ضبط الاستماع للنقر على كل زر
        for (Button button : buttons) {
            button.setOnClickListener(v -> onButtonClicked(button));
        }

       // addSampleStudent();
      // addSampleTeacher();
     //   addSampleCourses();
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
                startActivity(intent);
            }
        });
        // استدعاء دالة حذف الكورسات من ViewModel
        // myViewModel.deleteAllCourses();
        // myViewModel.deleteAllCourses();
        //addSampleCourses();

//        Course course=new Course();
//        My_Database.databaseWriteExecutor.execute(() -> {
//
//        myViewModel.insertCourse(course);
//        });
        // addSampleTeacher();
        // إضافة بيانات أولية (إذا أردت)
        //   addSampleCourses();
        // إضافة مستمع النقر لكل زر
//        for (Button button : buttons) {
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onButtonClicked(button);
//                }
//            });
//        }

        // تحميل الفراجمنت داخل FrameLayout
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fram_corse, new CourseFragment())
                    .commit();
        }


        // استدعاء الكورسات عند الضغط على الزر
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses(); // استدعاء الدالة داخل الفراجمنت
                    onButtonClicked(btnAll);


                    //   Course_Dao.updateBookmarkStatus(Course.getCourse_ID(), Course.isBookmarked());


                }
            }
        });

        // تحديث حالة المفضلة عند الضغط على زر
//        Iv_Bookmark.setOnClickListener(v -> {
//            int courseId = 1;  // استخدم الـ courseId المناسب هنا
//            boolean isBookmarked = true;  // يمكنك التبديل بين true و false حسب الحالة
//            myViewModel.updateBookmarkStatusAndGetCourses(courseId, isBookmarked).observe(getViewLifecycleOwner(), updatedCourses -> {
//                if (updatedCourses != null) {
//                    // تحديث الـ RecyclerView بعد التعديل
//                    CourseAdapter adapter = new CourseAdapter(getContext(), updatedCourses);
//                    recyclerView.setAdapter(adapter);
//                }
//            });
//        });


        tv_Seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      loadTeacher();
                Intent intent = new Intent(requireContext(), TopMentors.class);
                startActivity(intent);
            }
        });

        tv_Seeall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), CourseDetailsActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

    public void loadTeacher() {
        // جلب المدرسين من ViewModel
        myViewModel.getAllTeacher().observe(getViewLifecycleOwner(), teachers -> {
            // استخدام LinearLayoutManager مع التمرير الأفقي
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);


            teacherAdapter = new TeacherAdapter(requireContext(), new ArrayList<>());

            teacherRecyclerView.setLayoutManager(layoutManager);

            teacherRecyclerView.setAdapter(teacherAdapter);
            teacherAdapter.setTeacherList(teachers);

        });
    }


    private void addSampleCourses() {
        Course course1 = new Course(0, "Programming Basics", null, 100, "Programming", "Learn the basics of programming", "John Doe", null, false,"ahmad");
        // Course course2 = new Course(0, "3D Design", 150, "3D Design", "Create amazing 3D models", "Jane Smith");
//        Course course3 = new Course(0, "3D Design", null, 150, "3D Design", "Create amazing 3D models", "Jane Smith", null);
//        Course course4 = new Course(0, "3D Design", null, 150, "3D Design", "Create amazing 3D models", "Jane Smith", null);


        myViewModel.insertCourse(course1);
//        myViewModel.insertCourse(course2);
//        myViewModel.insertCourse(course3);
//        myViewModel.insertCourse(course4);
//        for(int i=0;i<=25;i++){
        // myViewModel.deleteCourse(course1);

    }

    private void addSampleStudent() {
        Student student = new Student("adnan", "adnan123", 5108708, "Adnan", null);
        myViewModel.insertStudent(student);

    }
    // }

    private void addSampleTeacher() {

//        Teacher teacher = new Teacher("adnan", "ahmad1", "programing", "ahmad123", null);
        Teacher teacher1 = new Teacher("ADNAN", "ADNAN", "programing", "ADNAN", null);
//        myViewModel.insertTeacher(teacher);
        myViewModel.insertTeacher(teacher1);


//        try {
//            Teacher teacher = new Teacher("ADNAN12", "Adnan1", "adnan1234", "programing", null, 1234);
//            myViewModel.insertTeacher(teacher);
//            Log.d("Teacher", "Teacher added successfully");
//        } catch (Exception e) {
//            Log.e("Teacher", "Error adding teacher: " + e.getMessage());
//        }

//        try {
//            Teacher teacher = new Teacher("Adnan", null);
//            Teacher teacher1 = new Teacher("Adnan", null);
//            Teacher teacher2 = new Teacher("Adnan", null);
        // My_Database.databaseWriteExecutor.execute(() -> {

//                myViewModel.insertTeacher(teacher);
//                myViewModel.insertTeacher(teacher1);
        //  myViewModel.insertTeacher(teacher2);


        //   });
//        }


//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }


//        if(savedInstanceState ==null)
//
//    {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.flFragment, new CourseFragment())
//                .commit();
//    }


    @SuppressLint("ResourceAsColor")
    private void onButtonClicked(Button clickedButton) {
        // إعادة جميع الأزرار إلى الشكل الافتراضي
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.catigories_btn);
            button.setTextColor(R.color.blue);
        }


        // تغيير شكل الزر المضغوط
        clickedButton.setBackgroundResource(R.drawable.catigories_btn_selected);
        clickedButton.setTextColor(R.color.white);

        // تحديث الزر النشط
        activeButton = clickedButton;


    }
}