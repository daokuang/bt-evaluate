package com.btjf.controller;

import com.btjf.application.util.XaResult;
import com.btjf.common.utils.HttpClientUtil;
import com.btjf.common.utils.JSONUtils;
import com.btjf.model.Evaluate;
import com.btjf.model.Question;
import com.btjf.model.User;
import com.btjf.response.PersonResponse;
import com.btjf.response.UserResponse;
import com.btjf.service.EvaluateServie;
import com.btjf.service.QuestionServie;
import com.btjf.service.UserServie;
import com.heige.aikajinrong.base.util.DateUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "EvaluateController", description = "评价", position = 1)
@RequestMapping(value = "/api/evaluate/")
@RestController("evaluateController")
public class EvaluateController {
    /** LOGGER */
    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateController.class);

    @Resource
    private EvaluateServie evaluateServie;
    @Resource
    private QuestionServie questionServie;

    @Value("call.persons.url")
    private String persons_url;
    /**
     * 叫号
     *
     * @return
     */
    @RequestMapping(value = "/call", method = RequestMethod.POST)
    public XaResult<Integer> call(@ApiParam("中心号") String centerId,
                                  @ApiParam("窗口号") String windowId,
                                    @ApiParam("公务员用户名") String username,
                                   @ApiParam("公务员身份证") String idCard,
                                    @ApiParam("叫号") String callCode,
                                  @ApiParam("办事人姓名") String name,
                                  @ApiParam("办事人手机号") String telphone,
                                  @ApiParam("办事事项") String affairName
                                  ) {
        if(StringUtils.isEmpty(windowId)){
            return XaResult.error("窗口号不能为空");
        }
        if(StringUtils.isEmpty(centerId)){
            return XaResult.error("中心号不能为空");
        }
        if(StringUtils.isEmpty(idCard)){
            return XaResult.error("公务员身份证不能为空");
        }
        if(StringUtils.isEmpty(callCode)){
            return XaResult.error("叫号不能为空");
        }

        String result = HttpClientUtil.sendGetRequest(persons_url+ centerId +"/" + windowId);
        if(StringUtils.isEmpty(result)){
            return XaResult.error("获取窗口工作人员信息失败");
        }
        LOGGER.info("获取窗口工作人员信息!返回：" + result);
        PersonResponse personResponse = JSONUtils.toBean(result, PersonResponse.class);

        Evaluate evaluate = new Evaluate();
        evaluate.setDeptID(null);
        evaluate.setDeptName("");
        evaluate.setWindowId(windowId);
        evaluate.setCenterId(centerId);
        evaluate.setStaffID("");
        evaluate.setStaffName("");
        if(personResponse != null){
            evaluate.setCustName(personResponse.getName());
            evaluate.setCustMobile(personResponse.getTelphone());
            evaluate.setItemID(null);
            evaluate.setItemName(personResponse.getAffairName());
            evaluate.setCallCode(personResponse.getCallCode());
        }
        evaluate.setCreateDate(new Date());
        evaluate.setBeginTime(new Date());

        evaluateServie.save(evaluate);
        //TODO 传递给前端
        evaluate.getID();
        return XaResult.success();
    }


    /**
     * 回答评价
     *
     * @return
     */
    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public XaResult<Integer> answer(@ApiParam("评价记录ID") Integer recordID,
                                  @ApiParam("回答 1 是 2否") Integer answer) {
        if(recordID == null){
            return XaResult.error("评价记录ID不能为空");
        }
        if(answer == null){
            return XaResult.error("回答不能为空");
        }

        evaluateServie.answer(recordID, answer);
        return XaResult.success();
    }

    /**
     * 评价星级
     *
     * @return
     */
    @RequestMapping(value = "/star", method = RequestMethod.POST)
    public XaResult<Integer> star(@ApiParam("评价记录ID") Integer recordID,
                                    @ApiParam("1 非常不满意 2不满意 3基本满意 4满意 5非常满意") Integer star,
                                  @ApiParam("标签组") String[] labels) {
        if(recordID == null){
            return XaResult.error("评价记录ID不能为空");
        }
        if(star == null){
            return XaResult.error("星级不能为空");
        }
        if(labels ==null || labels.length < 1){
            return XaResult.error("标签组不能为空");
        }

        evaluateServie.label(recordID, labels);
        evaluateServie.star(recordID, star);
        return XaResult.success();
    }

    /**
     * 评价标签
     *
     * @return
     */
    @RequestMapping(value = "/label", method = RequestMethod.POST)
    public XaResult<Integer> label(@ApiParam("评价记录ID") Integer recordID,
                                  @ApiParam("标签组") String[] labels) {
        if(recordID == null){
            return XaResult.error("评价记录ID不能为空");
        }
        if(labels ==null || labels.length < 1){
            return XaResult.error("标签组不能为空");
        }

        evaluateServie.label(recordID, labels);
        return XaResult.success();
    }

    /**
     * 办事完成
     *
     * @return
     */
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public XaResult<Integer> complete(@ApiParam("中心号") String centerId,
                                      @ApiParam("窗口号") String windowId,
                                      @ApiParam("叫号") String callCode) {
        if(StringUtils.isEmpty(windowId)){
            return XaResult.error("窗口号不能为空");
        }
        if(StringUtils.isEmpty(centerId)){
            return XaResult.error("中心号不能为空");
        }
        if(StringUtils.isEmpty(callCode)){
            return XaResult.error("叫号不能为空");
        }

        evaluateServie.complete(centerId, windowId, callCode);
        //TODO 传递给前端
        return XaResult.success();
    }


    /**
     * 获取问题
     *
     * @return
     */
    @RequestMapping(value = "/getQuestion", method = RequestMethod.GET)
    public XaResult<String> getQuestion(@ApiParam("评价记录ID") Integer recordID) {
        if(recordID == null){
            return XaResult.error("评价记录ID不能为空");
        }
        List<Question> list = questionServie.find();
        if(list != null && list.size() >0) {
            Random rand = new Random();
            Question question = list.get(rand.nextInt(list.size()));
            evaluateServie.question(recordID, question.getQuestion());
            return XaResult.success(question.getQuestion());
        }else{
            return XaResult.error("获取问题失败");
        }
    }




}
