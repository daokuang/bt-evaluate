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

    public Double getDownPayments() {
        return downPayments;
    }

    public void setDownPayments(Double downPayments) {
        this.downPayments = downPayments;
    }

    public Double getMonthlySupply() {
        return monthlySupply;
    }

    public void setMonthlySupply(Double monthlySupply) {
        this.monthlySupply = monthlySupply;
    }

    public Integer getRepaymentPeriods() {
        return repaymentPeriods;
    }

    public void setRepaymentPeriods(Integer repaymentPeriods) {
        this.repaymentPeriods = repaymentPeriods;
    }

    public Double getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(Double advancePayment) {
        this.advancePayment = advancePayment;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Double getLlcBuyPrice() {
        return llcBuyPrice;
    }

    public void setLlcBuyPrice(Double llcBuyPrice) {
        this.llcBuyPrice = llcBuyPrice;
    }

    public Double getProfits() {
        return profits;
    }

    public void setProfits(Double profits) {
        this.profits = profits;
    }

    public Long getLastValidTime() {
        return lastValidTime;
    }

    public void setLastValidTime(Long lastValidTime) {
        this.lastValidTime = lastValidTime;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }
}
