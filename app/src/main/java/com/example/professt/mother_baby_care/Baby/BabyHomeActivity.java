package com.example.professt.mother_baby_care.Baby;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.professt.mother_baby_care.Baby.BabyVacchineSchedule.Baby;
import com.example.professt.mother_baby_care.Baby.BabyVacchineSchedule.BabyVacchineScheduleActivity;
import com.example.professt.mother_baby_care.Baby.DoctorsFind.DoctorsFindActivity;
import com.example.professt.mother_baby_care.Baby.NearestHealth.ListHealthCenter;
import com.example.professt.mother_baby_care.Baby.Vaccine.BabyForgetVaccineActivity;
import com.example.professt.mother_baby_care.Baby.Vaccine.BabyVaccineScheduleActivity;
import com.example.professt.mother_baby_care.Baby.VaccineList.BabyVaccineListActivity;
import com.example.professt.mother_baby_care.Key;
import com.example.professt.mother_baby_care.MainActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorFindActivity;
import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.example.professt.mother_baby_care.Mother.Nearest_Hospital.ListHealthCenters;
import com.example.professt.mother_baby_care.Mother.Reminder;
import com.example.professt.mother_baby_care.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BabyHomeActivity extends AppCompatActivity {

    Button childVaccinationId,forgetVaccine,vaccineList,childDoctorsFindId,vaccinationCenterId,remindersetId,childVaccineScheduleId,childReminderId;
    TextView currentAgeTextView,bId,babyProfilename;
    String getCell;

    private ProgressDialog loading;
    public static ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_home);

        childVaccinationId = (Button)findViewById(R.id.childVaccinationId);
        forgetVaccine = (Button)findViewById(R.id.forgetVaccine);
        vaccineList = (Button)findViewById(R.id.vaccineList);
        childDoctorsFindId = (Button)findViewById(R.id.childDoctorsFindId);
        vaccinationCenterId = (Button)findViewById(R.id.vaccinationCenterId);
        remindersetId = (Button)findViewById(R.id.remindersetId);
        childVaccineScheduleId = (Button)findViewById(R.id.childVaccineScheduleId);
        childReminderId = (Button)findViewById(R.id.childReminderId);

        currentAgeTextView = (TextView)findViewById(R.id.currentAgeTextView);
        bId = (TextView)findViewById(R.id.bId);
        babyProfilename = (TextView)findViewById(R.id.babyProfilename);

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");

        Log.d("SP_CELL",getCell);

        getData();

        forgetVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(BabyHomeActivity.this,BabyForgetVaccineActivity.class);
                startActivity(intent);
            }
        });

        vaccineList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(BabyHomeActivity.this,BabyVaccineListActivity.class);
                startActivity(intent);
            }
        });

        childDoctorsFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(BabyHomeActivity.this,DoctorsFindActivity.class);
                startActivity(intent);
            }
        });

        vaccinationCenterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable())
                {
                    loading("Scanning Location...");
                    Intent intent =  new Intent(BabyHomeActivity.this,ListHealthCenter.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(BabyHomeActivity.this, "No internet connection", Toast.LENGTH_LONG).show();

            }
        });

        childReminderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(BabyHomeActivity.this,ChildReminderActivity.class);
                startActivity(intent);
            }
        });

        childVaccineScheduleId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(BabyHomeActivity.this,BabyVaccineScheduleActivity.class);
                startActivity(intent);
            }
        });

        remindersetId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(BabyHomeActivity.this,ChildTipsShowActivity.class);
                startActivity(intent);

            }
        });

        childVaccinationId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(BabyHomeActivity.this,BabyVacchineScheduleActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getData() {

        //for showing progress dialog
        loading = new ProgressDialog(BabyHomeActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Key.BABY_HOME_VIEW_URL+getCell;


        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Log.d("responses",response);
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toast.makeText(BabyHomeActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(BabyHomeActivity.this);
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
                Toast.makeText(BabyHomeActivity.this, "No Data Available!", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(MotherHomeActivity.this, MotherHomeActivity.class);
//
//                startActivity(intent);
//                //imgNoData.setImageResource(R.drawable.nodata);
            }

            else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_BID);
                    String name = jo.getString(Key.KEY_BNAME);
                    String email = jo.getString(Key.KEY_BMEMAIL);
                    String cell = jo.getString(Key.KEY_BFCELL);
                    String user = jo.getString(Key.KEY_BUSER);
                    String bday = jo.getString(Key.KEY_BDAY);
                    String gender = jo.getString(Key.KEY_BGENDER);

                    NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify= null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        notify = new Notification.Builder
                                (getApplicationContext()).setContentTitle("Welcome").setContentText("Welcome to Mother_Baby_Care").
                                setContentTitle("Hello "+name).setSmallIcon(R.drawable.alarm).build();
                    }

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);

                    String URL1 = Key.BABY_VACCINATION_CHECK+getCell;

                    StringRequest stringRequest1 = new StringRequest(URL1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            showJSON2(response);
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    loading.dismiss();
                                    Toast.makeText(BabyHomeActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                                }
                            });

                    RequestQueue requestQueue1 = Volley.newRequestQueue(BabyHomeActivity.this);
                    requestQueue1.add(stringRequest1);

                    Reminder reminder = Reminder.getInstance();
                    String doctorRemind;
                    doctorRemind = reminder.getDate();

