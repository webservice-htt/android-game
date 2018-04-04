package com.example.baobang.gameduangua.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.model.Course;
import com.example.baobang.gameduangua.model.UserCourse;

import java.util.List;

/**
 * Created by huuduc on 31/01/2018.
 */

public class UserCourseListAdapter extends RecyclerView.Adapter<UserCourseListAdapter.UserCourseHolder>{

    private Context context;
    private List<UserCourse> courseList;

    public UserCourseListAdapter(Context context, List<UserCourse> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public UserCourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.course_card_view_item, parent, false);
        return new UserCourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserCourseHolder holder, int position) {
        UserCourse course = this.courseList.get(position);
        holder.bindView(course);
    }

    @Override
    public int getItemCount() {
        return this.courseList != null ? courseList.size() : 0;
    }

    public class UserCourseHolder extends RecyclerView.ViewHolder {
        private CardView cvContainer;
        private TextView txtCourseName;
        private TextView txtDescription;
        private ImageView imgCourse;

        public UserCourseHolder(View itemView) {
            super(itemView);
            cvContainer = itemView.findViewById(R.id.container);
            txtCourseName = itemView.findViewById(R.id.txtCourseName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgCourse = itemView.findViewById(R.id.imgCourse);

        }

        public void bindView (UserCourse course){
            txtCourseName.setText(course.getCourse().getCourseName());
            txtDescription.setText(course.getCourse().getDescription());
            Glide.with(context).load(course.getCourse().getImgUrl()).into(imgCourse);
            cvContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    private UserCourseListAdapter.OnItemClickListener listener;
    public void setOnItemClickListener(UserCourseListAdapter.OnItemClickListener callBack){
        this.listener = callBack;
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
