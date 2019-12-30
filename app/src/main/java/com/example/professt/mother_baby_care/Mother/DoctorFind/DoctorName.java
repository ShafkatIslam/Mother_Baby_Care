package com.example.professt.mother_baby_care.Mother.DoctorFind;

public class DoctorName {

    private String id;

    private static DoctorName doctorName= new DoctorName();

    public DoctorName() {
    }

    public static DoctorName getInstance( ) {
        return doctorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
