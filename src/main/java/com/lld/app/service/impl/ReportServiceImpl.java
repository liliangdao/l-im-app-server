package com.lld.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lld.app.common.ResponseVO;
import com.lld.app.dao.Report;
import com.lld.app.dao.ReportTag;
import com.lld.app.dao.mapper.ReportMapper;
import com.lld.app.dao.mapper.ReportTagMapper;
import com.lld.app.enums.ErrorCode;
import com.lld.app.interceptor.RequestHolder;
import com.lld.app.model.req.ReportReq;
import com.lld.app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 15:10
 **/
@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    ReportTagMapper reportTagMapper;

    @Override
    public ResponseVO getReportTagByParentId(int parentId) {
        QueryWrapper<ReportTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ReportTag::getId,parentId);
        List<ReportTag> reportTags = reportTagMapper.selectList(wrapper);
        return ResponseVO.successResponse(reportTags);
    }

    @Override
    public ResponseVO getReportTagRoot() {
        QueryWrapper<ReportTag> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        List<ReportTag> reportTags = reportTagMapper.selectList(wrapper);
        return ResponseVO.successResponse(reportTags);
    }

    @Override
    public ResponseVO report(ReportReq req) {

        ReportTag reportTag = reportTagMapper.selectById(req.getReportTagId());
        if(reportTag == null){
            return ResponseVO.errorResponse(ErrorCode.REPORT_TAG_IS_NOT_EXIST);
        }

        String userIdStr = RequestHolder.get();
        Integer userId = Integer.valueOf(userIdStr);

        Report report = new Report();
        report.setContent(req.getContent());
        report.setCreateTime(System.currentTimeMillis());
        report.setProcessStatus(0);
        report.setReportTagId(req.getReportTagId());
        report.setReportedId(req.getReportedId());
        report.setUserId(userId);
        reportMapper.insert(report);

        return ResponseVO.successResponse();
    }
}
