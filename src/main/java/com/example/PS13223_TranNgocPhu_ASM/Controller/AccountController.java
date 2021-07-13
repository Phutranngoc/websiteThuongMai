package com.example.PS13223_TranNgocPhu_ASM.Controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import com.example.PS13223_TranNgocPhu_ASM.DAO.AccountDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Account;
import com.example.PS13223_TranNgocPhu_ASM.Service.CookieService;
import com.example.PS13223_TranNgocPhu_ASM.Service.MailerService;
import com.example.PS13223_TranNgocPhu_ASM.Service.ParamService;
import com.example.PS13223_TranNgocPhu_ASM.Service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    AccountDAO dao;
    @Autowired
    SessionService session;
    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @PostMapping("/login/user")
    public String login(Model model, @RequestParam("username") String username, HttpSession session1,
            @RequestParam("password") String password) {
        boolean rm = paramService.getBoolean("remember", false);
        try {
            Account user = dao.getOne(username);
            if (!user.getPassword().equals(password)) {
                model.addAttribute("message", "Sai Mật Khẩu!");
            } else {
                String uri = session.get("security-uri");
                if (uri != null) {
                    return "redirect:" + uri;
                } else if (user.isAdmin() == true) {
                    session1.setAttribute("user", username);
                    if (rm) {
                        cookieService.add("user", username, 0);
                        cookieService.add("pass", password, 10);
                    } else {
                        cookieService.remove(username);
                        cookieService.remove(password);
                    }
                    return "redirect:/adminIndex";
                } else if (user.isAdmin() == false) {
                    session1.setAttribute("user", username);
                    if (rm) {
                        cookieService.add("user", username, 0);
                        cookieService.add("pass", password, 10);
                    } else {
                        cookieService.remove(username);
                        cookieService.remove(password);
                    }
                    model.addAttribute("message", "Xin Chào:" + username);
                    return "redirect:/home/index";
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", "Không Tìm Thấy Username!");
        }
        return "User/dangnhap";
    }

    @GetMapping("/logout/user")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/home/index";
    }

    @Autowired
    MailerService mailer;

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("account") Account account,
            @RequestParam("conps") String conpas) {

        try {
            if (account.getPassword().equals(conpas)) {
                dao.save(account);
                model.addAttribute("mess", "Đăng Ký Thành Công");
                mailer.send(account.getEmail(), "Cảm Ơn", "Cảm Ơn Bạn Đã Đăng Ký Trang Web Chúng Tôi!");
            }
        } catch (MessagingException e) {
            model.addAttribute("mess", "Đăng Ký Thất Bại");
        }
        return "/User/sended";
    }
}
