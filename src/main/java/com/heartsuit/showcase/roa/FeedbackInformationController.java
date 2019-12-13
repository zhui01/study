package com.heartsuit.showcase.roa;

import com.heartsuit.showcase.domain.FeedbackInformation;
import com.heartsuit.showcase.service.impl.FeedbackInformationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/feedbackinformation")
@Api(tags = "反馈接口")
public class FeedbackInformationController {
    private FeedbackInformationServiceImpl feedbackInformationService;

    @Autowired
    public FeedbackInformationController(FeedbackInformationServiceImpl feedbackInformationService) {
        this.feedbackInformationService = feedbackInformationService;
    }

    @PutMapping("/update/process")
    @ApiOperation(value = "更新处理状态为true", notes = "请传入反馈id")
    @ResponseBody
    public String updateProcessedStatus(@RequestBody FeedbackInformation feedbackInformation) {
        feedbackInformationService.updateProcessedStatus(feedbackInformation);
        return "true";
    }

    @GetMapping("/queryall")
    @ApiOperation(value = "查询所有反馈", notes = "可以不用传参")
    @ResponseBody
    public List<FeedbackInformation> queryAll() {
        return feedbackInformationService.queryAll();
    }

    @GetMapping("/querynotprocessed")
    @ApiOperation(value = "查询所有未处理反馈", notes = "可以不用传参")
    @ResponseBody
    public List<FeedbackInformation> queryNotProcessed() {
        return feedbackInformationService.queryNotProcessed();
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增反馈", notes = "反馈内容，反馈时间，用户id；专家id可传可不传（指要么传入真的专家id，要么就不要传入，该字段所对应的值为空或者\"\"）")
    @ResponseBody
    public String insert(@RequestBody FeedbackInformation feedbackInformation) {
        feedbackInformationService.insert(feedbackInformation);
        return "true";
    }

    @PostMapping("/querybyid")
    @ApiOperation(value = "根据反馈id查询反馈信息", notes = "请传入反馈id")
    @ResponseBody
    public FeedbackInformation queryById(@RequestBody FeedbackInformation feedbackInformation) {
        return feedbackInformationService.queryById(feedbackInformation);
    }
}
