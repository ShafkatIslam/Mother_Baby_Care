package com.example.professt.mother_baby_care.Mother.Vaccination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.professt.mother_baby_care.R;

import java.util.ArrayList;

public class MotherVacchineCustomAdapter extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<MotherVacchine> objects1;

    public MotherVacchineCustomAdapter(Context context, ArrayList<MotherVacchine> products) {
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
            view = lInflater.inflate(R.layout.mother_vaccine, parent, false);
        }

        MotherVacchine motherVacchine = getFriends(position);

        String vName = motherVacchine.getVacchine_name();
        String duration = motherVacchine.getVacchine_duration();


        ((TextView) view.findViewById(R.id.vacchineNameId)).setText(vName);
        ((TextView) view.findViewById(R.id.vacchineDurationId)).setText(duration);

        return view;
    }

    private MotherVacchine getFriends(int position) {
        return ((MotherVacchine) getItem(position));
    }
}
