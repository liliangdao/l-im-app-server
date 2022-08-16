package com.lld.app.controller;

import com.lld.app.common.ResponseVO;
import com.lld.app.model.req.LoginReq;
import com.lld.app.model.req.ReportReq;
import com.lld.app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 15:06
 **/
@RestController
@RequestMapping("v1/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/getReportTagByParentId")
    public ResponseVO getReportTagByParentId(Integer parentId) {
        return reportService.getReportTagByParentId(parentId);
    }

    @GetMapping("/getReportTagRoot")
    public ResponseVO getReportTagRoot() {
        return reportService.getReportTagRoot();
    }

    @PostMapping("/report")
    public ResponseVO report(@RequestBody @Validated ReportReq req) {
        return reportService.report(req);
    }
}
