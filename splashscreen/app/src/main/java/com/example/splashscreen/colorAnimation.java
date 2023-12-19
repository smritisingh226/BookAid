package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class colorAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_animation);

        // Find the TextView by its ID
        final TextView colorChangingTextView = findViewById(R.id.colorChangingTextView);

        // Create an ObjectAnimator to animate the backgroundColor property
        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(colorChangingTextView,
                "backgroundColor", Color.RED,Color.GREEN);

        // Set the duration of the animation (in milliseconds)
        colorAnimator.setDuration(3000);

        // Use ArgbEvaluator to smoothly interpolate between colors
        colorAnimator.setEvaluator(new ArgbEvaluator());

        // Set repeat mode to REVERSE to make the animation reverse after finishing
        colorAnimator.setRepeatMode(ObjectAnimator.REVERSE);

        // Set repeat count to INFINITE to make the animation repeat indefinitely
        colorAnimator.setRepeatCount(ObjectAnimator.INFINITE);

        // Start the color animation when the TextView is clicked
        colorChangingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAnimator.start();
            }
        });
    }
}