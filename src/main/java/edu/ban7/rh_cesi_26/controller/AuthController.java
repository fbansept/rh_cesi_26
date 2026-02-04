package edu.ban7.rh_cesi_26.controller;

import edu.ban7.rh_cesi_26.dao.AppUserDao;
import edu.ban7.rh_cesi_26.model.AppUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    
    final PasswordEncoder passwordEncoder;
    final AppUserDao appUserDao;
    
    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(
            @RequestBody @Validated(AppUser.OnCreate.class) AppUser user) {
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAdmin(false);
        
        appUserDao.save(user);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
        
    }
    
}
