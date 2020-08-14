package com.hducommunity.community.controller;

import com.hducommunity.community.dto.PageDTO;
import com.hducommunity.community.mapper.IUserMapper;
import com.hducommunity.community.model.User;
import com.hducommunity.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

/**
 * @program: community
 * @description:
 * @author: LiHongyan
 * @create: 2020-08-15 04:00
 **/
@Controller
public class ProfileController {

    @Autowired
    private IQuestionService questionService;

    @Autowired(required = false)
    private IUserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "5")Integer size,
                          HttpServletRequest request){
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
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新消息");
        }

        PageDTO pageDTO = questionService.list(user.getId(),page,size);
        model.addAttribute("pageAll",pageDTO);
        return "profile";
    }
}
