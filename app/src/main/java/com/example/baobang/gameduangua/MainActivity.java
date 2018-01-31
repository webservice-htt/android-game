package com.example.baobang.gameduangua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgCharacterFirst = findViewById(R.id.imgCharacterFirst);
        ImageView imgCharacterSecond = findViewById(R.id.imgCharacterSecond);
        ImageView imgCharacterThird = findViewById(R.id.imgCharacterThird);
        ImageView imgCharacterForth = findViewById(R.id.imgCharacterForth);
        Character characterFirst = new Character(
                this,
                imgCharacterFirst,
                new int[]{
                        R.drawable.horse1_right,
                        R.drawable.horse1_down,
                        R.drawable.horse1_left,
                        R.drawable.horse1_up});
        Character characterSecond = new Character(
                this,
                imgCharacterSecond,
                new int[]{
                        R.drawable.horse2_right,
                        R.drawable.horse2_down,
                        R.drawable.horse2_left,
                        R.drawable.horse2_up});
        Character characterThird = new Character(
                this,
                imgCharacterThird,
                new int[]{
                        R.drawable.horse3_right,
                        R.drawable.horse3_down,
                        R.drawable.horse3_left,
                        R.drawable.horse3_up});
        Character characterForth = new Character(
                this,
                imgCharacterForth,
                new int[]{
                        R.drawable.horse4_right,
                        R.drawable.horse4_down,
                        R.drawable.horse4_left,
                        R.drawable.horse4_up});

        characterFirst.setCharacterX(10);
        characterFirst.setCharacterY(10);
        characterFirst.start();

        characterSecond.setCharacterX(10);
        characterSecond.setCharacterY(80);
        characterSecond.start();

        characterThird.setCharacterX(10);
        characterThird.setCharacterY(160);
        characterThird.start();

        characterForth.setCharacterX(10);
        characterForth.setCharacterY(240);
        characterForth.start();

    }


}
