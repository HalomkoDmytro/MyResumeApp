package com.myresume.service;

import com.myresume.entity.Certificate;
import com.myresume.entity.Contacts;
import com.myresume.entity.Course;
import com.myresume.entity.Education;
import com.myresume.entity.Hobby;
import com.myresume.entity.Language;
import com.myresume.entity.Practic;
import com.myresume.entity.Profile;
import com.myresume.entity.Skill;
import com.myresume.entity.SkillCategory;
import com.myresume.form.GeneralInfoForm;
import com.myresume.form.SignUpForm;
import com.myresume.form.SkillForm;
import org.apache.commons.codec.language.bm.Lang;

import java.util.List;

public interface EditProfileService {

    Profile createNewProfile(SignUpForm signUpForm);

    List<Skill> listSkills(long idProfile);

    List<Certificate> listCertificate(long idProfile);

    List<Education> listEducation(long idProfile);

    List<Hobby> listHobby(long idProfile);

    List<Language> listLanguage(long idProfile);

    List<Practic> listPractice(long idProfile);

    List<Course> listCourses(long idProfile);

    List<SkillCategory> listCategories();

    void updateContacts(long idProfile, Contacts contacts);

    void updateSkills(long idProfile, List<Skill> skills);

    void updateCertificate(long idProfile, List<Certificate> certificateList);

    void updateHobbies(long idProfile, List<Hobby> hobbies);

    void updatePractices(long idProfile, List<Practic> practices);

    void updateEducation(long idProfile, List<Education> educations);

    void updateAdditionalInfo(long idProfile, String newInfo);

    void updateLanguages(long idProfile, List<Language> practices);

    void updateCourses(long idProfile, List<Course> courses);

    void updateProfileMainInfo(long idProfile, GeneralInfoForm profile);

    SkillForm findSkillsByUid(String uid);

    List<SkillCategory> findSkillCategory();
}
