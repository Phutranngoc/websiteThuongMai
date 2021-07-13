package com.example.PS13223_TranNgocPhu_ASM.Controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import com.example.PS13223_TranNgocPhu_ASM.DAO.CategoryDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Category;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CategoryManagerController {
    @Autowired
    CategoryDAO Catedao;
    @RequestMapping("/Categories")
    public String show(Model model){
          // load category
          Category item = new Category();
          model.addAttribute("item", item);
          List<Category> Cateitems = Catedao.findAll();
          model.addAttribute("Category", Cateitems);
         return "Admin/Category";
    }


    @RequestMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Category item = Catedao.findById(id).get();
        model.addAttribute("item", item);
        List<Category> items = Catedao.findAll();
        model.addAttribute("Category", items);
        return "Admin/Category";
    }

    @RequestMapping("/category/save")
    public String create(Category item) {
        Catedao.save(item);
        return "redirect:/Categories";
    }

    @RequestMapping("/category/update")
    public String update(Category item, Model model) {
        try {
            Catedao.save(item);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/category/edit/" + item.getId();
    }

    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            Catedao.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/Categories";
    }

    
}
