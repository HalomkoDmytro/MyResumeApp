package com.myresume.controller;

import com.myresume.entity.Profile;
import com.myresume.service.SocialService;
import com.myresume.utils.SecurityUtils;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FacebookController {

    @Value("${social.facebook.idClient")
    private String idClient;

    @Value("${social.facebook.secret")
    private String secret;

    private String redirectUrl;

    @Autowired
    private SocialService<User> facebookSocialService;

    @Value("${app.host}")
    public void setRedirectUrl(String appHost) {
        this.redirectUrl = appHost + "/fromFb";
    }

    private String getAuthorizeUrl() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_10);
        return client.getLoginDialogUrl(idClient, redirectUrl, scopeBuilder);
    }

    @RequestMapping(value={"/fbLogin"}, method= RequestMethod.GET)
    public String gotoFacebook(){
        return "redirect:"+getAuthorizeUrl();
    }

    @RequestMapping(value={"/fromFb"}, method=RequestMethod.GET)
    public String fromFb(@RequestParam(value="code", required=false) String code) {
        if(StringUtils.isBlank(code)) {
            return "redirect:/sign-in";
        }
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_10);
        FacebookClient.AccessToken accessToken = client.obtainUserAccessToken(idClient, secret, redirectUrl, code);
        client = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_2_10);
        User user = client.fetchObject("me", User.class, Parameter.with("fields", "name,email,first_name,last_name"));
        Profile profile = facebookSocialService.loginViaSocialNetwork(user);
        if(profile != null) {
            SecurityUtils.authenticate(profile);
            return "redirect:/"+profile.getUid();
        } else{
            return "redirect:/sign-in";
        }
    }

}
