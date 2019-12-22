package com.myresume.service.impl;

import com.myresume.entity.Certificate;
import com.myresume.entity.ConfirmationToken;
import com.myresume.entity.Contacts;
import com.myresume.entity.Course;
import com.myresume.entity.Education;
import com.myresume.entity.Hobby;
import com.myresume.entity.Language;
import com.myresume.entity.Practic;
import com.myresume.entity.Profile;
import com.myresume.entity.Skill;
import com.myresume.entity.SkillCategory;
import com.myresume.exceptions.CantCompleteClientRequestException;
import com.myresume.exceptions.ProfileNotFound;
import com.myresume.form.GeneralInfoForm;
import com.myresume.form.SignUpForm;
import com.myresume.form.SkillForm;
import com.myresume.repository.dao.ConfirmationTokenRepository;
import com.myresume.repository.dao.CourseRepository;
import com.myresume.repository.dao.EducationRepository;
import com.myresume.repository.dao.HobbyRepository;
import com.myresume.repository.dao.LanguageRepository;
import com.myresume.repository.dao.PracticesExperienceRepository;
import com.myresume.repository.dao.ProfileRepository;
import com.myresume.repository.dao.SkillCategoryRepository;
import com.myresume.repository.dao.SkillRepository;
import com.myresume.repository.search.ProfileSearchRepository;
import com.myresume.service.EditProfileService;
import com.myresume.service.SendEmailService;
import com.myresume.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
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
    private EducationRepository educationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    private ProfileSearchRepository profileSearchRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private SendEmailService sendEmailService;

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
    @Transactional
    public Profile createNewProfile(SignUpForm signUpForm) {
        Profile profile = new Profile();
        profile.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        profile.setUid(generateProfileUid(signUpForm));
        profile.setFirstName(DataUtil.capitalizeName(signUpForm.getFirstName()));
        profile.setLastName(DataUtil.capitalizeName(signUpForm.getLastName()));
        profile.setEmail(signUpForm.getEmail());
        profile.setCompleted(false);

        setEmptyData(profile);

        profileRepository.save(profile);
        registerCreateIndexProfileIfTransactionSuccess(profile);

        final ConfirmationToken confirmationToken = new ConfirmationToken(profile);
        confirmationTokenRepository.save(confirmationToken);
        sendEmailService.activationEmail(profile.getEmail(), confirmationToken.getConfirmationToken());

        return profile;
    }

    @SuppressWarnings("unchecked")
    private void setEmptyData(Profile profile) {
        profile.setCreated(new Date());
        profile.setCertificates(Collections.EMPTY_LIST);
        profile.setEducations(Collections.EMPTY_LIST);
        profile.setHobbies(Collections.EMPTY_LIST);
        profile.setLanguages(Collections.EMPTY_LIST);
        profile.setPractices(Collections.EMPTY_LIST);
        profile.setSkills(Collections.EMPTY_LIST);
        profile.setCourses(Collections.EMPTY_LIST);
        profile.setContacts(new Contacts());
    }

    @SuppressWarnings("unchecked")
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
        return profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)))
                .getSkills();
    }

    @Override
    public List<Certificate> listCertificate(long idProfile) {
        return profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)))
                .getCertificates();
    }

    @Override
    public List<Education> listEducation(long idProfile) {
        return profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)))
                .getEducations();
    }

    @Override
    public List<Hobby> listHobby(long idProfile) {
        return profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)))
                .getHobbies();
    }

    @Override
    public List<Language> listLanguage(long idProfile) {
        return profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)))
                .getLanguages();
    }

    @Override
    public List<Practic> listPractice(long idProfile) {
        return profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)))
                .getPractices();
    }

    private String profileNotFoundMessage(long idProfile) {
        return String.format("Profile with id: %d not found", idProfile);
    }

    @Override
    public List<Course> listCourses(long idProfile) {
        return profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)))
                .getCourses();
    }

    @Override
    public List<SkillCategory> listCategories() {
//        return skillCategoryRepository.findAll(new Sort("id"));
        return skillCategoryRepository.findAll(); // todo add sorting
    }

    @Override
    @Transactional
    public void updateProfileMainInfo(long idProfile, GeneralInfoForm form) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));
        profile.setCountry(form.getCountry());
        profile.setCity(form.getCity());
        profile.setBirthDay(form.getBirthDay());
        profile.setLargePhoto(form.getLargePhoto());
        profile.setSmallPhoto(form.getSmallPhoto());
        profile.setPhone(form.getPhone());
        profile.setSummary(form.getSummary());
        profile.setObjective(form.getObjective());

        profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void updateContacts(long idProfile, Contacts updatedContacts) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        final Contacts oldContact = profile.getContacts();
        if (oldContact != null && oldContact.equals(updatedContacts)) {
            LOGGER.debug("Contacts don't contained any updates.");
        } else {
            profile.setContacts(updatedContacts);
            profileRepository.save(profile);
        }
    }

    @Override
    @Transactional
    public void updateSkills(long idProfile, List<Skill> updatedSkills) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        final List<Skill> oldSkills = profile.getSkills();
        if (CollectionUtils.isEqualCollection(oldSkills, updatedSkills)) {
            LOGGER.debug("List<Skills> don't contained any updates.");
        } else {
            skillRepository.deleteAll(oldSkills);
            profile.setSkills(updatedSkills);
            profileRepository.save(profile);
//            registerCreateIndexProfileSkillIfTransactionSuccess(idProfile, updatedSkills);
        }
    }

    @Override
    public void updateCertificate(long idProfile, List<Certificate> certificateList) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        // TODO
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
        Profile profile = profileSearchRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));
        profile.setSkills(updatedData);
        profileSearchRepository.save(profile);
        LOGGER.info("Profile skills index updated");
    }

    @Override
    @Transactional
    public void updateHobbies(long idProfile, List<Hobby> updatedHobbies) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        if (updatedHobbies.size() > maxProfileHobbies) {
            throw new CantCompleteClientRequestException("Detected more than " + maxProfileHobbies + " hobbies for profile: currentProfile=" + "" + ", hobbies=" + updatedHobbies);
        }

        final List<Hobby> oldHobby = profile.getHobbies();
        if (CollectionUtils.isEqualCollection(oldHobby, updatedHobbies)) {
            LOGGER.debug("List<Hobby> don't contained any updates.");
        } else {
            hobbyRepository.deleteAll(oldHobby);
            profile.setHobbies(updatedHobbies);
            profileRepository.save(profile);
        }
    }

    @Override
    public void updateAdditionalInfo(long idProfile, String newInfo) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        final String oldInfo = profile.getInfo();
        if (oldInfo != null && oldInfo.equals(newInfo)) {
            LOGGER.debug("Additional info don't contained any updates.");
        } else {
            profile.setInfo(newInfo);
            profileRepository.save(profile);
        }
    }

    @Override
    @Transactional
    public void updatePractices(long idProfile, List<Practic> practicesUpdated) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        final List<Practic> oldPractices = profile.getPractices();
        if (CollectionUtils.isEqualCollection(oldPractices, practicesUpdated)) {
            LOGGER.debug("List<Practice> don't contained any updates.");
        } else {
            practicesExperienceRepository.deleteAll(oldPractices);
            profile.setPractices(practicesUpdated);
            profileRepository.save(profile);
        }
    }

    @Override
    @Transactional
    public void updateEducation(long idProfile, List<Education> educations) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        final List<Education> oldEducation = profile.getEducations();
        if (CollectionUtils.isEqualCollection(oldEducation, educations)) {
            LOGGER.debug("List<Education> don't contained any updates.");
        } else {
            educationRepository.deleteAll(oldEducation);
            profile.setEducations(educations);
            profileRepository.save(profile);
        }
    }

    @Override
    @Transactional
    public void updateCourses(long idProfile, List<Course> updatedCourses) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        final List<Course> oldCourses = profile.getCourses();
        if (CollectionUtils.isEqualCollection(oldCourses, updatedCourses)) {
            LOGGER.debug("List<Course> don't contained any updates.");
        } else {
            courseRepository.deleteAll(oldCourses);
            profile.setCourses(updatedCourses);
            profileRepository.save(profile);
        }
    }

    @Override
    @Transactional
    public void updateLanguages(long idProfile, List<Language> updatedLanguages) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFound(profileNotFoundMessage(idProfile)));

        final List<Language> oldLanguages = profile.getLanguages();
        if (CollectionUtils.isEqualCollection(oldLanguages, updatedLanguages)) {
            LOGGER.debug("List<Language> don't contained any updates.");
        } else {
            languageRepository.deleteAll(oldLanguages);
            profile.setLanguages(updatedLanguages);
            profileRepository.save(profile);
        }
    }
}
