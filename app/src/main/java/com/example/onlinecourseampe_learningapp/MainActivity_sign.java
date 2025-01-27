package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

//        //  Handler handler = new Handler(Looper.getMainLooper());
//        addSampleTeachers();
//        addSampleCourses();
//        addSampleLessones();
//        addSampleStudents();
////// الخطوة 1: التحقق من المعلمين
//        handler.postDelayed(() -> {
//            myViewModel.getAllTeacher().observe(this, teachers -> {
//                if (teachers == null || teachers.isEmpty()) {
//                    addSampleTeachers();
//                }
//            });
//        }, 0); // تنفيذ فوري
//
//// الخطوة 2: التحقق من الدورات
//        handler.postDelayed(() -> {
//            myViewModel.getAllCourse().observe(this, courses -> {
//                if (courses == null || courses.isEmpty()) {
//                    addSampleCourses();
//                }
//            });
//        }, 4000); // تأخير لمدة 2 ثانية
//
//// الخطوة 3: التحقق من الدروس
//        handler.postDelayed(() -> {
//            myViewModel.getTotalLessonsCount().observe(this, lessonCount -> {
//                if (lessonCount == null || lessonCount == 0) {
//                    addSampleLessones();
//                }
//            });
//        }, 6000); // تأخير لمدة 4 ثوانٍ
//
//// الخطوة 4: التحقق من الطلاب
//        handler.postDelayed(() -> {
//            myViewModel.getAllStudent().observe(this, students -> {
//                if (students == null || students.isEmpty()) {
//                    addSampleStudents();
//                }
//            });
//        }, 8000); // تأخير لمدة 6 ثوانٍ


// addSampleLessones();
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

        for (int dbId = 1; dbId <= 38; dbId++) {
            myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم ثلاثي الأبعاد باستخدام Blender", "https://www.youtube.com/watch?v=VG8R7QGdGp8", 10, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تعلم أساسيات النمذجة في 3D Max", "https://www.youtube.com/watch?v=3Ic4kF3rdzQ", 12, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "كيفية إنشاء مشاهد واقعية في Maya", "https://www.youtube.com/watch?v=FvBqDqsmHzI", 8, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "إضاءة المشاهد ثلاثية الأبعاد في Cinema 4D", "https://www.youtube.com/watch?v=fsAxSxp88ZQ", 15, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الشخصيات في Blender", "https://www.youtube.com/watch?v=6dXM8Gocv6k", 13, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم بيئات ثلاثية الأبعاد في 3D Max", "https://www.youtube.com/watch?v=2U5M6vI6xzY", 14, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة مؤثرات خاصة في Maya", "https://www.youtube.com/watch?v=kYgYbEo7HTo", 11, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تصدير النماذج ثلاثية الأبعاد للألعاب", "https://www.youtube.com/watch?v=6MQyZyQDRh4", 9, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم الشخصيات ثلاثية الأبعاد", "https://www.youtube.com/watch?v=k1pS5lfH2kc", 12, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تقنيات الإكساء في Cinema 4D", "https://www.youtube.com/watch?v=nl5mcYIu7Fk", 16, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الكاميرا في مشاهد 3D Max", "https://www.youtube.com/watch?v=6dFtkdZTkmE", 17, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة إضاءة واقعية في Blender", "https://www.youtube.com/watch?v=0RPylvtaHhE", 10, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم مواد واقعية في Maya", "https://www.youtube.com/watch?v=mrctuUwEryo", 8, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم المشاهد الداخلية في 3D Max", "https://www.youtube.com/watch?v=JlxWqg7xeQU", 14, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الشخصيات في Cinema 4D", "https://www.youtube.com/watch?v=0VZqzxbzXYY", 10, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة مؤثرات خاصة في Blender", "https://www.youtube.com/watch?v=a4kDk1s5de4", 13, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم بيئات طبيعية في Maya", "https://www.youtube.com/watch?v=pBiv8nq-XGA", 12, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تقنيات الإكساء المتقدمة في 3D Max", "https://www.youtube.com/watch?v=HkVgOlgw9yA", 11, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الكائنات في Blender", "https://www.youtube.com/watch?v=c56y_AiEcrE", 10, false, dbId));
            myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة لتقنيات الـ 3D في الألعاب", "https://www.youtube.com/watch?v=c7Bx6m0bxg8", 9, false, dbId));

        }

