package com.myresume.service.impl;

import com.myresume.entity.Contacts;
import com.myresume.entity.Hobby;
import com.myresume.entity.Language;
import com.myresume.entity.Practic;
import com.myresume.entity.Profile;
import com.myresume.entity.Skill;
import com.myresume.entity.SkillCategory;
import com.myresume.exceptions.CantCompleteClientRequestException;
import com.myresume.form.SignUpForm;
import com.myresume.form.SkillForm;
import com.myresume.repository.dao.PracticesExperienceRepository;
import com.myresume.repository.dao.ProfileRepository;
import com.myresume.repository.dao.SkillCategoryRepository;
import com.myresume.repository.dao.SkillRepository;
import com.myresume.repository.search.ProfileSearchRepository;
import com.myresume.service.EditProfileService;
import com.myresume.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class EditProfileServiceImpl implements EditProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileServiceImpl.class);

    @Value("${generate.uid.alphabet}")
    private String generateUidAlphabet;

    @Value("${generate.uid.suffix.length}")
    private int generateUidSuffixLength;

    @Value("${generate.uid.max.try.count}")
    private int maxTryCountToGenerateUid;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PracticesExperienceRepository practicesExperienceRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    private ProfileSearchRepository profileSearchRepository;

    @Value("${profile.max.hobbies}")
    private int maxProfileHobbies;

    @Override
    public SkillForm findSkillsByUid(String uid) {
        final List<Skill> skills = profileRepository.findByUid(uid).getSkills();
        return new SkillForm(skills);
    }

    @Override
    public List<SkillCategory> findSkillCategory() {
        return skillCategoryRepository.findAll();
    }

    @Override
    public Profile createNewProfile(SignUpForm signUpForm) {
        Profile profile = new Profile();
        profile.setUid(generateProfileUid(signUpForm));
        profile.setFirstName(DataUtil.capitalizeName(signUpForm.getFirstName()));
        profile.setLastName(DataUtil.capitalizeName(signUpForm.getLastName()));
        profile.setPassword(signUpForm.getPassword());
        profile.setCompleted(false);
        profileRepository.save(profile);
        registerCreateIndexProfileIfTransactionSuccess(profile);
        return profile;
    }

    private void registerCreateIndexProfileIfTransactionSuccess(final Profile profile) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                LOGGER.info("New profile created:{}", profile.getUid());
                profile.setCertificates(Collections.EMPTY_LIST);
                profile.setLanguages(Collections.EMPTY_LIST);
                profile.setPractices(Collections.EMPTY_LIST);
                profile.setSkills(Collections.EMPTY_LIST);
                profile.setCourses(Collections.EMPTY_LIST);
                profileSearchRepository.save(profile);
                LOGGER.info("New profile index created: {}", profile.getUid());
            }
        });
    }

    private String generateProfileUid(SignUpForm signUpForm) {
        final String baseUid = DataUtil.generateProfileUid(signUpForm);
        String uid = baseUid;

        for (int i = 0; profileRepository.countByUid(uid) > 0; i++) {
            uid = DataUtil.regenerateUidWithRandomSuffix(baseUid, generateUidAlphabet, generateUidSuffixLength);
            if (i >= maxTryCountToGenerateUid) {
                throw new CantCompleteClientRequestException("Can't generate unique uid for profile: " + baseUid + ": maxTryCountToGenerateUid detected.");
            }
        }

        return uid;
    }

    @Override
    public List<Skill> listSkills(long idProfile) {
        return profileRepository.findById(idProfile).get().getSkills();
    }

    @Override
    public List<SkillCategory> listCategories() {
//        return skillCategoryRepository.findAll(new Sort("id"));
        return skillCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public void updateContacts(long idProfile, Contacts updatedContacts) {
        Profile profile = profileRepository.findById(idProfile).get();

        final Contacts oldContact = profile.getContacts();
        if (oldContact.equals(updatedContacts)) {
            LOGGER.debug("Contacts don't contained any updates.");
            return;
        } else {
            profile.setContacts(updatedContacts);
            profileRepository.save(profile);
        }
    }

    @Override
    @Transactional
    public void updateSkills(long idProfile, List<Skill> updatedSkills) {
        Profile profile = profileRepository.findById(idProfile).get();

        final List<Skill> oldSkills = profile.getSkills();
        if (CollectionUtils.isEqualCollection(oldSkills, updatedSkills)) {
            LOGGER.debug("List<Skills> don't contained any updates.");
            return;
        } else {
            skillRepository.deleteAll(oldSkills);
            profile.setSkills(updatedSkills);
            profileRepository.save(profile);
//            registerCreateIndexProfileSkillIfTransactionSuccess(idProfile, updatedSkills);
        }
    }

    @Override
    public void updateCertificate(long idProfile, List<Practic> practicesExperienceUpdated) {
        Profile profile = profileRepository.findById(idProfile).get();

        final List<Practic> oldPractices = profile.getPractices();
        if (CollectionUtils.isEqualCollection(oldPractices, practicesExperienceUpdated)) {
            LOGGER.debug("Practices experience don't contained any updates.");
            return;
        } else {
            practicesExperienceRepository.deleteAll(oldPractices);
            profile.setPractices(practicesExperienceUpdated);
            profileRepository.save(profile);
        }
    }

    private void registerCreateIndexProfileSkillIfTransactionSuccess(final long idProfile, final List<Skill> data) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                LOGGER.info("Profile skills updated");
                updateIndexProfileSkills(idProfile, data);
            }
        });
    }

    private void updateIndexProfileSkills(long idProfile, List<Skill> updatedData) {
        Profile profile = profileSearchRepository.findById(idProfile).get();
        profile.setSkills(updatedData);
        profileSearchRepository.save(profile);
        LOGGER.info("Profile skills index updated");
    }

    @Override
    @Transactional
    public void updateHobbies(long idProfile, List<Hobby> updatedHobbies) {
        Profile profile = profileRepository.findById(idProfile).get();

        if (updatedHobbies.size() > maxProfileHobbies) {
            throw new CantCompleteClientRequestException("Detected more than " + maxProfileHobbies + " hobbies for profile: currentProfile=" + "" + ", hobbies=" + updatedHobbies);
        }

        if (CollectionUtils.isEqualCollection(profile.getHobbies(), updatedHobbies)) {
            LOGGER.debug("List<Hobby> don't contained any updates.");
            return;
        } else {
            profile.setHobbies(updatedHobbies);
            profileRepository.save(profile);
        }
    }

    @Override
    public void updatePractices(long idProfile, List<Practic> practicesUpdated) {
        Profile profile = profileRepository.findById(idProfile).get();

        final List<Practic> oldPractices = profile.getPractices();
        if (CollectionUtils.isEqualCollection(oldPractices, practicesUpdated)) {
            LOGGER.debug("List<Practice> don't contained any updates.");
            return;
        } else {
            practicesExperienceRepository.deleteAll(oldPractices);
            profile.setPractices(practicesUpdated);
            profileRepository.save(profile);
        }
    }

    @Override
    public void updateLanguages(long idProfile, List<Language> languages) {
        Profile profile = profileRepository.findById(idProfile).get();

        if (CollectionUtils.isEqualCollection(profile.getHobbies(), languages)) {
            LOGGER.debug("List<Language> don't contained any updates.");
            return;
        } else {
            profile.setLanguages(languages);
            profileRepository.save(profile);
        }
    }
}
