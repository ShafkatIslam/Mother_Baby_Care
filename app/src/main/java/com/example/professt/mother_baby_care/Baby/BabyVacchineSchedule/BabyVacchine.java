package com.example.professt.mother_baby_care.Baby.BabyVacchineSchedule;

public class BabyVacchine {

    String vacchine_name;
    String vacchine_duration;

    public BabyVacchine(String vacchine_name, String vacchine_duration) {

        this.vacchine_name = vacchine_name;
        this.vacchine_duration = vacchine_duration;

    }

    public String getVacchine_name() {
        return vacchine_name;
    }

    public void setVacchine_name(String vacchine_name) {
        this.vacchine_name = vacchine_name;
    }

    public String getVacchine_duration() {
        return vacchine_duration;
    }

    public void setVacchine_duration(String vacchine_duration) {
        this.vacchine_duration = vacchine_duration;
    }
}
