package com.myresume.service;

import com.myresume.dao.ProfileRepository;
import com.myresume.dao.SkillCategoryRepository;
import com.myresume.entity.Profile;
import com.myresume.entity.Skill;
import com.myresume.entity.SkillCategory;
import com.myresume.form.SkillForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    public Profile findProfileByUid(String uid) {
        return profileRepository.findByUid(uid);
    }

    public SkillForm findSkills(String uid) {
        final List<Skill> skills = profileRepository.findByUid("aly-dutta").getSkills();
        return new SkillForm(skills);
    }

    public List<SkillCategory> findSkillCategory() {
        return skillCategoryRepository.findAll();
    }
}
