package com.myresume.service;

import com.myresume.entity.CurrentProfile;
import com.myresume.entity.Hobby;
import com.myresume.entity.Language;
import com.myresume.entity.Practic;
import com.myresume.entity.Profile;
import com.myresume.entity.Skill;
import com.myresume.entity.SkillCategory;
import com.myresume.form.SignUpForm;
import com.myresume.form.SkillForm;

import java.util.List;

public interface EditProfileService {

    Profile createNewProfile(SignUpForm signUpForm);

    List<Skill> listSkills(long idProfile);

    List<SkillCategory> listCategories();

    void updateSkills(long idProfile, List<Skill> skills);

    SkillForm findSkillsByUid(String uid);

    List<SkillCategory> findSkillCategory();

    void updateHobbies(long idProfile, List<Hobby> hobbies);

    void updatePractices(long idProfile, List<Practic> practices);

    void updateLanguages(long idProfile, List<Language> practices);
}
