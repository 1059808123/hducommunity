package com.hducommunity.community.controller;

import com.hducommunity.community.mapper.IQuestionMapper;
import com.hducommunity.community.model.Question;
import com.hducommunity.community.model.User;
import com.hducommunity.community.service.ICreateQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @description: 帖子页的controller
 * @author: LiHongyan
 * @create: 2020-08-13 16:02
 **/
@Controller
class PublishController {
    @Autowired(required = false)
    private IQuestionMapper iQuestionMapper;
    @Autowired
    private ICreateQuestion iCreateQuestion;

    @GetMapping("/publish")
    public String publih(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "description",required = false) String description,
        @RequestParam(value = "tag",required = false) String tag,
        HttpServletRequest request,
        Model model
    ){
        model.addAttribute("titile",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if(description == null || description == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }

        if(tag == null || tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;
        if((user = iCreateQuestion.addQuestion(request)) == null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        iQuestionMapper.create(question);
        return "redirect:/";
    }
}
