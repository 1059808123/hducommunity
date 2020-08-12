package com.hducommunity.community.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: community
 * @description: 主页controller
 * @author: LiHongyan
 * @create: 2020-08-12 11:59
 **/
public class IndexController {
    @GetMapping("/")
    public String index(){return "index";}
}
