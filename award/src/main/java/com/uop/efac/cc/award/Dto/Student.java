package com.uop.efac.cc.award.Dto;

import com.uop.efac.cc.award.Enum.GradeAndPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student {
    private String registrationNumber;
    private Double totalGPA = 0.0;
    private Double averageGPA = 0.0;
    private Integer totalCredits = 0;

    private Integer totalCourses = 0;
    private Integer totalAAPlus = 0;
    private Double percentageAAPlus = 0.0;

    private Integer totalCreditsMaths = 0;
    private Double totalGPAMaths = 0.0;
    private Double averageGPAMaths = 0.0;
    private List<Map<String, String>> resultsMap = new ArrayList<>();

    private List<GradeAndPoint> resultsGradePointMap = new ArrayList<>();

    public List<GradeAndPoint> getResultsGradePointMap() {
        return resultsGradePointMap;
    }

    public void setResultsGradePointMap(List<GradeAndPoint> resultsGradePointMap) {
        this.resultsGradePointMap = resultsGradePointMap;
    }

    public Integer getTotalCreditsMaths() { return totalCreditsMaths;}

    public void setTotalCreditsMaths(Integer totalCreditsMaths) {
        this.totalCreditsMaths = totalCreditsMaths;
    }

    public Double getTotalGPAMaths() {
        return totalGPAMaths;
    }

    public void setTotalGPAMaths(Double totalGPAMaths) {
        this.totalGPAMaths = totalGPAMaths;
    }

    public Double getAverageGPAMaths() {
        return averageGPAMaths;
    }

    public void setAverageGPAMaths(Double averageGPAMaths) {
        this.averageGPAMaths = averageGPAMaths;
    }

    public Integer getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Integer totalCourses) {
        this.totalCourses = totalCourses;
    }

    public Integer getTotalAAPlus() {
        return totalAAPlus;
    }

    public void setTotalAAPlus(Integer totalAAPlus) {
        this.totalAAPlus = totalAAPlus;
    }

    public Double getPercentageAAPlus() {
        return percentageAAPlus;
    }

    public void setPercentageAAPlus(Double percentageAAPlus) {
        this.percentageAAPlus = percentageAAPlus;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<Map<String, String>> getResultsMap() {
        return resultsMap;
    }

    public void setResultsMap(List<Map<String, String>> resultsMap) {
        this.resultsMap = resultsMap;
    }

    public Double getTotalGPA() {
        return totalGPA;
    }

    public void setTotalGPA(Double totalGPA) {
        this.totalGPA = totalGPA;
    }

    public Double getAverageGPA() {
        return averageGPA;
    }

    public void setAverageGPA(Double averageGPA) {
        this.averageGPA = averageGPA;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }
}