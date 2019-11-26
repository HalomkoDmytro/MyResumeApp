package com.myresume.service.impl;

import com.myresume.repository.dao.ProfileRepository;
import com.myresume.entity.Profile;
import com.myresume.repository.search.ProfileSearchRepository;
import com.myresume.service.FindProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FindProfileServiceImpl implements FindProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileSearchRepository profileSearchRepository;

    @Override
    public Page<Profile> findAll(Pageable pageable) {
        return profileRepository.findAllByCompletedTrue(pageable);
    }

    @Override
    public Profile findProfileByUid(String uid) {
        return profileRepository.findByUid(uid);
    }

    @Override
    @Transactional
    public Iterable<Profile> findAllForIndexing() {
        final Iterable<Profile> all = profileRepository.findAll();
        for(Profile p : all) {
            p.getSkills().size();
            p.getCertificates().size();
            p.getLanguages().size();
            p.getPractices().size();
            p.getCourses().size();
        }
        return all;
    }

    @Override
    public Page<Profile> findBySearchQuery(String query, Pageable pageable) {
        return profileSearchRepository.findByFirstNameLikeOrLastNameLikeOrObjectiveLikeOrSummaryLikeOrInfoLikeOrCertificatesNameLikeOrLanguagesNameLikeOrPracticesCompanyLikeOrPracticesPositionLikeOrPracticesResponsibilitiesLikeOrSkillsValueLike
                (query, query, query, query, query, query, query, query, query, query, query, pageable);
    }

}
