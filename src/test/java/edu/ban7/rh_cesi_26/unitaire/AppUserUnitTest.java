package edu.ban7.rh_cesi_26.unitaire;

import edu.ban7.rh_cesi_26.model.AppUser;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUserUnitTest {

    private static Validator validator;

    @BeforeAll
    static void init () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }


    @Test
    void createUserWithUpperCasedEmail_shouldLowerCaseEmail() {
        AppUser user = new AppUser();
        user.setEmail("USER@DOMAINE.COM");

        Assertions.assertEquals( "user@domaine.com", user.getEmail());
    }

    @Test
    void validUserDuringCreationWithAllMandatoryInformation_shouldBeValid() {

        AppUser user = new AppUser();
        user.setEmail("a@a.com");
        user.setPassword("password");

        Set<ConstraintViolation<AppUser>> contraintes = validator.validate(
                user, AppUser.OnCreate.class);

        Assertions.assertTrue(contraintes.isEmpty());

    }

    @Test
    void validUserDuringCreationWithoutEmail_shouldNotBeValid() {

        AppUser user = new AppUser();
        user.setPassword("password");

        Set<ConstraintViolation<AppUser>> contraintes = validator.validate(user, AppUser.OnCreate.class);

        Assertions.assertTrue(contraintes.stream()
                .anyMatch(c ->
                        c.getPropertyPath().toString().equals("email")
                && c.getConstraintDescriptor()
                                .getAnnotation()
                                .annotationType()
                                .getSimpleName()
                                .equals("NotBlank")));
    }

    @Test
    void validUserDuringCreationWithInvalidEmail_shouldNotBeValid() {

        AppUser user = new AppUser();
        user.setPassword("password");
        user.setEmail("a.com");

        Set<ConstraintViolation<AppUser>> contraintes = validator.validate(user, AppUser.OnCreate.class);

        Assertions.assertTrue(contraintes.stream()
                .anyMatch(c ->
                        c.getPropertyPath().toString().equals("email")
                                && c.getConstraintDescriptor()
                                .getAnnotation()
                                .annotationType()
                                .getSimpleName()
                                .equals("Email")));
    }

}
