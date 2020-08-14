package com.hducommunity.community.mapper;

import com.hducommunity.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.PathParam;

@Mapper
public interface IUserMapper {
    @Insert("insert into user (name,accountId,token,gmtCreate,gmtModified,avatarUrl) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken( String token);
    @Select("select * from user where id = #{id}")
    User findById( Integer id);
}
