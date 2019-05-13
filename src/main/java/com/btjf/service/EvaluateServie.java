package com.btjf.service;

import com.btjf.mapper.EvaluateLabelMapper;
import com.btjf.mapper.EvaluateMapper;
import com.btjf.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
}
