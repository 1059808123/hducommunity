package com.hducommunity.community.service;

import com.hducommunity.community.dto.PageDTO;
import com.hducommunity.community.dto.QuestionDto;
import com.hducommunity.community.model.Question;

import java.util.List;

public interface IQuestionService {
    public PageDTO list(Integer page, Integer size);
    public PageDTO list(Integer userId, Integer page, Integer size);
}
