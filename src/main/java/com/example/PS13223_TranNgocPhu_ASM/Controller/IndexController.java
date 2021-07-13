package com.example.PS13223_TranNgocPhu_ASM.Controller;


import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import com.example.PS13223_TranNgocPhu_ASM.DAO.ProductDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Account;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Product;
import com.example.PS13223_TranNgocPhu_ASM.Service.MailerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/home")
public class IndexController {
    @Autowired
    ProductDAO dao;
    @Autowired
	MailerService mailer;

    
    // localhost:8080//home/index
    @RequestMapping("/index")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p,HttpSession session) {
		
        // Pageable pageable = PageRequest.of(p.orElse(0), 6); //phân trang
		// Page<Product> page = dao.findAll(pageable);
        //model.addAttribute("items", page);
        List<Product> items = dao.findAll(); // truy vấn tất cả
        model.addAttribute("items", items);
        if(session.getAttribute("user") == null){
            model.addAttribute("message","Xin Chào: Guess");
        }else{
            model.addAttribute("message","Xin Chào: "+ session.getAttribute("user"));
        }
		return "/User/index";
	}
    @GetMapping("/account/register")
    public String register(Model model){
        Account  item = new Account();
          model.addAttribute("account", item);
        return "/User/dangky";
    }
    @GetMapping("/account/login")
    public String login(){
        return "/User/dangnhap";
    }

    @PostMapping("/mailer/newletter")
	public String send(@RequestParam("subemail") String subemail) {
        System.out.println(subemail);
		try {
			mailer.send(subemail, "THÔNG BÁO", "Cảm Ơn Bạn Đã Đăng Ký Nhận Mail.Chúng Tôi Sẽ Cập Nhật Những Tin Tức Về Xe Nhanh Nhất Có Thể Cho Bạn");
			return "/User/sended";
		} catch (MessagingException e) {
			return e.getMessage();
		}
	}
}
