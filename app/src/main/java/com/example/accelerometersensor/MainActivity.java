package com.example.accelerometersensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null){
            Sensor accleroSensor =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Sensor proxy=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            Sensor light=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if(accleroSensor!=null){
                sensorManager.registerListener(this, accleroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
            if (proxy!=null){
                sensorManager.registerListener(this,proxy,SensorManager.SENSOR_DELAY_NORMAL);
            }
            if (light!=null){
                sensorManager.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }else{
            Toast.makeText(this, "Sensor service not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ((TextView)findViewById(R.id.senserText)).setText("Values for Accelerometer\nAxis X: "+ sensorEvent.values[0]+"\nAxis Y: "+sensorEvent.values[1]+"\nAxis Z: "+sensorEvent.values[2]);

        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
            ((TextView)findViewById(R.id.senserTextTwo)).setText("Value for Proximity Sensor: "+sensorEvent.values[0]);
            if(sensorEvent.values[0]>0){
                Toast.makeText(this, "Object is Far", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Object is Near", Toast.LENGTH_SHORT).show();
            }
        }
        if (sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
            ((TextView)findViewById(R.id.senserTextThree)).setText("Value for Light Sensor: "+sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}