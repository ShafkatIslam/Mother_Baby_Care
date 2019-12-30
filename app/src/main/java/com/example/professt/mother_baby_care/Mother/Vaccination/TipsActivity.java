package com.example.professt.mother_baby_care.Mother.Vaccination;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.R;

public class TipsActivity extends AppCompatActivity {

    Button routineofFood,routineofVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        routineofFood = (Button)findViewById(R.id.routineofFood);
        routineofVaccine = (Button)findViewById(R.id.routineofVaccine);

        routineofVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(TipsActivity.this,MotherVacchineScheduleActivity.class);
                startActivity(intent);
            }
        });

        routineofFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(TipsActivity.this,TipShowActivity.class);
                startActivity(intent);
            }
        });
    }
}
