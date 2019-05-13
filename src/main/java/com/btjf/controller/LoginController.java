package com.btjf.controller;

import com.btjf.application.util.XaResult;
import com.btjf.file.domain.FileRecordDomain;
import com.btjf.model.User;
import com.btjf.service.UserServie;
import com.btjf.vo.PlanVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "LoginController", description = "登录", position = 1)
@RestController("loginController")
public class LoginController {

    @Resource
    private UserServie userServie;

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public XaResult<String> login(@ApiParam("登录名") String name,
                                    @ApiParam("密码") String pwd) {
        if(StringUtils.isEmpty(name)){
            return XaResult.error("用户名不能为空");
        }
        if(StringUtils.isEmpty(pwd)){
            return XaResult.error("密码不能为空");
        }
        User user = userServie.login(name,pwd);
        if(user !=null){
            return XaResult.success();
        }
        return XaResult.error("用户名或密码错误");
    }




}
