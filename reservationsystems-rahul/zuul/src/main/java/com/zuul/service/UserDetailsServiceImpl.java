package com.zuul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("Hotel"))
            return new User("Hotel", "password", Arrays.asList(()-> "ROLE_HOTEL"));
        else if(username.equals("Guest"))
            return new User("Guest", "password", Arrays.asList(()-> "ROLE_GUEST"));
        else
            return new User("username", "password", Arrays.asList(() -> "ROLE_ADMIN"));
    }
}
