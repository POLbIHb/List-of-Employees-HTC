package com.gmail.illobikol.htcemployerslist;

public class Employer {

    public String name; // имя сотрудника компании
    private String phone_number;  // телефон сотрудника
    private String skills; //список умений

    public Employer(String name, String phone_number, String skills){

        this.name=name;
        this.phone_number=phone_number;
        this.skills=skills;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSkills() {
        return this.skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}

