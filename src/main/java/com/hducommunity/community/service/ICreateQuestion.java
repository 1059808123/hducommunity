package com.hducommunity.community.service;

import com.hducommunity.community.model.User;

import javax.servlet.http.HttpServletRequest;

public interface ICreateQuestion {
    public User addQuestion(HttpServletRequest request);
}
