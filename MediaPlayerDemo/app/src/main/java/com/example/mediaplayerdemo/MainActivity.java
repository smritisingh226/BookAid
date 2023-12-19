package com.example.mediaplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button b1, b2, b3, b4;
    private ImageView iv;
    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();
    final private int forwardTime = 5000;
    final private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1;
    private TextView tx2;

    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.forward);
        b2 = findViewById(R.id.pause);
        b3 = findViewById(R.id.play);
        b4 = findViewById(R.id.backward);
        iv = findViewById(R.id.imageView);
        tx1 = findViewById(R.id.textView2);
        tx2 = findViewById(R.id.textView3);
        TextView tx3 = findViewById(R.id.textView4);
        tx3.setText(R.string.song_filename);
        seekbar = findViewById(R.id.seekBar);


        mediaPlayer = MediaPlayer.create(this, R.raw.song);

        seekbar.setClickable(false);
        b2.setEnabled(false);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing Sound", Toast.LENGTH_LONG).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }
                long minutes = TimeUnit.MILLISECONDS.toMinutes((long) (finalTime));
                long seconds = TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(minutes);
                tx2.setText(String.format(Locale.ENGLISH, "%d min, %d sec", minutes, seconds));


                long minutes1 = TimeUnit.MILLISECONDS.toMinutes((long) (startTime));
                long seconds1 = TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(minutes);

                String formattedTime = String.format(Locale.ENGLISH, "%d min, %d sec", minutes1, seconds1);
                tx1.setText(formattedTime);

                //Sets the initial progress of the seekbar to the current playback position (startTime),
                // casting it to an integer
                seekbar.setProgress((int) startTime);
                //Initiates the UpdateSongTime runnable to periodically update the
                // seekbar and time labels every 100 milliseconds.
                myHandler.postDelayed(UpdateSongTime, 100);
                //Enables the pause button
                b2.setEnabled(true);
                //Disables the play button
                b3.setEnabled(false);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pause button clicked
                Toast.makeText(getApplicationContext(), "Pausing sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();

                //pause button disabled
                b2.setEnabled(false);
                //play button enabled
                b3.setEnabled(true);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Forward button clicked
                int temp = (int) startTime;
                if ((temp + forwardTime) <= finalTime) {
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Backward button clicked
                int temp = (int) startTime;
                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format(Locale.ENGLISH, "%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime))));

            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}