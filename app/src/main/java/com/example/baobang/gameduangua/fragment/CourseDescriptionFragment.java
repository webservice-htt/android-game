package com.example.baobang.gameduangua.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.base.BaseFragment;
import com.example.baobang.gameduangua.model.LessonResponse;

public class CourseDescriptionFragment extends BaseFragment {

    private TextView txtCourseDescription;

    public static CourseDescriptionFragment newInstance(String des) {
        CourseDescriptionFragment fragment = new CourseDescriptionFragment();

        Bundle bundle = new Bundle();
        bundle.putString("DES", des);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_desc, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtCourseDescription = view.findViewById(R.id.courseDiscription);

        if(txtCourseDescription != null){
            String des = getArguments().getString("DES");
            txtCourseDescription.setText(des);
        }
    }

    @Override
    public void onUpdateData(LessonResponse lessonResponse) {
        if (txtCourseDescription != null)
            txtCourseDescription.setText(lessonResponse.getDescription());
    }
}
