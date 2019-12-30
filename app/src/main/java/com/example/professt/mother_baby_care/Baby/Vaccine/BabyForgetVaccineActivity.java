package com.example.professt.mother_baby_care.Baby.Vaccine;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.professt.mother_baby_care.Baby.BabyHomeActivity;
import com.example.professt.mother_baby_care.Key;
import com.example.professt.mother_baby_care.Mother.Mother;
import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.Mother.Vaccine.ForgetVaccineActivity;
import com.example.professt.mother_baby_care.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BabyForgetVaccineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Button VaccineSelectDateButtonId1,vaccineDone2ButtonId1;
    TextView vaccineDateTextViewId1,VaccineNoId1;

    String vacchinesNumber;

    Spinner vacchineNumber1;

    String[] arrrrrname1 = {"Select","1","2","3","4","5","6","7","8"};

    DatePickerDialog datePickerDialog;

    private ProgressDialog loading;

    String parsedDate;

    String getCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_forget_vaccine);

        VaccineSelectDateButtonId1 = (Button)findViewById(R.id.babyVaccineSelectDateButtonId1);
        vaccineDone2ButtonId1 = (Button)findViewById(R.id.babyvaccineDone2ButtonId1);

        vaccineDateTextViewId1 = (TextView)findViewById(R.id.babyvaccineDateTextViewId1);
        VaccineNoId1 = (TextView)findViewById(R.id.babyVaccineNoId1);

        vacchineNumber1 = (Spinner) findViewById(R.id.babyvacchineNumber1);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arrrrrname1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vacchineNumber1.setAdapter(arrayAdapter);
        vacchineNumber1.setOnItemSelectedListener(this);

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");


        VaccineSelectDateButtonId1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = new DatePicker(BabyForgetVaccineActivity.this);
                int currentday= datePicker.getDayOfMonth();
                int currentMonth = (datePicker.getMonth());
                int currentYear = datePicker.getYear();
                datePickerDialog = new DatePickerDialog(BabyForgetVaccineActivity.this,

                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                String result = (dayOfMonth+"/"+(month+1)+"/"+year);
                                vaccineDateTextViewId1.setText(result);
//                                EventClass eventClass = EventClass.getInstance();
//                                eventClass.setDate(result);

                            }
                        },currentYear,currentMonth,currentday);

                datePickerDialog.show();
            }
        });

        vaccineDone2ButtonId1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String date1,ids,tableName;
                date1 = vaccineDateTextViewId1.getText().toString();
                ids = VaccineNoId1.getText().toString();

                if(ids.equals("Select"))
                {
                    Toast.makeText(BabyForgetVaccineActivity.this, "Please Select Vacchine No", Toast.LENGTH_SHORT).show();
                }

                else if(date1.isEmpty())
                {
                    Toast.makeText(BabyForgetVaccineActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    try {
                        Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                        parsedDate = formatter.format(initDate);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Mother mother = Mother.getInstance();
                    String name3 = mother.getDoctor();
                    String name6 = mother.getReminder();
                    String name1 = mother.getName();

                    loading = new ProgressDialog(BabyForgetVaccineActivity.this);
                    loading.setIcon(R.drawable.load);
                    loading.setTitle("Doctor Schedule");
                    loading.setMessage("Please wait...");
                    loading.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.BABY_VACCINATION_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //for track response in Logcat
                            Log.d("RESPONSE", "" + response);

                            //if we are getting success from server
                            if (response.equals("success")) {
                                //creating a shared preference
                                loading.dismiss();
                                //starting profile activity
                                Intent intent = new Intent(BabyForgetVaccineActivity.this, BabyHomeActivity.class);
                                startActivity(intent);

                                Toast.makeText(BabyForgetVaccineActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                            } else if (response.equals("exists")) {
                                Toast.makeText(BabyForgetVaccineActivity.this, "Vaccine already exists", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            } else if (response.equals("failure")) {
                                Toast.makeText(BabyForgetVaccineActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(BabyForgetVaccineActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
                                    loading.dismiss();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            //return super.getParams();

                            Map<String,String> params = new HashMap<>();

                            //Ading parameters to request
                            params.put(Key.KEY_MUCELL,getCell);
                            params.put(Key.KEY_MPDATE,parsedDate);
                            params.put(Key.KEY_MNDATE,parsedDate);
                            params.put(Key.KEY_NUMBER, ids);
                            params.put(Key.KEY_NUMBERS, String.valueOf(3));

                            Log.d("info",""+getCell+" "+parsedDate);
                            //returning parameter
                            return params;

                        }
                    };

                    //Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(BabyForgetVaccineActivity.this);
                    requestQueue.add(stringRequest);


                }


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        vacchinesNumber = vacchineNumber1.getSelectedItem().toString();
        VaccineNoId1.setText(vacchinesNumber);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(BabyForgetVaccineActivity.this,BabyHomeActivity.class);
        startActivity(intent);

    }
}
