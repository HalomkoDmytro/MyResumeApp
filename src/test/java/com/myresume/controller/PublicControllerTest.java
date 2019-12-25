package com.myresume.controller;

import com.myresume.entity.Profile;
import com.myresume.repository.dao.ProfileRepository;
import com.myresume.service.FindProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PublicControllerTest {

    String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

    @Value("${search.max.profile.per.page}")
    private int MAX_PROFILE_PER_PAGE;

    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private FindProfileService findProfileService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void welcome_shouldReturnProprietyPage() throws Exception {
        List<Profile> profileList = new ArrayList<>();
        Page<Profile> page = new PageImpl<>(profileList);
        when(findProfileService.findAll(PageRequest.of(0, MAX_PROFILE_PER_PAGE))).thenReturn(page);

        this.mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/welcome.jsp"));
    }

    @Test
    public void moreProfiles_shouldReturnProprietyPage() throws Exception {
        List<Profile> profileList = new ArrayList<>();
        Page<Profile> pages = new PageImpl<>(profileList);

        when(findProfileService.findAll(any())).thenReturn(pages);

        this.mockMvc.perform(get("/fragment/more"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/fragment/profile-items.jsp"));
    }

    @Test
    public void profileWithName_shouldReturnProprietyPage_whenProfileNotFound() throws Exception {
        this.mockMvc.perform(get("/profile/uid"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/profile-not-found.jsp"));
    }

    @Test
    public void profileWithName_shouldReturnProprietyPage() throws Exception {
        final String uid = "profileName";
        when(profileRepository.findByUid(uid)).thenReturn(new Profile());

        this.mockMvc.perform(get("/profile/" + uid))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/profile.jsp"));
    }

    @Test
    public void elasticSearchProfileFirstPage_shouldReturnProprietyPage() throws Exception {
        List<Profile> profileList = new ArrayList<>();
        Page<Profile> pages = new PageImpl<>(profileList);

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());


        when(findProfileService.findBySearchQuery(any(), any())).thenReturn(pages);

        this.mockMvc.perform(post("/profile/search").param("query", "query").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/search-result.jsp"));
    }
}