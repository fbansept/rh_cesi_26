package edu.ban7.rh_cesi_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.rh_cesi_26.dao.AppUserDao;
import edu.ban7.rh_cesi_26.model.AppUser;
import edu.ban7.rh_cesi_26.model.Resource;
import edu.ban7.rh_cesi_26.view.AppUserView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/app-user")
public class AppUserController {

    @Autowired
    private AppUserDao appUserDao;

    @GetMapping("/list")
    @JsonView(AppUserView.class)
    public List<AppUser> getAppUsers() {
        return appUserDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(AppUserView.class)
    public ResponseEntity<AppUser> get(@PathVariable int id) {

        Optional<AppUser> optionalAppUser = appUserDao.findById(id);

        if(optionalAppUser.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AppUser appUser = optionalAppUser.get();

        //supprimer les informations indésirable "à la main"
//        appUser.setPassword(null);
//        appUser.setFavorites(null);
//        for (Resource resource : appUser.getCreatedResources()) {
//            resource.setOwner(null);
//        }

        //return ResponseEntity.ok(optionalAppUser.get());
        return new ResponseEntity<>(appUser,HttpStatus.OK);
    }

    @PostMapping
    @JsonView(AppUserView.class)
    public ResponseEntity<AppUser> create(@RequestBody @Validated(AppUser.OnCreate.class) AppUser appUser) {
        appUserDao.save(appUser);

        return new ResponseEntity<>(appUser,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<AppUser> optionalAppUser = appUserDao.findById(id);

        if(optionalAppUser.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        appUserDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @JsonView(AppUserView.class)
    public ResponseEntity<AppUser> update(
            @PathVariable int id,
            @RequestBody @Validated(AppUser.OnUpdate.class) AppUser appUser) {

        appUser.setId(id);

        Optional<AppUser> optionalAppUser = appUserDao.findById(id);

        if(optionalAppUser.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //pour empecher la possibilité de modifier le mdp, on affecte l'ancien mot de passe
        appUser.setPassword(optionalAppUser.get().getPassword());

        appUserDao.save(appUser);

        return new ResponseEntity<>(appUser,HttpStatus.OK);
    }

}
