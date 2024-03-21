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
public class GUNAWARDENACONTROLPRIZEService {



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

                boolean isEligibleForEE257 = false;
                boolean isEligibleForEE352 = false;
                boolean isEligibleForEE353 = false;
                boolean isEligibleForEE402 = false;
                boolean isEligibleForEE501 = false;

                boolean isEligibleForEE539 = false;
                boolean isEligibleForEE593 = false;
                boolean isEligibleForEE594 = false;


                for (Map<String, String> result : student.getResultsMap()) {
                    for (Map.Entry<String, String> entry : result.entrySet()) {
                        String code = entry.getKey();
                        String grade = entry.getValue();
                        Double gpa =  GradeAndPoint.fromGrade(grade).getPoint();
                        if (gpa == null) {
                            gpa = 0.0;
                        }

                        Optional<Subject> subjectFromCode = subjects.stream().filter(s -> s.getCode().equals(code)).findAny();
                        if (subjectFromCode.isPresent()) {
                            Subject subjectFound = subjectFromCode.get();
                            Integer credits = subjectFound.getCredits();

                            if (code.contains("EE257") && gpa >= 4.0 ) {
                                isEligibleForEE257 = true;
                            }

                            if (code.contains("EE352") && gpa >= 4.0) {
                                isEligibleForEE352 = true;
                            }
                            if (code.contains("EE353") && gpa >= 4.0) {
                                isEligibleForEE353 = true;
                            }

                            if (code.contains("EE402") && gpa >= 4.0) {
                                isEligibleForEE402 = true;
                            }

                            if (code.contains("EE501") && gpa >= 4.0) {
                                isEligibleForEE501 = true;
                            }

                            if (code.contains("EE539") && gpa >= 4.0) {
                                isEligibleForEE539 = true;
                            }

                            if (code.contains("EE593") && gpa >= 4.0) {
                                isEligibleForEE593 = true;
                            }

                            if (code.contains("EE594")  && gpa >= 4.0) {
                                isEligibleForEE594 = true;
                            }



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

                // Average Total Courses
                averageGPA = totalGPA / totalCredits;

                //Check The Eligibility GUNAWARDENA
                if(isEligibleForEE257 && isEligibleForEE352 && isEligibleForEE353 && isEligibleForEE402 && isEligibleForEE501) {
                    if((isEligibleForEE539 && isEligibleForEE593) || (isEligibleForEE539 && isEligibleForEE594)  || (isEligibleForEE593 && isEligibleForEE594))
                    studentListGUNAWARDENA.add(student);
                }


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
