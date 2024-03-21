package com.uop.efac.cc.award.Dto;

import com.uop.efac.cc.award.Enum.Award;
import com.uop.efac.cc.award.Enum.Department;

public class RequestBodyDTO {
    private Department department;
    private Award award;


    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }


}
