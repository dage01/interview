package com.dage.interview.dao;


import com.dage.interview.dto.AnswerDTO;
import com.dage.interview.dto.EmployeeDTO;
import com.dage.interview.dto.InterviewDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormDAO {

    /* 직원 목록 */
    List<EmployeeDTO> employeeList(String req_emp_no, String ans_emp_no);

    /* 면담자 지정 */
    void interviewCreate(InterviewDTO dto);

    Integer selectTempSaveCnt(String req_emp_no, String ans_emp_no);

    String selectTempSeq(String req_emp_no, String ans_emp_no);

    List<InterviewDTO> saveConfirm(String req_emp_no);

    void deleteLog(String seq);

    void deleteLogDet(String seq);


    void saveTag(String seq, String save_tag);


    /* 면담 내용 작성 */
    void saveAnswer(AnswerDTO answerDTO);

    /* 면담 내역 리스트 */
    List<InterviewDTO> interViewList(String req_emp_no, String req_dt, String req_name, String proj_code);

    /* 면담 내용 상세 */
    InterviewDTO interViewDetail(String seq);

}
