package com.myresume;

import com.myresume.controller.EditProfileController;
import com.myresume.controller.ErrorController;
import com.myresume.controller.PublicController;
import com.myresume.controller.SignInSignUpController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTestControllers {

    @Autowired
    private EditProfileController editProfileController;

    @Autowired
    private ErrorController errorController;

    @Autowired
    private PublicController publicController;

    @Autowired
    private SignInSignUpController signInSignUpController;

    @Test
    public void editProfileController_notNull() {
        assertThat(editProfileController).isNotNull();
    }

    @Test
    public void errorController_notNull() {
        assertThat(errorController).isNotNull();
    }

    @Test
    public void publicController_notNull() {
        assertThat(publicController).isNotNull();
    }

    @Test
    public void signInSignUpController_notNull() {
        assertThat(signInSignUpController).isNotNull();
    }
}
