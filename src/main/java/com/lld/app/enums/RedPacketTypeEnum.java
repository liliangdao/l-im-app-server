package com.lld.app.enums;

public enum RedPacketTypeEnum {

    /**
     *  个人红包
     */
    P2P(1000),

    /**
     *  群普通红包
     */
    GROUPAVG(2000),

    /**
     *  群拼手气红包
     */
    GROUPLUCK(2001),

    ;

    private int type;

    RedPacketTypeEnum(int type){
        this.type=type;
    }

    public int getType() {
        return type;
    }
}
