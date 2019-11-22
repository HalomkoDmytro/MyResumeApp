package com.myresume.service;

import com.myresume.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface FindProfileService {

    Page<Profile> findAll(Pageable pageable);

    Profile findProfileByUid(String uid);

    Iterable<Profile> findAllForIndexing();
}
