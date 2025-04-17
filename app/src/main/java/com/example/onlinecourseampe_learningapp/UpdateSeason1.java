package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
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

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateSeason1 extends AppCompatActivity {
    RecyclerView recyclerView;
    SeasonAdapter seasonAdapter;
    My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_season1);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        recyclerView = findViewById(R.id.rv_Seasons_update);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        seasonAdapter = new SeasonAdapter(UpdateSeason1.this, new ArrayList<>(), "");
        recyclerView.setAdapter(seasonAdapter);


        myViewModel.getAllSeason().observe(this, new Observer<List<Season>>() {
            @Override
            public void onChanged(List<Season> seasons) {
                seasonAdapter.setSeasonList(seasons);
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
                    float iconLeft = itemView.getLeft() + 100;
                    float iconTop = itemView.getTop() + iconMargin;

                    c.drawBitmap(trashIcon, iconLeft, iconTop, null);

                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                if (event.getX() > iconLeft && event.getX() < iconLeft + trashIcon.getWidth() &&
                                        event.getY() > iconTop && event.getY() < iconTop + trashIcon.getHeight()) {

                                    Intent intent=new Intent(UpdateSeason1.this,UpdateSeason.class);
                                    int position = viewHolder.getAdapterPosition();
                                    Season season = seasonAdapter.getSeasonAt(position);
                                    intent.putExtra("ID",season.getSeason_ID());
                                    intent.putExtra("Picture",season.getProfilePicture());
                                    intent.putExtra("Categorie",season.getCategorie());
                                    intent.putExtra("Description",season.getDescription());
                                    intent.putExtra("Price",season.getPrice());
                                    intent.putExtra("NAME",season.getSeason_NAME());
                                    intent.putExtra("USER_Name",season.getExpert_USER_Name());
                                    intent.putExtra("Image",season.getImage());
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