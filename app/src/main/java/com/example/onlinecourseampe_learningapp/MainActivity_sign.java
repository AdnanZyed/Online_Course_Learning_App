package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinecourseampe_learningapp.databinding.ActivityMainSignBinding;

import java.io.ByteArrayOutputStream;

public class MainActivity_sign extends AppCompatActivity {
    private My_View_Model myViewModel;

    private ActivityMainSignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainSignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

//        addSampleTeachers();
//        addSampleCourses();

        myViewModel.getAllTeacher().observe(this, teachers -> {
            if (teachers == null || teachers.isEmpty()) {
                addSampleTeachers();
            }
        });
        myViewModel.getAllCourse().observe(this, courses -> {
            if (courses == null || courses.isEmpty()) {
                addSampleCourses();
            }
        });
       addSampleLessones();

//
//
//        myViewModel.getAllStudent().observe(this, students -> {
//            if (students == null || students.isEmpty()) {
//                addSampleStudents();
//            }
//        });

        binding.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        binding.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        binding.SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_sign.this, ActivityMainSignIn.class);
                startActivity(intent);
            }
        });
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_sign.this, Sign_up.class);
                startActivity(intent);

            }
        });


    }

    private byte[] convertImageToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    // إنشاء البيانات التجريبية وإدخالها
    private void addSampleLessones() {
        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم ثلاثي الأبعاد باستخدام Blender", "https://www.youtube.com/watch?v=VG8R7QGdGp8", 10, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تعلم أساسيات النمذجة في 3D Max", "https://www.youtube.com/watch?v=3Ic4kF3rdzQ", 12, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "كيفية إنشاء مشاهد واقعية في Maya", "https://www.youtube.com/watch?v=FvBqDqsmHzI", 8, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "إضاءة المشاهد ثلاثية الأبعاد في Cinema 4D", "https://www.youtube.com/watch?v=fsAxSxp88ZQ", 15, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الشخصيات في Blender", "https://www.youtube.com/watch?v=6dXM8Gocv6k", 13, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم بيئات ثلاثية الأبعاد في 3D Max", "https://www.youtube.com/watch?v=2U5M6vI6xzY", 14, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة مؤثرات خاصة في Maya", "https://www.youtube.com/watch?v=kYgYbEo7HTo", 11, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تصدير النماذج ثلاثية الأبعاد للألعاب", "https://www.youtube.com/watch?v=6MQyZyQDRh4", 9, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم الشخصيات ثلاثية الأبعاد", "https://www.youtube.com/watch?v=k1pS5lfH2kc", 12, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تقنيات الإكساء في Cinema 4D", "https://www.youtube.com/watch?v=nl5mcYIu7Fk", 16, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الكاميرا في مشاهد 3D Max", "https://www.youtube.com/watch?v=6dFtkdZTkmE", 17, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة إضاءة واقعية في Blender", "https://www.youtube.com/watch?v=0RPylvtaHhE", 10, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم مواد واقعية في Maya", "https://www.youtube.com/watch?v=mrctuUwEryo", 8, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم المشاهد الداخلية في 3D Max", "https://www.youtube.com/watch?v=JlxWqg7xeQU", 14, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الشخصيات في Cinema 4D", "https://www.youtube.com/watch?v=0VZqzxbzXYY", 10, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة مؤثرات خاصة في Blender", "https://www.youtube.com/watch?v=a4kDk1s5de4", 13, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم بيئات طبيعية في Maya", "https://www.youtube.com/watch?v=pBiv8nq-XGA", 12, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تقنيات الإكساء المتقدمة في 3D Max", "https://www.youtube.com/watch?v=HkVgOlgw9yA", 11, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الكائنات في Blender", "https://www.youtube.com/watch?v=c56y_AiEcrE", 10, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة لتقنيات الـ 3D في الألعاب", "https://www.youtube.com/watch?v=c7Bx6m0bxg8", 9, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "إضاءة الأجسام في Maya", "https://www.youtube.com/watch?v=VK9wZG_7L6s", 15, false, 2));
        myViewModel.insertCourseLesson(new CourseLessons(0, "تعلم استخدام الـ UV Mapping في Blender", "https://www.youtube.com/watch?v=FFG5nCUu3tk", 12, false, 2));
    }

    private void addSampleCourses() {
        // تحويل الصورة من الموارد إلى Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes = convertImageToByteArray(bitmap); // تحويل الصورة إلى Byte Array

        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes1 = convertImageToByteArray(bitmap1); // تحويل الصورة إلى Byte Array

        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes2 = convertImageToByteArray(bitmap2); // تحويل الصورة إلى Byte Array

        Bitmap bitmap3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes3 = convertImageToByteArray(bitmap3); // f الصورة إلى Byte Array

        Bitmap bitmap4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes4 = convertImageToByteArray(bitmap4); // تحويل الصورة إلى Byte Array

        Bitmap bitmap5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes5 = convertImageToByteArray(bitmap5); // تحويل الصورة إلى Byte Array

        Bitmap bitmap6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes6 = convertImageToByteArray(bitmap6); // تحويل الصورة إلى Byte Array
        Bitmap bitmap7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes7 = convertImageToByteArray(bitmap7); // تحويل الصورة إلى Byte Array

        Bitmap bitmap8 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes8 = convertImageToByteArray(bitmap8); // تحويل الصورة إلى Byte Array

        Bitmap bitmap9 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes9 = convertImageToByteArray(bitmap9); // تحويل الصورة إلى Byte Array

        Bitmap bitmap10 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes10 = convertImageToByteArray(bitmap10); // تحويل الصورة إلى Byte Array

        Bitmap bitmap11 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes11 = convertImageToByteArray(bitmap11); // تحويل الصورة إلى Byte Array
        Bitmap bitmap12 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes12 = convertImageToByteArray(bitmap12); // تحويل الصورة إلى Byte Array
        Bitmap bitmap13 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes13 = convertImageToByteArray(bitmap13); // تحويل الصورة إلى Byte Array
        Bitmap bitmap14 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes14 = convertImageToByteArray(bitmap14); // تحويل الصورة إلى Byte Array
        Bitmap bitmap15 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes15 = convertImageToByteArray(bitmap15); // تحويل الصورة إلى Byte Array
        Bitmap bitmap16 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes16 = convertImageToByteArray(bitmap16); // تحويل الصورة إلى Byte Array
        Bitmap bitmap17 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes17 = convertImageToByteArray(bitmap17); // تحويل الصورة إلى Byte Array
        Bitmap bitmap18 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes18 = convertImageToByteArray(bitmap18); // تحويل الصورة إلى Byte Array
        Bitmap bitmap19 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes19 = convertImageToByteArray(bitmap19); // تحويل الصورة إلى Byte Array
        Bitmap bitmap20 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes20 = convertImageToByteArray(bitmap20); // تحويل الصورة إلى Byte Array
        Bitmap bitmap21 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes21 = convertImageToByteArray(bitmap21); // تحويل الصورة إلى Byte Array
        Bitmap bitmap22 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes22 = convertImageToByteArray(bitmap22); // تحويل الصورة إلى Byte Array
        Bitmap bitmap23 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes23 = convertImageToByteArray(bitmap23); // تحويل الصورة إلى Byte Array
        Bitmap bitmap24 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes24 = convertImageToByteArray(bitmap24); // تحويل الصورة إلى Byte Array
        Bitmap bitmap25 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes25 = convertImageToByteArray(bitmap25); // تحويل الصورة إلى Byte Array
        Bitmap bitmap26 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes26 = convertImageToByteArray(bitmap26); // تحويل الصورة إلى Byte Array
        Bitmap bitmap27 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes27 = convertImageToByteArray(bitmap27); // تحويل الصورة إلى Byte Array
        Bitmap bitmap28 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes28 = convertImageToByteArray(bitmap28); // تحويل الصورة إلى Byte Array
        Bitmap bitmap29 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes29 = convertImageToByteArray(bitmap29); // تحويل الصورة إلى Byte Array
        Bitmap bitmap30 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes30 = convertImageToByteArray(bitmap30); // تحويل الصورة إلى Byte Array
        Bitmap bitmap31 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes31 = convertImageToByteArray(bitmap31); // تحويل الصورة إلى Byte Array
        Bitmap bitmap32 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes32 = convertImageToByteArray(bitmap32); // تحويل الصورة إلى Byte Array
        Bitmap bitmap33 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes33 = convertImageToByteArray(bitmap33); // تحويل الصورة إلى Byte Array
        Bitmap bitmap34 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes34 = convertImageToByteArray(bitmap34); // تحويل الصورة إلى Byte Array
        Bitmap bitmap35 = BitmapFactory.decodeResource(this.getResources(), R.drawable.f);
        byte[] imageBytes35 = convertImageToByteArray(bitmap35); // تحويل الصورة إلى Byte Array
        Bitmap bitmap36 = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes36 = convertImageToByteArray(bitmap36); // تحويل الصورة إلى Byte Array
        Bitmap bitmap37 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes37 = convertImageToByteArray(bitmap37); // تحويل الصورة إلى Byte Array


        Course course1 = new Course(0, "3D Design", imageBytes, 100, "3D Design", "Learn the basics of programming", "John Doe", null, false, false, "john_doe");
        Course course2 = new Course(0, "3D Design", imageBytes1, 150, "3D Design", "Master Java programming", "Jane Smith", null, false, false, "john_doe");
        Course course3 = new Course(0, "Web Development", imageBytes2, 200, "3D Design", "Learn to build websites", "Alice Brown", null, false, false, "john_doe");
        Course course4 = new Course(0, "Mobile Development", imageBytes3, 180, "3D Design", "Create mobile applications", "Bob White", null, false, false, "john_doe");
        Course course5 = new Course(0, "Data Science", imageBytes4, 250, "3D Design", "Learn data science", "Charlie Green", null, false, false, "john_doe");
        Course course6 = new Course(0, "Machine Learning", imageBytes5, 220, "3D Design", "Introduction to machine learning", "Dave Black", null, false, false, "jane_smith");
        Course course7 = new Course(0, "Blockchain Basics", imageBytes6, 300, "3D Design", "Learn about blockchain", "Eve Blue", null, false, false, "jane_smith");
        Course course8 = new Course(0, "UI/UX Design", imageBytes7, 120, "3D Design", "Master UI/UX design", "Frank Pink", null, false, false, "jane_smith");
        Course course9 = new Course(0, "Cybersecurity", imageBytes8, 350, "3D Design", "Learn about cybersecurity", "Grace Yellow", null, false, false, "jane_smith");
        Course course10 = new Course(0, "Cloud Computing", imageBytes9, 280, "3D Design", "Understand cloud computing", "Harry Red", null, false, false, "jane_smith");
        Course course11 = new Course(0, "AI Fundamentals", imageBytes10, 230, "3D Design", "Learn the basics of artificial intelligence", "Ivy Green", null, false, false, "alice_brown");
        Course course12 = new Course(0, "App Development", imageBytes11, 170, "3D Design", "Develop apps for Android and iOS", "Jack White", null, false, false, "alice_brown");
        Course course13 = new Course(0, "Game Development", imageBytes12, 210, "3D Design", "Learn how to create games", "Kylie Blue", null, false, false, "alice_brown");
        Course course14 = new Course(0, "Database Design", imageBytes13, 240, "3D Design", "Understand database design", "Leo Brown", null, false, false, "alice_brown");
        Course course15 = new Course(0, "Digital Marketing", imageBytes14, 160, "Business", "Learn the basics of digital marketing", "Mia Green", null, false, false, "alice_brown");
        Course course16 = new Course(0, "Cloud Security", imageBytes15, 300, "Business", "Learn about cloud security", "Nathan Yellow", null, false, false, "bob_white");
        Course course17 = new Course(0, "Network Fundamentals", imageBytes16, 130, "Business", "Learn the basics of networking", "Olivia White", null, false, false, "bob_white");
        Course course18 = new Course(0, "Robotics", imageBytes17, 330, "Business", "Learn robotics and automation", "Paul Black", null, false, false, "bob_white");
        Course course19 = new Course(0, "Full Stack Development", imageBytes18, 260, "Business", "Master full-stack web development", "Quinn Blue", null, false, false, "bob_white");
        Course course20 = new Course(0, "Data Analytics", imageBytes19, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, false, "bob_white");
        Course course21 = new Course(0, "Data Analytics", imageBytes20, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, false, "bob_white");
        Course course22 = new Course(0, "Data Analytics", imageBytes21, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, false, "bob_white");
        Course course23 = new Course(0, "Data Analytics", imageBytes22, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, false, "bob_white");
        Course course24 = new Course(0, "Data Analytics", imageBytes23, 220, "Business", "Learn how to analyze data", "Rachel Green", null, false, false, "alice_brown");
        Course course25 = new Course(0, "Data Analytics", imageBytes24, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, false, "alice_brown");
        Course course26 = new Course(0, "Data Analytics", imageBytes25, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, false, "alice_brown");
        Course course27 = new Course(0, "Data Analytics", imageBytes26, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, false, "alice_brown");
        Course course28 = new Course(0, "Data Analytics", imageBytes27, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, false, "alice_brown");
        Course course29 = new Course(0, "Data Analytics", imageBytes28, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, false, "jane_smith");
        Course course30 = new Course(0, "Data Analytics", imageBytes29, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, false, "jane_smith");
        Course course31 = new Course(0, "Data Analytics", imageBytes30, 220, "Art", "Learn how to analyze data", "Rachel Green", null, false, false, "jane_smith");
        Course course32 = new Course(0, "Data Analytics", imageBytes31, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, false, "jane_smith");
        Course course33 = new Course(0, "Data Analytics", imageBytes32, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, false, "jane_smith");
        Course course34 = new Course(0, "Data Analytics", imageBytes33, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, false, "jane_smith");
        Course course35 = new Course(0, "Data Analytics", imageBytes34, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, false, "john_doe");
        Course course36 = new Course(0, "Data Analytics", imageBytes35, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, false, "john_doe");
        Course course37 = new Course(0, "Data Analytics", imageBytes36, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, false, "john_doe");
        Course course38 = new Course(0, "Data Analytics", imageBytes37, 220, "Programming", "Learn how to analyze data", "Rachel Green", null, false, false, "john_doe");

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
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed1);
        byte[] imageBytes = convertImageToByteArray(bitmap); // تحويل الصورة إلى Byte Array
        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed1);
        byte[] imageBytes1 = convertImageToByteArray(bitmap1); // تحويل الصورة إلى Byte Array
        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed1);
        byte[] imageBytes2 = convertImageToByteArray(bitmap2); // تحويل الصورة إلى Byte Array
        Bitmap bitmap3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed1);
        byte[] imageBytes3 = convertImageToByteArray(bitmap3); // تحويل الصورة إلى Byte Array


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

        Student student = new Student("userName", "Password", 1234, 1231231212, "ahmad", null);
        myViewModel.insertStudent(student);

        // ربط الطالب بالكورسات
        Student_Course studentCourse = new Student_Course(0, "userName", 0);
        myViewModel.insertStudentCourse(studentCourse);

        // ربط الطالب بالمدرس
        Student_Teacher studentTeacher = new Student_Teacher(0, "userName", "teacherUsername");
        myViewModel.insertStudentTeacher(studentTeacher);
    }
}