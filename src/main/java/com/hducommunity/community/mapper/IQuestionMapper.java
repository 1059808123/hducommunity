package com.hducommunity.community.mapper;

import com.hducommunity.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface IQuestionMapper {
    @Insert("Insert into question(title,description,gmtCreate,gmtModified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}")
    void create(Question question);

    @Select("Select count(1) from question")
    Integer count();

    @Select("Select * from question limit #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("Select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(Integer userId,Integer offset, Integer size);

    @Select("Select count(1) from question where creator = #{userId}")
    Integer countByUserId(Integer userId);
}
