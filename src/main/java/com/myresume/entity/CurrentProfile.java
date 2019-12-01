package com.myresume.entity;

import com.myresume.utils.Constants;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class CurrentProfile extends User {

    private final long id;
    private final String fullName;

    public CurrentProfile(Profile profile) {
        super(profile.getUid(), profile.getPassword(), true, true, true,
                true, Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
        this.id = profile.getId();
        this.fullName = profile.getFullName();
    }

    @Override
    public String toString() {
        return String.format("Current Profile [id=%s, username=%s]", id, getFullName());
    }
}
