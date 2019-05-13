package com.btjf.service;

import com.btjf.common.utils.MD5Utils;
import com.btjf.mapper.UserMapper;
import com.btjf.model.User;
import com.btjf.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yj on 2019/5/13.
 */
@Service
public class UserServie {

    @Resource
    private UserMapper userMapper;

    public User login(String name, String pwd) {
        pwd = MD5Utils.ecodeByMD5(pwd);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        criteria.andNameEqualTo(name);
        criteria.andPassWordEqualTo(pwd);
        criteria.andEnableEqualTo(1);
        List<User> list = userMapper.selectByExample(userExample);
        if(list !=null && list.size()> 0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
