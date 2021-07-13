package com.example.PS13223_TranNgocPhu_ASM.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.example.PS13223_TranNgocPhu_ASM.DAO.ProductDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Controller
public class ProductManagerController {
    @Autowired
    ProductDAO proDao;
    @RequestMapping("/Product")
    public String show(Model model){
          // load product
          Product  item = new Product();
          model.addAttribute("item", item);
          List<Product> AccItems = proDao.findAll();

          // @RequestParam("p") Optional<Integer> p
          //Pageable pageable = PageRequest.of(p.orElse(0), 5);  //lỗi phân trang bị xung đột với hàm edit
		  //Page<Product> page = dao.findAll(pageable);
          model.addAttribute("Product", AccItems);
         return "Admin/Product";
    }


    @RequestMapping("/product/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Product item = proDao.findById(id).get();
        model.addAttribute("item", item);
        List<Product> items = proDao.findAll();
        model.addAttribute("Product", items);
        return "Admin/Product";
    }

    @RequestMapping("/product/save")
    public String create(Product item) {
        proDao.save(item);
            // // save the file on the local file system    //lỗi không copy được file vào resource
            // @requestparam("image") MultipartFile image;
            // try {
            //     Path path = Paths.get("/image" + image.getOriginalFilename());
            //     Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        return "redirect:/Product";
    }

    @RequestMapping("/product/update")
    public String update(Product item, Model model) {
        try {
            proDao.save(item);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/product/edit/" + item.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            proDao.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("message", "Có Lỗi Xảy Ra,Có Thể Bạn Đang Xóa Hoặc Điều Chỉnh Khóa Ngoại Của Bảng Khác");
        }
        return "redirect:/Product";
    }
}
