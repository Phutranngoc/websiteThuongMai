package com.example.PS13223_TranNgocPhu_ASM.Controller;

import java.util.List;

import com.example.PS13223_TranNgocPhu_ASM.DAO.CategoryDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaoVeController {
    @Autowired
    CategoryDAO Catedao;
    @RequestMapping("/BaoVeCategories")
    public String show(Model model){
          // load category
          Category item = new Category();
          model.addAttribute("item", item);
          List<Category> Cateitems = Catedao.findAll();
          model.addAttribute("Category", Cateitems);
         return "BaoVeASM/danhmuc";
    }


    @RequestMapping("/baovecategory/edit1/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Category item = Catedao.findById(id).get();
        model.addAttribute("item", item);
        List<Category> items = Catedao.findAll();
        model.addAttribute("Category", items);
        return "BaoVeASM/danhmuc";
    }

    @RequestMapping("/baovecategory/save1")
    public String create(Category item) {
        Catedao.save(item);
        return "redirect:/BaoVeCategories";
    }

    @RequestMapping("/baovecategory/update1")
    public String update(Category item, Model model) {
        try {
            Catedao.save(item);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/baovecategory/edit1/" + item.getId();
    }

    @RequestMapping("/baovecategory/delete1/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            Catedao.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/BaoVeCategories";
    }
}
