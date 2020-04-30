package com.myresume.service;

import com.myresume.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface FindProfileService  {

    Profile findById(Long id);

    Page<Profile> findAll(Pageable pageable);

    Profile findProfileByUid(String uid);

    Iterable<Profile> findAllForIndexing();

    Page<Profile> findBySearchQuery(String query, Pageable pageable);

    Profile findByUIDOrEmailOrPhone(String param);
}
