package com.hducommunity.community.service.impl;

import com.hducommunity.community.mapper.IUserMapper;
import com.hducommunity.community.model.User;
import com.hducommunity.community.service.ICreateQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @description: 发起问题的服务类
 * @author: LiHongyan
 * @create: 2020-08-13 19:44
 **/
@Service
public class CreateQuestion implements ICreateQuestion {
    @Autowired(required = false)
    private IUserMapper userMapper;

    public User addQuestion(HttpServletRequest request){
        User result = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        result = user;
                    }
                    break;
                }
            }
        }
        return result;
    }
}
