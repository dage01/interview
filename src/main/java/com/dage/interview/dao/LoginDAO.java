package com.dage.interview.dao;


import com.dage.interview.dto.LoginDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDAO {

    /* 계정 리스트  */
    LoginDTO login(String user_id, String user_password);

    /* 자동 로그인 */
    LoginDTO autoLogin(String user_no);

}
