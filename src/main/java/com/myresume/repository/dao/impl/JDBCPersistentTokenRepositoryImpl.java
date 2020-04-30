package com.myresume.repository.dao.impl;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

public class JDBCPersistentTokenRepositoryImpl implements PersistentTokenRepository {
    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {

    }

    @Override
    public void updateToken(String s, String s1, Date date) {

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        return null;
    }

    @Override
    public void removeUserTokens(String s) {

    }
}
