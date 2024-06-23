package com.dage.interview.controller;

import com.dage.interview.dto.*;
import com.dage.interview.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FormController {

    @Autowired
    FormService formService;

    /* 면담자 지정 */
    @GetMapping("/interview-create")
    public String interviewCreate(HttpServletRequest request, HttpSession session, Model model) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        LoginDTO reqDTO = (LoginDTO) session.getAttribute("loginDto");
        model.addAttribute("reqDTO", reqDTO);

        String req_emp_no = reqDTO.getEmpNo();
        List<InterviewDTO> tempSaveDto = formService.saveConfirm(req_emp_no);

        model.addAttribute("tempSaveDto", tempSaveDto);

        model.addAttribute("interview", new InterviewDTO());
        System.out.println(tempSaveDto);

        return "create";
    }

    /* 면담자 선택 */
    @GetMapping("/interview-search")
    @ResponseBody
    public ResponseEntity<?> selectEmpDto(HttpServletRequest request, HttpSession session) {
        try {
            /* 접속자 */
            LoginDTO reqDTO = (LoginDTO) session.getAttribute("loginDto");
            String req_emp_no = reqDTO.getEmpNo();

            /* 직원 목록 */
            String ans_emp_no = request.getParameter("ans_emp_no");
            session.setAttribute("ans_emp_no", ans_emp_no);
            List<EmployeeDTO> employeeDTO = formService.employeeList(req_emp_no, ans_emp_no);

            int cnt = formService.selectTempSaveCnt(req_emp_no, ans_emp_no);
            String seq = formService.selectTempSeq(req_emp_no, ans_emp_no);

            System.out.println(seq);

            session.setAttribute("ansEmpDto", employeeDTO.get(0));

            Map<String, Object> response = new HashMap<>();

            response.put("employeeDTO", employeeDTO);

            response.put("cnt", cnt);
            response.put("seq", seq);


            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving data");
        }
    }

    /* 면담일지 */
    @RequestMapping("/interview-form")
    public String interviewForm(HttpServletRequest request, HttpSession session, Model model) {

        String seq = request.getParameter("seq");

        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        InterviewDTO interviewDTO = formService.interViewDetail(seq);

        if (interviewDTO == null) {
            interviewDTO = new InterviewDTO();
        }
        model.addAttribute("interviewDTO", interviewDTO);

        System.out.println(interviewDTO);


        return "form";
    }

    /* 면담내역 */
    @GetMapping("/interview-list")
    public String interViewList(HttpServletRequest request, HttpSession session, Model model) {

        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        LoginDTO reqDTO = (LoginDTO) session.getAttribute("loginDto");
        String req_emp_no = reqDTO.getEmpNo();

        List<InterviewDTO> InterviewDTO = formService.interViewList(req_emp_no, null, null, null);

        model.addAttribute("InterviewDTO", new InterviewDTO());
        return "list";
    }

    /* 면담내역조회 */
    @RequestMapping("/interview-list")
    @ResponseBody
    public List<InterviewDTO> getInterviewList(HttpSession session, HttpServletRequest request) {

//        String dept = request.getParameter("dept");
        String req_dt = request.getParameter("req_dt");
        String req_name = request.getParameter("req_name");
        LoginDTO reqDTO = (LoginDTO) session.getAttribute("loginDto");
        String req_emp_no = reqDTO.getEmpNo();

        List<InterviewDTO> interviewDTO = formService.interViewList(req_emp_no, req_dt, req_name, null);

        System.out.println(interviewDTO);

        return interviewDTO;
    }

    /* 면담내역상세 */
    @GetMapping("/interview-detail/{seq}")
    public String interViewDetail(Model model, @PathVariable String seq) {

        InterviewDTO InterviewDTO = formService.interViewDetail(seq);
        model.addAttribute("InterviewDTO", InterviewDTO);
        return "detail";
    }

}
