package com.btjf.controller;


import com.btjf.application.page.PlatformSourceEnum;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.model.Evaluate;
import com.btjf.model.User;
import com.btjf.service.EvaluateServie;
import com.btjf.service.UserServie;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "BackgroundController", description = "后台", position = 1)
@RequestMapping(value = "/api/evaluate/")
@RestController("backgroundController")
public class BackgroundController {

    @Resource
    private EvaluateServie evaluateServie;

    /**
     * 搜索
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public XaResult<List<Evaluate>> search(@ApiParam("当前页") Integer currentPage,
                                           @ApiParam("每页数量") Integer pageSize,
                                           @ApiParam("开始办事日期") String beginDate,
                                           @ApiParam("结束办事日期") String endDate,
                                           @ApiParam("开始办事时间") String beginTime,
                                           @ApiParam("结束办事时间") String endTime,
                                           @ApiParam("办理部") String deptName,
                                           @ApiParam("窗口人员") String staffName,
                                           @ApiParam("窗口号") String windowId,
                                           @ApiParam("办事人员姓名") String custName,
                                           @ApiParam("办事人员手机号") String custMobile,
                                           @ApiParam("办理事项") String itemName,
                                           @ApiParam("提问评价") String question,
                                           @ApiParam("评价结果") String answer,
                                           @ApiParam("星级") String evaluate
                                           ) {
        Page page = new Page(pageSize, currentPage);
        if (currentPage == null) {
            return XaResult.error("当前页不能为空");
        }
        if (pageSize == null) {
            return XaResult.error("当前页不能为空");
        }

        page = evaluateServie.search(page,beginDate,endDate,beginTime,endTime,deptName,staffName,windowId,custName,custMobile,itemName,question,answer,evaluate);



        XaResult xaResult = new XaResult();
        xaResult.setSuccess(page.getRows());
        xaResult.setPage(new com.btjf.application.page.Page(page, PlatformSourceEnum.APP));
        return xaResult;
    }




}
