package com.btjf.controller;

import com.btjf.application.util.XaResult;
import com.btjf.model.User;
import com.btjf.service.EvaluateServie;
import com.btjf.service.UserServie;
import com.btjf.vo.IndexInfoVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "HomeController", description = "主页信息", position = 1)
@RequestMapping(value = "/home/")
@RestController("homeController")
public class HomeController {

    @Resource
    private EvaluateServie evaluateServie;

    /**
     * 主页信息
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public XaResult<IndexInfoVo> login(@ApiParam("窗口号") Integer windowNo) {
        if(windowNo == null){
            return XaResult.error("窗口号不能为空");
        }

        IndexInfoVo indexInfoVo = new IndexInfoVo();

        Double avgScore = evaluateServie.averageScore("");
        Double avgTime = evaluateServie.averageTime("");

        return XaResult.success(indexInfoVo);
    }




}
