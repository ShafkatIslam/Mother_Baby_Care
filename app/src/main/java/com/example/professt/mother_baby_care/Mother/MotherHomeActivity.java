package com.example.professt.mother_baby_care.Mother;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.professt.mother_baby_care.Key;
import com.example.professt.mother_baby_care.MainActivity;
import com.example.professt.mother_baby_care.MainRegisterActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorFindActivity;
import com.example.professt.mother_baby_care.Mother.Nearest_Hospital.ListHealthCenters;
import com.example.professt.mother_baby_care.Mother.Vaccination.MotherVacchineScheduleActivity;
import com.example.professt.mother_baby_care.Mother.Vaccination.TipsActivity;
import com.example.professt.mother_baby_care.Mother.Vaccine.ForgetVaccineActivity;
import com.example.professt.mother_baby_care.Mother.Vaccine.VacchineScheduleActivity;
import com.example.professt.mother_baby_care.Mother.VaccineeList.MotherVaccineListActivity;
import com.example.professt.mother_baby_care.R;
import com.example.professt.mother_baby_care.RegisterActivity;
import com.example.professt.mother_baby_care.ReminderMother.DoctorReminder.ReminderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MotherHomeActivity extends AppCompatActivity {

    Button vaccination,forgetVaccine,vaccineList,doctorsFind,healthCenter,meetDoctor,vaccineSchedule,reminder;
    TextView mcurrentAgeTextView,mId,mName;
    String getCell;

    private ProgressDialog loading;
    public static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_home);

        mId = (TextView)findViewById(R.id.mId);
        mcurrentAgeTextView = (TextView)findViewById(R.id.mcurrentAgeTextView);
        mName = (TextView)findViewById(R.id.mothersName);

        vaccination = (Button)findViewById(R.id.vaccinationId);
        forgetVaccine = (Button)findViewById(R.id.forgetVaccine);
        vaccineList = (Button)findViewById(R.id.vaccineList);
        doctorsFind = (Button)findViewById(R.id.doctorsFindId);
        healthCenter = (Button)findViewById(R.id.healthCenterId);
        meetDoctor = (Button)findViewById(R.id.meetDoctorId);
        vaccineSchedule = (Button)findViewById(R.id.vaccineScheduleId);
        reminder = (Button)findViewById(R.id.reminderId);

        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Key.CELL_SHARED_PREF, "Not Available");

        Log.d("SP_CELL",getCell);

        getData();

        vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MotherHomeActivity.this,TipsActivity.class);
                startActivity(intent);
            }
        });

        vaccineList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MotherHomeActivity.this,MotherVaccineListActivity.class);
                startActivity(intent);
            }
        });

        meetDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MotherHomeActivity.this,MeetingDoctorActivity.class);
                startActivity(intent);
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MotherHomeActivity.this,ReminderActivity.class);
                startActivity(intent);
            }
        });

        doctorsFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MotherHomeActivity.this,DoctorFindActivity.class);
                startActivity(intent);
            }
        });

        vaccineSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MotherHomeActivity.this,VacchineScheduleActivity.class);
                startActivity(intent);
            }
        });

        forgetVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MotherHomeActivity.this,ForgetVaccineActivity.class);
                startActivity(intent);
            }
        });

        healthCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable())
                {
                    loading("Scanning Location...");
                    Intent intent =  new Intent(MotherHomeActivity.this,ListHealthCenters.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MotherHomeActivity.this, "No internet connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getData() {

        //for showing progress dialog
        loading = new ProgressDialog(MotherHomeActivity.this);
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
                        Toast.makeText(MotherHomeActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(MotherHomeActivity.this);
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
                Toast.makeText(MotherHomeActivity.this, "No Data Available!", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(MotherHomeActivity.this, MotherHomeActivity.class);
//
//                startActivity(intent);
//                //imgNoData.setImageResource(R.drawable.nodata);
            }

            else {

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

                    NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify= null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        notify = new Notification.Builder
                                (getApplicationContext()).setContentTitle("Welcome").setContentText("Welcome to Mother_Baby_Care").
                                setContentTitle("Hello "+name).setSmallIcon(R.drawable.alarm).build();
                    }

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);

                    String URL = Key.DOCTOR_URL+getCell;

                    StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            showJSON1(response);
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    loading.dismiss();
                                    Toast.makeText(MotherHomeActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                                }
                            });

                    RequestQueue requestQueue = Volley.newRequestQueue(MotherHomeActivity.this);
                    requestQueue.add(stringRequest);

                    String URL1 = Key.VACCINATION_CHECK+getCell;

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
                                    Toast.makeText(MotherHomeActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                                }
                            });

                    RequestQueue requestQueue1 = Volley.newRequestQueue(MotherHomeActivity.this);
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
                    mId.setText("ID: M0"+id);
                    mName.setText(name);

                    int d = Integer.parseInt(weeks);
                    int d1 = Integer.parseInt(date);

                    DatePicker datePicker = new DatePicker(MotherHomeActivity.this);
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

                    int week;

                    week = day1/7;
                    day1 = day1 % 7;

                    String day2 = String.valueOf(day1);
                    String week1 = String.valueOf(week);

                    if(day1==0)
                    {
                        if(week == 1)
                        {
                            mcurrentAgeTextView.setText(week1+ "week");
                        }
                        else
                        {
                            mcurrentAgeTextView.setText(week1+ "weeks");
                        }

                    }
                    else
                    {
                        if(week == 1)
                        {
                            if(day1 == 1)
                            {
                                mcurrentAgeTextView.setText(week1+ "week "+day2+" day");
                            }
                            else
                            {
                                mcurrentAgeTextView.setText(week1+ "week "+day2+" days");
                            }

                        }
                        else
                        {
                            if(day1 == 1)
                            {
                                mcurrentAgeTextView.setText(week1+ "weeks "+day2+" day");
                            }
                            else
                            {
                                mcurrentAgeTextView.setText(week1+ "weeks "+day2+" days");
                            }
                        }
                    }

                    if(week>=1 && week<=2)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 1st & 2nd weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("This first week is actually your menstrual period. Because your expected birth date (EDD or EDB) is calculated from the first day of your last period, this week counts as part of your 40-week pregnancy, even though your baby hasn’t been conceived yet.")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                        else if(week==3)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 3rd weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("Thirty hours after conception, the cell splits into two. Three days later, the cell (zygote) has divided into 16 cells. After two more days, the zygote has migrated from the fallopian tube to the uterus (womb). Seven days after conception, the zygote burrows itself into the plump uterine lining (endometrium). The zygote is now known as a blastocyst.")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                        else if(week>=4 && week<=5)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 4th & 5th weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("The developing baby is tinier than a grain of rice. The rapidly dividing cells are in the process of forming the various body systems, including the digestive system.The evolving neural tube will eventually become the central nervous system (brain and spinal cord).")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                        else if(week>=6 && week<=10)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 6th to 10th weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("The baby is now known as an embryo. It is around 3 mm in length. By this stage, it is secreting special hormones that prevent the mother from having a menstrual period.The heart is beating. The embryo has developed its placenta and amniotic sac. The placenta is burrowing into the uterine wall to access oxygen and nutrients from the mother’s bloodstream.")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                        else if(week>=11 && week<=16)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 11th to 16th weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("The eyelids are fused over the fully developed eyes. The baby can now mutely cry, since it has vocal cords. It may even start sucking its thumb. The fingers and toes are growing nails. The fetus is around 14 cm in length. Eyelashes and eyebrows have appeared, and the tongue has tastebuds. The second trimester maternal serum screening will be offered at this time if the first trimester test was not done ")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                        else if(week>=17 && week<=24)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 17th to 24th weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("The fetus is around 21 cm in length. The ears are fully functioning and can hear muffled sounds from the outside world. The fingertips have prints. The genitals can now be distinguished with an ultrasound scan.The fetus is around 33 cm in length. The fused eyelids now separate into upper and lower lids, enabling the baby to open and shut its eyes. The skin is covered in fine hair (lanugo) and protected by a layer of waxy secretion (vernix). The baby makes breathing movements with its lungs.")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                        else if(week>=25 && week<=32)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 25th to 32th weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("Your baby now weighs about 1 kg (1,000 g) or 2 lb 2oz (two pounds, two ounces) and measures about 25 cm (10 inches) from crown to rump. The crown-to-toe length is around 37 cm. The growing body has caught up with the large head and the baby now seems more in proportion.The baby spends most of its time asleep. Its movements are strong and coordinated. It has probably assumed the ‘head down’ position by now, in preparation for birth.")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                         else if(week>=33 && week<=36)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 33th to 36th weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("The baby is around 46 cm in length. It has probably nestled its head into its mother’s pelvis, ready for birth. If it is born now, its chances for survival are excellent. Development of the lungs is rapid over the next few weeks.")).build();
                        }

                        notify3.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif3.notify(3, notify3);
                        }

                         else if(week>=37 && week<=40)
                        {
                            NotificationManager notif3=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notify3= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify3 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").
                                    setContentTitle("Tips for 37th to 40th weeks pregnant Mother").setSmallIcon(R.drawable.alarm).setStyle(new Notification.BigTextStyle().bigText("The baby is around 51 cm in length and ready to be born. It is unknown exactly what causes the onset of labour. It is most likely a combination of physical, hormonal and emotional factors between the mother and baby.")).build();
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

    private void showJSON1(String response) {

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
                reminder.setBackgroundResource(R.drawable.capture);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String cell = jo.getString(Key.KEY_MUCELL);
                    String ppdate = jo.getString(Key.KEY_MPDATE);
                    String ndate = jo.getString(Key.KEY_MNDATE);
                    String number = jo.getString(Key.KEY_NUMBER);
                    String numbers = jo.getString(Key.KEY_NUMBERS);

                    Reminder reminder = Reminder.getInstance();
                    reminder.setDate(ndate);

                    String[] pparts = ndate.split("-");
                    String pyear = pparts[0]; // 004
                    String pmonth = pparts[1]; // 034556
                    String pday = pparts[2]; // 034556

                    String date = (pday + "/" + pmonth + "/" + pyear);
                    if(date.equalsIgnoreCase("00/00/0000"))
                    {
                        NotificationManager notif1=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify1= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify1 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").setContentText("Your Doctor Schedule has been completed").
                                    setContentTitle("Doctor Schedule: ").setSmallIcon(R.drawable.alarm).build();
                    }

                        notify1.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif1.notify(1, notify1);
                    }

                    else
                    {
                        NotificationManager notif1=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify1= null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            notify1 = new Notification.Builder
                                    (getApplicationContext()).setContentTitle("Welcome").setContentText("Your Next Doctor Schedule is "+date).
                                    setContentTitle("Doctor Schedule: ").setSmallIcon(R.drawable.alarm).build();
                    }

                        notify1.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif1.notify(1, notify1);
                    }


                }
            }
        }
        catch (JSONException e) {
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
                reminder.setBackgroundResource(R.drawable.capture);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Key.KEY_ID);
                    String cell = jo.getString(Key.KEY_MUCELL);
                    String ppdate = jo.getString(Key.KEY_MPDATE);
                    String ndate = jo.getString(Key.KEY_MNDATE);
                    String number = jo.getString(Key.KEY_NUMBER);
                    String numbers = jo.getString(Key.KEY_NUMBERS);

                    Log.d("Next", "Next: "+ndate);

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

        Intent intent =  new Intent(MotherHomeActivity.this,MainActivity.class);
        startActivity(intent);

    }
}
