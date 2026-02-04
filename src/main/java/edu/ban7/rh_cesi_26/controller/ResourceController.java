package edu.ban7.rh_cesi_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.rh_cesi_26.dao.ResourceDao;
import edu.ban7.rh_cesi_26.model.Resource;
import edu.ban7.rh_cesi_26.security.AppUserDetails;
import edu.ban7.rh_cesi_26.security.IsAdmin;
import edu.ban7.rh_cesi_26.security.IsUser;
import edu.ban7.rh_cesi_26.view.ResourceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resource")
@IsUser
public class ResourceController {

    @Autowired
    private ResourceDao resourceDao;

    @GetMapping("/list")
    @JsonView(ResourceView.class)
    public List<Resource> getResources() {
        return resourceDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ResourceView.class)
    public ResponseEntity<Resource> get(@PathVariable int id) {

        Optional<Resource> optionalResource = resourceDao.findById(id);

        if(optionalResource.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return ResponseEntity.ok(optionalResource.get());
        return new ResponseEntity<>(optionalResource.get(),HttpStatus.OK);
    }

    @PostMapping
    @JsonView(ResourceView.class)
    public ResponseEntity<Resource> create(
            @RequestBody Resource resource,
            @AuthenticationPrincipal AppUserDetails appUserDetails) {

        resource.setOwner(appUserDetails.getUser());
        resourceDao.save(resource);
        return new ResponseEntity<>(resource,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id,
            @AuthenticationPrincipal AppUserDetails appUserDetails
    ) {
        Optional<Resource> optionalResource = resourceDao.findById(id);

        if(optionalResource.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //si l'utilisateur n'est ni admin ni propriétaire de la resource
        if(!appUserDetails.getUser().isAdmin()
                && appUserDetails.getUser().getId() != optionalResource.get().getOwner().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        resourceDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @JsonView(ResourceView.class)
    public ResponseEntity<Resource> update(
            @PathVariable int id,
            @RequestBody Resource resource) {

        resource.setId(id);

        Optional<Resource> optionalResource = resourceDao.findById(id);

        if(optionalResource.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        resourceDao.save(resource);

        return new ResponseEntity<>(resource,HttpStatus.OK);
    }

}
