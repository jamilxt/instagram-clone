package com.jamilxt.instagram_clone.service;

import com.jamilxt.instagram_clone.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseService {

    protected User getLoggedInUser() {

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

}
