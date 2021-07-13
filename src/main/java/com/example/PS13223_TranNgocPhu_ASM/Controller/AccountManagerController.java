package com.example.PS13223_TranNgocPhu_ASM.Controller;

import java.util.List;

import com.example.PS13223_TranNgocPhu_ASM.DAO.AccountDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountManagerController {
    @Autowired
    AccountDAO accDao;
    @RequestMapping("/Account")
    public String show(Model model){
          // load account
          Account  item = new Account();
          model.addAttribute("item", item);
          List<Account> AccItems = accDao.findAll();
          model.addAttribute("Account", AccItems);
         return "Admin/Account";
    }


    @RequestMapping("/account/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Account item = accDao.findById(id).get();
        model.addAttribute("item", item);
        List<Account> items = accDao.findAll();
        model.addAttribute("Account", items);
        return "Admin/Account";
    }

    @RequestMapping("/account/save")
    public String create(Account item) {
        accDao.save(item);
        return "redirect:/Account";
    }

    @RequestMapping("/account/update")
    public String update(Account item, Model model) {
        try {
            accDao.save(item);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/account/edit/" + item.getUsername();
    }

    @RequestMapping("/account/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            accDao.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/Account";
    }
}
