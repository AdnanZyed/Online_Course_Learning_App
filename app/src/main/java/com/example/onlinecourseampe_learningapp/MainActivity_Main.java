package com.example.onlinecourseampe_learningapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Main  extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private List<Button> buttons = new ArrayList<>();
    private Button activeButton = null;

    HomeFragment homeFragment=new HomeFragment();
    Prophile_Fragment prophileFragment=new Prophile_Fragment();
    CoursesFragment coursesFragment=new CoursesFragment();
    CartFragment cartFragment=new CartFragment();
    InboxFragment inboxFragment=new InboxFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        // ربط الأزرار
        Button btnAll = findViewById(R.id.btn_all);
        Button btn3DDesign = findViewById(R.id.btn_3d_design);
        Button btnBusiness = findViewById(R.id.btn_business);
        Button btnArt = findViewById(R.id.btn_art);
        Button btnProgramming = findViewById(R.id.btn_programming);

        // إضافة الأزرار إلى القائمة
        buttons.add(btnAll);
        buttons.add(btn3DDesign);
        buttons.add(btnBusiness);
        buttons.add(btnArt);
        buttons.add(btnProgramming);

        // إضافة مستمع النقر لكل زر
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClicked(button);
                }
            });
        }
    }

    private void onButtonClicked(Button clickedButton) {
        // إعادة لون الزر النشط السابق إلى اللون الافتراضي
        if (activeButton != null) {
            activeButton.setBackgroundColor(Color.parseColor("#008577")); // اللون الافتراضي (Teal)
        }

        // تغيير لون الزر الذي تم النقر عليه
        clickedButton.setBackgroundColor(Color.parseColor("#FF6200EE")); // اللون الجديد (Purple)

        // تحديث الزر النشط
        activeButton = clickedButton;


//        Today's Special
//
//        Get a discount for every course ordert
//        Only volid for today!
//
//


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.prophile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, prophileFragment)
                        .commit();
                return true;

            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeFragment)
                        .commit();
                return true;

            case R.id.courses:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, coursesFragment)
                        .commit();
                return true;
                case R.id.cart:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, cartFragment)
                        .commit();
                return true;
                case R.id.inbox:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, inboxFragment)
                        .commit();
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    }
