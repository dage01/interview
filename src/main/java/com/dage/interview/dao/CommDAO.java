package com.dage.interview.dao;


import com.dage.interview.dto.CommDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommDAO {

    /* 현장 목록 */
    List<CommDTO> commProjCode(String user_no);

    /* 직원 목록 */
    List<CommDTO> employeeAuthList(String reg_emp_no);

    /* 권한 부여 목록 */
    List<CommDTO> accessList();

}
