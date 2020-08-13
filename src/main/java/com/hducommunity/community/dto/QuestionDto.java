package com.hducommunity.community.dto;

import com.hducommunity.community.model.User;

/**
 * @program: community
 * @description: 问题渲染时需要的输出数据格式
 * @author: LiHongyan
 * @create: 2020-08-13 23:41
 **/
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
