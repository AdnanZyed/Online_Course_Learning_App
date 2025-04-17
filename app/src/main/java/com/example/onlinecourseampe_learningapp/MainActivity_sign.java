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

import com.example.onlineSeasonampe_learningapp.R;
import com.example.onlineSeasonampe_learningapp.databinding.ActivityMainSignBinding;

import java.io.ByteArrayOutputStream;

public class MainActivity_sign extends AppCompatActivity {
    private My_View_Model myViewModel;
    ActivityMainSignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainSignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

                  //  addSampleExperts();
//                    addSampleSeasons();
//                    addSampleStepes();
//                    addSampleFarmers();

//            myViewModel.getAllExpert().observe(this, experts -> {
//                if (experts == null || experts.isEmpty()) {
//                    addSampleExperts();
//                }
//            });
//
//            myViewModel.getAllSeason().observe(this, seasons -> {
//                if (seasons == null || seasons.isEmpty()) {
//                    addSampleSeasons();
//                }
//            });
//
//
//            myViewModel.getTotalStepsCount().observe(this, stepCount -> {
//                if (stepCount == null || stepCount == 0) {
//                    addSampleStepes();
//                }
//            });
//
//            myViewModel.getAllFarmer().observe(this, farmers -> {
//                if (farmers == null || farmers.isEmpty()) {
//                    addSampleFarmers();
//                }
//            });

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

