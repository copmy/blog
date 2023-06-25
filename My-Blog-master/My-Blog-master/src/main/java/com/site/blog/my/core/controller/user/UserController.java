package com.site.blog.my.core.controller.user;


import com.site.blog.my.core.entity.User;
import com.site.blog.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author feixing
 * @date 2023/6/20 23:15
 */
@Controller
public class UserController {
    private static String theme = "default";
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/loginIn")
    public String AllBilling(@RequestParam("email") String username,
                             @RequestParam("password") String password) {
        User checkLogin = userService.login(username, password);
        System.out.println(checkLogin);
        if (checkLogin == null) {
            return "user/login";
        } else {
            System.out.println("登录成功");
            return "redirect:/index";
        }
    }

    @RequestMapping("/register")
    public String gisterre() {
        return "user/register";
    }

    @PostMapping("/registerIn")
    public String AllBilling(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("sex") String sex,
                             @RequestParam("phone") String phone) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setPhone(phone);
        user.setStatus("1");
        int result = userService.addUser(user);
        System.out.println(result);
        return "redirect:/login";
    }
}
