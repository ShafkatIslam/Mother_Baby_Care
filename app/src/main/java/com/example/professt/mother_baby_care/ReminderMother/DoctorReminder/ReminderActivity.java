package com.example.professt.mother_baby_care.ReminderMother.DoctorReminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.professt.mother_baby_care.Key;
import com.example.professt.mother_baby_care.Mother.MeetingDoctorActivity;
import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.Mother.Reminder;
import com.example.professt.mother_baby_care.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {

    public ListView DoctorReminderlistView;
    public ListView VaccineReminderlistView;
    DoctorReminderCustomAdapter doctorReminderCustomAdapter;
    VaccineReminderCustomAdapter vaccineReminderCustomAdapter;
    ArrayList<DoctorReminder> doctorReminders = new ArrayList<DoctorReminder>();
    ArrayList<VaccineReminder> vaccineReminders = new ArrayList<VaccineReminder>();

    String getCell;

    private ProgressDialog loading;
    private ProgressDialog loading1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        DoctorReminderlistView = (ListView)findViewById(R.id.DoctorReminderlistView);
        VaccineReminderlistView = (ListView)findViewById(R.id.VaccineReminderlistView);

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");

        getData();
        getData1();

    }

    private void getData() {

        //for showing progress dialog
        loading = new ProgressDialog(ReminderActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Key.REMINDER+getCell;

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

                        Intent intent = new Intent(ReminderActivity.this,MotherHomeActivity.class);
                        startActivity(intent);
                        loading.dismiss();
                        Toast.makeText(ReminderActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ReminderActivity.this);
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
                showData("Sorry","No Meeting Schedule Found");
            }
            else
            {

                for (int i = 0; i < result.length(); i++) {

                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String cell = jo.getString(Key.KEY_MUCELL);
                    String ppdate = jo.getString(Key.KEY_MPDATE);
                    String ndate = jo.getString(Key.KEY_MNDATE);
                    String number = jo.getString(Key.KEY_NUMBER);
                    String numbers = jo.getString(Key.KEY_NUMBERS);

                    int n1 = Integer.parseInt(number);
                    int n2 = Integer.parseInt(numbers);

                    Log.d("data",""+n1+" "+n2);

                    doctorReminders.add(new DoctorReminder(n1,n2,ppdate,ndate));

                }

                doctorReminderCustomAdapter = new DoctorReminderCustomAdapter(this,doctorReminders);
                DoctorReminderlistView.setAdapter(doctorReminderCustomAdapter);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getData1() {

        //for showing progress dialog
        loading1 = new ProgressDialog(ReminderActivity.this);
        loading1.setIcon(R.drawable.wait_icon);
        loading1.setTitle("Loading");
        loading1.setMessage("Please wait....");
        loading1.show();

        String URL1 = Key.REMINDERS+getCell;

        StringRequest stringRequest1 = new StringRequest(URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading1.dismiss();
                showJSON1(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Intent intent = new Intent(ReminderActivity.this,MotherHomeActivity.class);
                        startActivity(intent);
                        loading1.dismiss();
                        Toast.makeText(ReminderActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue1 = Volley.newRequestQueue(ReminderActivity.this);
        requestQueue1.add(stringRequest1);

    }

    private void showJSON1(String response) {

        //Create json object for receiving jason data
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Key.JSON_ARRAY);

            if (result.length()==0)
            {
                showData("Sorry","No Vaccine Schedule Found");
            }
            else
            {

                for (int i = 0; i < result.length(); i++) {

                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String cell = jo.getString(Key.KEY_MUCELL);
                    String ppdate = jo.getString(Key.KEY_MPDATE);
                    String ndate = jo.getString(Key.KEY_MNDATE);
                    String number = jo.getString(Key.KEY_NUMBER);
                    String numbers = jo.getString(Key.KEY_NUMBERS);

                    int n1 = Integer.parseInt(number);
                    int n2 = Integer.parseInt(numbers);

                    Log.d("data",""+n1+" "+n2);

                    vaccineReminders.add(new VaccineReminder(n1,n2,ppdate,ndate));

                }

                vaccineReminderCustomAdapter = new VaccineReminderCustomAdapter(this,vaccineReminders);
                VaccineReminderlistView.setAdapter(vaccineReminderCustomAdapter);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showData(String title, String message)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(ReminderActivity.this,MotherHomeActivity.class);
        startActivity(intent);

    }
}
