package com.uop.efac.cc.award.Enum;

public enum Department {
    CP("Department of Chemical & Process Engineering"),
    CE("Department of Civil Engineering"),
    CO("Department of Computer Engineering"),
    EE("Department of Electrical & Electronic Engineering"),
    ME("Department of Mechanical Engineering"),
    PR("Department of Manufacturing & Industrial Engineering");

    private String department;

    Department(String department) { this.department = department; }

    public String getDepartment() { return department; }

}
