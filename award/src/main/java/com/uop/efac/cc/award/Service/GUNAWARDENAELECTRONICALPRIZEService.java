package com.uop.efac.cc.award.Service;

import com.uop.efac.cc.award.Dto.Student;
import com.uop.efac.cc.award.Dto.Subject;
import com.uop.efac.cc.award.Enum.GradeAndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class GUNAWARDENAELECTRONICALPRIZEService {


    @Autowired
    private AllFacultyService allFacultyService;

    public ResponseEntity<String> getFacultyAward(MultipartFile file, List<Subject> subjects) {
        // Check Empty Or Not
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please Select File To Upload.", HttpStatus.BAD_REQUEST);
        }
        // Create New Array List for Students
        List<Student> studentList = new ArrayList<>();


        // File Reading In Media
        allFacultyService.ReadResultCSV(file, studentList);

        // GPA Calculation
        List<Student> studentListGUNAWARDENA = new ArrayList<>();
        for (Student student : studentList) {
            Double totalGPA = 0.0;
            Integer totalCredits = 0;
            Double averageGPA = 0.0;
            Integer totalCourses = 0;
            Integer totalAAPlus = 0;
            Double aAPlusPercentage = 0.0;

            boolean isEligibleForEE252 = false;
            boolean isEligibleForEE253 = false;
            boolean isEligibleForEE254 = false;
            boolean isEligibleForEE322 = false;
            boolean isEligibleForEE351 = false;
            boolean isEligibleForEE356 = false;

            boolean isEligibleForEE403 = false;
            boolean isEligibleForEE559 = false;
            boolean isEligibleForEE595 = false;
            boolean isEligibleForEE567 = false;
            boolean isEligibleForEE575 = false;
            boolean isEligibleForEE580 = false;
            boolean isEligibleForEE554 = false;


            for (Map<String, String> result : student.getResultsMap()) {
                for (Map.Entry<String, String> entry : result.entrySet()) {
                    String code = entry.getKey();
                    String grade = entry.getValue();
                    Double gpa = GradeAndPoint.fromGrade(grade).getPoint();
                    if (gpa == null) {
                        gpa = 0.0;
                    }

                    Optional<Subject> subjectFromCode = subjects.stream().filter(s -> s.getCode().equals(code)).findAny();
                    if (subjectFromCode.isPresent()) {
                        Subject subjectFound = subjectFromCode.get();
                        Integer credits = subjectFound.getCredits();

                        // Required Subjects
                        if ((code.contains("EE252") && gpa >= 4.0)) {
                            isEligibleForEE252 = true; }

                        if ((code.contains("EE253") && gpa >= 4.0)) {
                            isEligibleForEE253 = true; }

                        if ((code.contains("EE254") && gpa >= 4.0)) {
                            isEligibleForEE254 = true; }

                        if ((code.contains("EE322") && gpa >= 4.0)) {
                            isEligibleForEE322 = true; }

                        if ((code.contains("EE351") && gpa >= 4.0)) {
                            isEligibleForEE351 = true; }

                        if ((code.contains("EE356") && gpa >= 4.0)) {
                            isEligibleForEE356 = true; }

                        //  And Choose Subjects

                        if ((code.contains("EE403") && gpa >= 4.0)) {
                            isEligibleForEE403 = true; }

                        if ((code.contains("EE559") && gpa >= 4.0)) {
                            isEligibleForEE559 = true; }

                        if ((code.contains("EE595") && gpa >= 4.0)) {
                            isEligibleForEE595 = true; }

                        if ((code.contains("EE567") && gpa >= 4.0)) {
                            isEligibleForEE567 = true; }

                        if ((code.contains("EE575") && gpa >= 4.0)) {
                            isEligibleForEE575 = true; }

                        if ((code.contains("EE580") && gpa >= 4.0)) {
                            isEligibleForEE580 = true; }

                        if ((code.contains("EE554") && gpa >= 4.0)) {
                            isEligibleForEE554 = true; }


                        //GPA Calculation
                        Double gpaSubject = credits * gpa;
                        totalGPA += gpaSubject;
                        totalCredits += credits;

                        // AAPlus Calculation
                        totalCourses += 1;

                        //Find A,A Plus Courses
                        if (gpa >= 4.0) {
                            totalAAPlus += 1;
                        }
                    } else {
                        System.out.println("No subject found for code: " + code);
                    }
                }
            }

            if (isEligibleForEE252 && isEligibleForEE253 && isEligibleForEE254 && isEligibleForEE322 && isEligibleForEE351 && isEligibleForEE356 ) {
                int PassCount = (isEligibleForEE403 ? 1 : 0) + (isEligibleForEE559 ? 1 : 0) + (isEligibleForEE595 ? 1 : 0) + (isEligibleForEE567 ? 1 : 0) + (isEligibleForEE575 ? 1 : 0) + (isEligibleForEE580 ? 1 : 0) + (isEligibleForEE554 ? 1 : 0);
                if (PassCount >= 3) {
                    studentListGUNAWARDENA.add(student);
                }
            }

            // Average Total Courses
            averageGPA = totalGPA / totalCredits;

            //Calculate aAPlusPercentage
            if (totalCourses > 0) {
                aAPlusPercentage = (totalAAPlus * 100.0) / totalCourses;
            }
            //Set Values to The Student
            student.setTotalGPA(totalGPA);
            student.setAverageGPA(Math.round(averageGPA * 1000.0) / 1000.0);
            student.setTotalCredits(totalCredits);
            student.setTotalCourses(totalCourses);
            student.setTotalAAPlus(totalAAPlus);
            student.setPercentageAAPlus(Math.round(aAPlusPercentage * 1000.0) / 1000.0);

        }
        System.out.println("Done");

        // Sorting the list By averageGPA In Descending Order
        List<Student> sortedStudents = studentList.stream().sorted(Comparator.comparingDouble(Student::getAverageGPA).thenComparingDouble(Student::getPercentageAAPlus)).collect(Collectors.toList());

        Collections.reverse(sortedStudents);

        // Printing The Sorted list
        sortedStudents.forEach(student -> System.out.println(
                "Registration Number: " + student.getRegistrationNumber() +
                        ", Total GPA: " + student.getTotalGPA() +
                        ", Total Credits: " + student.getTotalCredits() +
                        ", Average GPA: " + student.getAverageGPA() +
                        ", Total Courses : " + student.getTotalCourses() +
                        ", Total A A+: " + student.getTotalAAPlus() +
                        ", Average A A+: " + student.getPercentageAAPlus()));

        // Error Exception Or Success Exception
        // excelExporter.exportStudentsToExcel(studentList);
        return new ResponseEntity<>("File upload successfully.", HttpStatus.OK);

    }

}



