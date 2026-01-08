package edu.ban7.rh_cesi_26.controller;

import edu.ban7.rh_cesi_26.dao.CommentDao;
import edu.ban7.rh_cesi_26.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        commentDao.save(comment);

        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Comment> optionalComment = commentDao.findById(id);

        if(optionalComment.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        commentDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(
            @PathVariable int id,
            @RequestBody Comment comment) {

        comment.setId(id);

        Optional<Comment> optionalComment = commentDao.findById(id);

        if(optionalComment.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        commentDao.save(comment);

        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

}
