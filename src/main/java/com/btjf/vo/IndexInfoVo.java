package com.btjf.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yj on 2019/5/13.
 */
public class IndexInfoVo  implements Serializable {

    private static final long serialVersionUID = -4867688660302561905L;

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="工号")
    private String staffID;

    @ApiModelProperty(value="工作年限")
    private String workingYear;

    @ApiModelProperty(value="照片")
    private String picture;

    @ApiModelProperty(value="所属部门")
    private String dept;

    @ApiModelProperty(value="本月评价平均分")
    private String score;

    @ApiModelProperty(value="平均时长")
    private String duration;

    @ApiModelProperty(value="公告")
    private String notice;

    @ApiModelProperty(value="轮播图列表")
    private List<String> imgs;

    @ApiModelProperty(value="标签列表")
    private List<String> labels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getWorkingYear() {
        return workingYear;
    }

    public void setWorkingYear(String workingYear) {
        this.workingYear = workingYear;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
