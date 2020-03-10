package com.myblog.blog.web;

import com.myblog.blog.po.User;
import com.myblog.blog.service.BlogService;
import com.myblog.blog.service.TagService;
import com.myblog.blog.service.TypeService;
import com.myblog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutShowController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;

    @GetMapping("/about")
    public String index(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable , Model model){
        model.addAttribute("types",typeService.listTypeTop(8));
        model.addAttribute("tags",tagService.listTagTop(8));
        model.addAttribute("users",userService.listUser());
        return "about";
    }

}
