package com.example.baobang.gameduangua.youtube_video;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.all_course.ListCourseActivity;
import com.example.baobang.gameduangua.all_course.detail.CourseDetailActivity;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by huuduc on 04/03/2018.
 */

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

        private YouTubePlayer YPlayer;
        private static final String API_KEY = "AIzaSyD7XAAYCbQPw6FOd8Yz5JqyOKh7I2PJS0c";
        private static String VIDEO_ID = "";
        private static final int RECOVERY_DIALOG_REQUEST = 1;
        private String COURSE_ID = "";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_study);

            Intent intent = getIntent();
            if (intent.hasExtra(Constant.URL)){
                VIDEO_ID = intent.getStringExtra(Constant.URL);
            }
            YouTubePlayerView youTubeView = findViewById(R.id.youtube_view);
            youTubeView.initialize(API_KEY, this);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.you_tube_api, menu);
            return true;
        }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "There was an error initializing the YouTubePlayer", Toast.LENGTH_LONG).show();
        }
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RECOVERY_DIALOG_REQUEST) {
                // Retry initialization if user performed a recovery action
                getYouTubePlayerProvider().initialize(API_KEY, this);
            }
        }

        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                            YouTubePlayer player, boolean wasRestored) {
            YPlayer = player;
		/*
		 * Now that this variable YPlayer is global you can access it
		 * throughout the activity, and perform all the player actions like
		 * play, pause and seeking to a position by code.
		 */
		    YPlayer.setPlayerStateChangeListener(playStateChangeListener);
		    YPlayer.setPlaybackEventListener(playbackEventListener);
            if (!wasRestored) {
                YPlayer.loadVideo(VIDEO_ID);
//                YPlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            }
        }

        private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onStopped() {

            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {

            }
        };

        private YouTubePlayer.PlayerStateChangeListener playStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {

            }

            @Override
            public void onVideoEnded() {

            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
