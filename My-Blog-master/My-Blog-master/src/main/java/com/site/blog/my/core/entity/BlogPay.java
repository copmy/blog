package com.site.blog.my.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BlogPay {

    //订单号
    private Integer out_trade_no;
    //支付类型：微信、支付宝
    private Byte payType;
    //支付名称
    private String payName;
    //支付状态
    private String subject;
    //交易凭证号
    private Integer trade_no;
    //支付金额
    private Double total_amount;
    //用户ID
    private Integer adminUserId;
    //付款时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Byte isDeleted;


}