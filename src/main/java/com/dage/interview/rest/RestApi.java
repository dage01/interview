package com.dage.interview.rest;


import com.dage.interview.dto.*;
import com.dage.interview.service.CommService;
import com.dage.interview.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestApi {

    @Autowired
    FormService formService;

    @Autowired
    CommService commService;

    /* 현장 목록 */
//    @RequestMapping("/projCode")
//    public List<CommDTO> commProjCode(HttpServletRequest request) {
//
//        String user_no = request.getParameter("user_no");
//
//        List<CommDTO> list = commService.commProjCode(user_no);
//        return list;
//    }

    @RequestMapping("/empAuthList")
    public List<CommDTO> employeeAuthList(HttpSession session) {
        LoginDTO reqDTO = (LoginDTO) session.getAttribute("loginDto");
        String req_emp_no = reqDTO.getEmpNo();

        List<CommDTO> list = commService.employeeAuthList(req_emp_no);
        return list;
    }

//    @RequestMapping("/accessList")
//    public List<CommDTO> accessList() {
//
//        List<CommDTO> list = commService.accessList();
//        return list;
//    }

    /*직원 목록*/
    @RequestMapping("/selectEmployee")
    public List<EmployeeDTO> employeeList(HttpSession session) {
        LoginDTO reqDTO = (LoginDTO) session.getAttribute("loginDto");
        String req_emp_no = reqDTO.getEmpNo();

        List<EmployeeDTO> list = formService.employeeList(req_emp_no, null);
        return list;
    }

    /* 면담자 지정 */
    @RequestMapping("/create")
    public String employeeList(HttpServletRequest request, HttpSession session, @ModelAttribute InterviewDTO dto) {

        /* 면담자 */
        LoginDTO reqDTO = (LoginDTO) session.getAttribute("loginDto");

        /* 면담 대상자 */
        EmployeeDTO ansDTO = (EmployeeDTO) session.getAttribute("ansEmpDto");

        InterviewDTO interviewDTO = new InterviewDTO();

        interviewDTO.setReqDt(dto.getReqDt());
        interviewDTO.setReqEmpNo(reqDTO.getEmpNo());
        interviewDTO.setReqAddDeptCode(String.valueOf(reqDTO.getAddDeptCode()));
        interviewDTO.setReqAddDept(reqDTO.getAddDept());
        interviewDTO.setReqSubDeptCode(String.valueOf(reqDTO.getSubDeptCode()));
        interviewDTO.setReqSubDept(reqDTO.getSubDept());
        interviewDTO.setReqPosCode(String.valueOf(reqDTO.getPositionCode()));
        interviewDTO.setReqPos(reqDTO.getPosition());
        interviewDTO.setReqName(reqDTO.getName());

        interviewDTO.setAnsEmpNo(ansDTO.getId());
        interviewDTO.setAnsDeptNo(ansDTO.getDispEmpNo());
        interviewDTO.setAnsAddDeptCode(ansDTO.getAddDeptCode());
        interviewDTO.setAnsAddDept(ansDTO.getAddDept());
        interviewDTO.setAnsSubDeptCode(ansDTO.getSubDeptCode());
        interviewDTO.setAnsSubDept(ansDTO.getSubDept());
        interviewDTO.setAnsPosCode(ansDTO.getPosCode());
        interviewDTO.setAnsPos(ansDTO.getPosition());
        interviewDTO.setAnsName(ansDTO.getName());

        formService.interviewCreate(interviewDTO);
        String seq = String.valueOf(interviewDTO.getSeq());

        return seq;
    }

    /* 면담일지 저장 */
    @RequestMapping("/saveForm")
    public void saveForm(@RequestParam Map<String, String> paramMap, @ModelAttribute InterviewDTO interviewDTO) {

        formService.deleteLogDet(interviewDTO.getSeq());

        formService.saveTag(interviewDTO.getSeq(), paramMap.get("save_tag"));

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setSeq(interviewDTO.getSeq());

        // 질문 1(단일) 처리
        answerDTO.setQuestNo("1");
        answerDTO.setQuestSubNo("1");
        if (interviewDTO.getQ1() != null) {
            answerDTO.setAnswerNo(interviewDTO.getQ1());
        } else {
            answerDTO.setAnswerNo("");
        }
        if (interviewDTO.getQ1Dtl() != null) {
            answerDTO.setAnswerDtl(interviewDTO.getQ1Dtl());
        } else {
            answerDTO.setAnswerDtl("");
        }

        formService.saveAnswer(answerDTO);


        AtomicInteger queSubNumCnt = new AtomicInteger(1);

        // 질문 2(다중) 처리
        paramMap.forEach((key, value) -> {
            if (key.startsWith("q2") && !key.endsWith("Dtl")) {
                answerDTO.setQuestNo("2");
                answerDTO.setQuestSubNo(String.valueOf(queSubNumCnt.getAndIncrement()));
                answerDTO.setAnswerNo(key.substring(2));
                answerDTO.setAnswerDtl(paramMap.get(key + "Dtl"));
                formService.saveAnswer(answerDTO);
            }
        });

//        // 질문 3(주관식) 처리
        answerDTO.setQuestNo("3");
        answerDTO.setQuestSubNo("1");
        answerDTO.setAnswerNo("0");
        answerDTO.setAnswerDtl(interviewDTO.getQ3Dtl());
        formService.saveAnswer(answerDTO);

        // 질문 4(주관식) 처리
        answerDTO.setQuestNo("4");
        answerDTO.setQuestSubNo("1");
        answerDTO.setAnswerNo("0");
        answerDTO.setAnswerDtl(interviewDTO.getQ4Dtl());
        formService.saveAnswer(answerDTO);
    }

    @RequestMapping("/tempDelete")
    public void saveForm(HttpServletRequest request) {

//        String cnt = request.getParameter("cnt");
        String seq = request.getParameter("seq");

        formService.deleteLogDet(seq);
        formService.deleteLog(seq);

    }

}
