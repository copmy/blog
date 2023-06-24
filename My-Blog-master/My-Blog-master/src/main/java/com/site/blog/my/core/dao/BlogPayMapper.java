package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogPay;
import com.site.blog.my.core.util.PageQueryUtil;

import java.util.List;

public interface BlogPayMapper {
    int deleteByPrimaryKey(Integer out_trade_no);

    int insert(BlogPay record);

    int insertSelective(BlogPay record);

    BlogPay selectByPrimaryKey(Integer out_trade_no);

    int updateByPrimaryKeySelective(BlogPay record);

    int updateByPrimaryKey(BlogPay record);

    List<BlogPay> findPayList(PageQueryUtil pageUtil);

    int getTotalPays(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);
}