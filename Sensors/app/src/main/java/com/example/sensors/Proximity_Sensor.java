package com.example.sensors;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Proximity_Sensor extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor proximitySensor;

    TextView sensorName, sensorDistance;
    RelativeLayout rootlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);
        sensorName = findViewById(R.id.name);
        sensorDistance = findViewById(R.id.distance);
        rootlayout = findViewById(R.id.relativeLayout);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //Toast.makeText(this, "" + proximitySensor.getMaximumRange(), Toast.LENGTH_LONG).show();

        if(proximitySensor == null){
            Log.e(TAG, "Proximity sensor not available");
            finish();
        }else{
            sensorManager.registerListener(this,
                    proximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorName.setText("Sensor Name" + event.sensor.getName());
        sensorDistance.setText("Distance" + String.valueOf(event.values[0]));
        if(event.values[0]< 4){
            rootlayout.setBackgroundColor(Color.BLACK);
        }
        else{
            rootlayout.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected  void onResume(){
        super.onResume();
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);

    }
//    protected void  onDestroy(){
//        super.onDestroy();
//        sensorManager.unregisterListener(this);
//    }
}