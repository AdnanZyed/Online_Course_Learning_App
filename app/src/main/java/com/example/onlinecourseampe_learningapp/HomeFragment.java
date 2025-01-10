package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayOutputStream;
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
                Intent intent = new Intent(requireContext(), TeacherProfileActivity.class);
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
            fragment.loadCourses_Categorie_Art(); // استدعاء الدالة داخل الفراجمنت
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
            fragment.loadCourses_Categorie_Art(); // استدعاء الدالة داخل الفراجمنت
            onButtonClicked(btnProgramming);


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


    // تحويل الصورة إلى Byte Array
    private byte[] convertImageToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    // إنشاء البيانات التجريبية وإدخالها
    private void addSampleCourses() {
        // تحويل الصورة من الموارد إلى Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes = convertImageToByteArray(bitmap); // تحويل الصورة إلى Byte Array

        Bitmap bitmap1 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes1 = convertImageToByteArray(bitmap1); // تحويل الصورة إلى Byte Array

        Bitmap bitmap2 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.f);
        byte[] imageBytes2 = convertImageToByteArray(bitmap2); // تحويل الصورة إلى Byte Array

        Bitmap bitmap3 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes3 = convertImageToByteArray(bitmap3); // تحويل الصورة إلى Byte Array

        Bitmap bitmap4 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes4 = convertImageToByteArray(bitmap4); // تحويل الصورة إلى Byte Array

        Bitmap bitmap5 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes5 = convertImageToByteArray(bitmap5); // تحويل الصورة إلى Byte Array

        Bitmap bitmap6 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.sample_course_image);
        byte[] imageBytes6 = convertImageToByteArray(bitmap6); // تحويل الصورة إلى Byte Array

        Bitmap bitmap7 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes7 = convertImageToByteArray(bitmap7); // تحويل الصورة إلى Byte Array

        Bitmap bitmap8 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes8 = convertImageToByteArray(bitmap8); // تحويل الصورة إلى Byte Array

        Bitmap bitmap9 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes9 = convertImageToByteArray(bitmap9); // تحويل الصورة إلى Byte Array

        Bitmap bitmap10 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes10 = convertImageToByteArray(bitmap10); // تحويل الصورة إلى Byte Array

        Bitmap bitmap11 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.sample_course_image);
        byte[] imageBytes11 = convertImageToByteArray(bitmap11); // تحويل الصورة إلى Byte Array
        Bitmap bitmap12 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes12 = convertImageToByteArray(bitmap12); // تحويل الصورة إلى Byte Array
        Bitmap bitmap13 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes13 = convertImageToByteArray(bitmap13); // تحويل الصورة إلى Byte Array
        Bitmap bitmap14 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.f);
        byte[] imageBytes14 = convertImageToByteArray(bitmap14); // تحويل الصورة إلى Byte Array
        Bitmap bitmap15 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes15 = convertImageToByteArray(bitmap15); // تحويل الصورة إلى Byte Array
        Bitmap bitmap16 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes16 = convertImageToByteArray(bitmap16); // تحويل الصورة إلى Byte Array
        Bitmap bitmap17 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes17 = convertImageToByteArray(bitmap17); // تحويل الصورة إلى Byte Array
        Bitmap bitmap18 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.sample_course_image);
        byte[] imageBytes18 = convertImageToByteArray(bitmap18); // تحويل الصورة إلى Byte Array
        Bitmap bitmap19 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes19 = convertImageToByteArray(bitmap19); // تحويل الصورة إلى Byte Array
        Bitmap bitmap20 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes20 = convertImageToByteArray(bitmap20); // تحويل الصورة إلى Byte Array
        Bitmap bitmap21 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.sample_course_image);
        byte[] imageBytes21 = convertImageToByteArray(bitmap21); // تحويل الصورة إلى Byte Array
        Bitmap bitmap22 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes22 = convertImageToByteArray(bitmap22); // تحويل الصورة إلى Byte Array
        Bitmap bitmap23 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes23 = convertImageToByteArray(bitmap23); // تحويل الصورة إلى Byte Array
        Bitmap bitmap24 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.f);
        byte[] imageBytes24 = convertImageToByteArray(bitmap24); // تحويل الصورة إلى Byte Array
        Bitmap bitmap25 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes25 = convertImageToByteArray(bitmap25); // تحويل الصورة إلى Byte Array
        Bitmap bitmap26 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes26 = convertImageToByteArray(bitmap26); // تحويل الصورة إلى Byte Array
        Bitmap bitmap27 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes27 = convertImageToByteArray(bitmap27); // تحويل الصورة إلى Byte Array
        Bitmap bitmap28 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.sample_course_image);
        byte[] imageBytes28 = convertImageToByteArray(bitmap28); // تحويل الصورة إلى Byte Array
        Bitmap bitmap29 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.f);
        byte[] imageBytes29 = convertImageToByteArray(bitmap29); // تحويل الصورة إلى Byte Array
        Bitmap bitmap30 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes30 = convertImageToByteArray(bitmap30); // تحويل الصورة إلى Byte Array
        Bitmap bitmap31 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes31 = convertImageToByteArray(bitmap31); // تحويل الصورة إلى Byte Array
        Bitmap bitmap32 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes32 = convertImageToByteArray(bitmap32); // تحويل الصورة إلى Byte Array
        Bitmap bitmap33 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.f);
        byte[] imageBytes33 = convertImageToByteArray(bitmap33); // تحويل الصورة إلى Byte Array
        Bitmap bitmap34 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.sample_course_image);
        byte[] imageBytes34 = convertImageToByteArray(bitmap34); // تحويل الصورة إلى Byte Array
        Bitmap bitmap35 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.d);
        byte[] imageBytes35 = convertImageToByteArray(bitmap35); // تحويل الصورة إلى Byte Array
        Bitmap bitmap36 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.s);
        byte[] imageBytes36 = convertImageToByteArray(bitmap36); // تحويل الصورة إلى Byte Array
        Bitmap bitmap37 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.a);
        byte[] imageBytes37 = convertImageToByteArray(bitmap37); // تحويل الصورة إلى Byte Array


        Course course1 = new Course(0, "3D Design", imageBytes, 100, "3D Design", "Learn the basics of programming", "John Doe", null, false, "john_doe");
        Course course2 = new Course(0, "3D Design", imageBytes1, 150, "3D Design", "Master Java programming", "Jane Smith", null, false, "john_doe");
        Course course3 = new Course(0, "Web Development", imageBytes2, 200, "3D Design", "Learn to build websites", "Alice Brown", null, false, "john_doe");
        Course course4 = new Course(0, "Mobile Development", imageBytes3, 180, "3D Design", "Create mobile applications", "Bob White", null, false, "john_doe");
        Course course5 = new Course(0, "Data Science", imageBytes4, 250, "3D Design", "Learn data science", "Charlie Green", null, false, "john_doe");
        Course course6 = new Course(0, "Machine Learning", imageBytes5, 220, "3D Design", "Introduction to machine learning", "Dave Black", null, false, "jane_smith");
        Course course7 = new Course(0, "Blockchain Basics", imageBytes6, 300, "3D Design", "Learn about blockchain", "Eve Blue", null, false, "jane_smith");
        Course course8 = new Course(0, "UI/UX Design", imageBytes7, 120, "3D Design", "Master UI/UX design", "Frank Pink", null, false, "jane_smith");
        Course course9 = new Course(0, "Cybersecurity", imageBytes8, 350, "3D Design", "Learn about cybersecurity", "Grace Yellow", null, false, "jane_smith");
        Course course10 = new Course(0, "Cloud Computing", imageBytes9, 280, "3D Design", "Understand cloud computing", "Harry Red", null, false, "jane_smith");
        Course course11 = new Course(0, "AI Fundamentals", imageBytes10, 230, "3D Design", "Learn the basics of artificial intelligence", "Ivy Green", null, false, "alice_brown");
        Course course12 = new Course(0, "App Development", imageBytes11, 170, "3D Design", "Develop apps for Android and iOS", "Jack White", null, false, "alice_brown");
        Course course13 = new Course(0, "Game Development", imageBytes12, 210, "3D Design", "Learn how to create games", "Kylie Blue", null, false, "alice_brown");
        Course course14 = new Course(0, "Database Design", imageBytes13, 240, "3D Design", "Understand database design", "Leo Brown", null, false, "alice_brown");
        Course course15 = new Course(0, "Digital Marketing", imageBytes14, 160, "Business", "Learn the basics of digital marketing", "Mia Green", null, false, "alice_brown");
        Course course16 = new Course(0, "Cloud Security", imageBytes15, 300, "Business", "Learn about cloud security", "Nathan Yellow", null, false, "bob_white");
        Course course17 = new Course(0, "Network Fundamentals", imageBytes16, 130, "Business", "Learn the basics of networking", "Olivia White", null, false, "bob_white");
        Course course18 = new Course(0, "Robotics", imageBytes17, 330, "Business", "Learn robotics and automation", "Paul Black", null, false, "bob_white");
        Course course19 = new Course(0, "Full Stack Development", imageBytes18, 260, "Business", "Master full-stack web development", "Quinn Blue", null, false, "bob_white");
        Course course20 = new Course(0, "Data Analytics", imageBytes19, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, "bob_white");
        Course course21 = new Course(0, "Data Analytics", imageBytes20, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, "bob_white");
        Course course22 = new Course(0, "Data Analytics", imageBytes21, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, "bob_white");
        Course course23 = new Course(0, "Data Analytics", imageBytes22, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, "bob_white");
        Course course24 = new Course(0, "Data Analytics", imageBytes23, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, "alice_brown");
        Course course25 = new Course(0, "Data Analytics", imageBytes24, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, "alice_brown");
        Course course26 = new Course(0, "Data Analytics", imageBytes25, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, "alice_brown");
        Course course27 = new Course(0, "Data Analytics", imageBytes26, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, "alice_brown");
        Course course28 = new Course(0, "Data Analytics", imageBytes27, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, "alice_brown");
        Course course29 = new Course(0, "Data Analytics", imageBytes28, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, "jane_smith");
        Course course30 = new Course(0, "Data Analytics", imageBytes29, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, "jane_smith");
        Course course31 = new Course(0, "Data Analytics", imageBytes30, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, "jane_smith");
        Course course32 = new Course(0, "Data Analytics", imageBytes31, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, "jane_smith");
        Course course33 = new Course(0, "Data Analytics", imageBytes32, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, "jane_smith");
        Course course34 = new Course(0, "Data Analytics", imageBytes33, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, "jane_smith");
        Course course35 = new Course(0, "Data Analytics", imageBytes34, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, "john_doe");
        Course course36 = new Course(0, "Data Analytics", imageBytes35, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, "john_doe");
        Course course37 = new Course(0, "Data Analytics", imageBytes36, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, "john_doe");
        Course course38 = new Course(0, "Data Analytics", imageBytes37, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, "john_doe");

        myViewModel.insertCourse(course1);
        myViewModel.insertCourse(course2);
        myViewModel.insertCourse(course3);
        myViewModel.insertCourse(course4);
        myViewModel.insertCourse(course5);
        myViewModel.insertCourse(course6);
        myViewModel.insertCourse(course7);
        myViewModel.insertCourse(course8);
        myViewModel.insertCourse(course9);
        myViewModel.insertCourse(course10);
        myViewModel.insertCourse(course11);
        myViewModel.insertCourse(course12);
        myViewModel.insertCourse(course13);
        myViewModel.insertCourse(course14);
        myViewModel.insertCourse(course15);
        myViewModel.insertCourse(course16);
        myViewModel.insertCourse(course17);
        myViewModel.insertCourse(course18);
        myViewModel.insertCourse(course19);
        myViewModel.insertCourse(course20);
        myViewModel.insertCourse(course21);
        myViewModel.insertCourse(course22);
        myViewModel.insertCourse(course23);
        myViewModel.insertCourse(course24);
        myViewModel.insertCourse(course25);
        myViewModel.insertCourse(course26);
        myViewModel.insertCourse(course27);
        myViewModel.insertCourse(course28);
        myViewModel.insertCourse(course29);
        myViewModel.insertCourse(course30);
        myViewModel.insertCourse(course31);
        myViewModel.insertCourse(course32);
        myViewModel.insertCourse(course33);
        myViewModel.insertCourse(course34);
        myViewModel.insertCourse(course35);
        myViewModel.insertCourse(course36);
        myViewModel.insertCourse(course37);
        myViewModel.insertCourse(course38);
    }

