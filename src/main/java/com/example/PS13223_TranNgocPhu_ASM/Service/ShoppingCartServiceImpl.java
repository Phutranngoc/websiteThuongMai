package com.example.PS13223_TranNgocPhu_ASM.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.PS13223_TranNgocPhu_ASM.DAO.ProductDAO;
import com.example.PS13223_TranNgocPhu_ASM.Entity.Product;
import com.example.PS13223_TranNgocPhu_ASM.Model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;



@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ProductDAO dao;

    Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        // TODO Auto-generated method stub
        Item item = map.get(id);
        if(item == null){
            item = new Item();
            Product p = new Product();
            List<Product> listproducts = dao.findAll();
            p=listproducts.stream().filter(it -> it.getId()==id).collect(Collectors.toList()).get(0);
            item.setId(p.getId());
            item.setName(p.getName());
            item.setPrice(p.getPrice());
            item.setQty(1);
            map.put(id,item);
        }else{
            item.setQty(item.getQty()+1); 
        }
        return item;
    }

    @Override
    public void remove(Integer id) {
        // TODO Auto-generated method stub
        map.remove(id);

    }

    @Override
    public Item update(Integer id, int qty) {
        // TODO Auto-generated method stub
        Item item = map.get(id);
        item.setQty(qty);
        return item;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        map.clear();

    }

    @Override
    public Collection<Item> getItems() {
        // TODO Auto-generated method stub
        return map.values();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return map.values().stream().mapToInt(item -> item.getQty()).sum();
    }

    @Override
    public double getAmount() {
        // TODO Auto-generated method stub
        return map.values().stream().mapToDouble(item -> item.getPrice() * item.getPrice()).sum();
    }

}
