package com.site.blog.my.core.controller.admin;


import com.site.blog.my.core.entity.User;
import com.site.blog.my.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class UserManagerController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    public String list(HttpServletRequest request, Model model) {
        request.setAttribute("path", "users");
        List<User> userList = userService.AllUsers();
        model.addAttribute("userList",userList);
        return "admin/user";
    }
    @GetMapping("/users/delete")
    public String delete(HttpServletRequest request, Model model,@RequestParam("user_id") Integer id) {
        request.setAttribute("path", "users");
        int delete = userService.delete(id);
        System.out.println(delete);
        return "redirect:/admin/users";
    }
    @GetMapping("/users/update")
    public String user_edit(HttpServletRequest request, Model model,@RequestParam("user_id") Integer id){
        User user = userService.selectOneUser(id);
        model.addAttribute("user",user);
        return "admin/useredit";
    }
    @PostMapping("/users/update")
    public String update(HttpServletRequest request, Model model,
                         @RequestParam("user_id") Integer id,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("sex") String sex,
                         @RequestParam("phone") String phone,
                         @RequestParam("status") String status
                         ){
        request.setAttribute("path", "users");
        User oldUser = userService.selectOneUser(id);
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        if (sex == null){
            user.setSex(oldUser.getSex());
        }else {
            user.setSex(sex);
        }
        user.setPhone(phone);
        user.setStatus(status);
        Integer result = userService.updateUser(user);
        System.out.println(result);
        return "redirect:/admin/users";
    }



}
