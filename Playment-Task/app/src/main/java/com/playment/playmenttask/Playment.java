package com.playment.playmenttask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Playment extends AppCompatActivity {
    public CanvasView canvasView;
    String coordinates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playment);
        canvasView = findViewById(R.id.canvas_view);


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
