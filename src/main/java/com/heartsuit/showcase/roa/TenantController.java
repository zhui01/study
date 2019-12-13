package com.heartsuit.showcase.roa;

import com.heartsuit.showcase.domain.Tenant;
import com.heartsuit.showcase.service.IMailService;
import com.heartsuit.showcase.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019/8/24 0024 16:59
 */
@RestController //@Component 类的注入
@RequestMapping("/tenant")
@Api("租客信息接口")

public class TenantController {
        private static final Logger LOGGER = LogManager.getLogger();
        private TenantService tenantService;
        private IMailService mailService;

        @Autowired
        public TenantController(TenantService tenantService, IMailService mailService) {
                this.tenantService = tenantService;
                this.mailService = mailService;
        }

        /**
         * 新增租客
         *
         * @param tenant 租客信息
         * @return 新增是否成功 string
         */
        @ApiOperation(value = "新增租客", notes = "shiman")
        @PostMapping("/insert")
        @ResponseBody
        public String insert(@RequestBody Tenant tenant) {
                LOGGER.info("test");
                //throw new ErrorCodeException(ErrorCode.ILLEGAL_PARAMETER_10003, tenant.getEmail());
                this.tenantService.insert(tenant);
                return "true";
        }

        /**
         * 查询所有租客
         *
         * @return 所有租客信息 list
         */
        @ApiOperation(value = "查询所有租客")
        @RequestMapping(value = "/queryall", method = RequestMethod.GET)
        @ResponseBody
        public List<Tenant> query() {
                return tenantService.findAll();
        }

}
