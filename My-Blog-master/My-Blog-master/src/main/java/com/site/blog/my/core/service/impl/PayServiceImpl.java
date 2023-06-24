package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.BlogPayMapper;
import com.site.blog.my.core.entity.BlogLink;
import com.site.blog.my.core.entity.BlogPay;
import com.site.blog.my.core.service.PayService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private BlogPayMapper blogPayMapper;


    @Override
    public PageResult getBlogPayPage(PageQueryUtil pageUtil) {
        List<BlogPay> pays = blogPayMapper.findPayList(pageUtil);
        int total = blogPayMapper.getTotalPays(pageUtil);
        System.out.println(total);
        PageResult pageResult = new PageResult(pays, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalPays() {
        return blogPayMapper.getTotalPays(null);
    }

    @Override
    public Boolean savePay(BlogPay pay) {
        return blogPayMapper.insertSelective(pay) > 0;
    }

    @Override
    public BlogPay selectById(Integer out_trade_no) {
        return blogPayMapper.selectByPrimaryKey(out_trade_no);
    }

    @Override
    public Boolean updatePay(BlogPay tempPay) {
        return blogPayMapper.updateByPrimaryKeySelective(tempPay) > 0;
    }


    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogPayMapper.deleteBatch(ids) > 0;
    }

    @Override
    public Map<Byte, List<BlogPay>> getPaysForPage() {
        //获取所有数据
        List<BlogPay> pays = blogPayMapper.findPayList(null);
        if (!CollectionUtils.isEmpty(pays)) {
            //根据type进行分组
            Map<Byte, List<BlogPay>> linksMap = pays.stream().collect(Collectors.groupingBy(BlogPay::getPayType));
            return linksMap;
        }
        return null;
    }
}
