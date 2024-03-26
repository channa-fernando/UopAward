package com.uop.efac.cc.award.Service;

import com.uop.efac.cc.award.Dto.Student;
import com.uop.efac.cc.award.Dto.Subject;
import com.uop.efac.cc.award.Enum.Award;
import com.uop.efac.cc.award.Enum.Department;
import com.uop.efac.cc.award.Enum.GradeAndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

import static com.uop.efac.cc.award.Enum.Award.*;
import static com.uop.efac.cc.award.Enum.CourseCreditsCSV.*;
import static com.uop.efac.cc.award.Enum.Department.*;

@Service
public class AllFacultyService {

    @Autowired
    private CEYLONELECTRICITYEEPRIZEService ceylonelectricityeeprizeService;

    @Autowired
    private ENGINEERINGMATHEMATICSPRIZEService engineeringmathematicsprizeService;

    @Autowired
    private EOEPERERRAPRIZEIService eoepererraprizeiService;

    @Autowired
    private EOEPERERRAPRIZEIIService eoepererraprizeiiService;

    @Autowired
    private AMARATHUNGAPRIZEService amarathungaprizeService;

    @Autowired
    private THURAIRAJAHPRIZEService thurairajahprizeService;

    @Autowired
    private SILVAPRIZEService silvaprizeService;

    @Autowired
    private MAHALINGAMPRIZEService mahalingamprizeService;

    @Autowired
    private JAYASEKARAPRIZEService jayasekaraprizeService;

    @Autowired
    private PRANAWEERAFINITEPRIZEService pranaweeraprizeService;

    @Autowired
    PRANAWEERASTRUCTURALDESIGNPRIZEService pranaweerastructuraldesignprizeService;

    @Autowired
    private GUNAWARDENACONTROLPRIZEService gunawardenacontrolprizeService;

    @Autowired
    private GUNAWARDENAELECTRONICALPRIZEService gunawardenaelectronicalprizeService;

    @Autowired
    SAMUELENTHUSIASMANDEXCELLENCEPRIZEService samuelenthusiasmandexcellenceprizeService;


