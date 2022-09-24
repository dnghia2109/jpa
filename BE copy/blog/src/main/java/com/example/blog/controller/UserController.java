package com.example.blog.controller;

import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("admin/users")
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/user/user-index";
    }

    @GetMapping("admin/users/{id}/detail")
    public String getUserDetail(@PathVariable int id) {
        return "admin/user/user-detail";
    }

}
