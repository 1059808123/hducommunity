package com.hducommunity.community.mapper;

import com.hducommunity.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avata_url) values (#{name},#{accountId},{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
