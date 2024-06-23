package com.dage.interview.service;

import com.dage.interview.dao.LoginDAO;
import com.dage.interview.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    LoginDAO loginDAO;

    /* 계정 리스트 */
    public LoginDTO login(String user_id, String user_password) {
        return loginDAO.login(user_id, user_password);
    }

    /* 자동 로그인 */
    public LoginDTO autoLogin(String user_no) {
        return loginDAO.autoLogin(user_no);
    }




}
