package com.example.professt.mother_baby_care.Mother;

public class DoctorMeet {

    private int id;
    private String date;
    private String date1;

    private static DoctorMeet doctorMeet= new DoctorMeet();

    public DoctorMeet() {
    }

    public static DoctorMeet getInstance( ) {
        return doctorMeet;
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