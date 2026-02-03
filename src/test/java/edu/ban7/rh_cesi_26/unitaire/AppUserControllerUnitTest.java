package edu.ban7.rh_cesi_26.unitaire;

import edu.ban7.rh_cesi_26.controller.AppUserController;
import edu.ban7.rh_cesi_26.mock.MockAppUserDao;
import edu.ban7.rh_cesi_26.model.AppUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AppUserControllerUnitTest {

    @Test
    void callGetAppUsers_shouldResponseWith200() {

        MockAppUserDao mockAppUserDao = new MockAppUserDao();

        AppUserController appUserController = new AppUserController(mockAppUserDao);
        ResponseEntity<List<AppUser>> reponse = appUserController.getAppUsers();

        Assertions.assertEquals(HttpStatus.OK, reponse.getStatusCode());

    }

    @Test
    void callGetWithNotExistingUser_shouldResponseWith404() {
        MockAppUserDao mockAppUserDao = new MockAppUserDao();
        AppUserController appUserController = new AppUserController(mockAppUserDao);
        ResponseEntity<AppUser> reponse = appUserController.get(2);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
    }

    @Test
    void callGetWithExistingUser_shouldResponseWith200() {
        MockAppUserDao mockAppUserDao = new MockAppUserDao();
        AppUserController appUserController = new AppUserController(mockAppUserDao);
        ResponseEntity<AppUser> reponse = appUserController.get(1);
        Assertions.assertEquals(HttpStatus.OK, reponse.getStatusCode());
    }

}
