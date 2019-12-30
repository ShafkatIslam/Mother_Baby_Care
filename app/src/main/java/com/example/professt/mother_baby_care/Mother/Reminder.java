package com.example.professt.mother_baby_care.Mother;

public class Reminder {

    private String date;
    private String date1;

    private static Reminder reminder= new Reminder();

    public Reminder() {
    }

    public static Reminder getInstance( ) {
        return reminder;
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
