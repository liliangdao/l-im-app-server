package com.lld.app.enums;

public enum ReportProcessStatusEnum {

    /**
     * 1 已收到；2 有问题 3无问题
     */
    RECEIVE(0),

    PROBLEM(1),

    NORMAL(2)
    ;

    private int status;

    ReportProcessStatusEnum(int status){
        this.status=status;
    }

    public int getStatus() {
        return status;
    }
}
