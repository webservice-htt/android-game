package com.example.baobang.gameduangua.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.LessonListAdapter;
import com.example.baobang.gameduangua.base.BaseFragment;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.model.Lesson;
import com.example.baobang.gameduangua.model.LessonResponse;
import com.example.baobang.gameduangua.youtube_video.VideoActivity;

import java.util.List;

/**
 * Created by huuduc on 08/03/2018.
 */

public class LessonFragment extends BaseFragment {

    private RecyclerView rvListLesson;
    private LessonListAdapter listAdapter;
    private List<Lesson> listLesson;
    private SOService mService;

    public static LessonFragment newInstance(List<Lesson> lessons) {
        LessonFragment fragment = new LessonFragment();
        fragment.listLesson = lessons;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.activity_lesson_list, container, false);
        return itemView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addControls(view);
    }

    private void addControls(View itemView) {
        rvListLesson = itemView.findViewById(R.id.rvLessonList);
        listAdapter = new LessonListAdapter();

        rvListLesson.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter.setListLesson(listLesson);
        rvListLesson.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener(new LessonListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Lesson lesson = listLesson.get(pos);
                String youtubeUrl = lesson.getLessonUrl().substring(32, 43);
                Log.d("youtube", "onClick: " + youtubeUrl);
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra(Constant.URL, youtubeUrl);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onUpdateData(LessonResponse lessonResponse) {

    }
}
