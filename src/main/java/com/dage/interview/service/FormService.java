package com.dage.interview.service;

import com.dage.interview.dao.FormDAO;
import com.dage.interview.dto.AnswerDTO;
import com.dage.interview.dto.EmployeeDTO;
import com.dage.interview.dto.InterviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FormService {

    @Autowired
    FormDAO formDAO;

    /* 직원 목록 */
    public List<EmployeeDTO> employeeList(String req_emp_no, String ans_emp_no) {
        return formDAO.employeeList(req_emp_no, ans_emp_no);
    }

    /* 면담자 지정 */
    public void interviewCreate(InterviewDTO dto){
        formDAO.interviewCreate(dto);
    }

    public List<InterviewDTO> saveConfirm(String req_emp_no) {
        return formDAO.saveConfirm(req_emp_no);
    }

    public Integer selectTempSaveCnt(String req_emp_no, String ans_emp_no){
        return formDAO.selectTempSaveCnt(req_emp_no, ans_emp_no);
    }

    public String selectTempSeq(String req_emp_no, String ans_emp_no){
        return formDAO.selectTempSeq(req_emp_no, ans_emp_no);
    }

    public void deleteLog(String seq){
        formDAO.deleteLog(seq);
    }

    public void deleteLogDet(String seq){
        formDAO.deleteLogDet(seq);
    }

    public void saveTag(String seq, String save_tag){
        formDAO.saveTag(seq, save_tag);
    }

    /* 면담 내용 작성 */
    public void saveAnswer(AnswerDTO answerDTO) {
        formDAO.saveAnswer(answerDTO);
    }

    /* 면담 내역 리스트 */
    public List<InterviewDTO> interViewList(String req_emp_no, String req_dt, String req_name, String proj_code) {
        return formDAO.interViewList(req_emp_no, req_dt, req_name, proj_code);
    }

    /* 면담 내용 상세 */
    public InterviewDTO interViewDetail(String seq) {
        return formDAO.interViewDetail(seq);
    }

}
