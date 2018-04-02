package com.example.baobang.gameduangua.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.all_course.ListCourseActivity;
import com.example.baobang.gameduangua.all_course.detail.CourseDetailActivity;
import com.example.baobang.gameduangua.model.Category;
import com.example.baobang.gameduangua.model.Course;
import com.example.baobang.gameduangua.utils.AppUtils;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/*
 * Created by baobang on 4/2/18.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryHolder> {

    private Context mContext;
    private List<Category> mCategories;

    public CategoryListAdapter(Context mContext, List<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_item, parent, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
            holder.onBindView(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{

        TextView txtCategoryName;
        RecyclerView rcCourse;
        CourseListAdapter courseListAdapter;

        CategoryHolder(View itemView) {
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
            rcCourse = itemView.findViewById(R.id.rcCourses);
        }

        void onBindView(Category category) {
            txtCategoryName.setText(category.getName());

            courseListAdapter = new CourseListAdapter(mContext, category.getCourses());
            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    mContext,
                    LinearLayoutManager.HORIZONTAL,
                    false);
            rcCourse.setLayoutManager(layoutManager);
            rcCourse.setAdapter(courseListAdapter);
            courseListAdapter.setOnItemClickListener(new CourseListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int pos) {

                Course course = category.getCourses().get(pos);

                AppUtils.setValueToSharedPreferences(
                        mContext,
                        Constant.KEY_PREFERENCES,
                        MODE_PRIVATE,
                        Constant.COURSE_ID,
                        course.getId()
                );

                Intent detailIntent = new Intent(mContext, CourseDetailActivity.class);
                detailIntent.putExtra(Constant.COURSE_ID, course.getId());
                mContext.startActivity(detailIntent);
                }
            });
        }
    }
}
