package com.example.blog.controller;

import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    private BlogService blogService;



    // Danh sách tất cả bài viết
    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("blogs", blogService.getBlogInfo());
        return "web/index";
    }
}
