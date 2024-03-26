package com.uop.efac.cc.award.Enum;

public enum CourseCreditsCSV {
    CP_CSV("Courses and CreditsCP.csv"),
    CE_CSV("Courses and CreditsCE.csv"),
    CO_CSV("Courses-and-CreditsCO.csv"),
    EE_CSV("Courses-and-CreditsEE.csv"),
    ME_CSV("Courses-and-CreditsME.csv"),
    PR_CSV("Courses-and-CreditsPR.csv");



    private String creditCSV;
    CourseCreditsCSV(String creditCSV) { this.creditCSV = creditCSV; }
    public String getCreditCSV() { return creditCSV; }
}