//        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم ثلاثي الأبعاد باستخدام Blender", "https://www.youtube.com/watch?v=VG8R7QGdGp8", 10, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تعلم أساسيات النمذجة في 3D Max", "https://www.youtube.com/watch?v=3Ic4kF3rdzQ", 12, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "كيفية إنشاء مشاهد واقعية في Maya", "https://www.youtube.com/watch?v=FvBqDqsmHzI", 8, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "إضاءة المشاهد ثلاثية الأبعاد في Cinema 4D", "https://www.youtube.com/watch?v=fsAxSxp88ZQ", 15, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الشخصيات في Blender", "https://www.youtube.com/watch?v=6dXM8Gocv6k", 13, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم بيئات ثلاثية الأبعاد في 3D Max", "https://www.youtube.com/watch?v=2U5M6vI6xzY", 14, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة مؤثرات خاصة في Maya", "https://www.youtube.com/watch?v=kYgYbEo7HTo", 11, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تصدير النماذج ثلاثية الأبعاد للألعاب", "https://www.youtube.com/watch?v=6MQyZyQDRh4", 9, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم الشخصيات ثلاثية الأبعاد", "https://www.youtube.com/watch?v=k1pS5lfH2kc", 12, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تقنيات الإكساء في Cinema 4D", "https://www.youtube.com/watch?v=nl5mcYIu7Fk", 16, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الكاميرا في مشاهد 3D Max", "https://www.youtube.com/watch?v=6dFtkdZTkmE", 17, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة إضاءة واقعية في Blender", "https://www.youtube.com/watch?v=0RPylvtaHhE", 10, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم مواد واقعية في Maya", "https://www.youtube.com/watch?v=mrctuUwEryo", 8, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة في تصميم المشاهد الداخلية في 3D Max", "https://www.youtube.com/watch?v=JlxWqg7xeQU", 14, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الشخصيات في Cinema 4D", "https://www.youtube.com/watch?v=0VZqzxbzXYY", 10, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "إضافة مؤثرات خاصة في Blender", "https://www.youtube.com/watch?v=a4kDk1s5de4", 13, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تصميم بيئات طبيعية في Maya", "https://www.youtube.com/watch?v=pBiv8nq-XGA", 12, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تقنيات الإكساء المتقدمة في 3D Max", "https://www.youtube.com/watch?v=HkVgOlgw9yA", 11, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تحريك الكائنات في Blender", "https://www.youtube.com/watch?v=c56y_AiEcrE", 10, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "مقدمة لتقنيات الـ 3D في الألعاب", "https://www.youtube.com/watch?v=c7Bx6m0bxg8", 9, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "إضاءة الأجسام في Maya", "https://www.youtube.com/watch?v=VK9wZG_7L6s", 15, false, 2));
//        myViewModel.insertCourseLesson(new CourseLessons(0, "تعلم استخدام الـ UV Mapping في Blender", "https://www.youtube.com/watch?v=FFG5nCUu3tk", 12, false, 2));
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

