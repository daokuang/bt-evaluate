package com.btjf.service;

import com.btjf.common.utils.MD5Utils;
import com.btjf.mapper.QuestionMapper;
import com.btjf.mapper.UserMapper;
import com.btjf.model.Question;
import com.btjf.model.QuestionExample;
import com.btjf.model.User;
import com.btjf.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yj on 2019/5/13.
 */
@Service
public class QuestionServie {

    @Resource
    private QuestionMapper questionMapper;


    public List<Question> find() {
        QuestionExample example = new QuestionExample();
        return questionMapper.selectByExample(example);
    }
}
