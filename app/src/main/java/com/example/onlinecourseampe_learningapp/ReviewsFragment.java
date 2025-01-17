package com.example.onlinecourseampe_learningapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReviewsFragment extends Fragment {
    private My_View_Model myViewModel;
    //    List<Course_Reviews> reviewsList;
    String student_name;
    byte[] bytes;
    ImageView imageView;
    RecyclerView recyclerView;
    Spinner ratingSpinner;

    Bundle args;
    String userString;
    TextView total_Comments;
    TextView total_Rating;
    EditText edit_Comment;
    ImageView imageSend;
    //  String coursest;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);
        imageView = view.findViewById(R.id.image_student);
        edit_Comment = view.findViewById(R.id.edit_comment);
        total_Comments = view.findViewById(R.id.total_comments);
        total_Rating = view.findViewById(R.id.total_rating);
        ratingSpinner = view.findViewById(R.id.rating);
        imageSend = view.findViewById(R.id.send);

        recyclerView = view.findViewById(R.id.rv_review);
//        args = getArguments();
//        if (args != null) {
//            userString = args.getString("USER_NAME");
//
//
//        }
//        Log.d("CourseDetailsActivity", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU" + userString);
//

        Bundle bundle1 = getArguments();
        int courseInt = bundle1.getInt("COURSE_ID1");

//        if (edit_Comment.getText().equals(null)) {
//            imageSend.setColorFilter(Color.BLUE);
//
//
//        }

        Log.d("CourseDetailsActivity", "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII" + courseInt);


        myViewModel.getAllStudentByUser("aDNAN@123").observe(getViewLifecycleOwner(), students -> {
            // استخدام LinearLayoutManager مع التمرير الأفقي

            Student student = students.get(0);
            student_name = student.getS_name().toString();


            bytes = student.getS_Image();
            if (bytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.drawable.profile);
            }


        });
        myViewModel.getAllReviewsByCourseId(courseInt).observe(requireActivity(), reviews -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // إنشاء قائمة بيانات

            // إنشاء Adapter وتعيينه إلى RecyclerView
            ReviewsAdapter adapter = new ReviewsAdapter(getContext(), reviews);
            recyclerView.setAdapter(adapter);
             int count = reviews.size();
            int f=0;

            for (int i = 0; i <= reviews.size()-1; i++) {
              f  =+reviews.get(i).getRating();
            }

            total_Comments.setText("" + count);
            total_Rating.setText("" + f );
        });

//
//        if (!edit_Comment.getText().equals(null) || !edit_Comment.getText().equals("")) {
//
//            imageSend.setColorFilter(Color.BLUE);
//
//        }


        // مراقبة النص داخل الـ EditText
        edit_Comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // لا حاجة لتنفيذ أي شيء هنا
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    // النص فارغ: تعطيل زر الإرسال وتغيير لونه إلى الرمادي
                    imageSend.setImageResource(R.drawable.send);
                    imageSend.setEnabled(false);
                } else {
                    // النص موجود: تمكين زر الإرسال وتغيير لونه إلى الأزرق
                    imageSend.setImageResource(R.drawable.send_b);
                    imageSend.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // لا حاجة لتنفيذ أي شيء هنا
            }
        });

        // تنفيذ الإجراء عند الضغط على زر الإرسال
        imageSend.setOnClickListener(v -> {
            if (!edit_Comment.getText().toString().trim().isEmpty()) {
                // الحصول على التاريخ والوقت الحالي
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = now.format(formatter);

                int rating_spinner = Integer.parseInt(ratingSpinner.getSelectedItem().toString());
                Log.d("ReviewsFragment", "Sending review at: " + formattedDate);

                // إنشاء تعليق جديد وإضافته إلى قاعدة البيانات
                Course_Reviews review = new Course_Reviews(0, bytes, student_name, userString,
                        edit_Comment.getText().toString(),
                        formattedDate, 0, courseInt, rating_spinner, false);
                myViewModel.insertReview(review);

                // إعادة تعيين حقل النص
                edit_Comment.setText(null);
            }
        });


        // استكمال الكود لمراقبة البيانات وربط RecyclerView...

        return view;
    }
}
