package com.heartsuit.showcase.roa;

import com.heartsuit.showcase.domain.Administrators;
import com.heartsuit.showcase.domain.application.Application;
import com.heartsuit.showcase.domain.user.Expert;
import com.heartsuit.showcase.service.impl.AdministratorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/admin")
@Api(tags = "管理员接口")
public class AdministratorController {
    private AdministratorServiceImpl administratorServiceImpl;

    /**
     * 自动注入
     * @param administratorServiceImpl administratorServiceImpl
     */
    @Autowired
    public AdministratorController(AdministratorServiceImpl administratorServiceImpl) {
        this.administratorServiceImpl = administratorServiceImpl;
    }


    @PostMapping("/invite/expert")
    @ApiOperation(value = "邀请专家", notes = "需要专家邮箱, 前提要有专家门户页")
    @ResponseBody
    public String inviteExpert(@RequestBody Expert expert) throws Exception {
        administratorServiceImpl.inviteExpert(expert);
        return "true";
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增管理员", notes = "传入管理员邮箱密码")
    @ResponseBody
    public String insert(@RequestBody Administrators administrators) throws Exception {
        administratorServiceImpl.insert(administrators);
        return "true";
    }

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录", notes = "传入管理员邮箱密码")
    @ResponseBody
    public String login(@RequestBody Administrators administrators) throws Exception {
        return administratorServiceImpl.login(administrators);
    }

    @PostMapping("/approval")
    @ApiOperation(value = "审批通过", notes = "传入application Id， email， name")
    @ResponseBody
    public String approval(@RequestBody Application application) {
        administratorServiceImpl.approval(application);
        return "true";
    }


    @PostMapping("/disApproval")
    @ApiOperation(value = "审批拒绝" , notes = "传入application Id， email， name")
    @ResponseBody
    public String disApproval(@RequestBody Application application) {
        administratorServiceImpl.disApproval(application);
        return "true";
    }
}
