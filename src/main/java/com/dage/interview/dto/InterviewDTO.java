package com.dage.interview.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


@Data
public class InterviewDTO {
    private String seq;
    private Date reqDt;
    private String reqEmpNo;
    private String reqAddDeptCode;
    private String reqAddDept;
    private String reqSubDeptCode;
    private String reqSubDept;
    private String reqName;
    private String reqPosCode;
    private String reqPos;
    private String ansEmpNo;
    private String ansDeptNo;
    private String ansEntDt;
    private String ansAddDeptCode;
    private String ansAddDept;
    private String ansSubDeptCode;
    private String ansSubDept;
    private String ansName;
    private String ansPosCode;
    private String ansPos;
    private String q1;
    private String q1Dtl;
    private boolean q21;
    private String q21Dtl;
    private boolean q22;
    private String q22Dtl;
    private boolean q23;
    private String q23Dtl;
    private boolean q24;
    private String q24Dtl;
    private boolean q25;
    private String q25Dtl;
    private boolean q26;
    private String q26Dtl;
    private boolean q27;
    private String q27Dtl;
    private boolean q28;
    private String q28Dtl;
    private boolean q29;
    private String q29Dtl;
    private boolean q210;
    private String q210Dtl;
    private String q3Dtl;
    private String q4Dtl;
    private String regDt;
}
