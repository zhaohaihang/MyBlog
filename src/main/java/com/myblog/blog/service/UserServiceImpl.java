package com.myblog.blog.service;

import com.myblog.blog.NotFoundException;
import com.myblog.blog.dao.UserRepository;
import com.myblog.blog.po.User;
import com.myblog.blog.util.MD5Utils;
import com.myblog.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        //User user =userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        User user =userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) {
        User u=userRepository.getOne(id);
        if (u==null){
            throw new NotFoundException("不存在该用户");
        }
        BeanUtils.copyProperties(user,u,MyBeanUtils.getNullPropertyNames(user));

        return userRepository.save(u);
    }

}
