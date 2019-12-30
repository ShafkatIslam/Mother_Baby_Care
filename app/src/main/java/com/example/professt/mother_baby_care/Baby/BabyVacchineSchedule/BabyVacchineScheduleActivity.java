package com.example.professt.mother_baby_care.Baby.BabyVacchineSchedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.professt.mother_baby_care.R;

import java.util.ArrayList;

public class BabyVacchineScheduleActivity extends AppCompatActivity {

    public ListView babyVachineListView;
    BabyVacchineCustomAdapter babyVacchineCustomAdapter;
    ArrayList<BabyVacchine> babyVacchines = new ArrayList<BabyVacchine>();
    String[] f1 = {"BCG","Polio","Hepatitis B(Stage-1)"};
    String[] f2 = {"DPT(Stage-1)","Oral Polio(Stage-1)","Hepatitis B(Stage-2)","Rotarix(Stage-1)","Hib(Stage-1)"};
    String[] f3 = {"DPT(Stage-2)","Oral Polio(Stage-2)","Hib(Stage-2)","Rotarix(Stage-2)"};
    String[] f4 = {"DPT(Stage-3)","Oral Polio(Stage-3)","Hib(Stage-3)"};
    String[] f5 = {"Hepatitis B(Stage-3)","Influenza(Stage-1)"};
    String[] f6 = {"Influenza(Stage-2)"};
    String[] f7 = {"Oral Polio(Stage-4)","M-R"};
    String[] f8 = {"Hepatitis A(Stage-1)","Chickenpox(Stage-1)"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_vacchine_schedule);

        babyVachineListView = (ListView)findViewById(R.id.babyVachineListView);
        babyVacchineCustomAdapter = new BabyVacchineCustomAdapter(this,babyVacchines);
        babyVachineListView.setAdapter(babyVacchineCustomAdapter);

        Baby baby = Baby.getInstance();
        String date;
        date = baby.getDate();
        String fDate = String.valueOf(date);

        String[] items1 = fDate.split("-");
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


        DatePicker datePicker = new DatePicker(BabyVacchineScheduleActivity.this);
        int currentday= datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth()+1);
        int currentYear = datePicker.getYear();

        int y2,m2,d2;

        y2= currentYear*12;
        m2 = y2+currentMonth;
        d2= m2*30;
        d2= d2+currentday+1;

        int result,dOb,dOm,dOy;
        String dOb1,dOm1,dOy1;

        Log.d("BDate", "onCreate: "+d1);
        Log.d("CDate", "onCreate: "+d2);

        result = d2-d1;
        int i;

        if(result>0 && result<41)
        {
            for(i=0;i<f1.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f1[i],"Before 6 weeks of Birth"));
            }

        }

        else if(result>41 && result<69)
        {
            for(i=0;i<f2.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f2[i],"Between 6 to 10 weeks of Birth"));
            }

        }

        else if(result>69 && result<97)
        {
            for(i=0;i<f3.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f3[i],"Between 10 to 14 weeks of Birth"));
            }

        }

        else if(result>97 && result<179)
        {
            for(i=0;i<f4.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f4[i],"Between 14 weeks to 6 months of Birth"));
            }

        }

        else if(result>179 && result<219)
        {
            for(i=0;i<f5.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f5[i],"Between 6 months to 7 months of Birth"));
            }

        }

        else if(result>179 && result<219)
        {
            for(i=0;i<f5.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f5[i],"Between 6 months to 7 months of Birth"));
            }

        }

        else if(result>219 && result<269)
        {
            for(i=0;i<f6.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f6[i],"Between 7 months to 9 months of Birth"));
            }

        }

        else if(result>269 && result<364)
        {
            for(i=0;i<f7.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f7[i],"Between 9 months to 1 year of Birth"));
            }

        }

        else if(result>364 && result<380)
        {
            for(i=0;i<f8.length;i++)
            {
                babyVacchines.add(new BabyVacchine(f8[i],"Between 1 year to 1 year 2 months of Birth"));
            }

        }

        else
        {
            babyVacchines.add(new BabyVacchine("All Vacchines have been taken","Now your baby is safe"));
        }

    }
}
