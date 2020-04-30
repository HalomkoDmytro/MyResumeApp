package com.myresume.service.impl;

import com.myresume.repository.dao.ProfileRepository;
import com.myresume.entity.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    private ProfileRepository profileRepository;

    public Page<Profile> getChunkCompletedProfiles(String fromTo) {
        int pageFrom = 0;
        int pageTo = 5;
        if (fromTo != null) {
            final Integer[] integers = convertNumberPage(fromTo);
            pageFrom = integers[0];
            pageTo = integers[1];
        }
        Pageable pageable = PageRequest.of(pageFrom, pageTo);
        return profileRepository.findAllByCompletedTrue(pageable);
    }

    public Integer[] convertNumberPage(String page) {
        if (page.contains("-")) {
            Integer[] fromToPages = new Integer[2];
            final String[] split = page.split("-");
            try {
                fromToPages[0] = Integer.valueOf(split[0]);
                fromToPages[1] = Integer.valueOf(split[1]);
            } catch (NumberFormatException e) {
                return null;
            }

            if(fromToPages[0] > fromToPages[1]) {
                return null;
            }

            return fromToPages;
        }
        return null;
    }
}
