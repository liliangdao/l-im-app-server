package com.lld.app.service;

import com.lld.app.common.ResponseVO;
import com.lld.app.model.req.ReportReq;

public interface ReportService {

    public ResponseVO getReportTagByParentId(int parentId);

    public ResponseVO getReportTagRoot();

    public ResponseVO report(ReportReq req);

}
