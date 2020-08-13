package com.hducommunity.community.service.impl;

import com.hducommunity.community.model.Question;
import com.hducommunity.community.service.IQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: community
 * @description: 问题渲染服务类
 * @author: LiHongyan
 * @create: 2020-08-13 23:48
 **/
@Service
public class QuestionService implements IQuestionService {

    @Override
    public List<Question> list() {
        return null;
    }
}
