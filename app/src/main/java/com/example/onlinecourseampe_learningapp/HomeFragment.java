package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<Button> buttons = new ArrayList<>();
    private Button activeButton = null;
    Button btnAll;
    ViewPager2 viewPager;
    Button btn3DDesign;
    Bundle args;
    Bundle bundle;
    RecyclerView expertRecyclerView;
    ExpertAdapter expertAdapter;
    Button btnProgramming;
    Button btnBusiness;
    Button btnArt;
    TextView tv_Seeall;
    TextView tv_Seeall2;
    TextView tv_Welcom;
    TextView tv_Name;
    EditText e_searsh;
    ImageView Iv_notification;
    ImageView Iv_Bookmark;
    String farmers_u;
    ImageView iv_S;
    String bio;

    private My_View_Model myViewModel;

    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);

        args = getArguments();
        if (args != null) {
            farmers_u = args.getString("USER_NAME");


        }


        ViewPager2 viewPager2 = rootView.findViewById(R.id.viewPager2);


        List<Integer> images = Arrays.asList(R.drawable.ad1, R.drawable.ad2, R.drawable.ad3);
        ImageAdapter adapter = new ImageAdapter(images);

        viewPager2.setAdapter(adapter);

        bundle = new Bundle();
        bundle.putString("USER_NAME_R", farmers_u);



        btnAll = rootView.findViewById(R.id.btn_all);
        Iv_notification = rootView.findViewById(R.id.iv_notification);
        iv_S = rootView.findViewById(R.id.iv_s);
        e_searsh = rootView.findViewById(R.id.et_searsh);
        Iv_Bookmark = rootView.findViewById(R.id.iv_Bookmark);
        tv_Seeall = rootView.findViewById(R.id.tv_seeall);
        tv_Seeall2 = rootView.findViewById(R.id.tv_seeall2);
        tv_Name = rootView.findViewById(R.id.tv_name);
        tv_Welcom = rootView.findViewById(R.id.tv_welcom);
        btn3DDesign = rootView.findViewById(R.id.btn_3d_design);
        btnProgramming = rootView.findViewById(R.id.btn_programming);
        expertRecyclerView = rootView.findViewById(R.id.rv_t);
        btnBusiness = rootView.findViewById(R.id.btn_business);
        btnArt = rootView.findViewById(R.id.btn_art);
        SeasonFragment fragment = (SeasonFragment) getParentFragmentManager()
                .findFragmentById(R.id.fram_corse);


        if (fragment != null) {
            fragment.loadSeasons();
            onButtonClicked(btnAll);
        }


        buttons.add(btnAll);
        buttons.add(btnProgramming);
        buttons.add(btn3DDesign);
        buttons.add(btnBusiness);
        buttons.add(btnArt);

        Button defaultButton = btnAll;
        defaultButton.setBackgroundResource(R.drawable.catigories_btn_selected);
        defaultButton.setTextColor(R.color.white);
        for (Button button : buttons) {
            button.setOnClickListener(v -> onButtonClicked(button));
        }


        myViewModel.getAllFarmerByUser(farmers_u).observe(getViewLifecycleOwner(), farmers -> {
            if (farmers != null && !farmers.isEmpty()) {

                Farmer farmer = farmers.get(0);
                String farmer_name = farmer.getS_name().toString();
                bio = farmer.getBio().toString();
                if (bio.isEmpty()) {
                    tv_Welcom.setText("Welcom");


                } else {
                    tv_Welcom.setText(bio);
                }
                byte[] bytes = farmer.getS_Image();
                if (bytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    iv_S.setImageBitmap(bitmap);
                } else {
                    iv_S.setImageResource(R.drawable.profile);
                }

                tv_Name.setText(farmer_name);
            }
        });

        e_searsh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                e_searsh.setBackgroundResource(R.drawable.shap_selected);

            }});
        iv_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmersProfileFragment fragment = new FarmersProfileFragment();
                Bundle args = new Bundle();
                args.putString("USER_NAME_R", farmers_u);
                fragment.setArguments(args);


                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

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
                intent.putExtra("USER", farmers_u);

                startActivity(intent);
            }
        });


        if (savedInstanceState == null) {
            SeasonFragment seasonFragment = new SeasonFragment();
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME_R", farmers_u);
            seasonFragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fram_corse, seasonFragment)
                    .commit();
            onButtonClicked(btnAll);

        }

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeasonFragment fragment = (SeasonFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadSeasons();
                    onButtonClicked(btnAll);


                }
            }
        });
        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SeasonFragment fragment = (SeasonFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadSeasons_Categorie_Art();
                    onButtonClicked(btnArt);


                }

            }
        });

        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SeasonFragment fragment = (SeasonFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadSeasons_Categorie_Business();
                    onButtonClicked(btnBusiness);


                }

            }
        });

        btnProgramming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SeasonFragment fragment = (SeasonFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadSeasons_Categorie_Programming();
                    onButtonClicked(btnProgramming);


                }

            }
        });

        btn3DDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SeasonFragment fragment = (SeasonFragment) getFragmentManager()
                        .findFragmentById(R.id.fram_corse);

                if (fragment != null) {
                    fragment.loadSeasons_Categorie_3D_Design();
                    onButtonClicked(btn3DDesign);


                }

            }
        });


        tv_Seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), TopMentors.class);
                intent.putExtra("STUDENT_USER", farmers_u);
                startActivity(intent);
            }
        });
        tv_Seeall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), Most_Popular.class);
                intent.putExtra("USER", farmers_u);

                startActivity(intent);
            }
        });


        loadExpert();


        return rootView;
    }

    public void loadExpert() {
        myViewModel.getAllExpert().observe(getViewLifecycleOwner(), experts -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);


            expertAdapter = new ExpertAdapter(requireContext(), new ArrayList<>(), farmers_u);

            expertRecyclerView.setLayoutManager(layoutManager);


            expertAdapter.setExpertList(experts);
            expertRecyclerView.setAdapter(expertAdapter);

        });
    }


    @SuppressLint("ResourceAsColor")
    private void onButtonClicked(Button clickedButton) {
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.catigories_btn);
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        }


        clickedButton.setBackgroundResource(R.drawable.catigories_btn_selected);
        clickedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));

        activeButton = clickedButton;


    }
}