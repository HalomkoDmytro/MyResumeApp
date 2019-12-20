package com.myresume.service.impl;

import com.myresume.entity.Profile;
import com.myresume.repository.dao.ProfileRepository;
import com.myresume.service.SocialService;
import com.restfb.types.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacebookSocialService implements SocialService<User> {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile loginViaSocialNetwork(User model) {
        if (StringUtils.isNotBlank(model.getEmail())) {
            Profile profile = profileRepository.findByEmail(model.getEmail());
            if(profile != null){
                return profile;
            }
        }
        return null;
    }
}
