package com.z.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: yuanjz
 * @Date: 2022-08-05 14:20
 * @Description:
 */
@Data
public class Order {

    private Long orderId;

    private Long custId;

    private String custName;

    private String telephone;

    private String address;

    private Integer orderStatus;

    private Date createTime;

    private Double totalCharge;

    private Double preCharge;

    private Integer payStatus;

    private Integer isValid;

    private String description;

    private Date startTime;

    private Date endTime;
}
