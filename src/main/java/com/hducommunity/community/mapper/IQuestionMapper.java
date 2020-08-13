package com.hducommunity.community.mapper;

import com.hducommunity.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IQuestionMapper {
    @Insert("Insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
