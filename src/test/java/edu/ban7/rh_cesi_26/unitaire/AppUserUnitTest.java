package edu.ban7.rh_cesi_26.unitaire;

import edu.ban7.rh_cesi_26.model.AppUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppUserUnitTest {

    @Test
    void createUserWithUpperCasedEmail_shouldLowerCaseEmail() {
        AppUser user = new AppUser();
        user.setEmail("USER@DOMAINE.COM");

        Assertions.assertEquals( "user@domaine.com", user.getEmail());
    }

}
