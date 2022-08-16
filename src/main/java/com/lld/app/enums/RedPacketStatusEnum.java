package com.lld.app.enums;

public enum RedPacketStatusEnum {


    /**
     *  可抢
     */
    CANOPEN(0),

    /**
     *  删除
     */
    FINISH(1),

    /**
     *  过期
     */
    EXPIRE(2),

    /**
     *  已经抢过 , 不入库
     */
    ISOPENED(3)
    ;

    private int status;

    RedPacketStatusEnum(int status){
        this.status=status;
    }

    public int getStatus() {
        return status;
    }
}
