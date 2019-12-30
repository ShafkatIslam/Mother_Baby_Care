package com.example.professt.mother_baby_care.Baby.DoctorsFind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.professt.mother_baby_care.Baby.BabyHomeActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorFindActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorNameActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.HospitalActivity;
import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.R;

public class DoctorsFindActivity extends AppCompatActivity {

    Button findByHospitalButton,findByNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_find);

        findByHospitalButton = (Button)findViewById(R.id.findByHospitalButtons);
        findByNameButton = (Button)findViewById(R.id.findByNameButtons);

        findByHospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorsFindActivity.this,HospitalsActivity.class);
                startActivity(intent);
            }
        });

        findByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorsFindActivity.this,DoctorNamesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(DoctorsFindActivity.this,BabyHomeActivity.class);
        startActivity(intent);

    }
}
