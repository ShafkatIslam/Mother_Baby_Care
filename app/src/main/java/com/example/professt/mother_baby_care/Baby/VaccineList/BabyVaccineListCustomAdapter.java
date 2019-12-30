package com.example.professt.mother_baby_care.Baby.VaccineList;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.professt.mother_baby_care.Baby.ChildReminder;
import com.example.professt.mother_baby_care.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BabyVaccineListCustomAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<BabyVaccineList> objects1;

    String parsedDate1,parsedDate2;
    int id,id1;

    public BabyVaccineListCustomAdapter(Context context, ArrayList<BabyVaccineList> products) {
        ctx = context;
        objects1 = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects1.size();
    }

    @Override
    public Object getItem(int position) {
        return objects1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.vaccine_list, parent, false);
        }

        BabyVaccineList babyVaccineList = getFriends(position);

        String time = babyVaccineList.getPreviouMeeting();
        String time1 = babyVaccineList.getNextMeeting();

        id = babyVaccineList.getId();
        id1 = babyVaccineList.getId1();



        try {
            Date initDate = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            Date initDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(time1);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            parsedDate1 = formatter.format(initDate);
            parsedDate2 = formatter1.format(initDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(id1 == 1)
        {

        }
        else if(id1 == 2)
        {
            String id2,id3;

            id2 = String.valueOf(id);
            id3 = String.valueOf(id-1);
            ((TextView) view.findViewById(R.id.vaccineNoId)).setText(id3);
            ((TextView) view.findViewById(R.id.vaccineTakenDateId)).setText(parsedDate1);
        }
        else if(id1 == 3)
        {
            String id2,id3;

            id2 = String.valueOf(id);
            id3 = String.valueOf(id-1);
            ((TextView) view.findViewById(R.id.vaccineNoId)).setText(id2);
            ((TextView) view.findViewById(R.id.vaccineTakenDateId)).setText(parsedDate1);
        }



        return view;
    }

    private BabyVaccineList getFriends(int position) { return ((BabyVaccineList) getItem(position));
    }


}
