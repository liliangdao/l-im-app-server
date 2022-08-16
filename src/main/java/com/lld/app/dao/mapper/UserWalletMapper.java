package com.lld.app.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lld.app.dao.RedPacketDetail;
import com.lld.app.dao.UserWallet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserWalletMapper extends BaseMapper<UserWallet> {

    @Update("update account_wallet set amount = amount + #{amount} where user_id = #{userId} and amount + #{amount} >= 0")
    public int updateAmount(@Param("userId") Integer userId, @Param("amount") BigDecimal amount);

}
