package com.site.blog.my.core.config;

/**
 * @Description
 * @Author linhongqi
 * @Data 2023/6/21 11:03
 */

import lombok.Data;
@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;
}
