package com.example.professt.mother_baby_care.Mother.Vaccination;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MotherVacchineScheduleActivity extends AppCompatActivity {

    public ListView motherVachineListView;
    MotherVacchineCustomAdapter motherVacchineCustomAdapter;
    ArrayList<MotherVacchine> motherVacchines = new ArrayList<MotherVacchine>();
    String getCell;

    private ProgressDialog loading;

    String[] f1 = {"BCG","Tetanus","Hepatitis B(Stage-1)","Meningococcal B"};
    String[] f2 = {"DPT(Stage-1)","Whooping Cough(Stage-1)","Hepatitis B(Stage-2)","Rotarix(Stage-1)","Hib(Stage-1)"};
    String[] f3 = {"DPT(Stage-2)","Whooping Cough(Stage-2)","Hib(Stage-2)","Rotarix(Stage-2)"};
    String[] f4 = {"DPT(Stage-3)","Whooping Cough(Stage-3)","Hib(Stage-3)"};
    String[] f5 = {"Hepatitis B(Stage-3)","Influenza(Stage-1)","Pneumococcal"};
    String[] f6 = {"Influenza(Stage-2)","HPV"};
    String[] f7 = {"Whooping Cough(Stage-4)","MMR","Chickenpox(Stage-1)"};
    String[] f8 = {"Hepatitis A(Stage-1)","Chickenpox(Stage-2)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_vacchine_schedule);

        motherVachineListView = (ListView)findViewById(R.id.motherVachineListView);

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");

        Log.d("SP_CELL",getCell);

        getData();

    }

    private void getData() {

        //for showing progress dialog
        loading = new ProgressDialog(MotherVacchineScheduleActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Key.MOTHER_HOME_VIEW_URL+getCell;


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

                        loading.dismiss();
                        Toast.makeText(MotherVacchineScheduleActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(MotherVacchineScheduleActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {

        //Create json object for receiving jason data
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Key.JSON_ARRAY);


            if (result.length() == 0) {
                Toast.makeText(MotherVacchineScheduleActivity.this, "No Data Available!", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(MotherHomeActivity.this, MotherHomeActivity.class);
//
//                startActivity(intent);
//                //imgNoData.setImageResource(R.drawable.nodata);
            } else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String name = jo.getString(Key.KEY_MNAME);
                    String email = jo.getString(Key.KEY_MEMAIL);
                    String cell = jo.getString(Key.KEY_MCELL);
                    String user = jo.getString(Key.KEY_MUSER);
                    String blood = jo.getString(Key.KEY_BLOOD);
                    String weeks = jo.getString(Key.KEY_WEEK);
                    String date = jo.getString(Key.KEY_DATE);

                    int d = Integer.parseInt(weeks);
                    int d1 = Integer.parseInt(date);

                    Log.d("Week", String.valueOf(d));
                    Log.d("day", String.valueOf(d1));

                    DatePicker datePicker = new DatePicker(MotherVacchineScheduleActivity.this);
                    int currentday= datePicker.getDayOfMonth();
                    int currentMonth = (datePicker.getMonth()+1);
                    int currentYear = datePicker.getYear();

                    int y2,m2,d2;

                    y2= currentYear*12;
                    m2 = y2+currentMonth;
                    d2= m2*30;
                    d2= d2+currentday+1;

                    int d3 = d2-d1;

                    int day = d*7;

                    int day1 = d3+day;

                    Log.d("sWeek", String.valueOf(d3));
                    Log.d("wday", String.valueOf(day1));
                    Log.d("tday", String.valueOf(day));

                    int result1;

                    result1 = day1/7;
                    int j;

                    if(result1>0 && result1<6)
                    {
                        for(j=0;j<f1.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f1[j],"Before 6 weeks of Pregnancy"));
                        }

                    }

                    else if(result1>6 && result1<10)
                    {
                        for(j=0;j<f2.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f2[j],"Between 6 to 10 weeks of Pregnancy"));
                        }

                    }

                    else if(result1>10 && result1<14)
                    {
                        for(j=0;j<f3.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f3[j],"Between 10 to 14 weeks of Pregnancy"));
                        }

                    }

                    else if(result1>14 && result1<24)
                    {
                        for(j=0;j<f4.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f4[j],"Between 14 weeks to 6 months of Pregnancy"));
                        }

                    }

                    else if(result1>24 && result1<28)
                    {
                        for(j=0;j<f5.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f5[j],"Between 6 months to 7 months of Pregnancy"));
                        }

                    }

                    else if(result1>28 && result1<32)
                    {
                        for(j=0;j<f5.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f5[j],"Between 7 months to 8 months of Pregnancy"));
                        }

                    }

                    else if(result1>32 && result1<36)
                    {
                        for(j=0;j<f6.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f6[j],"Between 8 months to 9 months of Pregnancy"));
                        }

                    }

                    else if(result1>36 && result1<48)
                    {
                        for(j=0;j<f7.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f7[j],"Between 9 months to 1 year of Pregnancy"));
                        }

                    }

                    else if(result1>48 && result1<72)
                    {
                        for(j=0;j<f8.length;j++)
                        {
                            motherVacchines.add(new MotherVacchine(f8[j],"Between 1 year to 1 year 6 months of Pregnancy"));
                        }

                    }

                    else
                    {
                        motherVacchines.add(new MotherVacchine("All Vacchines have been taken","Now you are safe"));
                    }
                }
            }

            motherVacchineCustomAdapter = new MotherVacchineCustomAdapter(this,motherVacchines);
            motherVachineListView.setAdapter(motherVacchineCustomAdapter);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
