package com.uop.efac.cc.award.Enum;

public enum GradeAndPoint {
    A("A", 4.0),
    A_PLUS("A+", 4.0),
    A_MINUS("A-", 3.7),
    B_PLUS("B+", 3.3),
    B("B", 3.3),
    B_MINUS("B-", 2.7),
    C_PLUS("C+", 2.3),
    C("C", 2.0),
    C_MINUS("C-", 1.7),
    D_PLUS("D+", 1.3),
    E("E", 0.0),
    F("F", 0.0);

    private String grade;
    private double point;

    // Constructor
    GradeAndPoint(String grade, double point) {
        this.grade = grade;
        this.point = point;
    }

    // Getter methods
    public String getGrade() { return grade; }

    public double getPoint() {
        return point;
    }

    public static GradeAndPoint fromGrade(String grade) {
        for (GradeAndPoint g : values()) {
            if (g.grade.equals(grade.toUpperCase().trim())) {
                return g;
            }
        }
        // Handle the case when the grade is not found
        return F;
    }

}
