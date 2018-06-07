package com.playment.playmenttask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class Playment extends AppCompatActivity {
    public CanvasView canvasView;
    String coordinates;
    SeekBar seekBar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playment);
        canvasView = findViewById(R.id.canvas_view);
        seekBar=findViewById(R.id.seekBar);
        frameLayout= findViewById(R.id.frame_layout);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = progress/110f  + 1;
                frameLayout.setScaleX(scale);
                frameLayout.setScaleY(scale);

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void calculateCoordinates(View v){


        coordinates = InitialCoordinatesModel.getCoordinates1();
        if (coordinates!=null) {
            Log.e("12", coordinates);

            Toast.makeText(Playment.this, coordinates, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Could not calculate coordinates",Toast.LENGTH_SHORT).show();
        }

    }
}
