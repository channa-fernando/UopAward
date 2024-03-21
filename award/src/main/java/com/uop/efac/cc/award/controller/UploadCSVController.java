package com.uop.efac.cc.award.controller;

import com.uop.efac.cc.award.Enum.Award;
import com.uop.efac.cc.award.Enum.Department;
import com.uop.efac.cc.award.Service.AllFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
public class UploadCSVController {

    @Autowired
    private AllFacultyService allFacultyService;

    @PostMapping("/generateAwardees")
    public ResponseEntity<String> generateAwardees(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("award") Award award,
                                                   @RequestParam("department") Department department) {
        return allFacultyService.selectService(award, file, department);
    }


}
