package com.myresume.service.impl;

import com.myresume.entity.Profile;
import com.myresume.repository.dao.ProfileRepository;
import com.myresume.service.FindProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindProfileServiceImplTest {

    private static final long ID_PROFILE_1 = 1;
    private static final long ID_PROFILE_2 = 2;
    private static final String UID_1 = "peter-parker";
    private static final String UID_2 = "arkadi-pirozkov";
    private static final String FIRST_NAME_1 = "Peter";
    private static final String FIRST_NAME_2 = "Arkadi";
    private static final String LAST_NAME_1 = "Parker";
    private static final String LAST_NAME_2 = "Pirozkov";
//    private static final String OBJECTIVE_1 = "";
//    private static final String SUMMARY_1 = "";
//    private static final String INFO_1 = "";
//    private static final String CERTIFICATE_NAME_1 = "";
//    private static final String LANGUAGE_NAME_1 = "";
//    private static final String PRACTICE_COMPANY_1 = "";
//    private static final String PRACTICE_POSITION_1 = "";
//    private static final String PRACTICE_RESPONSIBILITIES_1 = "";
//    private static final String SKILL_VALUE_1 = "";
    private static final boolean COMPLETED_1 = true;
    private static final boolean COMPLETED_2 = true;

    @InjectMocks
    private FindProfileService findProfileService = new FindProfileServiceImpl();

    @Mock
    private ProfileRepository profileRepository;

    @Before
    public void setUp() {
        Profile profile_1 = createProfileOne();

        Profile profile_2 = createProfileTwo();

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile_1);
        profiles.add(profile_2);

        Mockito.when(profileRepository.findByUid(UID_1)).thenReturn(profile_1);
        Mockito.when(profileRepository.findByUid(UID_2)).thenReturn(profile_2);
        Mockito.when(profileRepository.findAll()).thenReturn(profiles);
    }

    @Test
    public void whenFindAllForIndexing_thenAllProfilesShouldBeFound() {
        int expectedSize = 2;
        final Iterable<Profile> allForIndexing = findProfileService.findAllForIndexing();
        int count = 0;
        for (Profile p : allForIndexing) {
            count++;
        }
        final int actualSize = ((Collection<Profile>) allForIndexing).size();

        assertEquals(expectedSize, actualSize);
    }

//    @Test
//    public void findBySearchQueryByFirstName_thenGetAccountWithThisName() {
//        List<Profile> profiles = new ArrayList<>();
//        profiles.add(createProfileOne());
//        Page<Profile> pageProfile = new PageImpl<>(profiles, PageRequest.of(0, 100), 100);
//        Mockito.when(findProfileService.findBySearchQuery(FIRST_NAME_1, PageRequest.of(0, 100))).thenReturn(pageProfile);
//        final Page<Profile> bySearchQuery = findProfileService.findBySearchQuery(FIRST_NAME_1, PageRequest.of(0, 100));
//
//
//        final List<Profile> content = bySearchQuery.getContent();
//        assertTrue(content.contains(createProfileOne()));
//    }


    private Profile createProfileOne() {
        Profile profile = new Profile();

        profile.setId(ID_PROFILE_1);
        profile.setFirstName(FIRST_NAME_1);
        profile.setFirstName(LAST_NAME_1);
        profile.setCompleted(true);

        setEmptyList(profile);

        return profile;
    }

    private Profile createProfileTwo() {
        Profile profile = new Profile();

        profile.setId(ID_PROFILE_2);
        profile.setFirstName(FIRST_NAME_2);
        profile.setFirstName(LAST_NAME_2);
        profile.setCompleted(false);

        setEmptyList(profile);

        return profile;
    }

    private void setEmptyList(Profile profile) {
        profile.setCertificates(Collections.EMPTY_LIST);
        profile.setLanguages(Collections.EMPTY_LIST);
        profile.setPractices(Collections.EMPTY_LIST);
        profile.setSkills(Collections.EMPTY_LIST);
        profile.setCourses(Collections.EMPTY_LIST);
    }
}