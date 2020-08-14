package com.hducommunity.community.controller;

import com.hducommunity.community.mapper.IQuestionMapper;
import com.hducommunity.community.mapper.IUserMapper;
import com.hducommunity.community.model.Question;
import com.hducommunity.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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
    @Autowired(required = false)
    private IUserMapper userMapper;


    @GetMapping("/publish")
    public String publih(){
        System.out.println("1");
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        @RequestParam("tag") String tag,
        HttpServletRequest request,
        Model model
    ){
        model.addAttribute("title",title);
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
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                     user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        model.addAttribute("user",user);
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