    public ResponseEntity<String> selectService(Award award, MultipartFile file, Department department) {
        List<Subject> subjectList = new ArrayList<>();

        // CEYLON ELECTRICITY BOARD GOLD MEDAL AND PRICE FOR ELECTRICAL AND ELECTRONIC ENGINEERING EE
        if (award.equals(AWARD3) && department.equals(EE)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return ceylonelectricityeeprizeService.getFacultyAward(file, subjectList);
        }

        // EF BARTHOLOMEUSZ PRIZE FOR ENGINEERING MATHEMATICS CE
        if (award.equals(AWARD9) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return engineeringmathematicsprizeService.getFacultyAward(file, subjectList);
        }

        // EF BARTHOLOMEUSZ PRIZE FOR ENGINEERING MATHEMATICS CP
        if (award.equals(AWARD9) && department.equals(CP)) {
            ReadCourseCreditCSV(subjectList, CP_CSV.getCreditCSV());
            return engineeringmathematicsprizeService.getFacultyAward(file, subjectList);
        }

        // EF BARTHOLOMEUSZ PRIZE FOR ENGINEERING MATHEMATICS CO
        if (award.equals(AWARD9) && department.equals(CO)) {
            ReadCourseCreditCSV(subjectList, CO_CSV.getCreditCSV());
            return engineeringmathematicsprizeService.getFacultyAward(file, subjectList);
        }

        // EF BARTHOLOMEUSZ PRIZE FOR ENGINEERING MATHEMATICS EE
        if (award.equals(AWARD9) && department.equals(EE)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return engineeringmathematicsprizeService.getFacultyAward(file, subjectList);
        }

        // EF BARTHOLOMEUSZ PRIZE FOR ENGINEERING MATHEMATICS ME
        if (award.equals(AWARD9) && department.equals(ME)) {
            ReadCourseCreditCSV(subjectList, ME_CSV.getCreditCSV());
            return engineeringmathematicsprizeService.getFacultyAward(file, subjectList);
        }

        // EF BARTHOLOMEUSZ PRIZE FOR ENGINEERING MATHEMATICS PR
        if (award.equals(AWARD9) && department.equals(PR)) {
            ReadCourseCreditCSV(subjectList, PR_CSV.getCreditCSV());
            return engineeringmathematicsprizeService.getFacultyAward(file, subjectList);
        }

        // EOE PEREIRA PRIZE FOR STRUCTURES(I) CE
        if (award.equals(AWARD10) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return eoepererraprizeiService.getFacultyAward(file, subjectList);
        }

        // EOE PEREIRA PRIZE FOR STRUCTURES(II) CE
        if (award.equals(AWARD11) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return eoepererraprizeiiService.getFacultyAward(file, subjectList);
        }

        // THURAIRAJAH PRIZE FOR GEOTECHNICSG CE
        if (award.equals(AWARD13) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return thurairajahprizeService.getFacultyAward(file, subjectList);
        }

        // HB de SILVA PRIZE FOR SURVEYING CE
        if (award.equals(AWARD12) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return silvaprizeService.getFacultyAward(file, subjectList);
        }


        // AMAMARATHUNGA PRIZE FOR STRENGTH OF MATERIALS CE
        if (award.equals(AWARD14) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return amarathungaprizeService.getFacultyAward(file, subjectList);
        }


        // MAHALINGAM PRIZE FOR MECHANICS OF MACHINES ME
        if (award.equals(AWARD19) && department.equals(ME)) {
            ReadCourseCreditCSV(subjectList, ME_CSV.getCreditCSV());
            return mahalingamprizeService.getFacultyAward(file, subjectList);
        }

        // JAYASEKARA PRIZE FOR THE BEST STUDENT PROTECT IN ELECTRICAL & ELECTRONIC ENGINEERING EE
        if (award.equals(AWARD20) && department.equals(EE)) {
            ReadCourseCreditCSV(subjectList, ME_CSV.getCreditCSV());
            return jayasekaraprizeService.getFacultyAward(file, subjectList);
        }

        // REGULATIONS GOVERNING THE AWARD OF P.M RANAWEERA PRIZE FOR COMPUTER AIDED STRUCTURAL DESIGN CE
        if (award.equals(AWARD21) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return pranaweerastructuraldesignprizeService.getFacultyAward(file, subjectList);
        }

        // RANAWEERA PRIZE FOR FINITE ELEMENT METHODS IN SOLID MECHANICS CE
        if (award.equals(AWARD22) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return pranaweeraprizeService.getFacultyAward(file, subjectList);
        }

        // GUNAWARDENA PRIZE FOR THE BEST PERFORMANCE IN CONTROL ENGINEERING EE
        if (award.equals(AWARD24) && department.equals(EE)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return gunawardenacontrolprizeService.getFacultyAward(file, subjectList);
        }

        // PROFESSOR JA GUNAWARDENA PRIZE FOR THE BEST PERFORMANCE IN ELECTRONICAL ENGINEERING EE
        if (award.equals(AWARD25) && department.equals(EE)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return gunawardenaelectronicalprizeService.getFacultyAward(file, subjectList);
        }

        // PROFESSOR T.D.M.A SAMUEL PRIZE FOR AND ENTHUSIASM AND EXCELLENCE IN ENGINEERING MATHEMATICS CE
        if (award.equals(AWARD23) && department.equals(CE)) {
            ReadCourseCreditCSV(subjectList, CE_CSV.getCreditCSV());
            return samuelenthusiasmandexcellenceprizeService.getFacultyAward(file, subjectList);
        }

        // PROFESSOR T.D.M.A SAMUEL PRIZE FOR AND ENTHUSIASM AND EXCELLENCE IN ENGINEERING MATHEMATICS EE
        if (award.equals(AWARD23) && department.equals(EE)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return samuelenthusiasmandexcellenceprizeService.getFacultyAward(file, subjectList);
        }

        // PROFESSOR T.D.M.A SAMUEL PRIZE FOR AND ENTHUSIASM AND EXCELLENCE IN ENGINEERING MATHEMATICS CO
        if (award.equals(AWARD23) && department.equals(CO)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return samuelenthusiasmandexcellenceprizeService.getFacultyAward(file, subjectList);
        }

        // PROFESSOR T.D.M.A SAMUEL PRIZE FOR AND ENTHUSIASM AND EXCELLENCE IN ENGINEERING MATHEMATICS CP
        if (award.equals(AWARD23) && department.equals(CP)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return samuelenthusiasmandexcellenceprizeService.getFacultyAward(file, subjectList);
        }
        // PROFESSOR T.D.M.A SAMUEL PRIZE FOR AND ENTHUSIASM AND EXCELLENCE IN ENGINEERING MATHEMATICS ME
        if (award.equals(AWARD23) && department.equals(ME)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return samuelenthusiasmandexcellenceprizeService.getFacultyAward(file, subjectList);
        }
        // PROFESSOR T.D.M.A SAMUEL PRIZE FOR AND ENTHUSIASM AND EXCELLENCE IN ENGINEERING MATHEMATICS PR
        if (award.equals(AWARD23) && department.equals(PR)) {
            ReadCourseCreditCSV(subjectList, EE_CSV.getCreditCSV());
            return samuelenthusiasmandexcellenceprizeService.getFacultyAward(file, subjectList);
        }
        


        return new ResponseEntity<>("Please Select Correct Award and Department.", HttpStatus.BAD_REQUEST);

    }

