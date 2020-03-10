package com.myblog.blog.service;

import com.myblog.blog.po.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);
    List<User> listUser();
    User getUser(Long id);
    User updateUser(Long id,User user);
}
