package com.heartsuit.showcase.roa;

import com.heartsuit.showcase.domain.user.Expert;
import com.heartsuit.showcase.service.impl.ExpertServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/expert")
@Api(tags = "专家门户页")
public class ExpertController {
    private ExpertServiceImpl expertService;

    @Autowired
    public ExpertController(ExpertServiceImpl expertService) {
        this.expertService = expertService;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改专家门户页", notes = "号外号外，修改专家门户页，如果参数未全部传入，那么页面就空咯")
    @ResponseBody
    public String updateProcessedStatus(@RequestBody Expert expert) {
        expertService.update(expert);
        return "true";
    }

    @GetMapping("/queryall")
    @ApiOperation(value = "查询所有专家门户页", notes = "不用传入参")
    @ResponseBody
    public List<Expert> queryAll() {
        return expertService.queryAll();
    }


    @PostMapping("/insert")
    @ApiOperation(value = "新增专家门户页", notes = "号外号外，新增专家门户页，如果参数未全部传入，那么页面就空咯")
    @ResponseBody
    public String insert(@RequestBody Expert expert) {
        expertService.insert(expert);
        return "true";
    }

    @PostMapping("/querybyid")
    @ApiOperation(value = "根据专家id查询专家门户页", notes = "请传入专家门户页id")
    @ResponseBody
    public Expert queryById(@RequestBody Expert expert) {
        return expertService.queryById(expert);
    }

    @PostMapping("/add/achievement")
    @ApiOperation(value = "根据专家邮箱新增成果", notes = "请传入专家门户页邮箱，以及最新成果id")
    @ResponseBody
    public String addAchievementByEmail(@RequestBody Expert expert) {
        expertService.addAchievementByEmail(expert);
        return "true";
    }
}
