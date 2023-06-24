package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.BlogLink;
import com.site.blog.my.core.entity.BlogPay;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

import java.util.List;
import java.util.Map;

public interface PayService {
    /**
     * 查询支付的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogPayPage(PageQueryUtil pageUtil);

    int getTotalPays();

    Boolean savePay(BlogPay pay);

    BlogPay selectById(Integer id);

    Boolean updatePay(BlogPay tempPay);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回支付页面所需的所有数据
     *
     * @return
     */
    Map<Byte, List<BlogPay>> getPaysForPage();
}
