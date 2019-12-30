package com.example.professt.mother_baby_care.Mother.VaccineeList;

public class MotherVaccineList {

    int id,id1;
    String previouMeeting;
    String nextMeeting;

    public MotherVaccineList(int id, int id1, String previouMeeting, String nextMeeting) {
        this.id = id;
        this.id1 = id1;
        this.previouMeeting = previouMeeting;
        this.nextMeeting = nextMeeting;
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

    public String getPreviouMeeting() {
        return previouMeeting;
    }

    public void setPreviouMeeting(String previouMeeting) {
        this.previouMeeting = previouMeeting;
    }

    public String getNextMeeting() {
        return nextMeeting;
    }

    public void setNextMeeting(String nextMeeting) {
        this.nextMeeting = nextMeeting;
    }
}
