package com.example.jeremylim.mathgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class EasyActivity extends AppCompatActivity {

    private int n1, n2, answer, operation;
    private TextView textViewNum1, textViewNum2;
    private EditText editTextAnswer;
    private ImageView imageViewNext, imageViewOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        textViewNum1 = (TextView) findViewById(R.id.textViewNum1);
        textViewNum2 = (TextView) findViewById(R.id.textViewNum2);
        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        imageViewOperation = (ImageView) findViewById(R.id.imageViewOperation);
        imageViewNext = (ImageView) findViewById(R.id.imageViewNext);
        Button buttonHome = (Button) findViewById(R.id.buttonHome);
        Button buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EasyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        generateQuestion();
        setQuestion();

        final MediaPlayer r = MediaPlayer.create(this, R.raw.r);
        final MediaPlayer w = MediaPlayer.create(this, R.raw.w);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = -1;
                try {
                    n = Integer.parseInt(editTextAnswer.getText().toString());

                    if (n == answer) {
                        r.start();
                        imageViewNext.setVisibility(View.VISIBLE);
                    } else {
                        w.start();
                        imageViewNext.setVisibility(View.INVISIBLE);
                    }
                } catch (NumberFormatException e) {

                }

            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestion();
                setQuestion();
            }
        });

    }

        private void generateQuestion(){
            Random r = new Random();
            n1 = r.nextInt(10) + 1;
            n2 = r.nextInt(10) + 1;
            operation = r.nextInt(2);
            if (operation == 0) {
                answer = n1 + n2;
            } else {
                answer = n1 - n2;
            }
    }

        private void setQuestion(){
            textViewNum1.setText("" + n1);
            textViewNum2.setText("" + n2);
            editTextAnswer.setText("");
            if (operation == 0){
                imageViewOperation.setImageResource(R.drawable.plus);
            } else {
               imageViewOperation.setImageResource(R.drawable.m3);
            }
            imageViewNext.setVisibility(View.INVISIBLE);
        }


}