//        Student_Teacher studentTeacher = new Student_Teacher(0, "userStudent", "userTeacher");

        Course course1 = new Course(0, "3D Design", imageBytes, 100, "3D Design", "This course covers the foundational principles and techniques of 3D design, including modeling, rendering, and animation.", "John Doe", null, false, false, false, "john_doe", null);
        Course course2 = new Course(0, "3D Design", imageBytes1, 150, "3D Design", "Master Java programming with a focus on object-oriented design, advanced algorithms, and best practices.", "Jane Smith", null, false, false, false, "john_doe", null);
        Course course3 = new Course(0, "Web Development", imageBytes2, 200, "3D Design", "Learn to build responsive and interactive websites using HTML, CSS, JavaScript, and modern frameworks.", "Alice Brown", null, false, false, false, "john_doe", null);
        Course course4 = new Course(0, "Mobile Development", imageBytes3, 180, "3D Design", "Develop cross-platform mobile applications with a focus on performance, user experience, and scalability.", "Bob White", null, false, false, false, "john_doe", null);
        Course course5 = new Course(0, "Data Science", imageBytes4, 250, "3D Design", "Dive into data science concepts, including data analysis, visualization, and machine learning techniques.", "Charlie Green", null, false, false, false, "john_doe", null);
        Course course6 = new Course(0, "Machine Learning", imageBytes5, 220, "3D Design", "Understand machine learning fundamentals, including supervised and unsupervised learning, and model evaluation.", "Dave Black", null, false, false, false, "jane_smith", null);
        Course course7 = new Course(0, "Blockchain Basics", imageBytes6, 300, "3D Design", "Explore blockchain technology, its use cases, and how it is revolutionizing industries.", "Eve Blue", null, false, false, false, "jane_smith", null);
        Course course8 = new Course(0, "UI/UX Design", imageBytes7, 120, "3D Design", "Learn the principles of user interface and user experience design with hands-on projects.", "Frank Pink", null, false, false, false, "jane_smith", null);
        Course course9 = new Course(0, "Cybersecurity", imageBytes8, 350, "3D Design", "Gain knowledge in cybersecurity, covering topics like ethical hacking, threat analysis, and mitigation strategies.", "Grace Yellow", null, false, false, false, "jane_smith", null);
        Course course10 = new Course(0, "Cloud Computing", imageBytes9, 280, "3D Design", "Understand cloud computing services, deployment models, and the benefits of cloud infrastructure.", "Harry Red", null, false, false, false, "jane_smith", null);
        Course course11 = new Course(0, "AI Fundamentals", imageBytes10, 230, "3D Design", "Learn the basics of artificial intelligence, covering neural networks, natural language processing, and applications.", "Ivy Green", null, false, false, false, "alice_brown", null);
        Course course12 = new Course(0, "App Development", imageBytes11, 170, "3D Design", "Develop fully functional apps for Android and iOS using industry-standard tools and techniques.", "Jack White", null, false, false, false, "alice_brown", null);
        Course course13 = new Course(0, "Game Development", imageBytes12, 210, "3D Design", "Learn how to design, develop, and deploy engaging games for various platforms.", "Kylie Blue", null, false, false, false, "alice_brown", null);
        Course course14 = new Course(0, "Database Design", imageBytes13, 240, "3D Design", "Understand database design principles, normalization, and the use of SQL in managing databases.", "Leo Brown", null, false, false, false, "alice_brown", null);
        Course course15 = new Course(0, "Digital Marketing", imageBytes14, 160, "Business", "Learn about digital marketing strategies, including SEO, social media marketing, and analytics.", "Mia Green", null, false, false, false, "alice_brown", null);
        Course course16 = new Course(0, "Cloud Security", imageBytes15, 300, "Business", "Discover the essentials of cloud security, including securing cloud environments and managing risks effectively.", "Nathan Yellow", null, false, false, false, "bob_white", null);
        Course course17 = new Course(0, "Network Fundamentals", imageBytes16, 130, "Business", "Understand the fundamentals of networking, including protocols, hardware, and troubleshooting techniques.", "Olivia White", null, false, false, false, "bob_white", null);
        Course course18 = new Course(0, "Robotics", imageBytes17, 330, "Business", "Explore the world of robotics, covering automation, programming, and real-world applications.", "Paul Black", null, false, false, false, "bob_white", null);
        Course course19 = new Course(0, "Full Stack Development", imageBytes18, 260, "Business", "Master full-stack development, covering frontend, backend, and database integration.", "Quinn Blue", null, false, false, false, "bob_white", null);
        Course course20 = new Course(0, "Data Analytics", imageBytes19, 220, "Business", "Learn to extract insights from data, including statistical analysis and visualization tools.", "Rachel Green", null, false, false, false, "bob_white", null);
        Course course21 = new Course(0, "Data Analytics", imageBytes20, 220, "Business", "Delve deeper into data analytics with advanced techniques for decision-making and strategy.", "Rachel Green", null, false, false, false, "bob_white", null);
        Course course22 = new Course(0, "Data Analytics", imageBytes21, 220, "Business", "Explore big data concepts, tools, and frameworks to handle complex datasets efficiently.", "Rachel Green", null, false, false, false, "bob_white", null);
        Course course23 = new Course(0, "Data Analytics", imageBytes22, 220, "Business", "Learn predictive analytics techniques to forecast trends and patterns in various domains.", "Rachel Green", null, false, false, false, "alice_brown", null);
        Course course24 = new Course(0, "Data Analytics", imageBytes23, 220, "Business", "Get hands-on experience with data cleaning, wrangling, and visualization using modern tools.", "Rachel Green", null, false, false, false, "alice_brown", null);
        Course course25 = new Course(0, "Data Analytics", imageBytes24, 220, "Art", "Analyze art market trends and gain insights into the intersection of art and data analytics.", "Rachel Green", null, false, false, false, "alice_brown", null);
        Course course26 = new Course(0, "Data Analytics", imageBytes25, 220, "Art", "Learn how to apply data analytics techniques to understand artistic patterns and themes.", "Rachel Green", null, false, false, false, "alice_brown", null);
        Course course27 = new Course(0, "Data Analytics", imageBytes26, 220, "Art", "Explore data visualization in the context of art to better present and interpret creative datasets.", "Rachel Green", null, false, false, false, "alice_brown", null);
        Course course28 = new Course(0, "Data Analytics", imageBytes27, 220, "Art", "Learn statistical techniques and tools to analyze data in the art domain.", "Rachel Green", null, false, false, false, "alice_brown", null);
        Course course29 = new Course(0, "Data Analytics", imageBytes28, 220, "Art", "Understand how data analytics can influence modern art and design decision-making processes.", "Rachel Green", null, false, false, false, "jane_smith", null);
        Course course30 = new Course(0, "Data Analytics", imageBytes29, 220, "Art", "Dive into advanced data analytics methods tailored for art-related projects and markets.", "Rachel Green", null, false, false, false, "jane_smith", null);
        Course course31 = new Course(0, "Data Analytics", imageBytes30, 220, "Art", "Discover how to leverage data analytics to enhance creativity and innovation in art.", "Rachel Green", null, false, false, false, "jane_smith", null);
        Course course32 = new Course(0, "Data Analytics", imageBytes31, 220, "Programming", "Learn programming-focused data analytics, including Python libraries for data processing.", "Rachel Green", null, false, false, false, "jane_smith", null);
        Course course33 = new Course(0, "Data Analytics", imageBytes32, 220, "Programming", "Master data analytics algorithms and implement them programmatically in your projects.", "Rachel Green", null, false, false, false, "jane_smith", null);
        Course course34 = new Course(0, "Data Analytics", imageBytes33, 220, "Programming", "Understand advanced data processing and management techniques using coding skills.", "Rachel Green", null, false, false, false, "jane_smith", null);
        Course course35 = new Course(0, "Data Analytics", imageBytes34, 220, "Programming", "Apply machine learning and data mining techniques to programming-oriented datasets.", "Rachel Green", null, false, false, false, "john_doe", null);
        Course course36 = new Course(0, "Data Analytics", imageBytes35, 220, "Programming", "Dive into real-world programming problems and solve them using advanced analytics.", "Rachel Green", null, false, false, false, "john_doe", null);
        Course course37 = new Course(0, "Data Analytics", imageBytes36, 220, "Programming", "Learn to programmatically visualize and analyze data for better decision-making.", "Rachel Green", null, false, false, false, "john_doe", null);
        Course course38 = new Course(0, "Data Analytics", imageBytes37, 220, "Programming", "Explore cutting-edge data analytics tools and frameworks in the programming domain.", "Rachel Green", null, false, false, false, "john_doe", null);

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
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed);
        byte[] imageBytes = convertImageToByteArray(bitmap);
        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed);
        byte[] imageBytes1 = convertImageToByteArray(bitmap1); // تحويل الصورة إلى Byte Array
        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed);
        byte[] imageBytes2 = convertImageToByteArray(bitmap2); // تحويل الصورة إلى Byte Array
        Bitmap bitmap3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed);
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

