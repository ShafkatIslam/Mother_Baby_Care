package com.example.professt.mother_baby_care.Mother;

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
import com.example.professt.mother_baby_care.Key;
import com.example.professt.mother_baby_care.MainActivity;
import com.example.professt.mother_baby_care.R;
import com.example.professt.mother_baby_care.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class  MeetingDoctorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button selectDateButtonId,doneButtonId,done2ButtonId,okButtonId,ok2ButtonId;
    TextView dateTextViewId,meetingNoId,meetingNotextId,nextMeetingId,dateText2ViewId,previousMeetingId,dateText3ViewId,currentMeetingId;
    Spinner meetingNumber;

    DatePickerDialog datePickerDialog;
    public int i;
    int count1,count2;

    String count3,count4,count7;

    String parsedDate;
    String parsedDate1;
    String meetingsNumber;

    String getCell;

    String[] arrrrrname1 = {"Select","1","2","3","4","5"};

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_doctor);

        selectDateButtonId = (Button)findViewById(R.id.selectDateButtonId);
//        doneButtonId = (Button)findViewById(R.id.doneButtonId);
        done2ButtonId = (Button)findViewById(R.id.done2ButtonId);
        okButtonId = (Button)findViewById(R.id.okButtonId);
        ok2ButtonId = (Button)findViewById(R.id.ok2ButtonId);


        currentMeetingId = (TextView)findViewById(R.id.currentMeetingId);
        dateTextViewId = (TextView)findViewById(R.id.dateTextViewId);
        meetingNoId = (TextView)findViewById(R.id.meetingNoId);
        meetingNotextId = (TextView)findViewById(R.id.meetingNotextId);
        nextMeetingId = (TextView)findViewById(R.id.nextMeetingId);
        dateText2ViewId = (TextView)findViewById(R.id.dateText2ViewId);
        previousMeetingId = (TextView)findViewById(R.id.previousMeetingId);
        dateText3ViewId = (TextView)findViewById(R.id.dateText3ViewId);
        meetingNumber = (Spinner) findViewById(R.id.meetingNumber);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arrrrrname1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingNumber.setAdapter(arrayAdapter);
        meetingNumber.setOnItemSelectedListener(this);

        i=1;

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");

        //for showing progress dialog
        loading = new ProgressDialog(MeetingDoctorActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Key.DOCTOR_URL+getCell;

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

                        Intent intent = new Intent(MeetingDoctorActivity.this,MotherHomeActivity.class);
                        startActivity(intent);
                        loading.dismiss();
                        Toast.makeText(MeetingDoctorActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(MeetingDoctorActivity.this);
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
                meetingNoId.setVisibility(View.GONE);
                meetingNotextId.setVisibility(View.GONE);
                meetingNumber.setVisibility(View.GONE);
                nextMeetingId.setVisibility(View.GONE);
                dateText2ViewId.setVisibility(View.GONE);
                previousMeetingId.setVisibility(View.GONE);
                dateText3ViewId.setVisibility(View.GONE);
                done2ButtonId.setVisibility(View.GONE);
                okButtonId.setVisibility(View.GONE);
                ok2ButtonId.setVisibility(View.GONE);

                selectDateButtonId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePicker datePicker = new DatePicker(MeetingDoctorActivity.this);
                        int currentday = datePicker.getDayOfMonth();
                        int currentMonth = (datePicker.getMonth());
                        int currentYear = datePicker.getYear();
                        datePickerDialog = new DatePickerDialog(MeetingDoctorActivity.this,

                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                        String result = (dayOfMonth + "/" + (month + 1) + "/" + year);

                                        int day, month1, month2, months, years, total, total2, total3, total4;

                                        total = (year * 12);
                                        month2 = month + 1;
                                        total2 = total + (month + 1);
                                        total3 = total2 * 30;

                                        if (month2 == 1 || month2 == 3 || month2 == 5 || month2 == 7 || month2 == 8 || month2 == 10 || month2 == 12) {
                                            total4 = total3 + dayOfMonth + 55;
                                        } else {
                                            total4 = total3 + dayOfMonth + 56;
                                        }


                                        day = total4 % 30;
                                        month1 = total4 / 30;
                                        months = month1 % 12;
                                        years = month1 / 12;

//                                        String count = String.valueOf(i);
//                                        int j = i + 1;
//                                        count7 = String.valueOf(j);
//
//                                        Mother mother1 = Mother.getInstance();
//                                        mother1.setId1(i);
//                                        mother1.setId2(j);



                                        String result2 = (day + "/" + months + "/" + years);

                                        dateTextViewId.setText(result);
                                        meetingNoId.setVisibility(View.VISIBLE);
                                        meetingNumber.setVisibility(View.VISIBLE);
                                        meetingNotextId.setVisibility(View.VISIBLE);
                                        nextMeetingId.setVisibility(View.VISIBLE);
                                        dateText2ViewId.setVisibility(View.VISIBLE);
                                        done2ButtonId.setVisibility(View.VISIBLE);

                                        dateText2ViewId.setText(result);
                                        //meetingNoId.setText(count);

                                    }
                                }, currentYear, currentMonth, currentday);

                        datePickerDialog.show();
                    }
                });

                okButtonId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String date1,date2;
                        date1 = dateTextViewId.getText().toString();
                        date2 = String.valueOf(0);

                        if(date1.isEmpty())
                        {
                            Toast.makeText(MeetingDoctorActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
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
                            String name4 = mother.getDoctor();
                            String name5 = mother.getReminder();
                            String name1 = mother.getName();
                            final int id7 = 6;
                            final int id8 = 2;

                            loading = new ProgressDialog(MeetingDoctorActivity.this);
                            loading.setIcon(R.drawable.load);
                            loading.setTitle("Doctor Schedule");
                            loading.setMessage("Please wait...");
                            loading.show();

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINE, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    //for track response in Logcat
                                    Log.d("RESPONSE", "" + response);

                                    //if we are getting success from server
                                    if (response.equals("success")) {
                                        //creating a shared preference
                                        loading.dismiss();
                                        //starting profile activity
                                        Intent intent = new Intent(MeetingDoctorActivity.this, MotherHomeActivity.class);
                                        startActivity(intent);

                                        Toast.makeText(MeetingDoctorActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                    } else if (response.equals("exists")) {
                                        Toast.makeText(MeetingDoctorActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                        loading.dismiss();
                                    } else if (response.equals("failure")) {
                                        Toast.makeText(MeetingDoctorActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                        loading.dismiss();
                                    }
                                }
                            },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(MeetingDoctorActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                            RequestQueue requestQueue = Volley.newRequestQueue(MeetingDoctorActivity.this);
                            requestQueue.add(stringRequest);
//
//                            cursor3 = myDatabaseHelper.showAllData5(name4);
//
//                            if(cursor3.getCount()==0)
//                            {
//                                rowId1 = myDatabaseHelper.insertData6(name4,parsedDate,date2,id7);
//                                rowId2 = myDatabaseHelper.insertData5(name5,1,parsedDate,date2,id7,id8,200);
//                            }
//
//                            else
//                            {
//                                rowId1 = myDatabaseHelper.insertData6(name4,parsedDate,date2,id7);
//                                rowId2 = myDatabaseHelper.insertData5(name5,1,parsedDate,date2,id7,id8,100);
//                            }
//
//                            if (rowId1 > 0 && rowId2>0)
//                            {
//                                Toast.makeText(MeetingDoctorActivity.this, "All check up have been taken", Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(MeetingDoctorActivity.this,MotherHomeActivity.class);
//                                startActivity(intent);
//
//                            }
//
//                            else
//                            {
//                                Toast.makeText(MeetingDoctorActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                            }
                        }

//                        Intent intent = new Intent(MeetingDoctorActivity.this,MotherHomeActivity.class);
//                        startActivity(intent);
                    }
                });

                done2ButtonId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String date1,date2,ids;

                        date1 = dateTextViewId.getText().toString();
                        date2 = dateText2ViewId.getText().toString();
                        ids = meetingNoId.getText().toString();

                        Log.d("id", "id: "+ids);

                        if(ids.equals("Select"))
                        {
                            Toast.makeText(MeetingDoctorActivity.this, "Please Select Vacchine No", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String b = meetingNoId.getText().toString();

                            //String count = String.valueOf(i);
                            final int a = Integer.valueOf(b);
                            int j = a+1;

                            Log.d("a", "a: "+a);
                            Log.d("j", "j: "+j);

                            if(date1.isEmpty())
                            {
                                Toast.makeText(MeetingDoctorActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                            }

                            else if(date2.isEmpty())
                            {
                                Toast.makeText(MeetingDoctorActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
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

                                loading = new ProgressDialog(MeetingDoctorActivity.this);
                                loading.setIcon(R.drawable.load);
                                loading.setTitle("Doctor Schedule");
                                loading.setMessage("Please wait...");
                                loading.show();

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINE, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        //for track response in Logcat
                                        Log.d("RESPONSE", "" + response);

                                        //if we are getting success from server
                                        if (response.equals("success")) {
                                            //creating a shared preference
                                            loading.dismiss();
                                            //starting profile activity
                                            Intent intent = new Intent(MeetingDoctorActivity.this, MotherHomeActivity.class);
                                            startActivity(intent);

                                            Toast.makeText(MeetingDoctorActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                        } else if (response.equals("exists")) {
                                            Toast.makeText(MeetingDoctorActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                            loading.dismiss();
                                        } else if (response.equals("failure")) {
                                            Toast.makeText(MeetingDoctorActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                            loading.dismiss();
                                        }
                                    }
                                },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(MeetingDoctorActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                RequestQueue requestQueue = Volley.newRequestQueue(MeetingDoctorActivity.this);
                                requestQueue.add(stringRequest);


                            }
                        }
                    }
                });

            }

            else {

                meetingNumber.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String cell = jo.getString(Key.KEY_MUCELL);
                    String ppdate = jo.getString(Key.KEY_MPDATE);
                    String ndate = jo.getString(Key.KEY_MNDATE);
                    String number = jo.getString(Key.KEY_NUMBER);

                    Reminder reminder = Reminder.getInstance();
                    reminder.setDate(ndate);


                    int cou;
                    String date1,pdate;

                    cou = Integer.parseInt(number);
                    pdate = ppdate;
                    date1 = ndate;

                    DoctorMeet doctorMeet = DoctorMeet.getInstance();
                    doctorMeet.setId(cou);
                    doctorMeet.setDate(date1);
                    doctorMeet.setDate1(pdate);

                    if(number.equals("6"))
                    {
                        meetingNoId.setVisibility(View.GONE);
                        meetingNotextId.setVisibility(View.GONE);
                        nextMeetingId.setVisibility(View.GONE);
                        dateText2ViewId.setVisibility(View.GONE);
                        previousMeetingId.setVisibility(View.GONE);
                        dateText3ViewId.setVisibility(View.GONE);
                        done2ButtonId.setVisibility(View.GONE);
                        okButtonId.setVisibility(View.GONE);

                        ok2ButtonId.setVisibility(View.VISIBLE);

                        currentMeetingId.setText("All check up have been taken");
                        currentMeetingId.setGravity(Gravity.CENTER);
                        currentMeetingId.setTextSize(30);
                        currentMeetingId.setTextColor(Color.parseColor("#bdbdbd"));

                        ok2ButtonId.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MeetingDoctorActivity.this,MotherHomeActivity.class);
                                startActivity(intent);

                            }
                        });
                    }
                    else
                    {
                        meetingNoId.setVisibility(View.GONE);
                        meetingNotextId.setVisibility(View.GONE);
                        meetingNumber.setVisibility(View.GONE);
                        nextMeetingId.setVisibility(View.GONE);
                        dateText2ViewId.setVisibility(View.GONE);
                        previousMeetingId.setVisibility(View.GONE);
                        dateText3ViewId.setVisibility(View.GONE);
                        done2ButtonId.setVisibility(View.GONE);
                        okButtonId.setVisibility(View.GONE);
                        ok2ButtonId.setVisibility(View.GONE);

                        selectDateButtonId.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePicker datePicker = new DatePicker(MeetingDoctorActivity.this);
                                int currentday = datePicker.getDayOfMonth();
                                int currentMonth = (datePicker.getMonth());
                                int currentYear = datePicker.getYear();
                                datePickerDialog = new DatePickerDialog(MeetingDoctorActivity.this,

                                        new DatePickerDialog.OnDateSetListener() {
                                            @Override
                                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                                String result = (dayOfMonth + "/" + (month + 1) + "/" + year);

                                                int day, month1, month2, months, years, total, total2, total3, total4;

                                                total = (year * 12);
                                                month2 = month + 1;
                                                total2 = total + (month + 1);
                                                total3 = total2 * 30;
                                                total4 = total3 + dayOfMonth;

                                                String date2,pdate1;

                                                DoctorMeet doctorMeet1 = DoctorMeet.getInstance();
                                                count1 = doctorMeet1.getId();
                                                date2 = doctorMeet1.getDate();
                                                pdate1 = doctorMeet1.getDate1();

                                                String[] parts = date2.split("-");
                                                String yearss = parts[0]; // 004
                                                String monthss = parts[1]; // 034556
                                                String dayss = parts[2]; // 034556

                                                String[] pparts = pdate1.split("-");
                                                String pyear = pparts[0]; // 004
                                                String pmonth = pparts[1]; // 034556
                                                String pday = pparts[2]; // 034556

                                                Log.d("year>>", String.valueOf(yearss));
                                                Log.d("month>>", String.valueOf(monthss));
                                                Log.d("day>>", String.valueOf(dayss));

                                                int daysss, monthsss, yearsss;

                                                daysss = Integer.parseInt(dayss);
                                                monthsss = Integer.parseInt(monthss);
                                                yearsss = Integer.parseInt(yearss);

                                                Log.d("year>>", String.valueOf(yearsss));
                                                Log.d("month>>", String.valueOf(monthsss));
                                                Log.d("day>>", String.valueOf(daysss));

                                                int totals, totalss, totalsss, totalssss;

                                                totals = (yearsss * 12);
                                                totalss = totals + (monthsss);
                                                totalsss = totalss * 30;
                                                totalssss = totalsss + daysss;

                                                Log.d("total year1>>", String.valueOf(total4));
                                                Log.d("total year2>>", String.valueOf(totalssss));

                                                if (total4 > (totalssss - 1)) {

                                                    if (count1 == 1) {
                                                        if (month2 == 1 || month2 == 3 || month2 == 5 || month2 == 7 || month2 == 8 || month2 == 10 || month2 == 12) {
                                                            total4 = total3 + dayOfMonth + 55;
                                                        } else {
                                                            total4 = total3 + dayOfMonth + 56;
                                                        }

                                                        day = total4 % 30;
                                                        month1 = total4 / 30;
                                                        months = month1 % 12;
                                                        years = month1 / 12;

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

                                                        int c = count1 + 1;
                                                        String count = String.valueOf(c);
                                                        int j = count1 + 2;
                                                        count7 = String.valueOf(j);

                                                        Mother mother1 = Mother.getInstance();
                                                        mother1.setId1(c);
                                                        mother1.setId2(j);


                                                        String result2 = (day + "/" + months + "/" + years);
                                                        String presult = (pday + "/" + pmonth + "/" + pyear);

                                                        currentMeetingId.setText("Current Meeting");
                                                        dateTextViewId.setText(result);
                                                        meetingNoId.setVisibility(View.VISIBLE);
                                                        meetingNotextId.setVisibility(View.VISIBLE);
                                                        nextMeetingId.setVisibility(View.VISIBLE);
                                                        dateText2ViewId.setVisibility(View.VISIBLE);
                                                        previousMeetingId.setVisibility(View.VISIBLE);
                                                        dateText3ViewId.setVisibility(View.VISIBLE);
                                                        done2ButtonId.setVisibility(View.VISIBLE);

                                                        dateText2ViewId.setText(result2);
                                                        dateText3ViewId.setText(presult);
                                                        meetingNoId.setText(count);


                                                    }
                                                    else if (count1 == 2) {
                                                        if (month2 == 1 || month2 == 3 || month2 == 5 || month2 == 7 || month2 == 8 || month2 == 10 || month2 == 12) {
                                                            total4 = total3 + dayOfMonth + 69;
                                                        } else {
                                                            total4 = total3 + dayOfMonth + 70;
                                                        }

                                                        day = total4 % 30;
                                                        month1 = total4 / 30;
                                                        months = month1 % 12;
                                                        years = month1 / 12;

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


                                                        int c = count1 + 1;
                                                        String count = String.valueOf(c);
                                                        int j = count1 + 2;
                                                        count7 = String.valueOf(j);

                                                        Mother mother1 = Mother.getInstance();
                                                        mother1.setId1(c);
                                                        mother1.setId2(j);


                                                        String result2 = (day + "/" + months + "/" + years);
                                                        String presult = (pday + "/" + pmonth + "/" + pyear);

                                                        dateTextViewId.setText(result);
                                                        meetingNoId.setVisibility(View.VISIBLE);
                                                        meetingNotextId.setVisibility(View.VISIBLE);
                                                        nextMeetingId.setVisibility(View.VISIBLE);
                                                        dateText2ViewId.setVisibility(View.VISIBLE);
                                                        previousMeetingId.setVisibility(View.VISIBLE);
                                                        dateText3ViewId.setVisibility(View.VISIBLE);
                                                        done2ButtonId.setVisibility(View.VISIBLE);

                                                        dateText2ViewId.setText(result2);
                                                        dateText3ViewId.setText(presult);
                                                        meetingNoId.setText(count);
                                                    } else if (count1 == 3) {
                                                        if (month2 == 1 || month2 == 3 || month2 == 5 || month2 == 7 || month2 == 8 || month2 == 10 || month2 == 12) {
                                                            total4 = total3 + dayOfMonth + 34;
                                                        } else {
                                                            total4 = total3 + dayOfMonth + 35;
                                                        }

                                                        day = total4 % 30;
                                                        month1 = total4 / 30;
                                                        months = month1 % 12;
                                                        years = month1 / 12;

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


                                                        int c = count1 + 1;
                                                        String count = String.valueOf(c);
                                                        int j = count1 + 2;
                                                        count7 = String.valueOf(j);

                                                        Mother mother1 = Mother.getInstance();
                                                        mother1.setId1(c);
                                                        mother1.setId2(j);

                                                        String result2 = (day + "/" + months + "/" + years);
                                                        String presult = (pday + "/" + pmonth + "/" + pyear);

                                                        dateTextViewId.setText(result);
                                                        meetingNoId.setVisibility(View.VISIBLE);
                                                        meetingNotextId.setVisibility(View.VISIBLE);
                                                        nextMeetingId.setVisibility(View.VISIBLE);
                                                        dateText2ViewId.setVisibility(View.VISIBLE);
                                                        previousMeetingId.setVisibility(View.VISIBLE);
                                                        dateText3ViewId.setVisibility(View.VISIBLE);
                                                        done2ButtonId.setVisibility(View.VISIBLE);

                                                        dateText2ViewId.setText(result2);
                                                        dateText3ViewId.setText(presult);
                                                        meetingNoId.setText(count);
                                                    } else if (count1 == 4) {
                                                        if (month2 == 1 || month2 == 3 || month2 == 5 || month2 == 7 || month2 == 8 || month2 == 10 || month2 == 12) {
                                                            total4 = total3 + dayOfMonth + 20;
                                                        } else {
                                                            total4 = total3 + dayOfMonth + 21;
                                                        }

                                                        day = total4 % 30;
                                                        month1 = total4 / 30;
                                                        months = month1 % 12;
                                                        years = month1 / 12;

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


                                                        int c = count1 + 1;
                                                        String count = String.valueOf(c);
                                                        int j = count1 + 2;
                                                        count7 = String.valueOf(j);

                                                        Mother mother1 = Mother.getInstance();
                                                        mother1.setId1(c);
                                                        mother1.setId2(j);

                                                        String result2 = (day + "/" + months + "/" + years);
                                                        String presult = (pday + "/" + pmonth + "/" + pyear);

                                                        dateTextViewId.setText(result);
                                                        meetingNoId.setVisibility(View.VISIBLE);
                                                        meetingNotextId.setVisibility(View.VISIBLE);
                                                        nextMeetingId.setVisibility(View.VISIBLE);
                                                        dateText2ViewId.setVisibility(View.VISIBLE);
                                                        previousMeetingId.setVisibility(View.VISIBLE);
                                                        dateText3ViewId.setVisibility(View.VISIBLE);
                                                        done2ButtonId.setVisibility(View.VISIBLE);

                                                        dateText2ViewId.setText(result2);
                                                        dateText3ViewId.setText(presult);
                                                        meetingNoId.setText(count);
                                                    } else if (count1 == 5) {
                                                        if (month2 == 1 || month2 == 3 || month2 == 5 || month2 == 7 || month2 == 8 || month2 == 10 || month2 == 12) {
                                                            total4 = total3 + dayOfMonth + 20;
                                                        } else {
                                                            total4 = total3 + dayOfMonth + 21;
                                                        }

                                                        day = total4 % 30;
                                                        month1 = total4 / 30;
                                                        months = month1 % 12;
                                                        years = month1 / 12;


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


                                                        int c = count1 + 1;
                                                        String count = String.valueOf(c);
                                                        int j = count1 + 2;
                                                        count7 = String.valueOf(j);

                                                        Mother mother1 = Mother.getInstance();
                                                        mother1.setId1(c);
                                                        mother1.setId2(j);

                                                        String result2 = (day + "/" + months + "/" + years);
                                                        String presult = (pday + "/" + pmonth + "/" + pyear);

                                                        dateTextViewId.setText(result);
                                                        meetingNoId.setVisibility(View.VISIBLE);
                                                        meetingNotextId.setVisibility(View.GONE);
                                                        nextMeetingId.setVisibility(View.GONE);
                                                        dateText2ViewId.setVisibility(View.GONE);
                                                        previousMeetingId.setVisibility(View.VISIBLE);
                                                        dateText3ViewId.setVisibility(View.VISIBLE);
                                                        done2ButtonId.setVisibility(View.VISIBLE);
                                                        done2ButtonId.setEnabled(false);
                                                        okButtonId.setVisibility(View.VISIBLE);

                                                        dateText3ViewId.setText(presult);
                                                        meetingNoId.setText("All check up have been taken");
                                                        //meetingNoId.setText("Meeting No " + count + ":");
                                                    }
                                                } else {
                                                    Toast.makeText(MeetingDoctorActivity.this, "You can only appoint with doctor after completing your last settled schedule.\nPlease check your Doctor Schedule List", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        }, currentYear, currentMonth, currentday);

                                datePickerDialog.show();
                            }
                        });

                        okButtonId.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                final String date1,date2;
                                date1 = dateTextViewId.getText().toString();
                                date2 = String.valueOf(0);

                                if(date1.isEmpty())
                                {
                                    Toast.makeText(MeetingDoctorActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
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
                                    String name4 = mother.getDoctor();
                                    String name5 = mother.getReminder();
                                    String name1 = mother.getName();
                                    final int id7 = 6;
                                    int id8 = 7;

                                    loading = new ProgressDialog(MeetingDoctorActivity.this);
                                    loading.setIcon(R.drawable.load);
                                    loading.setTitle("Doctor Schedule");
                                    loading.setMessage("Please wait...");
                                    loading.show();

                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINE, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            //for track response in Logcat
                                            Log.d("RESPONSE", "" + response);

                                            //if we are getting success from server
                                            if (response.equals("success")) {
                                                //creating a shared preference
                                                loading.dismiss();
                                                //starting profile activity
                                                Intent intent = new Intent(MeetingDoctorActivity.this, MotherHomeActivity.class);
                                                startActivity(intent);

                                                Toast.makeText(MeetingDoctorActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                            } else if (response.equals("exists")) {
                                                Toast.makeText(MeetingDoctorActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                                loading.dismiss();
                                            } else if (response.equals("failure")) {
                                                Toast.makeText(MeetingDoctorActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                                loading.dismiss();
                                            }
                                        }
                                    },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(MeetingDoctorActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                            params.put(Key.KEY_NUMBERS, String.valueOf(2));

                                            Log.d("info",""+getCell+" "+parsedDate);
                                            //returning parameter
                                            return params;

                                        }
                                    };

                                    //Adding the string request to the queue
                                    RequestQueue requestQueue = Volley.newRequestQueue(MeetingDoctorActivity.this);
                                    requestQueue.add(stringRequest);
//
//                            cursor3 = myDatabaseHelper.showAllData5(name4);
//
//                            if(cursor3.getCount()==0)
//                            {
//                                rowId1 = myDatabaseHelper.insertData6(name4,parsedDate,date2,id7);
//                                rowId2 = myDatabaseHelper.insertData5(name5,1,parsedDate,date2,id7,id8,200);
//                            }
//
//                            else
//                            {
//                                rowId1 = myDatabaseHelper.insertData6(name4,parsedDate,date2,id7);
//                                rowId2 = myDatabaseHelper.insertData5(name5,1,parsedDate,date2,id7,id8,100);
//                            }
//
//                            if (rowId1 > 0 && rowId2>0)
//                            {
//                                Toast.makeText(MeetingDoctorActivity.this, "All check up have been taken", Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(MeetingDoctorActivity.this,MotherHomeActivity.class);
//                                startActivity(intent);
//
//                            }
//
//                            else
//                            {
//                                Toast.makeText(MeetingDoctorActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                            }
                                }

//                        Intent intent = new Intent(MeetingDoctorActivity.this,MotherHomeActivity.class);
//                        startActivity(intent);
                            }
                        });

                        done2ButtonId.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                final String date1,date2,ids;

                                date1 = dateTextViewId.getText().toString();
                                date2 = dateText2ViewId.getText().toString();
                                ids = meetingNoId.getText().toString();

                                Log.d("id", "id: "+ids);

                                if(ids.equals("Select"))
                                {
                                    Toast.makeText(MeetingDoctorActivity.this, "Please Select Vacchine No", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    String b = meetingNoId.getText().toString();

                                    //String count = String.valueOf(i);
                                    final int a = Integer.valueOf(b);
                                    int j = a+1;

                                    Log.d("a", "a: "+a);
                                    Log.d("j", "j: "+j);

                                    if(date1.isEmpty())
                                    {
                                        Toast.makeText(MeetingDoctorActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
                                    }

                                    else if(date2.isEmpty())
                                    {
                                        Toast.makeText(MeetingDoctorActivity.this,"Please select current date",Toast.LENGTH_SHORT).show();
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

                                        loading = new ProgressDialog(MeetingDoctorActivity.this);
                                        loading.setIcon(R.drawable.load);
                                        loading.setTitle("Doctor Schedule");
                                        loading.setMessage("Please wait...");
                                        loading.show();

                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.VACCINE, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                //for track response in Logcat
                                                Log.d("RESPONSE", "" + response);

                                                //if we are getting success from server
                                                if (response.equals("success")) {
                                                    //creating a shared preference
                                                    loading.dismiss();
                                                    //starting profile activity
                                                    Intent intent = new Intent(MeetingDoctorActivity.this, MotherHomeActivity.class);
                                                    startActivity(intent);

                                                    Toast.makeText(MeetingDoctorActivity.this, "Inserted Successfull", Toast.LENGTH_SHORT).show();



                                                } else if (response.equals("exists")) {
                                                    Toast.makeText(MeetingDoctorActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                                    loading.dismiss();
                                                } else if (response.equals("failure")) {
                                                    Toast.makeText(MeetingDoctorActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                                                    loading.dismiss();
                                                }
                                            }
                                        },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Toast.makeText(MeetingDoctorActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
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
                                        RequestQueue requestQueue = Volley.newRequestQueue(MeetingDoctorActivity.this);
                                        requestQueue.add(stringRequest);


                                    }
                                }
                            }
                        });


                    }


//                    Log.d("ID", "" + id);
//                    Log.d("NAME", "" + cell);
//
//                    int d = Integer.parseInt(weeks);
//                    int d1 = Integer.parseInt(date);
//
//                    DatePicker datePicker = new DatePicker(MeetingDoctorActivity.this);
//                    int currentday= datePicker.getDayOfMonth();
//                    int currentMonth = (datePicker.getMonth()+1);
//                    int currentYear = datePicker.getYear();
//
//                    int y2,m2,d2;
//
//                    y2= currentYear*12;
//                    m2 = y2+currentMonth;
//                    d2= m2*30;
//                    d2= d2+currentday+1;
//
//                    int d3 = d2-d1;
//
//                    int day = d*7;
//
//                    int day1 = d3+day;
//
//                    int week;
//
//                    week = day1/7;
//                    day1 = day1 % 7;
//
//                    String day2 = String.valueOf(day1);
//                    String week1 = String.valueOf(week);



                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        meetingsNumber = meetingNumber.getSelectedItem().toString();
        meetingNoId.setText(meetingsNumber);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(MeetingDoctorActivity.this,MotherHomeActivity.class);
        startActivity(intent);

    }
}
