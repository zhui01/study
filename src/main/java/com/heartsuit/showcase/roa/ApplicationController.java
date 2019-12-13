package com.heartsuit.showcase.roa;

import com.heartsuit.showcase.domain.application.Application;
import com.heartsuit.showcase.service.impl.ApplicationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/application")
@Api(tags = "申请接口")
public class ApplicationController {
    private ApplicationServiceImpl applicationService;

    @Autowired
    public ApplicationController(ApplicationServiceImpl applicationService) {
        this.applicationService = applicationService;
    }

    // 接口暂不通过Swagger对外提供
    @PostMapping("/update")
    @ResponseBody
    public String updateProcessedStatus(@RequestBody Application application) {
        applicationService.update(application); //!
        return "true";
    }

    @GetMapping("/queryall")
    @ApiOperation(value = "查询所有申请列表", notes = "不用传参")
    @ResponseBody
    public List<Application> queryAll() {
        return applicationService.queryAll();
    }


    @PostMapping("/insert")
    @ApiOperation(value = "插入认领/建立/修改门户页的申请表" , notes = "邮箱，名称和用户id是必须传入的而且是有效的；其他参数选传")
    @ResponseBody
    public String insert(@RequestBody Application application) {
        applicationService.insert(application);
        return "true";
    }

    @PostMapping("/querybyid")
    @ApiOperation(value = "根据申请id查询申请表", notes = "请传入申请表id")
    @ResponseBody
    public Application queryById(@RequestBody Application application) {
        return applicationService.queryById(application);
    }
}