// الطلاب مع المدرسين المتسلسلين
        Student student1 = new Student("user_1", "password_1", 1234, 1231231211, "Student 1", null, "");
        myViewModel.insertStudent(student1);
        Student_Teacher studentTeacher1 = new Student_Teacher(0, "user_1", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher1);

        Student student2 = new Student("user_2", "password_2", 1235, 1231231212, "Student 2", null, "");
        myViewModel.insertStudent(student2);
        Student_Teacher studentTeacher2 = new Student_Teacher(0, "user_2", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher2);

        Student student3 = new Student("user_3", "password_3", 1236, 1231231213, "Student 3", null, "");
        myViewModel.insertStudent(student3);
        Student_Teacher studentTeacher3 = new Student_Teacher(0, "user_3", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher3);

        Student student4 = new Student("user_4", "password_4", 1237, 1231231214, "Student 4", null, "");
        myViewModel.insertStudent(student4);
        Student_Teacher studentTeacher4 = new Student_Teacher(0, "user_4", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher4);

        Student student5 = new Student("user_5", "password_5", 1238, 1231231215, "Student 5", null, "");
        myViewModel.insertStudent(student5);
        Student_Teacher studentTeacher5 = new Student_Teacher(0, "user_5", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher5);

        Student student6 = new Student("user_6", "password_6", 1239, 1231231216, "Student 6", null, "");
        myViewModel.insertStudent(student6);
        Student_Teacher studentTeacher6 = new Student_Teacher(0, "user_6", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher6);

        Student student7 = new Student("user_7", "password_7", 1240, 1231231217, "Student 7", null, "");
        myViewModel.insertStudent(student7);
        Student_Teacher studentTeacher7 = new Student_Teacher(0, "user_7", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher7);

        Student student8 = new Student("user_8", "password_8", 1241, 1231231218, "Student 8", null, "");
        myViewModel.insertStudent(student8);
        Student_Teacher studentTeacher8 = new Student_Teacher(0, "user_8", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher8);

        Student student9 = new Student("user_9", "password_9", 1242, 1231231219, "Student 9", null, "");
        myViewModel.insertStudent(student9);
        Student_Teacher studentTeacher9 = new Student_Teacher(0, "user_9", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher9);

        Student student10 = new Student("user_10", "password_10", 1243, 1231231220, "Student 10", null, "");
        myViewModel.insertStudent(student10);
        Student_Teacher studentTeacher10 = new Student_Teacher(0, "user_10", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher10);

        Student student11 = new Student("user_11", "password_11", 1244, 1231231221, "Student 11", null, "");
        myViewModel.insertStudent(student11);
        Student_Teacher studentTeacher11 = new Student_Teacher(0, "user_11", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher11);

        Student student12 = new Student("user_12", "password_12", 1245, 1231231222, "Student 12", null, "");
        myViewModel.insertStudent(student12);
        Student_Teacher studentTeacher12 = new Student_Teacher(0, "user_12", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher12);

        Student student13 = new Student("user_13", "password_13", 1246, 1231231223, "Student 13", null, "");
        myViewModel.insertStudent(student13);
        Student_Teacher studentTeacher13 = new Student_Teacher(0, "user_13", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher13);

        Student student14 = new Student("user_14", "password_14", 1247, 1231231224, "Student 14", null, "");
        myViewModel.insertStudent(student14);
        Student_Teacher studentTeacher14 = new Student_Teacher(0, "user_14", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher14);

        Student student15 = new Student("user_15", "password_15", 1248, 1231231225, "Student 15", null, "");
        myViewModel.insertStudent(student15);
        Student_Teacher studentTeacher15 = new Student_Teacher(0, "user_15", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher15);

        Student student16 = new Student("user_16", "password_16", 1249, 1231231226, "Student 16", null, "");
        myViewModel.insertStudent(student16);
        Student_Teacher studentTeacher16 = new Student_Teacher(0, "user_16", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher16);

        Student student17 = new Student("user_17", "password_17", 1250, 1231231227, "Student 17", null, "");
        myViewModel.insertStudent(student17);
        Student_Teacher studentTeacher17 = new Student_Teacher(0, "user_17", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher17);

        Student student18 = new Student("user_18", "password_18", 1251, 1231231228, "Student 18", null, "");
        myViewModel.insertStudent(student18);
        Student_Teacher studentTeacher18 = new Student_Teacher(0, "user_18", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher18);

        Student student19 = new Student("user_19", "password_19", 1252, 1231231229, "Student 19", null, "");
        myViewModel.insertStudent(student19);
        Student_Teacher studentTeacher19 = new Student_Teacher(0, "user_19", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher19);

        Student student20 = new Student("user_20", "password_20", 1253, 1231231230, "Student 20", null, "");
        myViewModel.insertStudent(student20);
        Student_Teacher studentTeacher20 = new Student_Teacher(0, "user_20", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher20);

        Student student21 = new Student("user_21", "password_21", 1254, 1231231231, "Student 21", null, "");
        myViewModel.insertStudent(student21);
        Student_Teacher studentTeacher21 = new Student_Teacher(0, "user_21", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher21);

        Student student22 = new Student("user_22", "password_22", 1255, 1231231232, "Student 22", null, "");
        myViewModel.insertStudent(student22);
        Student_Teacher studentTeacher22 = new Student_Teacher(0, "user_22", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher22);

        Student student23 = new Student("user_23", "password_23", 1256, 1231231233, "Student 23", null, "");
        myViewModel.insertStudent(student23);
        Student_Teacher studentTeacher23 = new Student_Teacher(0, "user_23", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher23);

        Student student24 = new Student("user_24", "password_24", 1257, 1231231234, "Student 24", null, "");
        myViewModel.insertStudent(student24);
        Student_Teacher studentTeacher24 = new Student_Teacher(0, "user_24", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher24);

        Student student25 = new Student("user_25", "password_25", 1258, 1231231235, "Student 25", null, "");
        myViewModel.insertStudent(student25);
        Student_Teacher studentTeacher25 = new Student_Teacher(0, "user_25", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher25);

        Student student26 = new Student("user_26", "password_26", 1259, 1231231236, "Student 26", null, "");
        myViewModel.insertStudent(student26);
        Student_Teacher studentTeacher26 = new Student_Teacher(0, "user_26", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher26);

        Student student27 = new Student("user_27", "password_27", 1260, 1231231237, "Student 27", null, "");
        myViewModel.insertStudent(student27);
        Student_Teacher studentTeacher27 = new Student_Teacher(0, "user_27", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher27);

        Student student28 = new Student("user_28", "password_28", 1261, 1231231238, "Student 28", null, "");
        myViewModel.insertStudent(student28);
        Student_Teacher studentTeacher28 = new Student_Teacher(0, "user_28", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher28);

        Student student29 = new Student("user_29", "password_29", 1262, 1231231239, "Student 29", null, "");
        myViewModel.insertStudent(student29);
        Student_Teacher studentTeacher29 = new Student_Teacher(0, "user_29", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher29);

        Student student30 = new Student("user_30", "password_30", 1263, 1231231240, "Student 30", null, "");
        myViewModel.insertStudent(student30);
        Student_Teacher studentTeacher30 = new Student_Teacher(0, "user_30", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher30);

        Student student31 = new Student("user_31", "password_31", 1264, 1231231241, "Student 31", null, "");
        myViewModel.insertStudent(student31);
        Student_Teacher studentTeacher31 = new Student_Teacher(0, "user_31", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher31);

        Student student32 = new Student("user_32", "password_32", 1265, 1231231242, "Student 32", null, "");
        myViewModel.insertStudent(student32);
        Student_Teacher studentTeacher32 = new Student_Teacher(0, "user_32", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher32);

        Student student33 = new Student("user_33", "password_33", 1266, 1231231243, "Student 33", null, "");
        myViewModel.insertStudent(student33);
        Student_Teacher studentTeacher33 = new Student_Teacher(0, "user_33", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher33);

        Student student34 = new Student("user_34", "password_34", 1267, 1231231244, "Student 34", null, "");
        myViewModel.insertStudent(student34);
        Student_Teacher studentTeacher34 = new Student_Teacher(0, "user_34", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher34);

        Student student35 = new Student("user_35", "password_35", 1268, 1231231245, "Student 35", null, "");
        myViewModel.insertStudent(student35);
        Student_Teacher studentTeacher35 = new Student_Teacher(0, "user_35", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher35);

        Student student36 = new Student("user_36", "password_36", 1269, 1231231246, "Student 36", null, "");
        myViewModel.insertStudent(student36);
        Student_Teacher studentTeacher36 = new Student_Teacher(0, "user_36", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher36);

        Student student37 = new Student("user_37", "password_37", 1270, 1231231247, "Student 37", null, "");
        myViewModel.insertStudent(student37);
        Student_Teacher studentTeacher37 = new Student_Teacher(0, "user_37", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher37);

        Student student38 = new Student("user_38", "password_38", 1271, 1231231248, "Student 38", null, "");
        myViewModel.insertStudent(student38);
        Student_Teacher studentTeacher38 = new Student_Teacher(0, "user_38", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher38);

        Student student39 = new Student("user_39", "password_39", 1272, 1231231249, "Student 39", null, "");
        myViewModel.insertStudent(student39);
        Student_Teacher studentTeacher39 = new Student_Teacher(0, "user_39", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher39);

        Student student40 = new Student("user_40", "password_40", 1273, 1231231250, "Student 40", null, "");
        myViewModel.insertStudent(student40);
        Student_Teacher studentTeacher40 = new Student_Teacher(0, "user_40", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher40);

        Student student41 = new Student("user_41", "password_41", 1274, 1231231251, "Student 41", null, "");
        myViewModel.insertStudent(student41);
        Student_Teacher studentTeacher41 = new Student_Teacher(0, "user_41", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher41);

        Student student42 = new Student("user_42", "password_42", 1275, 1231231252, "Student 42", null, "");
        myViewModel.insertStudent(student42);
        Student_Teacher studentTeacher42 = new Student_Teacher(0, "user_42", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher42);

        Student student43 = new Student("user_43", "password_43", 1276, 1231231253, "Student 43", null, "");
        myViewModel.insertStudent(student43);
        Student_Teacher studentTeacher43 = new Student_Teacher(0, "user_43", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher43);

        Student student44 = new Student("user_44", "password_44", 1277, 1231231254, "Student 44", null, "");
        myViewModel.insertStudent(student44);
        Student_Teacher studentTeacher44 = new Student_Teacher(0, "user_44", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher44);

        Student student45 = new Student("user_45", "password_45", 1278, 1231231255, "Student 45", null, "");
        myViewModel.insertStudent(student45);
        Student_Teacher studentTeacher45 = new Student_Teacher(0, "user_45", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher45);

        Student student46 = new Student("user_46", "password_46", 1279, 1231231256, "Student 46", null, "");
        myViewModel.insertStudent(student46);
        Student_Teacher studentTeacher46 = new Student_Teacher(0, "user_46", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher46);

        Student student47 = new Student("user_47", "password_47", 1280, 1231231257, "Student 47", null, "");
        myViewModel.insertStudent(student47);
        Student_Teacher studentTeacher47 = new Student_Teacher(0, "user_47", "john_doe");
        myViewModel.insertStudentTeacher(studentTeacher47);

        Student student48 = new Student("user_48", "password_48", 1281, 1231231258, "Student 48", null, "");
        myViewModel.insertStudent(student48);
        Student_Teacher studentTeacher48 = new Student_Teacher(0, "user_48", "alice_brown");
        myViewModel.insertStudentTeacher(studentTeacher48);

        Student student49 = new Student("user_49", "password_49", 1282, 1231231259, "Student 49", null, "");
        myViewModel.insertStudent(student49);
        Student_Teacher studentTeacher49 = new Student_Teacher(0, "user_49", "bob_white");
        myViewModel.insertStudentTeacher(studentTeacher49);

        Student student50 = new Student("user_50", "password_50", 1283, 1231231260, "Student 50", null, "");
        myViewModel.insertStudent(student50);
        Student_Teacher studentTeacher50 = new Student_Teacher(0, "user_50", "jane_smith");
        myViewModel.insertStudentTeacher(studentTeacher50);

    }
