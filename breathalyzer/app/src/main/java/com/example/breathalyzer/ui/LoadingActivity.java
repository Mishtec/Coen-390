package com.example.breathalyzer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breathalyzer.MainActivity;
import com.example.breathalyzer.R;
import com.example.breathalyzer.ResultsActivity;
import com.example.breathalyzer.data.DatabaseHandler;
import com.example.breathalyzer.model.History;
import com.example.breathalyzer.ui.ProgressBarAnimation;


public class LoadingActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView, disclaimer;
    Animation discAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading_acitiviy);
        setTitle("Loading...");

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
        anim.setDuration(10000);
        progressBar.setAnimation(anim);
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(), "Can not Cancel", Toast.LENGTH_LONG).show();
    }

}