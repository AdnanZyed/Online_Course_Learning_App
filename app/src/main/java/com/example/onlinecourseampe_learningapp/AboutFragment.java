package com.example.onlinecourseampe_learningapp;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineSeasonampe_learningapp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {


    My_View_Model myViewModel;
    ImageView Message;
    TextView description;
    String FarmerUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);


        String expertUserName = getArguments().getString("TEACHER_USER_NAME1");
        String SeasonDescription = getArguments().getString("seasonDescription");
        FarmerUser = getArguments().getString("STUDENT_USER");

        Message = view.findViewById(R.id.message_btn);
        description = view.findViewById(R.id.description);

        description.setText(SeasonDescription);


        Bundle bundle = new Bundle();

        myViewModel.getAllExpertByUser(expertUserName).observe(getViewLifecycleOwner(), expert -> {

            if (expert != null) {
                TextView expertNameTextView = view.findViewById(R.id.expert_name1);
                TextView educationTextView = view.findViewById(R.id.expert_magor);
                ImageView expertImageView = view.findViewById(R.id.imageView4);

                Expert expertdata = expert.get(0);
                Bitmap bitmap = BitmapFactory.decodeByteArray(expertdata.getImage_teatcher(), 0, expertdata.getImage_teatcher().length);

                expertNameTextView.setText(expertdata.getTeatur_name());
                educationTextView.setText(expertdata.getEducation());
                expertImageView.setImageBitmap(bitmap);


                bundle.putString("TEACHER_NAME_TEXT_VIEW", expertdata.getTeatur_name());
                bundle.putString("TEACHER_USER_NAME_TEXT_VIEW", expertdata.getTeatur_USER_Name());
                bundle.putString("EDUCATION_TEXT_VIEW", expertdata.getEducation());
                bundle.putString("STUDENT_USER", FarmerUser);

                bundle.putByteArray("BITMAP", expertdata.getImage_teatcher());
            }
        });


        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), ExpertProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        return view;
    }

}