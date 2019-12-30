package com.example.professt.mother_baby_care.Baby;

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
import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.R;
import com.example.professt.mother_baby_care.ReminderMother.DoctorReminder.DoctorReminder;
import com.example.professt.mother_baby_care.ReminderMother.DoctorReminder.DoctorReminderCustomAdapter;
import com.example.professt.mother_baby_care.ReminderMother.DoctorReminder.ReminderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChildReminderActivity extends AppCompatActivity {

    public ListView ChildReminderlistView;
    ChildReminderCustomAdapter childReminderCustomAdapter;
    ArrayList<ChildReminder> childReminders = new ArrayList<ChildReminder>();

    String getCell;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_reminder);

        ChildReminderlistView = (ListView)findViewById(R.id.ChildReminderlistView);

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");

        getData();

        childReminderCustomAdapter = new ChildReminderCustomAdapter(this,childReminders);
        ChildReminderlistView.setAdapter(childReminderCustomAdapter);

    }

    private void getData() {

        //for showing progress dialog
        loading = new ProgressDialog(ChildReminderActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Key.BABY_REMINDER+getCell;

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

                        Intent intent = new Intent(ChildReminderActivity.this,BabyHomeActivity.class);
                        startActivity(intent);
                        loading.dismiss();
                        Toast.makeText(ChildReminderActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ChildReminderActivity.this);
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

                    childReminders.add(new ChildReminder(n1,n2,ppdate,ndate));

                }

                childReminderCustomAdapter = new ChildReminderCustomAdapter(this,childReminders);
                ChildReminderlistView.setAdapter(childReminderCustomAdapter);
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

        Intent intent =  new Intent(ChildReminderActivity.this,BabyHomeActivity.class);
        startActivity(intent);

    }
}
