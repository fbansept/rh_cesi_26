package edu.ban7.rh_cesi_26.controller;

import edu.ban7.rh_cesi_26.dao.AppUserDao;
import edu.ban7.rh_cesi_26.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/app-user")
public class AppUserController {

    @Autowired
    private AppUserDao appUserDao;

    @GetMapping("/list")
    public List<AppUser> getAppUsers() {
        return appUserDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> get(@PathVariable int id) {

        Optional<AppUser> optionalAppUser = appUserDao.findById(id);

        if(optionalAppUser.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return ResponseEntity.ok(optionalAppUser.get());
        return new ResponseEntity<>(optionalAppUser.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUser> create(@RequestBody AppUser appUser) {
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
    public ResponseEntity<AppUser> update(
            @PathVariable int id,
            @RequestBody AppUser appUser) {

        appUser.setId(id);

        Optional<AppUser> optionalAppUser = appUserDao.findById(id);

        if(optionalAppUser.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        appUserDao.save(appUser);

        return new ResponseEntity<>(appUser,HttpStatus.OK);
    }

}
