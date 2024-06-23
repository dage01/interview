package com.dage.interview.controller;

import com.dage.interview.dto.LoginDTO;
import com.dage.interview.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession currentSession = request.getSession(false);
        if (currentSession == null || currentSession.getAttribute("loginDto") == null) {
            System.out.println("세션이 무효화 상태입니다.");
            model.addAttribute("chk", "T");
            return "login";
        } else {
            System.out.println("세션이 유효한 상태입니다.");
            return "redirect:/main";
        }
    }

    @GetMapping("/auto")
    public String auto(HttpServletRequest request, Model model, HttpSession session) {

        String user_no = request.getParameter("user_no");

        System.out.println(user_no);
        if (user_no != null && !user_no.isEmpty()) {
            LoginDTO loginDto = loginService.autoLogin(user_no);
            if (loginDto != null) {
                session.setAttribute("loginDto", loginDto);
                model.addAttribute("user", loginDto);

                return "redirect:/main";
            } else {
                model.addAttribute("chk", "X");
                return "login";
            }
        }else{
            model.addAttribute("chk", "X");
            return "login";
        }
    }

    @RequestMapping("/login.do")
    public String login_do(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException {

        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String chk = "T";
        String return_val = "";
        LoginDTO loginDto = loginService.login(user_id, password);

        System.out.println("***suc*** : " + loginDto);

        if (user_id.equals("")) {
            chk = "ID";
            return_val = "login";
        } else if (password.equals("")) {
            chk = "PW";
            return_val = "login";
        } else if (loginDto == null) {
            chk = "F";
            return_val = "login";
        } else {
            chk = "T";
            return_val = "redirect:main";
            session = request.getSession();
            session.setAttribute("loginDto", loginDto);
        }
        model.addAttribute("chk", chk);
        return return_val;

    }


    /* 로그아웃 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session) {

        session = request.getSession();
        session.removeAttribute("loginDto");
        session.invalidate();


        return "redirect:/"; // 로그인 페이지로 리다이렉트
    }

    @GetMapping("/main")
    public String getMain(HttpServletRequest request, Model model) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                throw new Exception("Session is null");
            }
            return "main";
        }catch (Exception e){
            e.printStackTrace();
            return "error";  // 에러 페이지로 리다이렉트
        }
    }

}
