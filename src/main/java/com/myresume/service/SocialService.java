package com.myresume.service;

import com.myresume.entity.Profile;

public interface SocialService<T> {

    Profile loginViaSocialNetwork(T model);
}
