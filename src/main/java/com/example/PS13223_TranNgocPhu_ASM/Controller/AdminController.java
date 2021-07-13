package com.example.PS13223_TranNgocPhu_ASM.Controller;

import java.util.List;

import com.example.PS13223_TranNgocPhu_ASM.DAO.CategoryDAO;
import com.example.PS13223_TranNgocPhu_ASM.DAO.ProductDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Report;
import com.example.PS13223_TranNgocPhu_ASM.Service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    ProductDAO dao;
    @Autowired
    SessionService session;
    @Autowired
    CategoryDAO Catedao;

    @RequestMapping("/adminIndex")
    public String index(){
        return "Admin/AdminIndex";
    }
    @RequestMapping("/Report")
    public String inventory(Model model) {
        // load Report
        List<Report> items = dao.getInventoryByCategory();
        model.addAttribute("report", items);
        return "Admin/Report";
    }

}