package com.rishi.loginfirebase;

import java.util.List;

public class Dates {
    String id;
    String name;
    String occassion;
    String dob;
    String remindme;
    String frequency;

    public Dates(List<Dates> mlist) {

    }

    public Dates(String id, String name, String occassion, String dob, String remindme, String frequency) {
        this.id = id;
        this.name = name;
        this.occassion = occassion;
        this.dob = dob;
        this.remindme = remindme;
        this.frequency = frequency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccassion(String occassion) {
        this.occassion = occassion;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRemindme(String remindme) {
        this.remindme = remindme;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
