package com.example.professt.mother_baby_care.Baby.BabyVacchineSchedule;

public class Baby {

    private int id;
    private int id1;
    private int id2;
    private String name;
    private String reminder1;
    private String date;
    private String vaccine;

    private static Baby baby= new Baby();

    public Baby() {
    }

    public static Baby getInstance( ) {
        return baby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReminder1() {
        return reminder1;
    }

    public void setReminder1(String reminder1) {
        this.reminder1 = reminder1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }
}
