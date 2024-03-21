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
public class ENGINEERINGMATHEMATICSPRIZEService {


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
        for (Student student : studentList) {
            Double totalGPA = 0.0;
            Integer totalCredits = 0;
            Double averageGPA = 0.0;
            Integer totalCourses = 0;
            Integer totalAAPlus = 0;
            Double aAPlusPercentage = 0.0;
            Integer totalCreditsMaths = 0;
            Double totalGPAMaths = 0.0;
            Double averageGPAMaths = 0.0;
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
                        
                        if (code.contains("CE")) {
                            totalCreditsMaths += credits;
                            totalGPAMaths += credits * gpa;
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

            //Calculate AverageMathsGPA
            if (totalCreditsMaths > 0) {
                averageGPAMaths = totalGPAMaths / totalCreditsMaths;
            }
            //Calculate AverageGPA
            if (totalCredits > 0) {
                averageGPA = totalGPA / totalCredits;
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

            student.setTotalGPAMaths(Math.round(totalGPAMaths * 1000.0) / 1000.0);
            student.setAverageGPAMaths(Math.round(averageGPAMaths * 1000.0) / 1000.0);
            student.setTotalCreditsMaths(totalCreditsMaths);
        }
        List<Student> sortedStudents = studentList.stream().sorted(Comparator.comparingDouble(Student::getAverageGPA).thenComparingDouble(Student::getPercentageAAPlus)).collect(Collectors.toList());

        Collections.reverse(sortedStudents);

        // Printing The Sorted list
        sortedStudents.forEach(student -> System.out.println(
                        " Registration Number: " + student.getRegistrationNumber() +
                        ", Total GPA: " + student.getTotalGPA() +
                        ", Total Credits: " + student.getTotalCredits() +
                        ", Average GPA: " + student.getAverageGPA() +
                        ", Total Courses : " + student.getTotalCourses() +
                        ", Total A A+: " + student.getTotalAAPlus() +
                        ", Average A A+: " + student.getPercentageAAPlus() +
                        ", Average Maths GPA: " + student.getAverageGPAMaths() +
                        ", Total maths GPA : " + student.getTotalGPAMaths() +
                        ", Total maths Credits : " + student.getTotalCreditsMaths()));

        // Error Exception Or Success Exception
        // excelExporter.exportStudentsToExcel(studentList);
        return new ResponseEntity<>("File upload successfully.", HttpStatus.OK);


    }


}
