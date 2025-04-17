package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineSeasonampe_learningapp.R;

public class EnrollSeasonActivity extends AppCompatActivity {
    private My_View_Model myViewModel;
    String userName;
    int price;
    private boolean is;
    private boolean is2;
    int seasond;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_season);
        Button btn_buy = findViewById(R.id.bt_buy2);
        ImageView imageView = findViewById(R.id.back_icon_enrollSeason);
        Button btn_cart = findViewById(R.id.add_cart);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        userName = getIntent().getStringExtra("USER");
        price = getIntent().getIntExtra("PRICE", -1);
        seasond = getIntent().getIntExtra("COURSE_ID", -1);
//        myViewModel.isFarmerSeasonExists(userName, seasond, true).observe((EnrollSeasonActivity.this), isHad -> {
//            is = isHad;
//        });
        myViewModel.isFarmerSeasonExistsC(userName, seasond, true).observe((EnrollSeasonActivity.this), isHadc -> {
            if (isHadc) {
                btn_cart.setBackgroundResource(R.drawable.delete_btn);
                btn_cart.setTextColor(R.color.white);
                btn_cart.setText("Delete Card");
            }


        });
//        if (is) {
//            btn_cart.setVisibility(View.GONE);
//            btn_buy.setVisibility(View.GONE);
//
//        }


        btn_buy.setText("Enroll Season - $" + price);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollSeasonActivity.this, EnrollCodeActivity.class);
                intent.putExtra("USER", userName);
                intent.putExtra("COURSE_ID", seasond);
                intent.putExtra("PRICE", price);
                startActivity(intent);

            }
        });

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                myViewModel.isFarmerSeasonExistsC(userName, seasond, true).observe((EnrollSeasonActivity.this), isHadc -> {
                    myViewModel.isFarmerSeasonExistsB(userName, seasond, true).observe((EnrollSeasonActivity.this), isHadb -> {
                        myViewModel.isFarmerSeasonExists(userName, seasond, true).observe((EnrollSeasonActivity.this), isHad -> {

                            if (!isHadc && !isHadb && !isHad) {
                                Farmer_Seasons farmerSeason = new Farmer_Seasons(userName, seasond, false, false, true, 0);
                                myViewModel.insertFarmerSeason(farmerSeason);
                                btn_cart.setBackgroundResource(R.drawable.delete_btn);
                                btn_cart.setTextColor(R.color.white);
                                btn_cart.setText("Delete Card");
                            } else if (isHadb && !isHadc) {
                                Farmer_Seasons farmerSeason = new Farmer_Seasons(userName, seasond, true, false, true, 0);
                                btn_cart.setBackgroundResource(R.drawable.delete_btn);
                                btn_cart.setTextColor(R.color.white);
                                btn_cart.setText("Delete Card");
                                myViewModel.updateSeasonFarmer(farmerSeason);
                            } else if (isHadc && !isHadb && !isHad) {

                                Farmer_Seasons farmerSeason = new Farmer_Seasons(userName, seasond, false, false, false, 0);
                                myViewModel.deleteFarmerSeason(farmerSeason);
                                btn_cart.setBackgroundResource(R.drawable.btn_cart);
                                btn_cart.setTextColor(R.color.green);
                                btn_cart.setText("Add New Card");

                            }

                        });
                    });
                });
            }
        });
    }
}