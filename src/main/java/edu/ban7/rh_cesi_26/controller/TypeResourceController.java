package edu.ban7.rh_cesi_26.controller;

import edu.ban7.rh_cesi_26.dao.TypeResourceDao;
import edu.ban7.rh_cesi_26.model.TypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-resource")
public class TypeResourceController {

    @Autowired
    private TypeResourceDao typeResourceDao;

    @GetMapping("/list")
    public List<TypeResource> getTypeResources() {
        return typeResourceDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResource> get(@PathVariable int id) {

        Optional<TypeResource> optionalTypeResource = typeResourceDao.findById(id);

        if(optionalTypeResource.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return ResponseEntity.ok(optionalTypeResource.get());
        return new ResponseEntity<>(optionalTypeResource.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TypeResource> create(@RequestBody TypeResource typeResource) {
        typeResourceDao.save(typeResource);

        return new ResponseEntity<>(typeResource,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<TypeResource> optionalTypeResource = typeResourceDao.findById(id);

        if(optionalTypeResource.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        typeResourceDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeResource> update(
            @PathVariable int id,
            @RequestBody TypeResource typeResource) {

        typeResource.setId(id);

        Optional<TypeResource> optionalTypeResource = typeResourceDao.findById(id);

        if(optionalTypeResource.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        typeResourceDao.save(typeResource);

        return new ResponseEntity<>(typeResource,HttpStatus.OK);
    }

}