    public void ReadResultCSV(MultipartFile file, List<Student> studentList) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            boolean isFirstLine = true;
            String line;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                // Creating Gradepoints for Student
                Student student = new Student();


                String[] values = line.split(",");
                // Handling To Human Errors(Space,Upper Case, Etc...)
                if (values.length >= 3) {
                    String indexUpperCase = values[0].trim().toUpperCase();
                    String courseUpperCase = values[1].trim().toUpperCase();
                    String grade = values[2].trim().toUpperCase();

                    //TODO: Please Check This E/ or e
                    if (indexUpperCase.contains("E")) {
                        Optional<Student> findStudentInList = studentList.stream().filter(s -> s.getRegistrationNumber().equals(indexUpperCase)).findAny();
                        if (findStudentInList.isPresent()) {
                            student = findStudentInList.get();

                            // New Code
                            Map<String, String> resultDuplicate = null;

                            // Checking Results greater Than Or Equal To The Old Grade
                            for (Map<String, String> oldResult : student.getResultsMap()) {
                                String oldGrade = oldResult.get(courseUpperCase);
                                if (oldGrade != null) {
                                    GradeAndPoint oldMark = GradeAndPoint.fromGrade(oldGrade);
                                    GradeAndPoint newMark = GradeAndPoint.fromGrade(grade);
                                    if (newMark.getPoint() >= oldMark.getPoint()) {
                                        resultDuplicate = oldResult;
                                        break;
                                    }
                                }
                            }
                            // If Check Student Results Duplicate Or Note
                            if (resultDuplicate != null) {
                                student.getResultsMap().remove(resultDuplicate);
                            }
                            // Saving and Adding New Results On That File
                            Map<String, String> newResultMap = new HashMap<>();
                            newResultMap.put(courseUpperCase, grade);
                            student.getResultsMap().add(newResultMap);
                        } else {
                            student.setRegistrationNumber(indexUpperCase);
                            Map<String, String> newResultMap = new HashMap<>();
                            newResultMap.put(courseUpperCase, grade);
                            student.getResultsMap().add(newResultMap);
                        }

                        //Add Student To findStudentInList
                        if (!findStudentInList.isPresent()) {
                            studentList.add(student);
                        }

                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void ReadCourseCreditCSV(List<Subject> subjects, String fileName) {
        try {
            String csvFilePath = new ClassPathResource(fileName).getFile().getPath();
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            boolean isFirstLineCredits = true;
            // Read and process the CSV data
            String line;
            while ((line = reader.readLine()) != null) {
                if (isFirstLineCredits) {
                    isFirstLineCredits = false;
                    continue;
                }

                String[] creditValues = line.split(",");
                if (creditValues.length >= 3) {
                    String code = creditValues[0].trim().replaceAll("^\"+|\"+$", "").toUpperCase();
                    String credits = creditValues[2].trim().replaceAll("^\"+|\"+$", "").toUpperCase();
                    Subject subject = new Subject();
                    subject.setCode(code);
                    subject.setCredits(Integer.parseInt(credits));
                    subjects.add(subject);
                }
            }
            // Close the reader when done
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
