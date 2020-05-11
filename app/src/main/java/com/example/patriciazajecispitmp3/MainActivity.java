package com.example.patriciazajecispitmp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Listener {
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    @BindView(R.id.slider)
    HorizontalSlider Slider;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        Slider.setOnProgressChangeListener(this);
    }

    @OnClick(R.id.btn_main_play_stop)
    public void btnPlayStop(Button button) {
        animate(button);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            button.setText(R.string.play);
        } else {
            mediaPlayer.start();
            button.setText(R.string.pause);
        }
    }

    private void animate(Button button) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        button.startAnimation(animation);
    }


    @OnClick(R.id.imageView)
    public void Picture(ImageView imageView){
        animate2(imageView);
    }

    private void animate2(ImageView ImageView) {
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.animation);
        ImageView.startAnimation(animation2);
    }

    private void exitApp() {
        new Exit().show(getSupportFragmentManager(), "Exit");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_exit) {
            exitApp();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> MainActivity.super.onBackPressed())

                .setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }


    @Override
    public void onProgressChanged(View v, float progress) {
        if (mediaPlayer != null) {
            float volumePercentage = progress / 100;
            mediaPlayer.setVolume(volumePercentage, volumePercentage);

        }
    }
}
