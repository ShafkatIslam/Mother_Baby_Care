package com.example.professt.mother_baby_care.Baby.BabyVacchineSchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.professt.mother_baby_care.R;

import java.util.ArrayList;

public class BabyVacchineCustomAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<BabyVacchine> objects1;

    public BabyVacchineCustomAdapter(Context context, ArrayList<BabyVacchine> products) {
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
            view = lInflater.inflate(R.layout.baby_vacchine, parent, false);
        }

        BabyVacchine babyVacchine = getFriends(position);

        String vName = babyVacchine.getVacchine_name();
        String duration = babyVacchine.getVacchine_duration();


            ((TextView) view.findViewById(R.id.vacchineNameId)).setText(vName);
            ((TextView) view.findViewById(R.id.vacchineDurationId)).setText(duration);

        return view;
    }

    private BabyVacchine getFriends(int position) {
        return ((BabyVacchine) getItem(position));
    }

}
