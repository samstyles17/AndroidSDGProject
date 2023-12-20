package com.example.sdg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class SplashScreen extends AppCompatActivity {
    private static final long ANIMATION_DURATION = 1000; // Duration of crossfade animation in milliseconds
    private static final long DELAY_DURATION = 2000; //// Duration of splash screen display in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Start the crossfade animation after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                crossfadeToMainActivity();
            }
        }, DELAY_DURATION);
    }

    private void crossfadeToMainActivity() {
        // Create crossfade animation
        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(ANIMATION_DURATION);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start MainActivity after the animation is complete
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish(); // Finish the splash screen activity so it's not shown when back button is pressed
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ConstraintLayout splashLayout = findViewById(R.id.splashLayout);
        splashLayout.startAnimation(fadeIn);
    }
}