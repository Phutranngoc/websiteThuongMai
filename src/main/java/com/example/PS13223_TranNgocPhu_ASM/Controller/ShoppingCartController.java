package com.example.PS13223_TranNgocPhu_ASM.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.PS13223_TranNgocPhu_ASM.DAO.OrderDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Order;
import com.example.PS13223_TranNgocPhu_ASM.Service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {

    @Autowired
    ShoppingCartService cart; // tiêm Spring Bean đã viết ở bài trước

    @RequestMapping("/cart/view")
    public String view(Model model) {
        model.addAttribute("cart", cart.getItems());
        System.out.println(cart.getItems());
        return "/Cart/view";
    }

    @RequestMapping("/cart/add")
    public String add(@RequestParam("id") Integer id) {
        cart.add(id);
        return "redirect:/cart/view"; // hiển thị giỏ hàng
    }

    @RequestMapping("/cart/remove")
    public String remove(@RequestParam("id") Integer id) { 
        cart.remove(id);
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/update")
    public String update(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
        cart.update(id, qty);
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/clear")
    public String clear() {
        cart.clear();
        return "redirect:/cart/view";
    }


    @RequestMapping("/cart/checkout")
    public String checkout() {
       
        return null; 
    }
}
