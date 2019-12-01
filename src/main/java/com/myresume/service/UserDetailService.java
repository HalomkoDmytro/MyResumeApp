package com.myresume.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailService {

    UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException;
}
