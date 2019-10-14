package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.Type;
import com.codegym.repository.TypeRepository;
import com.codegym.service.ProductService;
import com.codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    TypeService typeService;
    @ModelAttribute("types")
    public Iterable<Type> types(Pageable pageable){
        return typeService.findAll(pageable);
    }
    @GetMapping("/list")
    public ModelAndView listProduct(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Product> products;
        if(s.isPresent()){
            products = productService.findByName(s.get(),pageable);
        }
        else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products",products);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView editProduct(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product",product);
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        Product product = productService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product = productService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;

    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.delete(product.getId());
        return "redirect:/product/list";
    }
}
