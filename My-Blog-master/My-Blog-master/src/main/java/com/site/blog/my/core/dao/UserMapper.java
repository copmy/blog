package com.site.blog.my.core.dao;


import com.site.blog.my.core.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author feixing
 * @date 2023/6/20 22:47
 */
public interface UserMapper {

    @Select("select * from tb_user")
    public List<User> AllUsers();

    @Select("select * from tb_user where id = #{id}")
    public User selectOneUser(Integer id);

    @Select("select * from tb_user where username = #{username} and password = #{password}")
    public User loginUser(@Param("username") String username, @Param("password") String password);

    @Insert("insert into tb_user values(null,#{username},#{password},#{sex},#{phone},#{status})")
    public Integer addUser(User user);

    @Update("update tb_user set username =#{username}," +
            "password = #{password}," +
            "sex = #{sex},phone =#{phone},status =#{status} where id = #{id}")
    public Integer updateUser(User user);

    @Delete("DELETE from tb_user where id = #{id}")
    public Integer deleteOneUser(Integer id);

}
