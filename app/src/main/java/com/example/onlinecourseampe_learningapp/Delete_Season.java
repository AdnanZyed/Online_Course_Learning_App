package com.example.onlinecourseampe_learningapp;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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

public class Delete_Season extends AppCompatActivity {
    RecyclerView recyclerView;
    SeasonAdapter seasonAdapter;
    My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_season);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        recyclerView = findViewById(R.id.rv_Seasons_delete);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        seasonAdapter = new SeasonAdapter(Delete_Season.this, new ArrayList<>(), "");
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


            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                int recyclerViewWidth = recyclerView.getWidth();


                viewHolder.itemView.setTranslationX(recyclerViewWidth / 4);

                View itemView = viewHolder.itemView;
                Paint paint = new Paint();
                paint.setColor(Color.RED);


                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom(), paint);
                Bitmap trashIcon = BitmapFactory.decodeResource(getResources(), R.drawable.trash);

                if (dX > 0) {
                    float iconMargin = (itemView.getHeight() - trashIcon.getHeight()) / 2;
                    float iconLeft = itemView.getLeft() + 120;
                    float iconTop = itemView.getTop() + iconMargin;

                    c.drawBitmap(trashIcon, iconLeft, iconTop, null);

                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                if (event.getX() > iconLeft && event.getX() < iconLeft + trashIcon.getWidth() &&
                                        event.getY() > iconTop && event.getY() < iconTop + trashIcon.getHeight()) {

                                    showCustomDialog(viewHolder);
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

    private void showCustomDialog(RecyclerView.ViewHolder viewHolder) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_dialog);
        dialog.setCancelable(true);


        Button viewSeasonButton = dialog.findViewById(R.id.btn_view_Season2);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel2);


        viewSeasonButton.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Season season = seasonAdapter.getSeasonAt(position);
            myViewModel.deleteSeason(season);

            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> {
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            window.setAttributes(params);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setBackgroundDrawableResource(R.drawable.rounded_dialog_background);

        }
        dialog.show();
    }


}