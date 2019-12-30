package com.example.professt.mother_baby_care.Mother.Vaccine;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.professt.mother_baby_care.Key;
import com.example.professt.mother_baby_care.Mother.DoctorMeet;
import com.example.professt.mother_baby_care.Mother.MeetingDoctorActivity;
import com.example.professt.mother_baby_care.Mother.Mother;
import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VacchineScheduleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button VaccineSelectDateButtonId,VaccineSelectDateButton2Id,vaccineDone2ButtonId,okButtonId,ok2ButtonId,VaccineDoneButtonId;
    TextView currentVaccineId,vaccineDateTextViewId,VaccineNoId,vaccinenoId,VaccineNotextId,nextVaccineId,vaccineDateText2ViewId,previousVaccineId,vaccineDateText3ViewId,vaccineId,takenId;
    private CheckBox yesCheckBox,noCheckBox;
    private RadioButton genderButton;
    private RadioGroup radioGrroup;
    EditText vacchineNumber1;
    Spinner vacchineNumber;
    DatePickerDialog datePickerDialog;

    public int i;

    int count1,count2,count5,count6;
    String count3,count4,count7;


    String parsedDate;
    String parsedDate1;
    String vacchinesNumber;

    String getCell;

    private ProgressDialog loading;

    String[] arrrrrname1 = {"Select","1","2","3","4","5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacchine_schedule);

        VaccineSelectDateButtonId = (Button)findViewById(R.id.VaccineSelectDateButtonId);
        VaccineSelectDateButton2Id = (Button)findViewById(R.id.VaccineSelectDateButton2Id);
        vaccineDone2ButtonId = (Button)findViewById(R.id.vaccineDone2ButtonId);
        okButtonId = (Button)findViewById(R.id.okButtonId);
        ok2ButtonId = (Button)findViewById(R.id.ok2ButtonId);
        VaccineDoneButtonId = (Button)findViewById(R.id.VaccineDoneButtonId);

        vaccineDateTextViewId = (TextView)findViewById(R.id.vaccineDateTextViewId);
        VaccineNoId = (TextView)findViewById(R.id.VaccineNoId);
        VaccineNotextId = (TextView)findViewById(R.id.VaccineNotextId);
        vaccinenoId = (TextView)findViewById(R.id.vaccinenoId);
        nextVaccineId = (TextView)findViewById(R.id.nextVaccineId);
        vaccineDateText2ViewId = (TextView)findViewById(R.id.vaccineDateText2ViewId);
        previousVaccineId = (TextView)findViewById(R.id.previousVaccineId);
        vaccineDateText3ViewId = (TextView)findViewById(R.id.vaccineDateText3ViewId);
        vaccineId = (TextView)findViewById(R.id.vaccineId);
        takenId = (TextView)findViewById(R.id.takenId);
        currentVaccineId = (TextView)findViewById(R.id.currentVaccineId);
        vacchineNumber = (Spinner) findViewById(R.id.vacchineNumber);
        radioGrroup = (RadioGroup)findViewById(R.id.radioGroupId);

//        yesCheckBox = (CheckBox)findViewById(R.id.yesCheckBoxId);
//        noCheckBox = (CheckBox)findViewById(R.id.noCheckBoxId);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arrrrrname1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vacchineNumber.setAdapter(arrayAdapter);
        vacchineNumber.setOnItemSelectedListener(this);

        i=1;

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");

        //for showing progress dialog
        loading = new ProgressDialog(VacchineScheduleActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Key.VACCINATION_CHECK+getCell;

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Intent intent = new Intent(VacchineScheduleActivity.this,MotherHomeActivity.class);
                        startActivity(intent);
                        loading.dismiss();
                        Toast.makeText(VacchineScheduleActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(VacchineScheduleActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {

        //Create json object for receiving jason data
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Key.JSON_ARRAY);

            if (result.length()==0)
            {
                vaccineDone2ButtonId.setVisibility(View.GONE);
                VaccineSelectDateButton2Id.setVisibility(View.GONE);
                VaccineNoId.setVisibility(View.GONE);
                vacchineNumber.setVisibility(View.GONE);
                vaccinenoId.setVisibility(View.GONE);
                VaccineNotextId.setVisibility(View.GONE);
                nextVaccineId.setVisibility(View.GONE);
                vaccineDateText2ViewId.setVisibility(View.GONE);
                previousVaccineId.setVisibility(View.GONE);
                vaccineDateText3ViewId.setVisibility(View.GONE);
                vaccineId.setVisibility(View.GONE);
                takenId.setVisibility(View.GONE);
                okButtonId.setVisibility(View.GONE);
                ok2ButtonId.setVisibility(View.GONE);
                radioGrroup.setVisibility(View.GONE);
                VaccineDoneButtonId.setVisibility(View.GONE);
//                yesCheckBox.setVisibility(View.GONE);
//                noCheckBox.setVisibility(View.GONE);

                VaccineSelectDateButtonId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePicker datePicker = new DatePicker(VacchineScheduleActivity.this);
                        int currentday= datePicker.getDayOfMonth();
                        int currentMonth = (datePicker.getMonth());
                        int currentYear = datePicker.getYear();
                        datePickerDialog = new DatePickerDialog(VacchineScheduleActivity.this,

                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                        String result = (dayOfMonth+"/"+(month+1)+"/"+year);

                                        int day,month1,month2,months,years,total,total2,total3,total4;

                                        total = (year*12);
                                        month2 = month+1;
                                        total2 = total+(month+1);
                                        total3 = total2*30;

                                        if(month2==1 || month2==3 || month2==5 || month2==7 || month2==8 || month2==10 || month2==12)
                                        {
                                            total4 = total3+dayOfMonth+27;
                                        }
                                        else
                                        {
                                            total4 = total3+dayOfMonth+28;
                                        }


                                        day = total4%30;
                                        month1 = total4/30;
                                        months = month1%12;
                                        years = month1/12;


//                                        String count = String.valueOf(i);
//                                        int j = i+1;
//                                        count7 = String.valueOf(j);
//
//                                        Mother mother1 = Mother.getInstance();
//                                        mother1.setId1(i);
//                                        mother1.setId2(j);

                                        String result2 = (day+"/"+months+"/"+years);

                                        vaccineDateTextViewId.setText(result);
                                        vaccineDone2ButtonId.setVisibility(View.VISIBLE);
                                        VaccineNoId.setVisibility(View.VISIBLE);
                                        vacchineNumber.setVisibility(View.VISIBLE);
                                        VaccineNotextId.setVisibility(View.VISIBLE);
                                        nextVaccineId.setVisibility(View.VISIBLE);
                                        vaccineDateText2ViewId.setVisibility(View.VISIBLE);

                                        vaccineDateText2ViewId.setText(result);
                                        //VaccineNoId.setText(count);

                                    }
                                },currentYear,currentMonth,currentday);

                        datePickerDialog.show();
                    }
                });

                okButtonId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String date1,date2;
                        date1 = vaccineDateTextViewId.getText().toString();
                        date2 = String.valueOf(0);

                        if(date1.isEmpty())
                        {
                            Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                        }

                        else
                        {

                            try {
                                Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                parsedDate = formatter.format(initDate);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Mother mother = Mother.getInstance();
                            String name4 = mother.getName();
                            String name5 = mother.getReminder();
                            String tableName = mother.getVaccine();
                            final int id7 = 6;
                            final int id8 = 2;

                            loading = new ProgressDialog(VacchineScheduleActivity.this);
                            loading.setIcon(R.drawable.load);
                            loading.setTitle("Vaccine Schedule");
                            loading.setMessage("Please wait...");
                            loading.show();

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINATION_URL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    //for track response in Logcat
                                    Log.d("RESPONSE", "" + response);

                                    //if we are getting success from server
                                    if (response.equals("success")) {
                                        //creating a shared preference
                                        loading.dismiss();
                                        //starting profile activity
                                        Intent intent = new Intent(VacchineScheduleActivity.this, MotherHomeActivity.class);
                                        startActivity(intent);

                                        Toast.makeText(VacchineScheduleActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                    } else if (response.equals("exists")) {
                                        Toast.makeText(VacchineScheduleActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                        loading.dismiss();
                                    } else if (response.equals("failure")) {
                                        Toast.makeText(VacchineScheduleActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                        loading.dismiss();
                                    }
                                }
                            },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(VacchineScheduleActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                    params.put(Key.KEY_MNDATE,date1);
                                    params.put(Key.KEY_NUMBER, String.valueOf(id7));
                                    params.put(Key.KEY_NUMBERS, String.valueOf(id8));

                                    Log.d("info",""+getCell+" "+parsedDate);
                                    //returning parameter
                                    return params;

                                }
                            };

                            //Adding the string request to the queue
                            RequestQueue requestQueue = Volley.newRequestQueue(VacchineScheduleActivity.this);
                            requestQueue.add(stringRequest);
//
                        }

                        Intent intent = new Intent(VacchineScheduleActivity.this,MotherHomeActivity.class);
                        startActivity(intent);
                    }
                });

                vaccineDone2ButtonId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String date1,date2,ids;

                        date1 = vaccineDateTextViewId.getText().toString();
                        date2 = vaccineDateText2ViewId.getText().toString();
                        ids = VaccineNoId.getText().toString();

                        Log.d("id", "id: "+ids);

                        if(ids.equals("Select"))
                        {
                            Toast.makeText(VacchineScheduleActivity.this, "Please Select Vacchine No", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String b = VaccineNoId.getText().toString();

                            //String count = String.valueOf(i);
                            final int a = Integer.valueOf(b);
                            int j = a+1;

                            Log.d("a", "a: "+a);
                            Log.d("j", "j: "+j);

                            if(date1.isEmpty())
                            {
                                Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                            }

                            else if(date2.isEmpty())
                            {
                                Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                            }

                            else
                            {

                                try {
                                    Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                                    Date initDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                                    parsedDate = formatter.format(initDate);
                                    parsedDate1 = formatter1.format(initDate1);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Mother mother = Mother.getInstance();
                                String name3 = mother.getDoctor();
                                String name6 = mother.getReminder();
                                String name1 = mother.getName();
                                final int id5 = a;
                                int id6 = j;

                                loading = new ProgressDialog(VacchineScheduleActivity.this);
                                loading.setIcon(R.drawable.load);
                                loading.setTitle("Doctor Schedule");
                                loading.setMessage("Please wait...");
                                loading.show();

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINATION_URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        //for track response in Logcat
                                        Log.d("RESPONSE", "" + response);

                                        //if we are getting success from server
                                        if (response.equals("success")) {
                                            //creating a shared preference
                                            loading.dismiss();
                                            //starting profile activity
                                            Intent intent = new Intent(VacchineScheduleActivity.this, MotherHomeActivity.class);
                                            startActivity(intent);

                                            Toast.makeText(VacchineScheduleActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                        } else if (response.equals("exists")) {
                                            Toast.makeText(VacchineScheduleActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                            loading.dismiss();
                                        } else if (response.equals("failure")) {
                                            Toast.makeText(VacchineScheduleActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                            loading.dismiss();
                                        }
                                    }
                                },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(VacchineScheduleActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                        params.put(Key.KEY_MNDATE,parsedDate1);
                                        params.put(Key.KEY_NUMBER, String.valueOf(id5));
                                        params.put(Key.KEY_NUMBERS, String.valueOf(1));

                                        Log.d("info",""+getCell+" "+parsedDate);
                                        //returning parameter
                                        return params;

                                    }
                                };

                                //Adding the string request to the queue
                                RequestQueue requestQueue = Volley.newRequestQueue(VacchineScheduleActivity.this);
                                requestQueue.add(stringRequest);


                            }
                        }
                    }
                });


            }
            else
            {
                vacchineNumber.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String cell = jo.getString(Key.KEY_MUCELL);
                    String ppdate = jo.getString(Key.KEY_MPDATE);
                    String ndate = jo.getString(Key.KEY_MNDATE);
                    String number = jo.getString(Key.KEY_NUMBER);

                    int cou;
                    String date1,pdate;

                    cou = Integer.parseInt(number);
                    pdate = ppdate;
                    date1 = ndate;

                    Vaccine vaccine = Vaccine.getInstance();
                    vaccine.setId(cou);
                    vaccine.setDate(date1);
                    vaccine.setDate1(pdate);

                    Log.d("Numbers", "Number: "+number);

                    if(number.equals("6") && ndate.equalsIgnoreCase("0000-00-00"))
                    {
                        Log.d("Hello", "Hello: "+number);
                        currentVaccineId.setVisibility(View.VISIBLE);
                        ok2ButtonId.setVisibility(View.VISIBLE);
                        vaccineDone2ButtonId.setVisibility(View.GONE);
                        VaccineSelectDateButton2Id.setVisibility(View.GONE);
                        VaccineNoId.setVisibility(View.GONE);
                        VaccineNotextId.setVisibility(View.GONE);
                        nextVaccineId.setVisibility(View.GONE);
                        vaccineDateText2ViewId.setVisibility(View.GONE);
                        previousVaccineId.setVisibility(View.GONE);
                        vaccineDateText3ViewId.setVisibility(View.GONE);
                        vaccineId.setVisibility(View.GONE);
                        takenId.setVisibility(View.GONE);
                        okButtonId.setVisibility(View.GONE);
                        VaccineSelectDateButtonId.setVisibility(View.GONE);
                        radioGrroup.setVisibility(View.GONE);
                        vaccinenoId.setVisibility(View.GONE);
                        VaccineDoneButtonId.setVisibility(View.GONE);

                        currentVaccineId.setText("All vacchine has been taken");
                        currentVaccineId.setGravity(Gravity.CENTER);
                        currentVaccineId.setTextSize(30);
                        currentVaccineId.setTextColor(Color.parseColor("#bdbdbd"));
                        ok2ButtonId.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(VacchineScheduleActivity.this,MotherHomeActivity.class);
                                startActivity(intent);

                            }
                        });
                    }

                    else
                    {
                        currentVaccineId.setVisibility(View.GONE);
                        VaccineSelectDateButtonId.setVisibility(View.GONE);
                        vaccineDateTextViewId.setVisibility(View.GONE);
                        vaccineDone2ButtonId.setVisibility(View.GONE);
                        VaccineSelectDateButton2Id.setVisibility(View.GONE);
                        VaccineNoId.setVisibility(View.GONE);
                        vacchineNumber.setVisibility(View.GONE);
                        VaccineNotextId.setVisibility(View.GONE);
                        nextVaccineId.setVisibility(View.GONE);
                        vaccineDateText2ViewId.setVisibility(View.GONE);
                        previousVaccineId.setVisibility(View.GONE);
                        vaccineDateText3ViewId.setVisibility(View.GONE);
                        VaccineSelectDateButtonId.setVisibility(View.GONE);
                        vaccineId.setVisibility(View.VISIBLE);
                        takenId.setVisibility(View.VISIBLE);
                        okButtonId.setVisibility(View.GONE);
                        ok2ButtonId.setVisibility(View.GONE);
                        radioGrroup.setVisibility(View.VISIBLE);
                        vaccinenoId.setVisibility(View.VISIBLE);
                        VaccineDoneButtonId.setVisibility(View.VISIBLE);
//                        yesCheckBox.setVisibility(View.GONE);
//                        noCheckBox.setVisibility(View.GONE);

                        Vaccine vaccines = Vaccine.getInstance();
                        count1 = vaccines.getId();
                        String countt = String.valueOf(count1);
                        vaccinenoId.setText(countt);

                        try {

                            VaccineDoneButtonId.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if(radioGrroup.getCheckedRadioButtonId()!=-1)
                                    {
                                        int selectedId = radioGrroup.getCheckedRadioButtonId();
                                        genderButton = (RadioButton) findViewById(selectedId);
                                        String value = genderButton.getText().toString();
                                        if(value.equalsIgnoreCase("Yes"))
                                        {
                                            VaccineSelectDateButton2Id.setVisibility(View.GONE);
                                            currentVaccineId.setVisibility(View.GONE);
                                            VaccineSelectDateButtonId.setVisibility(View.GONE);
                                            vaccineDateTextViewId.setVisibility(View.GONE);

                                            String dates,datess;

                                            Vaccine vaccine = Vaccine.getInstance();
                                            count1 = vaccine.getId();
                                            dates = vaccine.getDate();
                                            datess = vaccine.getDate1();

                                            String[] parts = dates.split("-");
                                            String yearss = parts[0]; // 004
                                            String monthss = parts[1]; // 034556
                                            String dayss = parts[2]; // 034556

                                            int y,m,d,day,month1,month2,months,years,total,total2,total3,total4;

                                            y = Integer.parseInt(yearss);
                                            m = Integer.parseInt(monthss);
                                            d = Integer.parseInt(dayss);

                                            total = (y*12);
                                            total2 = total+m;
                                            total3 = total2*30;
                                            total4 = total3+d;

                                            if(count1==1)
                                            {

                                                if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12)
                                                {
                                                    total4 = total3+d+27;
                                                }
                                                else
                                                {
                                                    total4 = total3+d+28;
                                                }

                                                day = total4%30;
                                                month1 = total4/30;
                                                months = month1%12;
                                                years = month1/12;

                                                if(day==0 && months==0)
                                                {
                                                    day = day+1;
                                                    months = months+1;
                                                }

                                                if(day==0)
                                                {
                                                    day = day+1;
                                                }

                                                if(months==0)
                                                {
                                                    months = months+1;
                                                }

                                                int c = count1+1;
                                                String count = String.valueOf(c);
                                                String countt = String.valueOf(count1);
                                                int j = count1+2;
                                                count7 = String.valueOf(j);

                                                Mother mother1 = Mother.getInstance();
                                                mother1.setId1(c);
                                                mother1.setId2(j);

                                                String result2 = (day+"/"+months+"/"+years);
                                                String result3 = (d+"/"+m+"/"+y);

                                                //currentVaccineId.setText("Current Vaccine date: ");
                                                //vaccineDateTextViewId.setText(result);
                                                vaccineDone2ButtonId.setVisibility(View.VISIBLE);
                                                VaccineNoId.setVisibility(View.VISIBLE);
                                                VaccineNotextId.setVisibility(View.VISIBLE);
                                                nextVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText2ViewId.setVisibility(View.VISIBLE);
                                                previousVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                //VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                //vaccineId.setVisibility(View.VISIBLE);
                                                //takenId.setVisibility(View.VISIBLE);
                                                //radioGrroup.setVisibility(View.VISIBLE);
                                                //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                vaccineDateText2ViewId.setText(result2);
                                                vaccineDateText3ViewId.setText(result3);
                                                VaccineNoId.setText(count);
                                            }
                                            else if(count1==2)
                                            {

                                                if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12)
                                                {
                                                    total4 = total3+d+180;
                                                }
                                                else
                                                {
                                                    total4 = total3+d+181;
                                                }

                                                day = total4%30;
                                                month1 = total4/30;
                                                months = month1%12;
                                                years = month1/12;

                                                if(day==0 && months==0)
                                                {
                                                    day = day+1;
                                                    months = months+1;
                                                }

                                                if(day==0)
                                                {
                                                    day = day+1;
                                                }

                                                if(months==0)
                                                {
                                                    months = months+1;
                                                }

                                                int c = count1+1;
                                                String count = String.valueOf(c);
                                                String countt = String.valueOf(count1);
                                                int j = count1+2;
                                                count7 = String.valueOf(j);

                                                Mother mother1 = Mother.getInstance();
                                                mother1.setId1(c);
                                                mother1.setId2(j);

                                                String result2 = (day+"/"+months+"/"+years);
                                                String result3 = (d+"/"+m+"/"+y);

                                                //currentVaccineId.setText("Current Vaccine date: ");
                                                //vaccineDateTextViewId.setText(result);
                                                vaccineDone2ButtonId.setVisibility(View.VISIBLE);
                                                VaccineNoId.setVisibility(View.VISIBLE);
                                                VaccineNotextId.setVisibility(View.VISIBLE);
                                                nextVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText2ViewId.setVisibility(View.VISIBLE);
                                                previousVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                //VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                //vaccineId.setVisibility(View.VISIBLE);
                                                //takenId.setVisibility(View.VISIBLE);
                                                //radioGrroup.setVisibility(View.VISIBLE);
                                                //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                vaccineDateText2ViewId.setText(result2);
                                                vaccineDateText3ViewId.setText(result3);
                                                VaccineNoId.setText(count);
                                                //vaccineId.setText("Vaccine No "+countt+":");
                                            }
                                            else if(count1==3)
                                            {

                                                if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12)
                                                {
                                                    total4 = total3+d+359;
                                                }
                                                else
                                                {
                                                    total4 = total3+d+360;
                                                }

                                                day = total4%30;
                                                month1 = total4/30;
                                                months = month1%12;
                                                years = month1/12;

                                                if(day==0 && months==0)
                                                {
                                                    day = day+1;
                                                    months = months+1;
                                                }

                                                if(day==0)
                                                {
                                                    day = day+1;
                                                }

                                                if(months==0)
                                                {
                                                    months = months+1;
                                                }

                                                int c = count1+1;
                                                String count = String.valueOf(c);
                                                String countt = String.valueOf(count1);
                                                int j = count1+2;
                                                count7 = String.valueOf(j);

                                                Mother mother1 = Mother.getInstance();
                                                mother1.setId1(c);
                                                mother1.setId2(j);

                                                String result2 = (day+"/"+months+"/"+years);
                                                String result3 = (d+"/"+m+"/"+y);

                                                //currentVaccineId.setText("Current Vaccine date: ");
                                                //vaccineDateTextViewId.setText(result);
                                                vaccineDone2ButtonId.setVisibility(View.VISIBLE);
                                                VaccineNoId.setVisibility(View.VISIBLE);
                                                VaccineNotextId.setVisibility(View.VISIBLE);
                                                nextVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText2ViewId.setVisibility(View.VISIBLE);
                                                previousVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                //VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                //vaccineId.setVisibility(View.VISIBLE);
                                                //takenId.setVisibility(View.VISIBLE);
                                                //radioGrroup.setVisibility(View.VISIBLE);
                                                //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                vaccineDateText2ViewId.setText(result2);
                                                vaccineDateText3ViewId.setText(result3);
                                                VaccineNoId.setText(count);
                                                //vaccineId.setText("Vaccine No "+countt+":");
                                            }
                                            else if(count1==4)
                                            {

                                                if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12)
                                                {
                                                    total4 = total3+d+359;
                                                }
                                                else
                                                {
                                                    total4 = total3+d+360;
                                                }

                                                day = total4%30;
                                                month1 = total4/30;
                                                months = month1%12;
                                                years = month1/12;

                                                if(day==0 && months==0)
                                                {
                                                    day = day+1;
                                                    months = months+1;
                                                }

                                                if(day==0)
                                                {
                                                    day = day+1;
                                                }

                                                if(months==0)
                                                {
                                                    months = months+1;
                                                }

                                                int c = count1+1;
                                                String count = String.valueOf(c);
                                                String countt = String.valueOf(count1);
                                                int j = count1+2;
                                                count7 = String.valueOf(j);

                                                Mother mother1 = Mother.getInstance();
                                                mother1.setId1(c);
                                                mother1.setId2(j);

                                                String result2 = (day+"/"+months+"/"+years);
                                                String result3 = (d+"/"+m+"/"+y);

                                                //currentVaccineId.setText("Current Vaccine date: ");
                                                //vaccineDateTextViewId.setText(result);
                                                vaccineDone2ButtonId.setVisibility(View.VISIBLE);
                                                VaccineNoId.setVisibility(View.VISIBLE);
                                                VaccineNotextId.setVisibility(View.VISIBLE);
                                                nextVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText2ViewId.setVisibility(View.VISIBLE);
                                                previousVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                //VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                //vaccineId.setVisibility(View.VISIBLE);
                                                //takenId.setVisibility(View.VISIBLE);
                                                //radioGrroup.setVisibility(View.VISIBLE);
                                                //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                vaccineDateText2ViewId.setText(result2);
                                                vaccineDateText3ViewId.setText(result3);
                                                VaccineNoId.setText(count);
                                                //vaccineId.setText("Vaccine No "+countt+":");
                                            }
                                            else if(count1==5)
                                            {

                                                if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12)
                                                {
                                                    total4 = total3+d+359;
                                                }
                                                else
                                                {
                                                    total4 = total3+d+360;
                                                }

                                                day = total4%30;
                                                month1 = total4/30;
                                                months = month1%12;
                                                years = month1/12;

                                                if(day==0 && months==0)
                                                {
                                                    day = day+1;
                                                    months = months+1;
                                                }

                                                if(day==0)
                                                {
                                                    day = day+1;
                                                }

                                                if(months==0)
                                                {
                                                    months = months+1;
                                                }

                                                int c = count1+1;
                                                String count = String.valueOf(c);
                                                String countt = String.valueOf(count1);
                                                int j = count1+2;
                                                count7 = String.valueOf(j);

                                                Mother mother1 = Mother.getInstance();
                                                mother1.setId1(c);
                                                mother1.setId2(j);

                                                String result2 = (day+"/"+months+"/"+years);
                                                String result3 = (d+"/"+m+"/"+y);

                                                //currentVaccineId.setText("Current Vaccine date: ");
                                                //vaccineDateTextViewId.setText(result);
                                                vaccineDone2ButtonId.setVisibility(View.VISIBLE);
                                                VaccineNoId.setVisibility(View.GONE);
                                                vaccineDone2ButtonId.setEnabled(false);
                                                okButtonId.setVisibility(View.VISIBLE);
                                                VaccineNotextId.setVisibility(View.GONE);
                                                nextVaccineId.setVisibility(View.GONE);
                                                vaccineDateText2ViewId.setVisibility(View.VISIBLE);
                                                previousVaccineId.setVisibility(View.VISIBLE);
                                                vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                //VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                //vaccineId.setVisibility(View.VISIBLE);
                                                //takenId.setVisibility(View.VISIBLE);
                                                //radioGrroup.setVisibility(View.VISIBLE);
                                                //VaccineDoneButtonId.setVisibility(View.VISIBLE);


                                                vaccineDateText2ViewId.setText(result2);
                                                vaccineDateText3ViewId.setText(result3);
                                                VaccineNoId.setText(count);
                                                //vaccineId.setText("Vaccine No "+countt+":");

                                                vaccineDateText2ViewId.setText("All Vacchines have been taken");
                                                vaccineDateText3ViewId.setText(result3);
                                            }

                                        }
                                        else if(value.equalsIgnoreCase("No"))
                                        {
                                            //VaccineDoneButtonId.setVisibility(View.GONE);
                                            VaccineSelectDateButtonId.setVisibility(View.VISIBLE);
                                            vaccineDone2ButtonId.setVisibility(View.GONE);
                                            okButtonId.setVisibility(View.GONE);
                                            ok2ButtonId.setVisibility(View.GONE);
                                            currentVaccineId.setVisibility(View.VISIBLE);
                                            vaccineDateTextViewId.setVisibility(View.VISIBLE);
                                            nextVaccineId.setVisibility(View.GONE);
                                            vaccineDateText2ViewId.setVisibility(View.GONE);
                                            VaccineNotextId.setVisibility(View.GONE);
                                            VaccineNoId.setVisibility(View.GONE);
                                            vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                            previousVaccineId.setVisibility(View.VISIBLE);

                                            String dates;
                                            Vaccine vaccinee = Vaccine.getInstance();
                                            dates = vaccinee.getDate();
                                            String[] parts = dates.split("-");
                                            String yearss = parts[0]; // 004
                                            String monthss = parts[1]; // 034556
                                            String dayss = parts[2]; // 034556

                                            int daysss,monthsss,yearsss;

                                            daysss = Integer.parseInt(dayss);
                                            monthsss = Integer.parseInt(monthss);
                                            yearsss = Integer.parseInt(yearss);

                                            String result4 = (dayss+"/"+monthss+"/"+yearss);

                                            vaccineDateText3ViewId.setText(result4);
//                                            VaccineSelectDateButton2Id.setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    DatePicker datePicker = new DatePicker(VacchineScheduleActivity.this);
//                                                    int currentday= datePicker.getDayOfMonth();
//                                                    int currentMonth = (datePicker.getMonth());
//                                                    int currentYear = datePicker.getYear();
//                                                    datePickerDialog = new DatePickerDialog(VacchineScheduleActivity.this,
//
//                                                            new DatePickerDialog.OnDateSetListener() {
//                                                                @Override
//                                                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                                                                    String result = (dayOfMonth+"/"+(month+1)+"/"+year);
//                                                                    vaccineDateText3ViewId.setText(result);
//                                                                    VaccineSelectDateButton2Id.setVisibility(View.GONE);
//                                                                    VaccineDoneButtonId.setVisibility(View.VISIBLE);
//
//                                                                }
//                                                            },currentYear,currentMonth,currentday);
//
//                                                    datePickerDialog.show();
//                                                }
//                                            });
                                            VaccineSelectDateButtonId.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DatePicker datePicker = new DatePicker(VacchineScheduleActivity.this);
                                                    int currentday= datePicker.getDayOfMonth();
                                                    int currentMonth = (datePicker.getMonth());
                                                    int currentYear = datePicker.getYear();
                                                    datePickerDialog = new DatePickerDialog(VacchineScheduleActivity.this,

                                                            new DatePickerDialog.OnDateSetListener() {
                                                                @Override
                                                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                                                    String result = (dayOfMonth+"/"+(month+1)+"/"+year);

                                                                    int day,month1,month2,months,years,total,total2,total3,total4;

                                                                    total = (year*12);
                                                                    month2 = month+1;
                                                                    total2 = total+(month+1);
                                                                    total3 = total2*30;
                                                                    total4 = total3+dayOfMonth;


                                                                    String date2,date3;

                                                                    Vaccine vaccine = Vaccine.getInstance();
                                                                    count1 = vaccine.getId();
                                                                    date2 = vaccine.getDate();
                                                                    date3 = vaccine.getDate1();

                                                                    String[] parts = date2.split("-");
                                                                    String yearss = parts[0]; // 004
                                                                    String monthss = parts[1]; // 034556
                                                                    String dayss = parts[2]; // 034556

                                                                    String[] pparts = date3.split("-");
                                                                    String pyear = pparts[0]; // 004
                                                                    String pmonth = pparts[1]; // 034556
                                                                    String pday = pparts[2]; // 034556

                                                                    Log.d("year>>", String.valueOf(yearss));
                                                                    Log.d("month>>", String.valueOf(monthss));
                                                                    Log.d("day>>", String.valueOf(dayss));

                                                                    int daysss,monthsss,yearsss;

                                                                    daysss = Integer.parseInt(dayss);
                                                                    monthsss = Integer.parseInt(monthss);
                                                                    yearsss = Integer.parseInt(yearss);

                                                                    Log.d("year>>", String.valueOf(yearsss));
                                                                    Log.d("month>>", String.valueOf(monthsss));
                                                                    Log.d("day>>", String.valueOf(daysss));

                                                                    int totals,totalss,totalsss,totalssss;

                                                                    totals = (yearsss*12);
                                                                    totalss = totals+(monthsss);
                                                                    totalsss = totalss*30;
                                                                    totalssss = totalsss+daysss;

                                                                    Log.d("total year1>>", String.valueOf(total4));
                                                                    Log.d("total year2>>", String.valueOf(totalssss));

//                                                                    if(total4>(totalssss-1))
                                                                    {
                                                                        if(count1==1)
                                                                        {
                                                                            if(month2==1 || month2==3 || month2==5 || month2==7 || month2==8 || month2==10 || month2==12)
                                                                            {
                                                                                total4 = total3+dayOfMonth+27;
                                                                            }
                                                                            else
                                                                            {
                                                                                total4 = total3+dayOfMonth+28;
                                                                            }

                                                                            day = total4%30;
                                                                            month1 = total4/30;
                                                                            months = month1%12;
                                                                            years = month1/12;

                                                                            if(day==0 && months==0)
                                                                            {
                                                                                day = day+1;
                                                                                months = months+1;
                                                                            }

                                                                            if(day==0)
                                                                            {
                                                                                day = day+1;
                                                                            }

                                                                            if(months==0)
                                                                            {
                                                                                months = months+1;
                                                                            }

                                                                            int c = count1+1;
                                                                            String count = String.valueOf(c);
                                                                            String countt = String.valueOf(count1);
                                                                            int j = count1+2;
                                                                            count7 = String.valueOf(j);

                                                                            Mother mother1 = Mother.getInstance();
                                                                            mother1.setId1(c);
                                                                            mother1.setId2(j);

                                                                            String result2 = (day+"/"+months+"/"+years);
                                                                            String result3 = (pday+"/"+pmonth+"/"+pyear);
                                                                            String result4 = (dayss+"/"+monthss+"/"+yearss);

                                                                            currentVaccineId.setText("Current Vaccine date: ");
                                                                            vaccineDateTextViewId.setText(result);
                                                                            vaccineDone2ButtonId.setVisibility(View.GONE);
                                                                            VaccineNoId.setVisibility(View.GONE);
                                                                            VaccineNotextId.setVisibility(View.GONE);
                                                                            nextVaccineId.setVisibility(View.GONE);
                                                                            vaccineDateText2ViewId.setVisibility(View.GONE);
                                                                            previousVaccineId.setVisibility(View.VISIBLE);
                                                                            vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                                            VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                                            //vaccineId.setVisibility(View.VISIBLE);
                                                                            //takenId.setVisibility(View.VISIBLE);
                                                                            //radioGrroup.setVisibility(View.VISIBLE);
                                                                            //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                                            //vaccineDateText2ViewId.setText(result2);
                                                                            vaccineDateText3ViewId.setText(result4);
                                                                            //VaccineNoId.setText(count);
                                                                            vaccinenoId.setText(countt);
                                                                        }
                                                                        else if(count1==2)
                                                                        {
                                                                            if(month2==1 || month2==3 || month2==5 || month2==7 || month2==8 || month2==10 || month2==12)
                                                                            {
                                                                                total4 = total3+dayOfMonth+180;
                                                                            }
                                                                            else
                                                                            {
                                                                                total4 = total3+dayOfMonth+181;
                                                                            }

                                                                            day = total4%30;
                                                                            month1 = total4/30;
                                                                            months = month1%12;
                                                                            years = month1/12;

                                                                            if(day==0 && months==0)
                                                                            {
                                                                                day = day+1;
                                                                                months = months+1;
                                                                            }

                                                                            if(day==0)
                                                                            {
                                                                                day = day+1;
                                                                            }

                                                                            if(months==0)
                                                                            {
                                                                                months = months+1;
                                                                            }

                                                                            int c = count1+1;
                                                                            String count = String.valueOf(c);
                                                                            String countt = String.valueOf(count1);
                                                                            int j = count1+2;
                                                                            count7 = String.valueOf(j);

                                                                            Mother mother1 = Mother.getInstance();
                                                                            mother1.setId1(c);
                                                                            mother1.setId2(j);

                                                                            String result2 = (day+"/"+months+"/"+years);
                                                                            String result3 = (pday+"/"+pmonth+"/"+pyear);
                                                                            String result4 = (dayss+"/"+monthss+"/"+yearss);

                                                                            currentVaccineId.setText("Current Vaccine date: ");
                                                                            vaccineDateTextViewId.setText(result);
                                                                            vaccineDone2ButtonId.setVisibility(View.GONE);
                                                                            VaccineNoId.setVisibility(View.GONE);
                                                                            VaccineNotextId.setVisibility(View.GONE);
                                                                            nextVaccineId.setVisibility(View.GONE);
                                                                            vaccineDateText2ViewId.setVisibility(View.GONE);
                                                                            previousVaccineId.setVisibility(View.VISIBLE);
                                                                            vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                                            VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                                            //vaccineId.setVisibility(View.VISIBLE);
                                                                            //takenId.setVisibility(View.VISIBLE);
                                                                            //radioGrroup.setVisibility(View.VISIBLE);
                                                                            //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                                            //vaccineDateText2ViewId.setText(result2);
                                                                            vaccineDateText3ViewId.setText(result4);
                                                                            //VaccineNoId.setText(count);
                                                                            vaccinenoId.setText(countt);
                                                                        }

                                                                        else if(count1==3)
                                                                        {
                                                                            if(month2==1 || month2==3 || month2==5 || month2==7 || month2==8 || month2==10 || month2==12)
                                                                            {
                                                                                total4 = total3+dayOfMonth+359;
                                                                            }
                                                                            else
                                                                            {
                                                                                total4 = total3+dayOfMonth+360;
                                                                            }

                                                                            day = total4%30;
                                                                            month1 = total4/30;
                                                                            months = month1%12;
                                                                            years = month1/12;


                                                                            if(day==0 && months==0)
                                                                            {
                                                                                day = day+1;
                                                                                months = months+1;
                                                                            }

                                                                            if(day==0)
                                                                            {
                                                                                day = day+1;
                                                                            }

                                                                            if(months==0)
                                                                            {
                                                                                months = months+1;
                                                                            }

                                                                            int c = count1+1;
                                                                            String count = String.valueOf(c);
                                                                            String countt = String.valueOf(count1);
                                                                            int j = count1+2;
                                                                            count7 = String.valueOf(j);

                                                                            Mother mother1 = Mother.getInstance();
                                                                            mother1.setId1(c);
                                                                            mother1.setId2(j);

                                                                            String result2 = (day+"/"+months+"/"+years);
                                                                            String result3 = (pday+"/"+pmonth+"/"+pyear);
                                                                            String result4 = (dayss+"/"+monthss+"/"+yearss);

                                                                            currentVaccineId.setText("Current Vaccine date: ");
                                                                            vaccineDateTextViewId.setText(result);
                                                                            vaccineDone2ButtonId.setVisibility(View.GONE);
                                                                            VaccineNoId.setVisibility(View.GONE);
                                                                            VaccineNotextId.setVisibility(View.GONE);
                                                                            nextVaccineId.setVisibility(View.GONE);
                                                                            vaccineDateText2ViewId.setVisibility(View.GONE);
                                                                            previousVaccineId.setVisibility(View.VISIBLE);
                                                                            vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                                            VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                                            //vaccineId.setVisibility(View.VISIBLE);
                                                                            //takenId.setVisibility(View.VISIBLE);
                                                                            //radioGrroup.setVisibility(View.VISIBLE);
                                                                            //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                                            //vaccineDateText2ViewId.setText(result2);
                                                                            vaccineDateText3ViewId.setText(result4);
                                                                            //VaccineNoId.setText(count);
                                                                            vaccinenoId.setText(countt);
                                                                        }

                                                                        else if(count1==4)
                                                                        {
                                                                            if(month2==1 || month2==3 || month2==5 || month2==7 || month2==8 || month2==10 || month2==12)
                                                                            {
                                                                                total4 = total3+dayOfMonth+359;
                                                                            }
                                                                            else
                                                                            {
                                                                                total4 = total3+dayOfMonth+360;
                                                                            }

                                                                            day = total4%30;
                                                                            month1 = total4/30;
                                                                            months = month1%12;
                                                                            years = month1/12;

                                                                            if(day==0 && months==0)
                                                                            {
                                                                                day = day+1;
                                                                                months = months+1;
                                                                            }

                                                                            if(day==0)
                                                                            {
                                                                                day = day+1;
                                                                            }

                                                                            if(months==0)
                                                                            {
                                                                                months = months+1;
                                                                            }

                                                                            int c = count1+1;
                                                                            String count = String.valueOf(c);
                                                                            String countt = String.valueOf(count1);
                                                                            int j = count1+2;
                                                                            count7 = String.valueOf(j);

                                                                            Mother mother1 = Mother.getInstance();
                                                                            mother1.setId1(c);
                                                                            mother1.setId2(j);

                                                                            String result2 = (day+"/"+months+"/"+years);
                                                                            String result3 = (pday+"/"+pmonth+"/"+pyear);
                                                                            String result4 = (dayss+"/"+monthss+"/"+yearss);

                                                                            currentVaccineId.setText("Current Vaccine date: ");
                                                                            vaccineDateTextViewId.setText(result);
                                                                            vaccineDone2ButtonId.setVisibility(View.GONE);
                                                                            VaccineNoId.setVisibility(View.GONE);
                                                                            VaccineNotextId.setVisibility(View.GONE);
                                                                            nextVaccineId.setVisibility(View.GONE);
                                                                            vaccineDateText2ViewId.setVisibility(View.GONE);
                                                                            previousVaccineId.setVisibility(View.VISIBLE);
                                                                            vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                                            VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                                            //vaccineId.setVisibility(View.VISIBLE);
                                                                            //takenId.setVisibility(View.VISIBLE);
                                                                            //radioGrroup.setVisibility(View.VISIBLE);
                                                                            //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                                            //vaccineDateText2ViewId.setText(result2);
                                                                            vaccineDateText3ViewId.setText(result4);
                                                                            //VaccineNoId.setText(count);
                                                                            vaccinenoId.setText(countt);
                                                                        }

                                                                        else if(count1==5)
                                                                        {
                                                                            if(month2==1 || month2==3 || month2==5 || month2==7 || month2==8 || month2==10 || month2==12)
                                                                            {
                                                                                total4 = total3+dayOfMonth+359;
                                                                            }
                                                                            else
                                                                            {
                                                                                total4 = total3+dayOfMonth+360;
                                                                            }

                                                                            day = total4%30;
                                                                            month1 = total4/30;
                                                                            months = month1%12;
                                                                            years = month1/12;

                                                                            if(day==0 && months==0)
                                                                            {
                                                                                day = day+1;
                                                                                months = months+1;
                                                                            }

                                                                            if(day==0)
                                                                            {
                                                                                day = day+1;
                                                                            }

                                                                            if(months==0)
                                                                            {
                                                                                months = months+1;
                                                                            }

                                                                            int c = count1+1;
                                                                            String count = String.valueOf(c);
                                                                            String countt = String.valueOf(count1);
                                                                            int j = count1+2;
                                                                            count7 = String.valueOf(j);

                                                                            Mother mother1 = Mother.getInstance();
                                                                            mother1.setId1(c);
                                                                            mother1.setId2(j);

                                                                            String result2 = (day+"/"+months+"/"+years);
                                                                            String result3 = (pday+"/"+pmonth+"/"+pyear);
                                                                            String result4 = (dayss+"/"+monthss+"/"+yearss);

                                                                            currentVaccineId.setText("Current Vaccine date: ");
                                                                            vaccineDateTextViewId.setText(result);
                                                                            vaccineDone2ButtonId.setVisibility(View.GONE);
                                                                            VaccineNoId.setVisibility(View.GONE);
                                                                            VaccineNotextId.setVisibility(View.GONE);
                                                                            nextVaccineId.setVisibility(View.GONE);
                                                                            vaccineDateText2ViewId.setVisibility(View.GONE);
                                                                            previousVaccineId.setVisibility(View.VISIBLE);
                                                                            vaccineDateText3ViewId.setVisibility(View.VISIBLE);
                                                                            VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
                                                                            //vaccineId.setVisibility(View.VISIBLE);
                                                                            //takenId.setVisibility(View.VISIBLE);
                                                                            //radioGrroup.setVisibility(View.VISIBLE);
                                                                            //VaccineDoneButtonId.setVisibility(View.VISIBLE);

                                                                            //vaccineDateText2ViewId.setText(result2);
                                                                            vaccineDateText3ViewId.setText(result4);
                                                                            //VaccineNoId.setText(count);
                                                                            vaccinenoId.setText(countt);
//                                                                            vaccineDateTextViewId.setText(result);
//                                                                            vaccineDone2ButtonId.setVisibility(View.VISIBLE);
//                                                                            VaccineNoId.setVisibility(View.GONE);
//                                                                            VaccineNotextId.setVisibility(View.GONE);
//                                                                            nextVaccineId.setVisibility(View.GONE);
//                                                                            vaccineDateText2ViewId.setVisibility(View.VISIBLE);
//                                                                            previousVaccineId.setVisibility(View.VISIBLE);
//                                                                            vaccineDateText3ViewId.setVisibility(View.VISIBLE);
//                                                                            vaccineId.setVisibility(View.VISIBLE);
//                                                                            takenId.setVisibility(View.VISIBLE);
//                                                                            vaccineDone2ButtonId.setEnabled(false);
//                                                                            okButtonId.setVisibility(View.VISIBLE);
//
//                                                                            radioGrroup.setVisibility(View.VISIBLE);
//                                                                            VaccineDoneButtonId.setVisibility(View.VISIBLE);

//                                                        try {
//
//                                                            VaccineDoneButtonId.setOnClickListener(new View.OnClickListener() {
//                                                                @Override
//                                                                public void onClick(View v) {
//
//                                                                    if(radioGrroup.getCheckedRadioButtonId()!=-1)
//                                                                    {
//                                                                        int selectedId = radioGrroup.getCheckedRadioButtonId();
//                                                                        genderButton = (RadioButton) findViewById(selectedId);
//                                                                        String value = genderButton.getText().toString();
//                                                                        if(value.equalsIgnoreCase("Yes"))
//                                                                        {
//                                                                            VaccineSelectDateButton2Id.setVisibility(View.GONE);
//                                                                        }
//                                                                        else if(value.equalsIgnoreCase("No"))
//                                                                        {
//                                                                            VaccineDoneButtonId.setVisibility(View.GONE);
//                                                                            VaccineSelectDateButton2Id.setVisibility(View.VISIBLE);
//                                                                            VaccineSelectDateButton2Id.setOnClickListener(new View.OnClickListener() {
//                                                                                @Override
//                                                                                public void onClick(View v) {
//                                                                                    DatePicker datePicker = new DatePicker(VacchineScheduleActivity.this);
//                                                                                    int currentday= datePicker.getDayOfMonth();
//                                                                                    int currentMonth = (datePicker.getMonth());
//                                                                                    int currentYear = datePicker.getYear();
//                                                                                    datePickerDialog = new DatePickerDialog(VacchineScheduleActivity.this,
//
//                                                                                            new DatePickerDialog.OnDateSetListener() {
//                                                                                                @Override
//                                                                                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                                                                                                    String result = (dayOfMonth+"/"+(month+1)+"/"+year);
//                                                                                                    vaccineDateText3ViewId.setText(result);
//                                                                                                    VaccineSelectDateButton2Id.setVisibility(View.GONE);
//                                                                                                    VaccineDoneButtonId.setVisibility(View.VISIBLE);
//
//                                                                                                }
//                                                                                            },currentYear,currentMonth,currentday);
//
//                                                                                    datePickerDialog.show();
//                                                                                }
//                                                                            });
//                                                                        }
//                                                                    }
//
//                                                                    else
//                                                                    {
//                                                                        Toast.makeText(VacchineScheduleActivity.this,"Please select anything",Toast.LENGTH_SHORT).show();
//                                                                    }
//                                                                }
//                                                            });
//                                                        }
//                                                        catch(Exception e){
//                                                            Toast.makeText(VacchineScheduleActivity.this,"Please select anything",Toast.LENGTH_SHORT).show();
//
//                                                        }


//                                                                            vaccineDateText2ViewId.setText("All Vacchines have been taken");
//                                                                            vaccineDateText3ViewId.setText(result3);
//                                                                            vaccineId.setText("Vaccine No "+countt+":");
//
//                                                                            vaccineDateTextViewId.setEnabled(false);
//                                                                            vaccineDone2ButtonId.setEnabled(false);
//                                                                            VaccineNoId.setEnabled(false);
//                                                                            nextVaccineId.setEnabled(false);
//                                                                            vaccineDateText2ViewId.setEnabled(false);
//                                                                            vaccineId.setEnabled(false);
//                                                                            takenId.setEnabled(false);
                                                                        }
                                                                    }

//                                                                    else
//                                                                    {
//                                                                        Toast.makeText(VacchineScheduleActivity.this,"You can only take vacchine after completing your last settled vacchine.\nPlease check your Vacchination List",Toast.LENGTH_SHORT).show();
//                                                                    }

                                                                }
                                                            },currentYear,currentMonth,currentday);

                                                    datePickerDialog.show();
                                                }
                                            });
                                        }
                                    }

                                    else
                                    {
                                        Toast.makeText(VacchineScheduleActivity.this,"Please select anything",Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                        }
                        catch(Exception e){
                            Toast.makeText(VacchineScheduleActivity.this,"Please select anything",Toast.LENGTH_SHORT).show();

                        }

                        okButtonId.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if(radioGrroup.getCheckedRadioButtonId()!=-1)
                                {

                                    final String date1,date2;
                                    date1 = vaccineDateText3ViewId.getText().toString();
                                    date2 = String.valueOf(0);


                                    if(date1.isEmpty())
                                    {
                                        Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                                    }

                                    else
                                    {

                                        try {
                                            Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                            parsedDate = formatter.format(initDate);

                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        Mother mother = Mother.getInstance();
                                        String name4 = mother.getName();
                                        String name5 = mother.getReminder();
                                        String tableName = mother.getVaccine();
                                        final int id7 = 6;
                                        final int id8 = 2;

                                        loading = new ProgressDialog(VacchineScheduleActivity.this);
                                        loading.setIcon(R.drawable.load);
                                        loading.setTitle("Vaccine Schedule");
                                        loading.setMessage("Please wait...");
                                        loading.show();

                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINATION_URL, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                //for track response in Logcat
                                                Log.d("RESPONSE", "" + response);

                                                //if we are getting success from server
                                                if (response.equals("success")) {
                                                    //creating a shared preference
                                                    loading.dismiss();
                                                    //starting profile activity
                                                    Intent intent = new Intent(VacchineScheduleActivity.this, MotherHomeActivity.class);
                                                    startActivity(intent);

                                                    Toast.makeText(VacchineScheduleActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                                } else if (response.equals("exists")) {
                                                    Toast.makeText(VacchineScheduleActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                                    loading.dismiss();
                                                } else if (response.equals("failure")) {
                                                    Toast.makeText(VacchineScheduleActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                                    loading.dismiss();
                                                }
                                            }
                                        },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Toast.makeText(VacchineScheduleActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                                params.put(Key.KEY_MNDATE,date1);
                                                params.put(Key.KEY_NUMBER, String.valueOf(id7));
                                                params.put(Key.KEY_NUMBERS, String.valueOf(id8));

                                                Log.d("info",""+getCell+" "+parsedDate);
                                                //returning parameter
                                                return params;

                                            }
                                        };

                                        //Adding the string request to the queue
                                        RequestQueue requestQueue = Volley.newRequestQueue(VacchineScheduleActivity.this);
                                        requestQueue.add(stringRequest);
//
                                    }

                                    Intent intent = new Intent(VacchineScheduleActivity.this,MotherHomeActivity.class);
                                    startActivity(intent);
                                }

                                else
                                {
                                    Toast.makeText(VacchineScheduleActivity.this,"Please select anything",Toast.LENGTH_SHORT).show();
                                }



                            }
                        });

                        vaccineDone2ButtonId.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if(radioGrroup.getCheckedRadioButtonId()!=-1)
                                {
                                    final String date1,date2,ids;

                                    date1 = vaccineDateText3ViewId.getText().toString();
                                    date2 = vaccineDateText2ViewId.getText().toString();
                                    ids = VaccineNoId.getText().toString();

                                    Log.d("id", "id: "+ids);

                                    if(ids.equals("Select"))
                                    {
                                        Toast.makeText(VacchineScheduleActivity.this, "Please Select Vacchine No", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        String b = VaccineNoId.getText().toString();

                                        //String count = String.valueOf(i);
                                        final int a = Integer.valueOf(b);
                                        int j = a+1;

                                        Log.d("a", "a: "+a);
                                        Log.d("j", "j: "+j);

                                        if(date1.isEmpty())
                                        {
                                            Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                                        }

                                        else if(date2.isEmpty())
                                        {
                                            Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                                        }

                                        else
                                        {

                                            try {
                                                Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                                                Date initDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
                                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                                                parsedDate = formatter.format(initDate);
                                                parsedDate1 = formatter1.format(initDate1);

                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                            Mother mother = Mother.getInstance();
                                            String name3 = mother.getDoctor();
                                            String name6 = mother.getReminder();
                                            String name1 = mother.getName();
                                            final int id5 = a;
                                            int id6 = j;

                                            loading = new ProgressDialog(VacchineScheduleActivity.this);
                                            loading.setIcon(R.drawable.load);
                                            loading.setTitle("Doctor Schedule");
                                            loading.setMessage("Please wait...");
                                            loading.show();

                                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINATION_URL, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    //for track response in Logcat
                                                    Log.d("RESPONSE", "" + response);

                                                    //if we are getting success from server
                                                    if (response.equals("success")) {
                                                        //creating a shared preference
                                                        loading.dismiss();
                                                        //starting profile activity
                                                        Intent intent = new Intent(VacchineScheduleActivity.this, MotherHomeActivity.class);
                                                        startActivity(intent);

                                                        Toast.makeText(VacchineScheduleActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                                    } else if (response.equals("exists")) {
                                                        Toast.makeText(VacchineScheduleActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                                        loading.dismiss();
                                                    } else if (response.equals("failure")) {
                                                        Toast.makeText(VacchineScheduleActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                                        loading.dismiss();
                                                    }
                                                }
                                            },
                                                    new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                            Toast.makeText(VacchineScheduleActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                                    params.put(Key.KEY_MNDATE,parsedDate1);
                                                    params.put(Key.KEY_NUMBER, String.valueOf(id5));
                                                    params.put(Key.KEY_NUMBERS, String.valueOf(2));

                                                    Log.d("info",""+getCell+" "+parsedDate);
                                                    //returning parameter
                                                    return params;

                                                }
                                            };

                                            //Adding the string request to the queue
                                            RequestQueue requestQueue = Volley.newRequestQueue(VacchineScheduleActivity.this);
                                            requestQueue.add(stringRequest);


                                        }
                                    }
                                }

                                else
                                {
                                    Toast.makeText(VacchineScheduleActivity.this,"Please select anything",Toast.LENGTH_SHORT).show();
                                }
                                

                            }
                        });

                        VaccineSelectDateButton2Id.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(radioGrroup.getCheckedRadioButtonId()!=-1)
                                {
                                    final String date1,date2,ids;

                                    date1 = vaccineDateTextViewId.getText().toString();
                                    date2 = vaccineDateText3ViewId.getText().toString();
                                    ids = vaccinenoId.getText().toString();

                                    //Log.d("id", "id: "+ids);

                                    if(ids.equals("Select"))
                                    {
                                        Toast.makeText(VacchineScheduleActivity.this, "Please Select Vacchine No", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        String b = vaccinenoId.getText().toString();

                                        //String count = String.valueOf(i);
                                        final int a = Integer.valueOf(b);
                                        int j = a+1;

                                        Log.d("a", "a: "+a);
                                        Log.d("j", "j: "+j);

                                        if(date1.isEmpty())
                                        {
                                            Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                                        }

                                        else if(date2.isEmpty())
                                        {
                                            Toast.makeText(VacchineScheduleActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                                        }

                                        else
                                        {

                                            try {
                                                Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                                                Date initDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
                                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                                                parsedDate = formatter.format(initDate);
                                                parsedDate1 = formatter1.format(initDate1);

                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                            Mother mother = Mother.getInstance();
                                            String name3 = mother.getDoctor();
                                            String name6 = mother.getReminder();
                                            String name1 = mother.getName();
                                            final int id5 = a;
                                            int id6 = j;

                                            loading = new ProgressDialog(VacchineScheduleActivity.this);
                                            loading.setIcon(R.drawable.load);
                                            loading.setTitle("Doctor Schedule");
                                            loading.setMessage("Please wait...");
                                            loading.show();

                                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINATION_UPDATE, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    //for track response in Logcat
                                                    Log.d("RESPONSE", "" + response);

                                                    //if we are getting success from server
                                                    if (response.equals("success")) {
                                                        //creating a shared preference
                                                        loading.dismiss();
                                                        //starting profile activity
                                                        Intent intent = new Intent(VacchineScheduleActivity.this, MotherHomeActivity.class);
                                                        startActivity(intent);

                                                        Toast.makeText(VacchineScheduleActivity.this, "Updated Successfull", Toast.LENGTH_SHORT).show();



                                                    } else if (response.equals("failure")) {
                                                        Toast.makeText(VacchineScheduleActivity.this, "Not Update", Toast.LENGTH_SHORT).show();
                                                        loading.dismiss();
                                                    }
                                                }
                                            },
                                                    new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                            Toast.makeText(VacchineScheduleActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                                    params.put(Key.KEY_NUMBER, String.valueOf(id5));
                                                    params.put(Key.KEY_NUMBERS, String.valueOf(2));

                                                    Log.d("info",""+getCell+" "+parsedDate);
                                                    //returning parameter
                                                    return params;

                                                }
                                            };

                                            //Adding the string request to the queue
                                            RequestQueue requestQueue = Volley.newRequestQueue(VacchineScheduleActivity.this);
                                            requestQueue.add(stringRequest);


                                        }
                                    }
                                }
                                else
                                {
                                    Toast.makeText(VacchineScheduleActivity.this,"Please select anything",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                }

                }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        vacchinesNumber = vacchineNumber.getSelectedItem().toString();
        VaccineNoId.setText(vacchinesNumber);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(VacchineScheduleActivity.this,MotherHomeActivity.class);
        startActivity(intent);

    }
}
