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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Button> buttons = new ArrayList<>();
    private Button activeButton = null;
    Button btnAll;
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
    RecyclerView recyclerView;
    ImageView Iv_notification;
    ImageView Iv_Bookmark;
    String students_u;
    ImageView iv_S;
    String bio;
    private My_View_Model myViewModel;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // قم بتحميل واجهة المستخدم الخاصة بالـ Fragment (layout)
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);


        args = getArguments();
        if (args != null) {
            students_u = args.getString("USER_NAME");


        }
        Log.d("MainActivity_Main", "MMMMMMMMMMMMMNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNMMMMMMMMMMMMMMMMMMMMMMMMM " + students_u);

        bundle = new Bundle();
        bundle.putString("USER_NAME_R", students_u);

// إنشاؤ Fragment الجديد
        Fragment targetFragment = new ReviewsFragment();
        Fragment fragment2 = new CourseFragment();
        fragment2.setArguments(bundle);
// ضبط البيانات المرسلة على الـ Fragment
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
        // استدعاء CourseFragment وتحميل الدورات عند تحميل الفراجمنت
        CourseFragment fragment = (CourseFragment) getParentFragmentManager()
                .findFragmentById(R.id.fram_corse);

        if (fragment != null) {
            fragment.loadCourses(); // استدعاء الدالة داخل الفراجمنت
            onButtonClicked(btnAll);  // تفعيل الزر الافتراضي
        }

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

//        assert getArguments() != null;
//        String user_student = getArguments().getString("USER_NAME");


        Log.d("HomeFragment1111", "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" + students_u);

        myViewModel.getAllStudentByUser(students_u).observe(getViewLifecycleOwner(), students -> {
            // استخدام LinearLayoutManager مع التمرير الأفقي
            if (students != null && !students.isEmpty()) {

                Student student = students.get(0);
                String student_name = student.getS_name().toString();
                bio = student.getBio().toString();
                if (bio.isEmpty()){
                    tv_Welcom.setText("Welcom");


                }else {
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

        //addSampleStudents();
        // addSampleTeachers();
        //  addSampleCourses();


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
                Log.d("MainActivity_Main", "/////////////////////////////////////////////////////////// " + students_u);

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


        //   Course_Dao.updateBookmarkStatus(Course.getCourse_ID(), Course.isBookmarked());
        if (savedInstanceState == null) {
            CourseFragment courseFragment = new CourseFragment();
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME_R", students_u);
            courseFragment.setArguments(bundle);

// استبدل R.id.frame_container بالـ ID الخاص بـ FrameLayout
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fram_corse, courseFragment)
                    .commit();
            onButtonClicked(btnAll);

        }
        // تحميل الفراجمنت داخل FrameLayout


        //   Course_Dao.updateBookmarkStatus(Course.getCourse_ID(), Course.isBookmarked());


//        CourseFragment fragment = (CourseFragment) getFragmentManager()
//                .findFragmentById(R.id.fram_corse);
//
//        if (fragment != null) {
//            fragment.loadCourses(); // استدعاء الدالة داخل الفراجمنت
//            onButtonClicked(btnAll);
//
//
//            //   Course_Dao.updateBookmarkStatus(Course.getCourse_ID(), Course.isBookmarked());
//
//
//        }

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
        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_Art(); // استدعاء الدالة داخل الفراجمنت
                    onButtonClicked(btnArt);


                    //   Course_Dao.updateBookmarkStatus(Course.getCourse_ID(), Course.isBookmarked());


                }

            }
        });

        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_Business(); // استدعاء الدالة داخل الفراجمنت
                    onButtonClicked(btnBusiness);


                    //   Course_Dao.updateBookmarkStatus(Course.getCourse_ID(), Course.isBookmarked());


                }

            }
        });

        btnProgramming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_Programming(); // استدعاء الدالة داخل الفراجمنت
                    onButtonClicked(btnProgramming);


                    //   Course_Dao.updateBookmarkStatus(Course.getCourse_ID(), Course.isBookmarked());


                }

            }
        });

        btn3DDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CourseFragment fragment = (CourseFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadCourses_Categorie_3D_Design(); // استدعاء الدالة داخل الفراجمنت
                    onButtonClicked(btn3DDesign);


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
                intent.putExtra("STUDENT_USER",students_u);
                startActivity(intent);
            }
        });
        tv_Seeall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      loadTeacher();
                Intent intent = new Intent(requireContext(), BookmarkActivity.class);
                intent.putExtra("USER", students_u);
                Log.d("MainActivity_Main", "/////////////////////////////////////////////////////////// " + students_u);

                startActivity(intent);
            }
        });


        loadTeacher();


        return rootView;
    }

    public void loadTeacher() {
        // جلب المدرسين من ViewModel
        myViewModel.getAllTeacher().observe(getViewLifecycleOwner(), teachers -> {
            // استخدام LinearLayoutManager مع التمرير الأفقي
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);


            teacherAdapter = new TeacherAdapter(requireContext(), new ArrayList<>(),students_u);

            teacherRecyclerView.setLayoutManager(layoutManager);


            teacherAdapter.setTeacherList(teachers);
            teacherRecyclerView.setAdapter(teacherAdapter);

        });
    }


//    private void addSampleTeacher() {
//
////        Teacher teacher = new Teacher("adnan", "ahmad1", "programing", "ahmad123", null);
//        Teacher teacher1 = new Teacher("ADNAN", "ADNAN", "programing", "ADNAN", null);
////        myViewModel.insertTeacher(teacher);
//        myViewModel.insertTeacher(teacher1);
//
//
////        try {
////            Teacher teacher = new Teacher("ADNAN12", "Adnan1", "adnan1234", "programing", null, 1234);
////            myViewModel.insertTeacher(teacher);
////            Log.d("Teacher", "Teacher added successfully");
////        } catch (Exception e) {
////            Log.e("Teacher", "Error adding teacher: " + e.getMessage());
////        }
//
////        try {
////            Teacher teacher = new Teacher("Adnan", null);
////            Teacher teacher1 = new Teacher("Adnan", null);
////            Teacher teacher2 = new Teacher("Adnan", null);
//        // My_Database.databaseWriteExecutor.execute(() -> {
//
////                myViewModel.insertTeacher(teacher);
////                myViewModel.insertTeacher(teacher1);
//        //  myViewModel.insertTeacher(teacher2);
//
//
//        //   });
////        }
//
//
////        catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//
////        if(savedInstanceState ==null)
////
////    {
////        getSupportFragmentManager()
////                .beginTransaction()
////                .replace(R.id.flFragment, new CourseFragment())
////                .commit();
////    }


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