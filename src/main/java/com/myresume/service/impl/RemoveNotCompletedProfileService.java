package com.myresume.service.impl;

import com.myresume.dao.ProfileRepository;
import com.myresume.entity.Profile;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RemoveNotCompletedProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Value("${remove.not.completed.profiles.interval}")
    private int removeNotCompletedProfilesInterval;

    @Transactional
    @Scheduled(cron = "0 59 23 * * *")
    public void removeNotCompletedProfiles() {
        DateTime date = DateTime.now().minusDays(removeNotCompletedProfilesInterval);
        List<Long> idsToRemove = new ArrayList<>();
        for (Profile profile : profileRepository.findByCompletedFalseAndCreatedBefore(new Timestamp(date.getMillis()))) {
            idsToRemove.add(profile.getId());
            profileRepository.delete(profile);
        }
    }
}
