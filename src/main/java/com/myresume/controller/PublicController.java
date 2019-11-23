package com.myresume.controller;

import com.myresume.annotation.constraints.EnglishLanguage;
import com.myresume.form.LanguageForm;
import com.myresume.repository.dao.ProfileRepository;
import com.myresume.repository.dao.SkillCategoryRepository;
import com.myresume.entity.Profile;
import com.myresume.service.FindProfileService;
import com.myresume.service.impl.NameService;
import com.myresume.service.impl.ProfileService;
import com.myresume.utils.Constants;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private NameService nameService;

    @Autowired
    private FindProfileService findProfileService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @GetMapping({"/"})
    public String index() {
        return "index";
    }

    @GetMapping({"/profile"})
    public String profile() {
        return "jsp/profile";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model) {
        Page<Profile> page = findProfileService.findAll(PageRequest.of(0, Constants.MAX_PROFILE_PER_PAGE));
        final List<Profile> profiles = page.getContent();
        model.addAttribute("page", page);
        model.addAttribute("profiles", profiles);
        return "jsp/welcome";
    }

    @GetMapping("/fragment/more")
    public String moreProfiles(Model model, @PageableDefault @SortDefault(sort = "id") Pageable pageable) {
        final Page<Profile> pages = findProfileService.findAll(pageable);
        final List<Profile> profiles = pages.getContent();
        model.addAttribute("profiles", profiles);
        return "jsp/fragment/profile-items";
    }

    @GetMapping({"/profile/{uid}"})
    public String profileWithName(@PathVariable("uid") String uis, Model model) {
        final Profile profile = profileRepository.findByUid(uis);
        if (profile == null) {
            return "jsp/profile-not-found";
        }
        model.addAttribute("profile", profile);
        model.addAttribute("contacts", profile.getContacts());
        return "jsp/profile";
    }

//    @GetMapping({"/profile/search"})
//    public String elasticSearchProfileFirstPage(Model model, @RequestParam String query) {
//        final Page<Profile> pages = findProfileService.findBySearchQuery(query, PageRequest.of(0, Constants.MAX_PROFILE_PER_PAGE));
//        final List<Profile> profiles = pages.getContent();
//        model.addAttribute("profiles", profiles);
//        return "jsp/search-result";
//    }

    @PostMapping({"/profile/search"})
    public String elasticSearchProfileFirstPage(Model model, @RequestParam("query") String query) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        QueryBuilder matchPhraseQuery = QueryBuilders.matchPhrasePrefixQuery("firstName", query);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();


        final Page<Profile> pages = findProfileService.findBySearchQuery(query, PageRequest.of(0, Constants.MAX_PROFILE_PER_PAGE));
        final List<Profile> profiles = pages.getContent();
        model.addAttribute("profiles", profiles);
        return "jsp/search-result";
    }

    private String gotoSkillsJSP(Model model) {
        model.addAttribute("skillCategories", skillCategoryRepository.findAll());
        return "jsp/edit/skills";
    }

}
