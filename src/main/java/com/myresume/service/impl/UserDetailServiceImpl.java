package com.myresume.service.impl;

import com.myresume.entity.CurrentProfile;
import com.myresume.entity.Profile;
import com.myresume.repository.dao.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Profile profile = findProfile(userName);

        if(profile != null) {
            return new CurrentProfile(profile);
        } else {
            LOGGER.error("Profile not found by " + userName);
            throw new UsernameNotFoundException("Profile not found by " + userName);
        }
    }

    private Profile findProfile(String anyUniqueId) {
        Profile profile = profileRepository.findByUid(anyUniqueId);
        if(profile == null) {
            profile = profileRepository.findByEmail(anyUniqueId);
            if(profile == null) {
                profile = profileRepository.findByPhone(anyUniqueId);
            }
        }

        return profile;
    }
}
