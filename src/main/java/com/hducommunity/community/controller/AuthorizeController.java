package com.hducommunity.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: community
 * @description: QQ登录APIcontroller
 * @author: LiHongyan
 * @create: 2020-08-12 16:12
 **/
@Controller
public class AuthorizeController {
    @GetMapping("/callback")
    public String callback()
}
