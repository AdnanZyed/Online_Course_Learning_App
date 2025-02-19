package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;





public class UpdateCourse1 extends AppCompatActivity {
    RecyclerView recyclerView;
    CourseAdapter courseAdapter;
    My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_course1);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        recyclerView = findViewById(R.id.rv_courses_update);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter = new CourseAdapter(UpdateCourse1.this, new ArrayList<>(), "");
        recyclerView.setAdapter(courseAdapter);


        myViewModel.getAllCourse().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                courseAdapter.setCourseList(courses);
            }
        });


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


            }


            @SuppressLint("ResourceAsColor")
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                int recyclerViewWidth = recyclerView.getWidth();


                viewHolder.itemView.setTranslationX(recyclerViewWidth / 4);

                View itemView = viewHolder.itemView;
                Paint paint = new Paint();
                paint.setColor(0xFF25750C);


                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom(), paint);
                Bitmap trashIcon = BitmapFactory.decodeResource(getResources(), R.drawable.img_19);

                if (dX > 0) {
                    float iconMargin = (itemView.getHeight() - trashIcon.getHeight()) / 2;
                    float iconLeft = itemView.getLeft() + 140;
                    float iconTop = itemView.getTop() + iconMargin;

                    c.drawBitmap(trashIcon, iconLeft, iconTop, null);

                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                if (event.getX() > iconLeft && event.getX() < iconLeft + trashIcon.getWidth() &&
                                        event.getY() > iconTop && event.getY() < iconTop + trashIcon.getHeight()) {

                                    Intent intent=new Intent(UpdateCourse1.this,UpdateCourse.class);
                                    int position = viewHolder.getAdapterPosition();
                                    Course course = courseAdapter.getCourseAt(position);
                                    intent.putExtra("ID",course.getCourse_ID());
                                    intent.putExtra("Picture",course.getProfilePicture());
                                    intent.putExtra("Categorie",course.getCategorie());
                                    intent.putExtra("Description",course.getDescription());
                                    intent.putExtra("Price",course.getPrice());
                                    intent.putExtra("NAME",course.getCourse_NAME());
                                    intent.putExtra("USER_Name",course.getTeacher_USER_Name());
                                    intent.putExtra("Image",course.getImage());
                                    startActivity(intent);

                                }
                            }
                            return false;
                        }
                    });

                }
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }




}