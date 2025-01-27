package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewTreeViewModelKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    CourseAdapter adapter;
    String students_u;
    private List<Course> studentsList1 = new ArrayList<>();

    My_View_Model myViewModel;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        Bundle args = getArguments();
        if (args != null) {
            students_u = args.getString("USER_NAME");
        }

        recyclerView = view.findViewById(R.id.recycler_view_Cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        // مراقبة الـ LiveData للكورسات المحفوظة
        myViewModel.getisAddCartCoursesByStudent(students_u).observe((LifecycleOwner) getViewLifecycleOwner(), AddCart -> {

            Log.d("MainActivity_Main", "1111111111111111111111111111111111 " + AddCart);

            List<Integer> courseIds = new ArrayList<>();
            for (Student_Course sc : AddCart) {
                if (!sc.isAddCart() || !sc.isRegister()) {
                    courseIds.add(sc.getCourse_ID());
                    Log.d("MainActivity_Main", "2222222222222222222222222222222222222222 " + sc);
                    Log.d("MainActivity_Main", "333333333333333333333333333333333333333 " + courseIds);

                }
            }

            myViewModel.getAllCoursesByIds(courseIds).observe(getViewLifecycleOwner(), courses -> {
                Log.d("MainActivity_Main", "444444444444444444444444444444444444444444444 " + courses);

                CourseAdapter adapter = new CourseAdapter(requireContext(),courses,students_u);
                recyclerView.setAdapter(adapter);

            });
        });


//
//
//            if (AddCart != null && !AddCart.isEmpty()) {
//                studentsList1.clear(); // تأكد من مسح القائمة قبل إضافة العناصر الجديدة
//
//                // عملية غير متزامنة للحصول على جميع الكورسات المرتبطة بـ Student_Course
//                for (Student_Course studentCourse : AddCart) {
//                    myViewModel.getAllCoursesById(studentCourse.getCourse_ID()).observe((LifecycleOwner) getViewLifecycleOwner(), courses -> {
//                        if (courses != null && !courses.isEmpty()) {
//                            studentsList1.addAll(courses); // إضافة الكورسات إلى القائمة
//
//                            // بعد تحديث الـ studentsList1، تأكد من أن واجهة المستخدم تتحدث
//                                adapter = new CourseAdapter(requireContext(), studentsList1, students_u);
//                                recyclerView.setAdapter(adapter); // تعيين الـ Adapter
//
//                        }
//                    });
//                }
//
//            } else {
//                // عرض رسالة في حال عدم وجود كورسات محفوظة
////                Toast.makeText(this, "لا توجد كورسات محفوظة!", Toast.LENGTH_SHORT).show();
//            }
//        });


        return view;
    }
}