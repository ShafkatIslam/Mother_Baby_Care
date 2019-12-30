package com.example.professt.mother_baby_care.Mother.DoctorFind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.R;

public class DoctorFindActivity extends AppCompatActivity {

    Button findByHospitalButton,findByNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_find);

        findByHospitalButton = (Button)findViewById(R.id.findByHospitalButton);
        findByNameButton = (Button)findViewById(R.id.findByNameButton);

        findByHospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorFindActivity.this,HospitalActivity.class);
                startActivity(intent);
            }
        });

        findByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorFindActivity.this,DoctorNameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(DoctorFindActivity.this,MotherHomeActivity.class);
        startActivity(intent);

    }
}
