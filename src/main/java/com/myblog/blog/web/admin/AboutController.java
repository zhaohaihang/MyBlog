package com.myblog.blog.web.admin;

import com.myblog.blog.po.User;
import com.myblog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jnlp.UnavailableServiceException;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AboutController {

    @Autowired
    private UserService userService;

    @GetMapping("/about")
    public String types(Model model){
        model.addAttribute("user",userService.listUser().get(0));
        return "admin/about";
    }

    @GetMapping("/about/{id}/input")
    public  String  editInput(@PathVariable Long id , Model model){
        model.addAttribute("user",userService.getUser(id));
        return "admin/about-input";
    }

    @PostMapping("/about/{id}")
    public String editPost(@Valid User user, BindingResult result,@PathVariable Long id ,RedirectAttributes attributes){

        User u = userService.updateUser(id,user);

        if (u==null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/about";
    }

}
