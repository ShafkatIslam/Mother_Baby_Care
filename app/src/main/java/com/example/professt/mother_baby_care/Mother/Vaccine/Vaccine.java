package com.example.professt.mother_baby_care.Mother.Vaccine;

import com.example.professt.mother_baby_care.Mother.DoctorMeet;

public class Vaccine {

    private int id;
    private String date;
    private String date1;

    private static Vaccine vaccine= new Vaccine();

    public Vaccine() {
    }

    public static Vaccine getInstance( ) {
        return vaccine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }
}
