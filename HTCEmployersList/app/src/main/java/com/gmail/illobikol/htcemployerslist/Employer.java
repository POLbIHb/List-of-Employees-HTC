package com.gmail.illobikol.htcemployerslist;

public class Employer {

    private String name;
    private String phoneNumber;
    private String skills;

    public Employer(String name, String phoneNumber, String skills){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSkills() {
        return skills;
    }
}
