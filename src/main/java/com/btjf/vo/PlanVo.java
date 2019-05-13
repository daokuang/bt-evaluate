package com.btjf.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by yj on 2018/11/20.
 */
public class PlanVo implements Serializable{

    private static final long serialVersionUID = 9042139369548071719L;

    @ApiModelProperty(value="首付")
    private Double downPayments;

    @ApiModelProperty(value="月供")
    private Double monthlySupply;

    @ApiModelProperty(value="期数")
    private Integer repaymentPeriods;

    @ApiModelProperty(value="客户提前付")
    private Double advancePayment;

    @ApiModelProperty(value="预计收入")
    private Double expectedIncome;

    @ApiModelProperty(value="来靓车收购价")
    private Double llcBuyPrice;

    @ApiModelProperty(value="收益")
    private Double profits;

    @ApiModelProperty(value="报价最后有效期")
    private Long lastValidTime;

    @ApiModelProperty(value="价签打印url(价签的实际效果图链接)")
    private String labelUrl;


}
