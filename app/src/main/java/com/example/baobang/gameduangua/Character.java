package com.example.baobang.gameduangua;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

class Character {

    private int recourses[];

    private ImageView character;
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private int screenWidth;
    private int screenHeight;
    private int characterX;
    private int characterY;
    private int flag;
    Character(Activity context, ImageView character, int recourses[]){
        this.recourses = recourses;
        this.character = character;
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        flag = Constants.RIGHT;
        timer = new Timer();
        handler = new Handler();
    }

    void start(){
        character.setBackgroundResource(R.drawable.horse1_right);
        ((AnimationDrawable)character.getBackground()).start();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);
    }

    private void changePos() {
        if(flag == Constants.RIGHT){
            characterX += 10;
            if(character.getX() + characterX > screenWidth){
                flag = Constants.DOWN;
                characterX -= 10;
                character.setBackgroundResource(recourses[flag]);
                ((AnimationDrawable)character.getBackground()).start();
            }

        }else if(flag == Constants.DOWN){
            characterY += 10;
            if(character.getY() + characterX > screenHeight){
                flag = Constants.LEFT;
                characterY -= 10;
                character.setBackgroundResource(recourses[flag]);
                ((AnimationDrawable)character.getBackground()).start();
            }

        }else if(flag == Constants.LEFT){
            characterX -= 10;
            if(character.getX() + characterX < 0){
                flag = Constants.UP;
                characterX += 10;
                character.setBackgroundResource(recourses[flag]);
                ((AnimationDrawable)character.getBackground()).start();
            }
        }else if(flag == Constants.UP){
            characterY -= 10;
            if(character.getY() + characterX < 0){
                flag = Constants.RIGHT;
                characterY += 10;
                character.setBackgroundResource(recourses[flag]);
                ((AnimationDrawable)character.getBackground()).start();
            }
        }
        character.setX(characterX);
        character.setY(characterY);
    }

    void setCharacterX(int characterX) {
        this.characterX = characterX;
    }


    void setCharacterY(int characterY) {
        this.characterY = characterY;
    }
}
