package com.dage.interview.service;

import com.dage.interview.dao.CommDAO;
import com.dage.interview.dto.CommDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommService {

    @Autowired
    CommDAO commDAO;

    /* 현장 목록 */
    public List<CommDTO> commProjCode(String user_no) {
        return commDAO.commProjCode(user_no);
    }

    public List<CommDTO> employeeAuthList(String reg_emp_no) {
        return commDAO.employeeAuthList(reg_emp_no);
    }

    /* 권한 부여 목록 */
    public List<CommDTO> accessList() {
        return commDAO.accessList();
    }


}
