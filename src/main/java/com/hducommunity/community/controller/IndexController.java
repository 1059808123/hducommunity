package com.hducommunity.community.controller;

import com.hducommunity.community.mapper.IQuestionMapper;
import com.hducommunity.community.mapper.IUserMapper;
import com.hducommunity.community.model.Question;
import com.hducommunity.community.model.User;
import com.hducommunity.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: community
 * @description: 主页controller
 * @author: LiHongyan
 * @create: 2020-08-12 11:59
 **/
public class IndexController {
    @Autowired
    private IUserMapper iUserMapper;
    @Autowired
    private IQuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = iUserMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<Question> questionList = questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }
}
