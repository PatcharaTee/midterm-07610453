package com.example.speedcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button calButt, clearButt;
    EditText meterInput, secondInput;
    TextView tvAvg;
    String disAndtimeRe, timeRe, alertMessage;
    double avgSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calButt = findViewById(R.id.calbutton);
        clearButt = findViewById(R.id.clearbutton);
        meterInput = findViewById(R.id.meterinput);
        secondInput = findViewById(R.id.secondinput);
        tvAvg = findViewById(R.id.result);

        //message
        disAndtimeRe = getResources().getString(R.string.toast1);
        timeRe = getResources().getString(R.string.toast2);
        alertMessage = getResources().getString(R.string.alert1);

        calButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sMeter = meterInput.getText().toString();
                String sSecond = secondInput.getText().toString();
                if (sMeter.length() == 0 || sSecond.length() == 0) {
                    Toast.makeText(MainActivity.this, disAndtimeRe, Toast.LENGTH_SHORT).show();
                } else if (sSecond.equals("0")) {
                    Toast.makeText(MainActivity.this, timeRe, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("SPEED CALCULATOR");
                    double dMeter = Double.parseDouble(sMeter);
                    double dSecond = Double.parseDouble(sSecond);
                    avgSpeed = (dMeter / dSecond) * 3.6;
                    String result = String.format(
                            Locale.getDefault(), "%.2f", avgSpeed
                    );
                    tvAvg.setText(result);
                    if (avgSpeed > 80) {
                        dialog.setMessage(alertMessage);
                        dialog.setPositiveButton("OK", null);
                        dialog.show();
                    }
                }
            }
        });

        clearButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meterInput.setText(null);
                secondInput.setText(null);
                tvAvg.setText(null);
            }
        });
    }
}