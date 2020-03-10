package com.myblog.blog.web.admin;

import com.myblog.blog.service.BlogService;
import com.myblog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminFragController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listBlogTop(3));

        return "admin/_fragments :: newblogList";
    }

    @GetMapping("/footer/callme")
    public String callme(Model model){
        model.addAttribute("callme",userService.listUser());
        return "admin/_fragments :: callmeList";
    }
}
