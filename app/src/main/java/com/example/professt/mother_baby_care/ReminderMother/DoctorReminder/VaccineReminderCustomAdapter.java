package com.example.professt.mother_baby_care.ReminderMother.DoctorReminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.professt.mother_baby_care.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VaccineReminderCustomAdapter extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<VaccineReminder> objects1;

    String parsedDate1,parsedDate2;
    int id,id1;

    public VaccineReminderCustomAdapter(Context context, ArrayList<VaccineReminder> products) {
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
            view = lInflater.inflate(R.layout.doctor_reminder, parent, false);
        }

        VaccineReminder vaccineReminder = getFriends(position);

        String time = vaccineReminder.getPreviouMeeting();
        String time1 = vaccineReminder.getNextMeeting();

        id = vaccineReminder.getId();
        id1 = vaccineReminder.getId1();


        String id2,id3;

        id2 = String.valueOf(id);
        id3 = String.valueOf(id-1);


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
            ((TextView) view.findViewById(R.id.previous)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.next)).setText("Next Vaccine: ");
            ((TextView) view.findViewById(R.id.previousNoId)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.nextNoId)).setText(id2);
            ((TextView) view.findViewById(R.id.previousDateId)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.nextDateId)).setText(parsedDate2 + "");
        }
        else
        {
            ((TextView) view.findViewById(R.id.previous)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.previous)).setText("Previous Vaccine: ");
            ((TextView) view.findViewById(R.id.next)).setText("Next Vaccine: ");
            ((TextView) view.findViewById(R.id.previousNoId)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.previousNoId)).setText(id3);
            ((TextView) view.findViewById(R.id.nextNoId)).setText(id2);
            ((TextView) view.findViewById(R.id.previousDateId)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.previousDateId)).setText(parsedDate1);
            ((TextView) view.findViewById(R.id.nextDateId)).setText(parsedDate2 + "");
        }



        return view;
    }

    private VaccineReminder getFriends(int position) { return ((VaccineReminder) getItem(position));
    }
}
