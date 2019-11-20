package com.myresume.repository.dao;

import com.myresume.entity.ProfileRestore;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRestoreRepository extends CrudRepository<ProfileRestore, Long> {

    ProfileRestore findByToken(String token);
}
