package com.example.baobang.gameduangua.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.model.Lesson;

import java.util.List;

/**
 * Created by huuduc on 08/03/2018.
 */

public class LessonListAdapter extends RecyclerView.Adapter<LessonListAdapter.LessonViewHolder> {

    private List<Lesson> listLesson;

    public void setListLesson(List<Lesson> listLesson) {
        this.listLesson = listLesson;
    }

    @Override
    public LessonListAdapter.LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.lesson_item, parent, false);


        return new LessonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LessonListAdapter.LessonViewHolder holder, int position) {
        Lesson lesson = listLesson.get(position);
        holder.bindView(lesson);
    }

    @Override
    public int getItemCount() {
        return listLesson == null ? 0 : listLesson.size();
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder{

        private TextView txtLessonName;
        private ConstraintLayout clLesson;

        public LessonViewHolder(View itemView) {
            super(itemView);
            txtLessonName = itemView.findViewById(R.id.txtLessonName);
            clLesson = itemView.findViewById(R.id.layoutLesson);
        }

        public void bindView(Lesson lesson){
            txtLessonName.setText(lesson.getTenBH());
            clLesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onClick(getAdapterPosition());
                }
            });
        }
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener callBack){
        this.listener = callBack;
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
