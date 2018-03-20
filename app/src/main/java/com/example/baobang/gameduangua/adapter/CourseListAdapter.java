package com.example.baobang.gameduangua.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.all_course.detail.CourseDetailActivity;
import com.example.baobang.gameduangua.model.Course;

import java.util.List;

/**
 * Created by huuduc on 31/01/2018.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseHolder>{

    private Context context;
    private List<Course> courseList;

    public CourseListAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.course_card_view_item, parent, false);
        return new CourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseHolder holder, int position) {
        Course course = this.courseList.get(position);
        holder.bindView(course);
    }

    @Override
    public int getItemCount() {
        return this.courseList != null ? courseList.size() : 0;
    }

    public class CourseHolder extends RecyclerView.ViewHolder {

        private TextView txtCourseName;
        private TextView txtDescription;
        private ImageView imgCourse;

        public CourseHolder(View itemView) {
            super(itemView);
            txtCourseName = itemView.findViewById(R.id.txtCourseName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgCourse = itemView.findViewById(R.id.imgCourse);

        }

        public void bindView (Course course){
            txtCourseName.setText(course.getCourseName());
            txtDescription.setText(course.getDescription());
            Glide.with(context).load(course.getImgUrl()).into(imgCourse);
        }
    }
}
