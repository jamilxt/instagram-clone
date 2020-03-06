package com.jamilxt.instagram_clone.service;

import com.jamilxt.instagram_clone.model.Authority;
import com.jamilxt.instagram_clone.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority create(Authority authority) {

        if (exists(authority.getAuthority())) {
            return null;
        } else {
            authorityRepository.save(authority);
            return authority;
        }
    }

    public Authority findByRoleName(String roleName) {
        return authorityRepository.findByAuthority(roleName);
    }

    public boolean exists(String role) {
        if (findByRoleName(role) != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<Authority> listAllAuthorities() {
        return authorityRepository.findAll();
    }
}
