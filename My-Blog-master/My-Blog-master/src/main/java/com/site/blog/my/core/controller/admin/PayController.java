package com.site.blog.my.core.controller.admin;

import com.site.blog.my.core.entity.BlogLink;
import com.site.blog.my.core.entity.BlogPay;
import com.site.blog.my.core.service.LinkService;
import com.site.blog.my.core.service.PayService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.Result;
import com.site.blog.my.core.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class PayController {

    @Resource
    private PayService payService;

    @GetMapping("/pays")
    public String linkPage(HttpServletRequest request) {
        request.setAttribute("path", "pays");
        return "admin/pay";
    }

    @GetMapping("/pays/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(payService.getBlogPayPage(pageUtil));
    }

    /**
     * 支付信息添加
     */
    @RequestMapping(value = "/pays/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("out_trade_no") Integer out_trade_no,
                       @RequestParam("payType") Byte payType,
                       @RequestParam("payName") String payName,
                       @RequestParam("subject") String subject,
                       @RequestParam("trade_no") Integer trade_no,
                       @RequestParam("total_amount") Double total_amount,
                       @RequestParam("adminUserId") Integer adminUserId) {
        if (payType == null || payType < 0 || trade_no == null || StringUtils.isEmpty(payName) || StringUtils.isEmpty(subject) || total_amount < 0 || adminUserId < 0) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        BlogPay pay = new BlogPay();
        pay.setOut_trade_no(out_trade_no);
        pay.setPayType(payType.byteValue());
        pay.setPayName(payName);
        pay.setSubject(subject);
        pay.setTrade_no(trade_no);
        pay.setTotal_amount(total_amount);
        pay.setAdminUserId(adminUserId);
        pay.setCreateTime(new Date());
        return ResultGenerator.genSuccessResult(payService.savePay(pay));
    }

    /**
     * 详情
     */
    @GetMapping("/pays/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id) {
        BlogPay pay = payService.selectById(id);
        return ResultGenerator.genSuccessResult(pay);
    }

    /**
     * 友链修改
     */
    @RequestMapping(value = "/pays/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("out_trade_no2") Integer out_trade_no,
                         @RequestParam("payType2") Byte payType,
                         @RequestParam("payName2") String payName,
                         @RequestParam("subject2") String subject,
                         @RequestParam("trade_no2") Integer trade_no,
                         @RequestParam("total_amount2") Double total_amount,
                         @RequestParam("adminUserId2") Integer adminUserId) {

        BlogPay tempPay = payService.selectById(out_trade_no);

        if (tempPay == null) {
            return ResultGenerator.genFailResult("无数据！");
        }
        if (payType == null || payType < 0 || trade_no == null || StringUtils.isEmpty(payName) || StringUtils.isEmpty(subject) || total_amount < 0 || adminUserId < 0) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        tempPay.setOut_trade_no(out_trade_no);
        tempPay.setPayType(payType.byteValue());
        tempPay.setPayName(payName);
        tempPay.setSubject(subject);
        tempPay.setTrade_no(trade_no);
        tempPay.setTotal_amount(total_amount);
        tempPay.setAdminUserId(adminUserId);
        tempPay.setCreateTime(new Date());

        return ResultGenerator.genSuccessResult(payService.updatePay(tempPay));

    }

    /**
     * 友链删除
     */
    @RequestMapping(value = "/pays/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (payService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

}
