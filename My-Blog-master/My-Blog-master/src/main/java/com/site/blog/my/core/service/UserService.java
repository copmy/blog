package com.site.blog.my.core.service;


import com.site.blog.my.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User login(String userName, String password);
    List<User> AllUsers();
    int delete(Integer id);
    int addUser(User user);
    User selectOneUser(Integer id);
    Integer updateUser(User user);
}
