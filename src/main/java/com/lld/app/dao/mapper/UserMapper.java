package com.lld.app.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lld.app.dao.User;
import com.lld.app.model.req.SearchUserReq;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {


    @Select("<script>" +
            " select user_id from app_user  " +
            "<if test = 'searchType == 1'> " +
            " where mobile = #{keyWord} " +
            " </if>" +
            " <if test = 'searchType == 2'> " +
            "  where user_name = #{keyWord} " +
            " </if> " +
            " </script> ")
    public List<String> searchUser( SearchUserReq req);

}