//        for (int i = 1; i <= 50; i++) {
//            // إنشاء بيانات الطالب
//            String userName = "user_" + i; // اسم مستخدم فريد
//            String password = "password_" + i; // كلمة مرور فريدة
//            int phoneNumber = 1000000000 + i; // رقم هاتف فريد
//            int cardNumber = 123 + i; // رقم بطاقة فريد
//            String studentName = "Student " + i; // اسم الطالب
//            byte[] image = null; // صورة افتراضية (يمكنك تحديثها لاحقًا)
//
//            // إنشاء الطالب
//            Student student1 = new Student(userName, password, phoneNumber, cardNumber, studentName, image, "");
//
//            // إدراج الطالب باستخدام ViewModel
//           // myViewModel.insertStudent(student1);
//
//            // تحديد اسم المدرس بناءً على توزيع معين
//            String teacherUserName;
//            if (i <= 12) {
//                teacherUserName = "bob_white"; // أول 12 طالب مع المدرس الأول
//            } else if (i <= 24) {
//                teacherUserName = "jane_smith"; // من 13 إلى 24 مع المدرس الثاني
//            } else if (i <= 36) {
//                teacherUserName = "john_doe"; // من 25 إلى 36 مع المدرس الثالث
//            } else {
//                teacherUserName = "alice_brown"; // من 37 إلى 50 مع المدرس الرابع
//            }
//
//            // إنشاء العلاقة بين الطالب والمدرس
//            Student_Teacher studentTeacher = new Student_Teacher(0, userName, teacherUserName);
//
//            // إدراج العلاقة باستخدام ViewModel
//                myViewModel.insertStudentTeacher(studentTeacher);
}


//        // ربط الطالب بالكورسات
//        Student_Course studentCourse = new Student_Course(0, "userName", 0);
//        myViewModel.insertStudentCourse(studentCourse);
//
//        // ربط الطالب بالمدرس
//        Student_Teacher studentTeacher = new Student_Teacher(0, "userName", "teacherUsername");
//        myViewModel.insertStudentTeacher(studentTeacher);

