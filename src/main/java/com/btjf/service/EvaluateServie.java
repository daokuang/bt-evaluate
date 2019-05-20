package com.btjf.service;

import com.btjf.common.page.Page;
import com.btjf.mapper.EvaluateLabelMapper;
import com.btjf.mapper.EvaluateMapper;
import com.btjf.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yj on 2019/5/13.
 */
@Service
public class EvaluateServie {

    @Resource
    private EvaluateMapper evaluateMapper;

    @Resource
    private EvaluateLabelMapper evaluateLabelMapper;

    public void save(Evaluate evaluate) {
        evaluateMapper.insertSelective(evaluate);
    }

    public void answer(Integer recordID, Integer answer) {
        EvaluateExample evaluateExample = new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andIDEqualTo(recordID);
        Evaluate evaluate = new Evaluate();
        evaluate.setAnswer(answer);
        evaluateMapper.updateByExampleSelective(evaluate, evaluateExample);
    }


    public void star(Integer recordID, Integer star) {
        EvaluateExample evaluateExample = new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andIDEqualTo(recordID);
        Evaluate evaluate = new Evaluate();
        evaluate.setEvaluate(star);
        evaluateMapper.updateByExampleSelective(evaluate, evaluateExample);
    }


    public void label(Integer recordID, String[] labels) {
        for(int i = 0; i < labels.length; i++) {
            EvaluateLabel evaluateLabel = new EvaluateLabel();
            evaluateLabel.setEvaluateID(recordID);
            evaluateLabel.setLabel(labels[i]);
            evaluateLabelMapper.insertSelective(evaluateLabel);
        }
    }

    public void complete(Integer recordID) {
        Evaluate e = get(recordID);
        EvaluateExample evaluateExample = new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andIDEqualTo(recordID);
        Evaluate evaluate = new Evaluate();
        Date date = new Date();
        evaluate.setEndTime(date);
        evaluate.setDuration((int) ((date.getTime() - e.getBeginTime().getTime())/1000));
        evaluateMapper.updateByExampleSelective(evaluate, evaluateExample);
    }

    private Evaluate get(Integer recordID) {
        return evaluateMapper.selectByPrimaryKey(recordID);
    }


    public void question(Integer recordID, String question) {
        EvaluateExample evaluateExample = new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andIDEqualTo(recordID);
        Evaluate evaluate = new Evaluate();
        evaluate.setQuestion(question);
        evaluateMapper.updateByExampleSelective(evaluate, evaluateExample);
    }

    public Double averageScore(String staffID) {
        return evaluateMapper.averageScore(staffID);
    }

    public Double averageTime(String staffID) {
        return evaluateMapper.averageTime(staffID);
    }

    public List<String> getLabels(String staffID) {
        return evaluateMapper.getLabels(staffID);
    }

    public void complete(String centerId, String windowId, String callCode) {
        Evaluate e = get(centerId, windowId, callCode);
        //不存在 或者 已经结束
        if (e == null || e.getEndTime() != null) {
            return;
        }
        EvaluateExample evaluateExample = new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andIDEqualTo(e.getID());
        Evaluate evaluate = new Evaluate();
        Date date = new Date();
        evaluate.setEndTime(date);
        evaluate.setDuration((int) ((date.getTime() - e.getBeginTime().getTime())/1000));
        evaluateMapper.updateByExampleSelective(evaluate, evaluateExample);
    }

    private Evaluate get(String centerId, String windowId, String callCode) {
        EvaluateExample evaluateExample = new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andCenterIdEqualTo(centerId);
        criteria.andWindowIdEqualTo(windowId);
        criteria.andCallCodeEqualTo(callCode);
        SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = sp.parse(new Date().toString());
            criteria.andCreateDateEqualTo(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        evaluateExample.setOrderByClause("FID desc");
        List<Evaluate> list =  evaluateMapper.selectByExample(evaluateExample);
        if(list != null && list.size() >0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public Page search(Page page, String beginDate, String endDate, String beginTime, String endTime,
                       String deptName, String staffName, String windowId, String custName, String custMobile,
                       String itemName, String question, Integer answer, Integer evaluate) {
        PageHelper.startPage(page.getPage(), page.getRp());//mybatis默认分页从1开始
        List<Evaluate> list = findListForExcel(beginDate, endDate, beginTime, endTime, deptName, staffName,
                windowId, custName, custMobile, itemName, question, answer, evaluate);
        PageInfo<Evaluate> pageInfo = new PageInfo<>(list);
        return new Page(pageInfo);
    }

    public List<Evaluate> findListForExcel(String beginDate, String endDate, String beginTime, String endTime, String deptName,
                                           String staffName, String windowId, String custName, String custMobile, String itemName,
                                           String question, Integer answer, Integer evaluate) {
        EvaluateExample evaluateExample = new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        if(StringUtils.isNotEmpty(windowId)) {
            criteria.andWindowIdEqualTo(windowId);
        }
        if(StringUtils.isNotEmpty(deptName)) {
            criteria.andDeptNameEqualTo(deptName);
        }
        if(StringUtils.isNotEmpty(staffName)) {
            criteria.andStaffNameLike("%" +staffName + "%");
        }
        if(StringUtils.isNotEmpty(custName)) {
            criteria.andCustNameLike("%" +custName + "%");
        }
        if(StringUtils.isNotEmpty(custMobile)) {
            criteria.andCustMobileLike("%" +custMobile + "%");
        }
        if(StringUtils.isNotEmpty(itemName)) {
            criteria.andItemNameEqualTo(itemName);
        }
        if(StringUtils.isNotEmpty(question)) {
            criteria.andQuestionEqualTo(question);
        }
        if(answer != null) {
            criteria.andAnswerEqualTo(answer);
        }
        if(evaluate != null) {
            criteria.andEvaluateEqualTo(evaluate);
        }
        SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat spt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(beginDate)){
                Date date = sp.parse(beginDate);
                criteria.andCreateDateGreaterThanOrEqualTo(date);
            }
            if(StringUtils.isNotEmpty(endDate)){
                Date date = sp.parse(endDate);
                criteria.andCreateDateLessThanOrEqualTo(date);
            }
            if(StringUtils.isNotEmpty(beginTime)){
                Date date = spt.parse(beginTime);
                criteria.andBeginTimeGreaterThanOrEqualTo(date);
            }
            if(StringUtils.isNotEmpty(endTime)){
                Date date = spt.parse(endTime);
                criteria.andEndTimeLessThanOrEqualTo(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        evaluateExample.setOrderByClause("FID desc");
        List<Evaluate> list = evaluateMapper.selectByExample(evaluateExample);
        return list;
    }
}
