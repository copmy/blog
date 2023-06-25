package com.site.blog.my.core.service.impl;


import com.site.blog.my.core.dao.UserMapper;
import com.site.blog.my.core.entity.User;
import com.site.blog.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feixing
 * @date 2023/6/20 23:09
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        User user = userMapper.loginUser(userName,password);
        return user;
    }

    @Override
    public List<User> AllUsers() {
        List<User> users = userMapper.AllUsers();
        return users;
    }

    @Override
    public int delete(Integer id) {
        Integer result = userMapper.deleteOneUser(id);
        return  result;
    }

    @Override
    public int addUser(User user) {
        Integer result = userMapper.addUser(user);
        return result;
    }

    @Override
    public User selectOneUser(Integer id) {
        User user = userMapper.selectOneUser(id);
        return user;
    }

    @Override
    public Integer updateUser(User user) {
        Integer result = userMapper.updateUser(user);
        return result;
    }
}