//                    NotificationManager notif1=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                    Notification notify1= null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                        notify1 = new Notification.Builder
//                                (getApplicationContext()).setContentTitle("Welcome").setContentText("Your Next Doctor Schedule is "+doctorRemind).
//                                setContentTitle("Doctor Schedule: ").setSmallIcon(R.drawable.alarm).build();
//                    }
//
//                    notify1.flags |= Notification.FLAG_AUTO_CANCEL;
//                    notif1.notify(1, notify1);

                    Log.d("ID", "" + id);
                    Log.d("NAME", "" + name);
                    bId.setText("ID: B0"+id);
                    babyProfilename.setText(name);

                    Baby baby = Baby.getInstance();
                    baby.setDate(bday);

                    String[] items1 = bday.split("-");
                    String date1=items1[2];
                    String month=items1[1];
                    String year=items1[0];

                    int d = Integer.parseInt(date1);
                    int m = Integer.parseInt(month);
                    int y = Integer.parseInt(year);

                    int y1,m1,d1;

                    y1= y*12;
                    m1 = y1+m;
                    d1= m1*30;
                    d1= d1+d;


                    DatePicker datePicker = new DatePicker(BabyHomeActivity.this);
                    int currentday= datePicker.getDayOfMonth();
                    int currentMonth = (datePicker.getMonth()+1);
                    int currentYear = datePicker.getYear();

                    int y2,m2,d2;

                    y2= currentYear*12;
                    m2 = y2+currentMonth;
                    d2= m2*30;
                    d2= d2+currentday+1;

                    int result1,dOb,dOm,dOy;
                    String dOb1,dOm1,dOy1;

                    Log.d("BDate", "onCreate: "+d1);
                    Log.d("CDate", "onCreate: "+d2);

                    result1 = d2-d1;

                    dOb = result1 % 30;
                    dOm = result1 /30;
                    dOm = dOm % 12;
                    dOy = dOm / 12;

                    dOb1 = String.valueOf(dOb);
                    dOm1 = String.valueOf(dOm);
                    dOy1 = String.valueOf(dOy);

                    currentAgeTextView.setText(dOy1+ " years "+dOm1+" months "+dOb1+" days");


                    if(result1>=1 && result1<=120)
                    {
                        NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify3= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 1 month to 4 months Baby").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("You need to feed your baby Mammary Glands")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                    }

                    else if (result1>=121 && result1<=180)
                    {
                        NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify3= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 4 to 6 months Baby").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("You need to feed your baby Mammary Glands & also liquid food")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                    }
                    else if (d1>=181 && d1<240)
                    {
                        NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify3= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 7 to 8 months Baby").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("You need to feed your baby Mammary Glands,liquid food & vegetables")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                    }
                    else if (result1>241 && result1<300)
                    {
                        NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify3= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).
                                    setContentTitle("Tips for 9 to 10 months Baby").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("You need to feed your baby Mammary Glands,liquid food,fruits,vegetables,meat & grains")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                    }
                    else if (result1>301 && result1<330)
                    {
                        NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify3= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 11 months Baby").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("You need to feed your baby Mammary Glands,liquid food,fruits,vegetables,meat,dairy & grains")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                    }
                    else
                    {
                        NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify3= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 1 year aged Baby").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("You need to feed your baby Mammary Glands,liquid food,fruits,vegetables,meat,dairy,grains & all kind of normal foods")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);

                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showJSON2(String response) {

        //Create json object for receiving jason data
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Key.JSON_ARRAY);

            if (result.length()==0)
            {

            }
            else
            {
                childReminderId.setBackgroundResource(R.drawable.capture);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String cell = jo.getString(Key.KEY_MUCELL);
                    String ppdate = jo.getString(Key.KEY_MPDATE);
                    String ndate = jo.getString(Key.KEY_MNDATE);
                    String number = jo.getString(Key.KEY_NUMBER);
                    String numbers = jo.getString(Key.KEY_NUMBERS);

                    Reminder reminder = Reminder.getInstance();
                    reminder.setDate1(ndate);

                    String[] pparts = ndate.split("-");
                    String pyear = pparts[0]; // 004
                    String pmonth = pparts[1]; // 034556
                    String pday = pparts[2]; // 034556

                    String date = (pday + "/" + pmonth + "/" + pyear);
                    if(date.equalsIgnoreCase("00/00/0000"))
                    {
                        NotificationManager notif2=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify2= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify2 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").setContentText("Your Vaccine Schedule has been completed").
                                    setContentTitle("Vaccine Schedule: ").setSmallIcon(R.drawable.alarm).build();
                        }

                        notify2.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif2.notify(2, notify2);
                    }

                    else
                    {
                        NotificationManager notif2=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify2= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify2 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").setContentText("Your Next Vaccine Schedule is "+date).
                                    setContentTitle("Vaccine Schedule: ").setSmallIcon(R.drawable.alarm).build();
                        }

                        notify2.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif2.notify(2, notify2);
                    }


                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    void loading(String message){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(BabyHomeActivity.this,MainActivity.class);
        startActivity(intent);

    }
}