    private void addSampleStepes() {

        for (int dbId = 1; dbId <= 38; dbId++) {
            myViewModel.insertSeasonStep(new SeasonStep(0, "مقدمة في تصميم ثلاثي الأبعاد", "https://www.youtube.com/watch?v=VG8R7QGdGp8", 10, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تعلم أساسيات النمذجة 3D Max", "https://www.youtube.com/watch?v=3Ic4kF3rdzQ", 12, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "إنشاء مشاهد واقعية في Maya", "https://www.youtube.com/watch?v=FvBqDqsmHzI", 8, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "إضاءة المشاهد   في Cinema 4D", "https://www.youtube.com/watch?v=fsAxSxp88ZQ", 15, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تحريك الشخصيات في Blender", "https://www.youtube.com/watch?v=6dXM8Gocv6k", 13, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تصميم بيئات ثلاثية 3D Max", "https://www.youtube.com/watch?v=2U5M6vI6xzY", 14, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "إضافة مؤثرات خاصة في Maya", "https://www.youtube.com/watch?v=kYgYbEo7HTo", 11, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تصدير النماذج للألعاب", "https://www.youtube.com/watch?v=6MQyZyQDRh4", 9, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "مقدمة في تصميم الشخصيات", "https://www.youtube.com/watch?v=k1pS5lfH2kc", 12, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تقنيات الإكساء في Cinema 4D", "https://www.youtube.com/watch?v=nl5mcYIu7Fk", 16, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تحريك الكاميرا في مشاهد 3D Max", "https://www.youtube.com/watch?v=6dFtkdZTkmE", 17, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "إضافة إضاءة واقعية في Blender", "https://www.youtube.com/watch?v=0RPylvtaHhE", 10, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تصميم مواد واقعية في Maya", "https://www.youtube.com/watch?v=mrctuUwEryo", 8, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "المشاهد الداخلية في 3D Max", "https://www.youtube.com/watch?v=JlxWqg7xeQU", 14, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تحريك الشخصيات في Cinema 4D", "https://www.youtube.com/watch?v=0VZqzxbzXYY", 10, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "إضافة مؤثرات خاصة في Blender", "https://www.youtube.com/watch?v=a4kDk1s5de4", 13, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تصميم بيئات طبيعية في Maya", "https://www.youtube.com/watch?v=pBiv8nq-XGA", 12, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تقنيات الإكساء في 3D Max", "https://www.youtube.com/watch?v=HkVgOlgw9yA", 11, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "تحريك الكائنات في Blender", "https://www.youtube.com/watch?v=c56y_AiEcrE", 10, false, dbId));
            myViewModel.insertSeasonStep(new SeasonStep(0, "مقدمة لتقنيات الـ 3D في الألعاب", "https://www.youtube.com/watch?v=c7Bx6m0bxg8", 9, false, dbId));

        }


    }

    private void addSampleSeasons() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.a);
        byte[] imageBytes = convertImageToByteArray(bitmap);
        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s);
        byte[] imageBytes1 = convertImageToByteArray(bitmap1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.d);
        byte[] imageBytes2 = convertImageToByteArray(bitmap2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.y1);
        byte[] imageBytes3 = convertImageToByteArray(bitmap3);
        Bitmap bitmap4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c1);
        byte[] imageBytes4 = convertImageToByteArray(bitmap4);
        Bitmap bitmap5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.y2);
        byte[] imageBytes5 = convertImageToByteArray(bitmap5);
        Bitmap bitmap6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.y4);
        byte[] imageBytes6 = convertImageToByteArray(bitmap6);
        Bitmap bitmap7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.y5);
        byte[] imageBytes7 = convertImageToByteArray(bitmap7);
        Bitmap bitmap8 = BitmapFactory.decodeResource(this.getResources(), R.drawable.y6);
        byte[] imageBytes8 = convertImageToByteArray(bitmap8);
        Bitmap bitmap9 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c6);
        byte[] imageBytes9 = convertImageToByteArray(bitmap9);
        Bitmap bitmap10 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c7);
        byte[] imageBytes10 = convertImageToByteArray(bitmap10);
        Bitmap bitmap11 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c8);
        byte[] imageBytes11 = convertImageToByteArray(bitmap11);
        Bitmap bitmap12 = BitmapFactory.decodeResource(this.getResources(), R.drawable.y7);
        byte[] imageBytes12 = convertImageToByteArray(bitmap12);
        Bitmap bitmap13 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c4);
        byte[] imageBytes13 = convertImageToByteArray(bitmap13);
        Bitmap bitmap14 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c11);
        byte[] imageBytes14 = convertImageToByteArray(bitmap14);
        Bitmap bitmap15 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c12);
        byte[] imageBytes15 = convertImageToByteArray(bitmap15);
        Bitmap bitmap16 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c13);
        byte[] imageBytes16 = convertImageToByteArray(bitmap16);
        Bitmap bitmap17 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c14);
        byte[] imageBytes17 = convertImageToByteArray(bitmap17);
        Bitmap bitmap18 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c15);
        byte[] imageBytes18 = convertImageToByteArray(bitmap18);
        Bitmap bitmap19 = BitmapFactory.decodeResource(this.getResources(), R.drawable.y8);
        byte[] imageBytes19 = convertImageToByteArray(bitmap19);
        Bitmap bitmap20 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c17);
        byte[] imageBytes20 = convertImageToByteArray(bitmap20);
        Bitmap bitmap21 = BitmapFactory.decodeResource(this.getResources(), R.drawable.i5);
        byte[] imageBytes21 = convertImageToByteArray(bitmap21);
        Bitmap bitmap22 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c19);
        byte[] imageBytes22 = convertImageToByteArray(bitmap22);
        Bitmap bitmap23 = BitmapFactory.decodeResource(this.getResources(), R.drawable.i6);
        byte[] imageBytes23 = convertImageToByteArray(bitmap23);
        Bitmap bitmap24 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c21);
        byte[] imageBytes24 = convertImageToByteArray(bitmap24);
        Bitmap bitmap25 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c22);
        byte[] imageBytes25 = convertImageToByteArray(bitmap25);
        Bitmap bitmap26 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c23);
        byte[] imageBytes26 = convertImageToByteArray(bitmap26);
        Bitmap bitmap27 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c24);
        byte[] imageBytes27 = convertImageToByteArray(bitmap27);
        Bitmap bitmap28 = BitmapFactory.decodeResource(this.getResources(), R.drawable.i10);
        byte[] imageBytes28 = convertImageToByteArray(bitmap28);
        Bitmap bitmap29 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c26);
        byte[] imageBytes29 = convertImageToByteArray(bitmap29);
        Bitmap bitmap30 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c27);
        byte[] imageBytes30 = convertImageToByteArray(bitmap30);
        Bitmap bitmap31 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c28);
        byte[] imageBytes31 = convertImageToByteArray(bitmap31);
        Bitmap bitmap32 = BitmapFactory.decodeResource(this.getResources(), R.drawable.i8);
        byte[] imageBytes32 = convertImageToByteArray(bitmap32);
        Bitmap bitmap33 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c30);
        byte[] imageBytes33 = convertImageToByteArray(bitmap33);
        Bitmap bitmap34 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c31);
        byte[] imageBytes34 = convertImageToByteArray(bitmap34);
        Bitmap bitmap35 = BitmapFactory.decodeResource(this.getResources(), R.drawable.i7);
        byte[] imageBytes35 = convertImageToByteArray(bitmap35);
        Bitmap bitmap36 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c33);
        byte[] imageBytes36 = convertImageToByteArray(bitmap36);
        Bitmap bitmap37 = BitmapFactory.decodeResource(this.getResources(), R.drawable.c34);
        byte[] imageBytes37 = convertImageToByteArray(bitmap37);


        Season season1 = new Season(0, "Modeling Basics", imageBytes, 100, "3D Design", "This season covers the foundational principles and techniques of 3D design, including modeling, rendering, and animation.", "John Doe", null, false, false, false, "john_doe", null, 0, 0);
        Season season2 = new Season(0, "Advanced Animation", imageBytes1, 150, "3D Design", "Master Java programming with a focus on object-oriented design, advanced algorithms, and best practices.", "Jane Smith", null, false, false, false, "john_doe", null, 0, 0);
        Season season3 = new Season(0, "3D Sculpting Techniques", imageBytes2, 200, "3D Design", "Learn to build responsive and interactive websites using HTML, CSS, JavaScript, and modern frameworks.", "Alice Brown", null, false, false, false, "john_doe", null, 0, 0);
        Season season4 = new Season(0, "Lighting Scenes", imageBytes3, 180, "3D Design", "Develop cross-platform mobile applications with a focus on performance, user experience, and scalability.", "Bob White", null, false, false, false, "john_doe", null, 0, 0);
        Season season5 = new Season(0, "Lighting Scenes", imageBytes4, 250, "3D Design", "Dive into data science concepts, including data analysis, visualization, and machine learning techniques.", "Charlie Green", null, false, false, false, "john_doe", null, 0, 0);
        Season season6 = new Season(0, "3D Rendering Essentials", imageBytes5, 220, "3D Design", "Understand machine learning fundamentals, including supervised and unsupervised learning, and model evaluation.", "Dave Black", null, false, false, false, "jane_smith", null, 0, 0);
        Season season7 = new Season(0, "Character Modeling", imageBytes6, 300, "3D Design", "Explore blockchain technology, its use cases, and how it is revolutionizing industries.", "Eve Blue", null, false, false, false, "jane_smith", null, 0, 0);
        Season season8 = new Season(0, "Environmental 3D Design", imageBytes7, 120, "3D Design", "Learn the principles of user interface and user experience design with hands-on projects.", "Frank Pink", null, false, false, false, "jane_smith", null, 0, 0);
        Season season9 = new Season(0, "3D Printing and Prototyping", imageBytes8, 350, "3D Design", "Gain knowledge in cybersecurity, covering topics like ethical hacking, threat analysis, and mitigation strategies.", "Grace Yellow", null, false, false, false, "jane_smith", null, 0, 0);
        Season season10 = new Season(0, "Architectural Visualization", imageBytes9, 280, "3D Design", "Understand cloud computing services, deployment models, and the benefits of cloud infrastructure.", "Harry Red", null, false, false, false, "jane_smith", null, 0, 0);
        Season season11 = new Season(0, "3D Motion Graphics", imageBytes10, 230, "3D Design", "Learn the basics of artificial intelligence, covering neural networks, natural language processing, and applications.", "Ivy Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season12 = new Season(0, "Game Asset Creation in 3D", imageBytes11, 170, "3D Design", "Develop fully functional apps for Android and iOS using industry-standard tools and techniques.", "Jack White", null, false, false, false, "alice_brown", null, 0, 0);
        Season season13 = new Season(0, "VFX in 3D", imageBytes12, 210, "3D Design", "Learn how to design, develop, and deploy engaging games for various platforms.", "Kylie Blue", null, false, false, false, "alice_brown", null, 0, 0);
        Season season14 = new Season(0, "3D Animation for Advertising", imageBytes13, 240, "3D Design", "Understand database design principles, normalization, and the use of SQL in managing databases.", "Leo Brown", null, false, false, false, "alice_brown", null, 0, 0);
        Season season15 = new Season(0, "Digital Marketing", imageBytes14, 160, "Business", "Learn about digital marketing strategies, including SEO, social media marketing, and analytics.", "Mia Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season16 = new Season(0, "Cloud Security", imageBytes15, 300, "Business", "Discover the essentials of cloud security, including securing cloud environments and managing risks effectively.", "Nathan Yellow", null, false, false, false, "bob_white", null, 0, 0);
        Season season17 = new Season(0, "Network Fundamentals", imageBytes16, 130, "Business", "Understand the fundamentals of networking, including protocols, hardware, and troubleshooting techniques.", "Olivia White", null, false, false, false, "bob_white", null, 0, 0);
        Season season18 = new Season(0, "Robotics", imageBytes17, 330, "Business", "Explore the world of robotics, covering automation, programming, and real-world applications.", "Paul Black", null, false, false, false, "bob_white", null, 0, 0);
        Season season19 = new Season(0, "Full Stack Development", imageBytes18, 260, "Business", "Master full-stack development, covering frontend, backend, and database integration.", "Quinn Blue", null, false, false, false, "bob_white", null, 0, 0);
        Season season20 = new Season(0, "Business Fundamentals", imageBytes19, 220, "Business", "Learn to extract insights from data, including statistical analysis and visualization tools.", "Rachel Green", null, false, false, false, "bob_white", null, 0, 0);
        Season season21 = new Season(0, "Growth and Expansion Strategies", imageBytes20, 220, "Business", "Delve deeper into data analytics with advanced techniques for decision-making and strategy.", "Rachel Green", null, false, false, false, "bob_white", null, 0, 0);
        Season season22 = new Season(0, "Project Management: From Idea to Execution", imageBytes21, 220, "Business", "Explore big data concepts, tools, and frameworks to handle complex datasets efficiently.", "Rachel Green", null, false, false, false, "bob_white", null, 0, 0);
        Season season23 = new Season(0, "Financial Analysis for Businesses", imageBytes22, 220, "Business", "Learn predictive analytics techniques to forecast trends and patterns in various domains.", "Rachel Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season24 = new Season(0, "Digital Marketing", imageBytes23, 220, "Business", "Get hands-on experience with data cleaning, wrangling, and visualization using modern tools.", "Rachel Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season25 = new Season(0, "Customer Relationship", imageBytes24, 220, "Art", "Analyze art market trends and gain insights into the intersection of art and data analytics.", "Rachel Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season26 = new Season(0, "Leadership", imageBytes25, 220, "Art", "Learn how to apply data analytics techniques to understand artistic patterns and themes.", "Rachel Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season27 = new Season(0, "Innovation", imageBytes26, 220, "Art", "Explore data visualization in the context of art to better present and interpret creative datasets.", "Rachel Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season28 = new Season(0, "Art History and Appreciation", imageBytes27, 220, "Art", "Learn statistical techniques and tools to analyze data in the art domain.", "Rachel Green", null, false, false, false, "alice_brown", null, 0, 0);
        Season season29 = new Season(0, "Modern and Contemporary Art", imageBytes28, 220, "Art", "Understand how data analytics can influence modern art and design decision-making processes.", "Rachel Green", null, false, false, false, "jane_smith", null, 0, 0);
        Season season30 = new Season(0, "Digital Art and Media", imageBytes29, 220, "Art", "Dive into advanced data analytics methods tailored for art-related projects and markets.", "Rachel Green", null, false, false, false, "jane_smith", null, 0, 0);
        Season season31 = new Season(0, "Drawing Techniques", imageBytes30, 220, "Art", "Discover how to leverage data analytics to enhance creativity and innovation in art.", "Rachel Green", null, false, false, false, "jane_smith", null, 0, 0);
        Season season32 = new Season(0, "Programming with Python", imageBytes31, 220, "Programming", "Learn programming-focused data analytics, including Python libraries for data processing.", "Rachel Green", null, false, false, false, "jane_smith", null, 0, 0);
        Season season33 = new Season(0, "Data Algorithms", imageBytes32, 220, "Programming", "Master data analytics algorithms and implement them programmatically in your projects.", "Rachel Green", null, false, false, false, "jane_smith", null, 0, 0);
        Season season34 = new Season(0, "Web Development", imageBytes33, 220, "Programming", "Understand advanced data processing and management techniques using coding skills.", "Rachel Green", null, false, false, false, "jane_smith", null, 0, 0);
        Season season35 = new Season(0, "Object-Oriented Java", imageBytes34, 220, "Programming", "Apply machine learning and data mining techniques to programming-oriented datasets.", "Rachel Green", null, false, false, false, "john_doe", null, 0, 0);
        Season season36 = new Season(0, "Mobile App Development", imageBytes35, 220, "Programming", "Dive into real-world programming problems and solve them using advanced analytics.", "Rachel Green", null, false, false, false, "john_doe", null, 0, 0);
        Season season37 = new Season(0, "Database Management", imageBytes36, 220, "Programming", "Learn to programmatically visualize and analyze data for better decision-making.", "Rachel Green", null, false, false, false, "john_doe", null, 0, 0);
        Season season38 = new Season(0, "Functional Programming", imageBytes37, 220, "Programming", "Explore cutting-edge data analytics tools and frameworks in the programming domain.", "Rachel Green", null, false, false, false, "john_doe", null, 0, 0);

        myViewModel.insertSeason(season1);
        myViewModel.insertSeason(season2);
        myViewModel.insertSeason(season3);
        myViewModel.insertSeason(season4);
        myViewModel.insertSeason(season5);
        myViewModel.insertSeason(season6);
        myViewModel.insertSeason(season7);
        myViewModel.insertSeason(season8);
        myViewModel.insertSeason(season9);
        myViewModel.insertSeason(season10);
        myViewModel.insertSeason(season11);
        myViewModel.insertSeason(season12);
        myViewModel.insertSeason(season13);
        myViewModel.insertSeason(season14);
        myViewModel.insertSeason(season15);
        myViewModel.insertSeason(season16);
        myViewModel.insertSeason(season17);
        myViewModel.insertSeason(season18);
        myViewModel.insertSeason(season19);
        myViewModel.insertSeason(season20);
        myViewModel.insertSeason(season21);
        myViewModel.insertSeason(season22);
        myViewModel.insertSeason(season23);
        myViewModel.insertSeason(season24);
        myViewModel.insertSeason(season25);
        myViewModel.insertSeason(season26);
        myViewModel.insertSeason(season27);
        myViewModel.insertSeason(season28);
        myViewModel.insertSeason(season29);
        myViewModel.insertSeason(season30);
        myViewModel.insertSeason(season31);
        myViewModel.insertSeason(season32);
        myViewModel.insertSeason(season33);
        myViewModel.insertSeason(season34);
        myViewModel.insertSeason(season35);
        myViewModel.insertSeason(season36);
        myViewModel.insertSeason(season37);
        myViewModel.insertSeason(season38);
    }




    private void addSampleFarmers() {

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.unnamed);
        byte[] imageBytes = convertImageToByteArray(bitmap);
        Farmer farmer1 = new Farmer("user_1", "password_1", 1234, 1231231211, "Farmer 1", imageBytes, "");
        myViewModel.insertFarmer(farmer1);


        Farmer_Expert farmerExpert1 = new Farmer_Expert(0, "user_1", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert1);

        Farmer farmer2 = new Farmer("user_2", "password_2", 1235, 1231231212, "Farmer 2", null, "");
        myViewModel.insertFarmer(farmer2);
        Farmer_Expert farmerExpert2 = new Farmer_Expert(0, "user_2", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert2);

        Farmer farmer3 = new Farmer("user_3", "password_3", 1236, 1231231213, "Farmer 3", null, "");
        myViewModel.insertFarmer(farmer3);
        Farmer_Expert farmerExpert3 = new Farmer_Expert(0, "user_3", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert3);

        Farmer farmer4 = new Farmer("user_4", "password_4", 1237, 1231231214, "Farmer 4", null, "");
        myViewModel.insertFarmer(farmer4);
        Farmer_Expert farmerExpert4 = new Farmer_Expert(0, "user_4", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert4);

        Farmer farmer5 = new Farmer("user_5", "password_5", 1238, 1231231215, "Farmer 5", null, "");
        myViewModel.insertFarmer(farmer5);
        Farmer_Expert farmerExpert5 = new Farmer_Expert(0, "user_5", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert5);

        Farmer farmer6 = new Farmer("user_6", "password_6", 1239, 1231231216, "Farmer 6", null, "");
        myViewModel.insertFarmer(farmer6);
        Farmer_Expert farmerExpert6 = new Farmer_Expert(0, "user_6", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert6);

        Farmer farmer7 = new Farmer("user_7", "password_7", 1240, 1231231217, "Farmer 7", null, "");
        myViewModel.insertFarmer(farmer7);
        Farmer_Expert farmerExpert7 = new Farmer_Expert(0, "user_7", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert7);

        Farmer farmer8 = new Farmer("user_8", "password_8", 1241, 1231231218, "Farmer 8", null, "");
        myViewModel.insertFarmer(farmer8);
        Farmer_Expert farmerExpert8 = new Farmer_Expert(0, "user_8", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert8);

        Farmer farmer9 = new Farmer("user_9", "password_9", 1242, 1231231219, "Farmer 9", null, "");
        myViewModel.insertFarmer(farmer9);
        Farmer_Expert farmerExpert9 = new Farmer_Expert(0, "user_9", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert9);

        Farmer farmer10 = new Farmer("user_10", "password_10", 1243, 1231231220, "Farmer 10", null, "");
        myViewModel.insertFarmer(farmer10);
        Farmer_Expert farmerExpert10 = new Farmer_Expert(0, "user_10", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert10);

        Farmer farmer11 = new Farmer("user_11", "password_11", 1244, 1231231221, "Farmer 11", null, "");
        myViewModel.insertFarmer(farmer11);
        Farmer_Expert farmerExpert11 = new Farmer_Expert(0, "user_11", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert11);

        Farmer farmer12 = new Farmer("user_12", "password_12", 1245, 1231231222, "Farmer 12", null, "");
        myViewModel.insertFarmer(farmer12);
        Farmer_Expert farmerExpert12 = new Farmer_Expert(0, "user_12", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert12);

        Farmer farmer13 = new Farmer("user_13", "password_13", 1246, 1231231223, "Farmer 13", null, "");
        myViewModel.insertFarmer(farmer13);
        Farmer_Expert farmerExpert13 = new Farmer_Expert(0, "user_13", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert13);

        Farmer farmer14 = new Farmer("user_14", "password_14", 1247, 1231231224, "Farmer 14", null, "");
        myViewModel.insertFarmer(farmer14);
        Farmer_Expert farmerExpert14 = new Farmer_Expert(0, "user_14", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert14);

        Farmer farmer15 = new Farmer("user_15", "password_15", 1248, 1231231225, "Farmer 15", null, "");
        myViewModel.insertFarmer(farmer15);
        Farmer_Expert farmerExpert15 = new Farmer_Expert(0, "user_15", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert15);

        Farmer farmer16 = new Farmer("user_16", "password_16", 1249, 1231231226, "Farmer 16", null, "");
        myViewModel.insertFarmer(farmer16);
        Farmer_Expert farmerExpert16 = new Farmer_Expert(0, "user_16", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert16);

        Farmer farmer17 = new Farmer("user_17", "password_17", 1250, 1231231227, "Farmer 17", null, "");
        myViewModel.insertFarmer(farmer17);
        Farmer_Expert farmerExpert17 = new Farmer_Expert(0, "user_17", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert17);

        Farmer farmer18 = new Farmer("user_18", "password_18", 1251, 1231231228, "Farmer 18", null, "");
        myViewModel.insertFarmer(farmer18);
        Farmer_Expert farmerExpert18 = new Farmer_Expert(0, "user_18", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert18);

        Farmer farmer19 = new Farmer("user_19", "password_19", 1252, 1231231229, "Farmer 19", null, "");
        myViewModel.insertFarmer(farmer19);
        Farmer_Expert farmerExpert19 = new Farmer_Expert(0, "user_19", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert19);

        Farmer farmer20 = new Farmer("user_20", "password_20", 1253, 1231231230, "Farmer 20", null, "");
        myViewModel.insertFarmer(farmer20);
        Farmer_Expert farmerExpert20 = new Farmer_Expert(0, "user_20", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert20);

        Farmer farmer21 = new Farmer("user_21", "password_21", 1254, 1231231231, "Farmer 21", null, "");
        myViewModel.insertFarmer(farmer21);
        Farmer_Expert farmerExpert21 = new Farmer_Expert(0, "user_21", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert21);

        Farmer farmer22 = new Farmer("user_22", "password_22", 1255, 1231231232, "Farmer 22", null, "");
        myViewModel.insertFarmer(farmer22);
        Farmer_Expert farmerExpert22 = new Farmer_Expert(0, "user_22", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert22);

        Farmer farmer23 = new Farmer("user_23", "password_23", 1256, 1231231233, "Farmer 23", null, "");
        myViewModel.insertFarmer(farmer23);
        Farmer_Expert farmerExpert23 = new Farmer_Expert(0, "user_23", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert23);

        Farmer farmer24 = new Farmer("user_24", "password_24", 1257, 1231231234, "Farmer 24", null, "");
        myViewModel.insertFarmer(farmer24);
        Farmer_Expert farmerExpert24 = new Farmer_Expert(0, "user_24", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert24);

        Farmer farmer25 = new Farmer("user_25", "password_25", 1258, 1231231235, "Farmer 25", null, "");
        myViewModel.insertFarmer(farmer25);
        Farmer_Expert farmerExpert25 = new Farmer_Expert(0, "user_25", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert25);

        Farmer farmer26 = new Farmer("user_26", "password_26", 1259, 1231231236, "Farmer 26", null, "");
        myViewModel.insertFarmer(farmer26);
        Farmer_Expert farmerExpert26 = new Farmer_Expert(0, "user_26", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert26);

        Farmer farmer27 = new Farmer("user_27", "password_27", 1260, 1231231237, "Farmer 27", null, "");
        myViewModel.insertFarmer(farmer27);
        Farmer_Expert farmerExpert27 = new Farmer_Expert(0, "user_27", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert27);

        Farmer farmer28 = new Farmer("user_28", "password_28", 1261, 1231231238, "Farmer 28", null, "");
        myViewModel.insertFarmer(farmer28);
        Farmer_Expert farmerExpert28 = new Farmer_Expert(0, "user_28", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert28);

        Farmer farmer29 = new Farmer("user_29", "password_29", 1262, 1231231239, "Farmer 29", null, "");
        myViewModel.insertFarmer(farmer29);
        Farmer_Expert farmerExpert29 = new Farmer_Expert(0, "user_29", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert29);

        Farmer farmer30 = new Farmer("user_30", "password_30", 1263, 1231231240, "Farmer 30", null, "");
        myViewModel.insertFarmer(farmer30);
        Farmer_Expert farmerExpert30 = new Farmer_Expert(0, "user_30", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert30);

        Farmer farmer31 = new Farmer("user_31", "password_31", 1264, 1231231241, "Farmer 31", null, "");
        myViewModel.insertFarmer(farmer31);
        Farmer_Expert farmerExpert31 = new Farmer_Expert(0, "user_31", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert31);

        Farmer farmer32 = new Farmer("user_32", "password_32", 1265, 1231231242, "Farmer 32", null, "");
        myViewModel.insertFarmer(farmer32);
        Farmer_Expert farmerExpert32 = new Farmer_Expert(0, "user_32", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert32);

        Farmer farmer33 = new Farmer("user_33", "password_33", 1266, 1231231243, "Farmer 33", null, "");
        myViewModel.insertFarmer(farmer33);
        Farmer_Expert farmerExpert33 = new Farmer_Expert(0, "user_33", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert33);

        Farmer farmer34 = new Farmer("user_34", "password_34", 1267, 1231231244, "Farmer 34", null, "");
        myViewModel.insertFarmer(farmer34);
        Farmer_Expert farmerExpert34 = new Farmer_Expert(0, "user_34", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert34);

        Farmer farmer35 = new Farmer("user_35", "password_35", 1268, 1231231245, "Farmer 35", null, "");
        myViewModel.insertFarmer(farmer35);
        Farmer_Expert farmerExpert35 = new Farmer_Expert(0, "user_35", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert35);

        Farmer farmer36 = new Farmer("user_36", "password_36", 1269, 1231231246, "Farmer 36", null, "");
        myViewModel.insertFarmer(farmer36);
        Farmer_Expert farmerExpert36 = new Farmer_Expert(0, "user_36", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert36);

        Farmer farmer37 = new Farmer("user_37", "password_37", 1270, 1231231247, "Farmer 37", null, "");
        myViewModel.insertFarmer(farmer37);
        Farmer_Expert farmerExpert37 = new Farmer_Expert(0, "user_37", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert37);

        Farmer farmer38 = new Farmer("user_38", "password_38", 1271, 1231231248, "Farmer 38", null, "");
        myViewModel.insertFarmer(farmer38);
        Farmer_Expert farmerExpert38 = new Farmer_Expert(0, "user_38", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert38);

        Farmer farmer39 = new Farmer("user_39", "password_39", 1272, 1231231249, "Farmer 39", null, "");
        myViewModel.insertFarmer(farmer39);
        Farmer_Expert farmerExpert39 = new Farmer_Expert(0, "user_39", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert39);

        Farmer farmer40 = new Farmer("user_40", "password_40", 1273, 1231231250, "Farmer 40", null, "");
        myViewModel.insertFarmer(farmer40);
        Farmer_Expert farmerExpert40 = new Farmer_Expert(0, "user_40", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert40);

        Farmer farmer41 = new Farmer("user_41", "password_41", 1274, 1231231251, "Farmer 41", null, "");
        myViewModel.insertFarmer(farmer41);
        Farmer_Expert farmerExpert41 = new Farmer_Expert(0, "user_41", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert41);

        Farmer farmer42 = new Farmer("user_42", "password_42", 1275, 1231231252, "Farmer 42", null, "");
        myViewModel.insertFarmer(farmer42);
        Farmer_Expert farmerExpert42 = new Farmer_Expert(0, "user_42", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert42);

        Farmer farmer43 = new Farmer("user_43", "password_43", 1276, 1231231253, "Farmer 43", null, "");
        myViewModel.insertFarmer(farmer43);
        Farmer_Expert farmerExpert43 = new Farmer_Expert(0, "user_43", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert43);

        Farmer farmer44 = new Farmer("user_44", "password_44", 1277, 1231231254, "Farmer 44", null, "");
        myViewModel.insertFarmer(farmer44);
        Farmer_Expert farmerExpert44 = new Farmer_Expert(0, "user_44", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert44);

        Farmer farmer45 = new Farmer("user_45", "password_45", 1278, 1231231255, "Farmer 45", null, "");
        myViewModel.insertFarmer(farmer45);
        Farmer_Expert farmerExpert45 = new Farmer_Expert(0, "user_45", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert45);

        Farmer farmer46 = new Farmer("user_46", "password_46", 1279, 1231231256, "Farmer 46", null, "");
        myViewModel.insertFarmer(farmer46);
        Farmer_Expert farmerExpert46 = new Farmer_Expert(0, "user_46", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert46);

        Farmer farmer47 = new Farmer("user_47", "password_47", 1280, 1231231257, "Farmer 47", null, "");
        myViewModel.insertFarmer(farmer47);
        Farmer_Expert farmerExpert47 = new Farmer_Expert(0, "user_47", "john_doe");
        myViewModel.insertFarmerExpert(farmerExpert47);

        Farmer farmer48 = new Farmer("user_48", "password_48", 1281, 1231231258, "Farmer 48", null, "");
        myViewModel.insertFarmer(farmer48);
        Farmer_Expert farmerExpert48 = new Farmer_Expert(0, "user_48", "alice_brown");
        myViewModel.insertFarmerExpert(farmerExpert48);

        Farmer farmer49 = new Farmer("user_49", "password_49", 1282, 1231231259, "Farmer 49", null, "");
        myViewModel.insertFarmer(farmer49);
        Farmer_Expert farmerExpert49 = new Farmer_Expert(0, "user_49", "bob_white");
        myViewModel.insertFarmerExpert(farmerExpert49);

        Farmer farmer50 = new Farmer("user_50", "password_50", 1283, 1231231260, "Farmer 50", null, "");
        myViewModel.insertFarmer(farmer50);
        Farmer_Expert farmerExpert50 = new Farmer_Expert(0, "user_50", "jane_smith");
        myViewModel.insertFarmerExpert(farmerExpert50);

    }
}