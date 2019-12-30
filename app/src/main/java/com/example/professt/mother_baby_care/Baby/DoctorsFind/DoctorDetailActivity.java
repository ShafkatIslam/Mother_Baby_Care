package com.example.professt.mother_baby_care.Baby.DoctorsFind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorDetailsActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorName;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorNameActivity;
import com.example.professt.mother_baby_care.R;

public class DoctorDetailActivity extends AppCompatActivity {

    TextView doctorNames,doctorNumber,doctorHospital,doctorDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        doctorNames = (TextView)findViewById(R.id.doctorNames);
        doctorNumber = (TextView)findViewById(R.id.doctorNumbers);
        doctorHospital = (TextView)findViewById(R.id.doctorHospitals);
        doctorDegree = (TextView)findViewById(R.id.doctorDegrees);


        DoctorName doctorName = DoctorName.getInstance();
        String name = doctorName.getId();

        if(name.equalsIgnoreCase("Dr. Rahim"))
        {
            doctorNames.setText("Dr. Rahim");
            doctorNumber.setText("01816256523");
            doctorHospital.setText("Chittagong Medical");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Karim"))
        {
            doctorNames.setText("Dr. Karim");
            doctorNumber.setText("01816256993");
            doctorHospital.setText("Chittagong Medical");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Rahima"))
        {
            doctorNames.setText("Dr. Rahima");
            doctorNumber.setText("01816250123");
            doctorHospital.setText("Chittagong Medical");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Kamal"))
        {
            doctorNames.setText("Dr. Kamal");
            doctorNumber.setText("01816256509");
            doctorHospital.setText("Chittagong Maa O Shishu Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Jamal"))
        {
            doctorNames.setText("Dr. Jamal");
            doctorNumber.setText("01816256913");
            doctorHospital.setText("Chittagong Maa O Shishu Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Asad"))
        {
            doctorNames.setText("Dr. Asad");
            doctorNumber.setText("01812650123");
            doctorHospital.setText("Chittagong Maa O Shishu Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Jamil"))
        {
            doctorNames.setText("Dr. Jamil");
            doctorNumber.setText("01716256509");
            doctorHospital.setText("Metropoliton Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Amir"))
        {
            doctorNames.setText("Dr. Amir");
            doctorNumber.setText("01916256913");
            doctorHospital.setText("Metropoliton Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Salam"))
        {
            doctorNames.setText("Dr. Salam");
            doctorNumber.setText("01716116509");
            doctorHospital.setText("General Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Selina"))
        {
            doctorNames.setText("Dr. Selina");
            doctorNumber.setText("01616256913");
            doctorHospital.setText("General Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Tareq"))
        {
            doctorNames.setText("Dr. Tareq");
            doctorNumber.setText("01710016509");
            doctorHospital.setText("USTC Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Tanim"))
        {
            doctorNames.setText("Dr. Tanim");
            doctorNumber.setText("01678256913");
            doctorHospital.setText("USTC Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Sihab"))
        {
            doctorNames.setText("Dr. Sihab");
            doctorNumber.setText("01711116509");
            doctorHospital.setText("Max Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Minar"))
        {
            doctorNames.setText("Dr. Minar");
            doctorNumber.setText("01678886913");
            doctorHospital.setText("Max Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Rubel"))
        {
            doctorNames.setText("Dr. Rubel");
            doctorNumber.setText("01711116511");
            doctorHospital.setText("Medical Center");
            doctorDegree.setText("MBBS");
        }

        else if(name.equalsIgnoreCase("Dr. Rafat"))
        {
            doctorNames.setText("Dr. Rafat");
            doctorNumber.setText("01678886999");
            doctorHospital.setText("Medical Center");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Maisha"))
        {
            doctorNames.setText("Dr. Maisha");
            doctorNumber.setText("01711155511");
            doctorHospital.setText("National Hospital");
            doctorDegree.setText("MBBS");
        }
        else if(name.equalsIgnoreCase("Dr. Naima"))
        {
            doctorNames.setText("Dr. Naima");
            doctorNumber.setText("01670086999");
            doctorHospital.setText("National Hospital");
            doctorDegree.setText("MBBS");
        }

    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(DoctorDetailActivity.this,DoctorNamesActivity.class);
        startActivity(intent);

    }
}