//    private void addSampleCourses() {
//        Course course1 = new Course(0, "Programming Basics", null, 100, "Programming", "Learn the basics of programming", "John Doe", null, false,"ahmad");
//        // Course course2 = new Course(0, "3D Design", 150, "3D Design", "Create amazing 3D models", "Jane Smith");
////        Course course3 = new Course(0, "3D Design", null, 150, "3D Design", "Create amazing 3D models", "Jane Smith", null);
////        Course course4 = new Course(0, "3D Design", null, 150, "3D Design", "Create amazing 3D models", "Jane Smith", null);
//
//
//        myViewModel.insertCourse(course1);
////        myViewModel.insertCourse(course2);
////        myViewModel.insertCourse(course3);
////        myViewModel.insertCourse(course4);
////        for(int i=0;i<=25;i++){
//        // myViewModel.deleteCourse(course1);
//
//    }

//    private void addSampleStudent() {
//        Student student = new Student("adnan", "adnan123", 5108708, "Adnan", null);
//        myViewModel.insertStudent(student);
//
//    }
//


    private void addSampleTeachers() {
        // تحويل الصورة من الموارد إلى Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.unnamed);
        byte[] imageBytes = convertImageToByteArray(bitmap); // تحويل الصورة إلى Byte Array
        Bitmap bitmap1 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.unnamed);
        byte[] imageBytes1 = convertImageToByteArray(bitmap1); // تحويل الصورة إلى Byte Array
        Bitmap bitmap2 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.unnamed);
        byte[] imageBytes2 = convertImageToByteArray(bitmap2); // تحويل الصورة إلى Byte Array
        Bitmap bitmap3 = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.unnamed);
        byte[] imageBytes3= convertImageToByteArray(bitmap3); // تحويل الصورة إلى Byte Array


        Teacher teacher1 = new Teacher("john_doe", "John Doe", "MSc in Computer Science", "password123", imageBytes);
        Teacher teacher2 = new Teacher("jane_smith", "Jane Smith", "PhD in Software Engineering", "password123", imageBytes2);
        Teacher teacher3 = new Teacher("alice_brown", "Alice Brown", "MSc in Web Development", "password123", imageBytes3);
        Teacher teacher4 = new Teacher("bob_white", "Bob White", "BSc in Mobile Computing", "password123", imageBytes1);

        myViewModel.insertTeacher(teacher1);
        myViewModel.insertTeacher(teacher2);
        myViewModel.insertTeacher(teacher3);
        myViewModel.insertTeacher(teacher4);
    }

    private void addSampleStudents() {
        for (int i = 1; i <= 50; i++) {
            String studentUsername = "student" + i;
            Student student = new Student(studentUsername, "password" + i, 123456789, "Student " + i, null);
            myViewModel.insertStudent(student);

            // ربط الطالب بالكورسات
            int courseId = (i % 20) + 1; // تحديد الكورس بناءً على رقم الطالب
            Student_Course studentCourse = new Student_Course(studentUsername, courseId);
            myViewModel.insertStudentCourse(studentCourse);

            // ربط الطالب بالمدرس
            String teacherUsername = "teacher" + (i % 4 + 1); // ربط الطالب بأحد المدرسين الأربعة
            Student_Teacher studentTeacher = new Student_Teacher(studentUsername, teacherUsername);
            myViewModel.insertStudentTeacher(studentTeacher);
        }
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