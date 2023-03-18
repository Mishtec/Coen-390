package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView, disclaimer;
    Animation discAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);
        disclaimer = findViewById(R.id.Disclaimer);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();

        discAnim = AnimationUtils.loadAnimation(this,R.anim.disclaimer_anim);
        disclaimer.setAnimation(discAnim);

    }

    public void progressAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, textView, 0f, 100f);
        anim.setDuration(20000);
        progressBar.setAnimation(anim);
    }


}