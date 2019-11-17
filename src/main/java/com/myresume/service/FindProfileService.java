package com.myresume.service;

import com.myresume.entity.Profile;

public interface FindProfileService {

    Profile findProfileByUid(String uid);
}
