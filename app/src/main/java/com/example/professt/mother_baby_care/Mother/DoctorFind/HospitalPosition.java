package com.example.professt.mother_baby_care.Mother.DoctorFind;

public class HospitalPosition {

    private String id;

    private static HospitalPosition hospitalPosition= new HospitalPosition();

    public HospitalPosition() {
    }

    public static HospitalPosition getInstance( ) {
        return hospitalPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
