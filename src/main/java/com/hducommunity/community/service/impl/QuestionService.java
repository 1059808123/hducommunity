package com.hducommunity.community.service.impl;

import com.hducommunity.community.dto.PageDTO;
import com.hducommunity.community.dto.QuestionDto;
import com.hducommunity.community.mapper.IQuestionMapper;
import com.hducommunity.community.mapper.IUserMapper;
import com.hducommunity.community.model.Question;
import com.hducommunity.community.model.User;
import com.hducommunity.community.service.IQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: community
 * @description: 问题渲染服务类
 * @author: LiHongyan
 * @create: 2020-08-13 23:48
 **/
@Service
public class QuestionService implements IQuestionService {

    @Autowired(required = false)
    private IUserMapper userMapper;

    @Autowired(required = false)
    private IQuestionMapper questionMapper;

    @Override
    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        pageDTO.setNeedPage(totalPage,page);
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question : questionList){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }

        pageDTO.setQuestions(questionDtoList);

        return pageDTO;
    }

    @Override
    public PageDTO list(Integer userId, Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        pageDTO.setNeedPage(totalPage,page);
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question : questionList){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }

        pageDTO.setQuestions(questionDtoList);

        return pageDTO;
    }

}
