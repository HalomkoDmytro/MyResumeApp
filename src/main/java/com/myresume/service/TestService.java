package com.myresume.service;

import com.myresume.dao.ProfileRepository;
import com.myresume.entity.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private ProfileRepository profileRepository;

    public Profile profile() {
        return profileRepository.findByUid("aly-dutta");
    }
}
