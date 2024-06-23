package com.dage.interview.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private Integer userNo;
    private String empNo;
    private String userId;
    private String password;
    private String addDept;
    private int addDeptCode;
    private String subDept;
    private int subDeptCode;
    private String name;
    private int positionCode;
    private String position;
    private String perTag;
}
