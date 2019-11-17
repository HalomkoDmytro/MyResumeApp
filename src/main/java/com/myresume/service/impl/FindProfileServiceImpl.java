package com.myresume.service.impl;

import com.myresume.dao.ProfileRepository;
import com.myresume.entity.Profile;
import com.myresume.filter.CustomSiteMashFilter;
import com.myresume.service.FindProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProfileServiceImpl implements FindProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile findProfileByUid(String uid) {
        return profileRepository.findByUid(uid);
    }
}
