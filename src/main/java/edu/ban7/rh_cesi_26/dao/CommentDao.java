package edu.ban7.rh_cesi_26.dao;

import edu.ban7.rh_cesi_26.model.Comment;
import edu.ban7.rh_cesi_26.model.TypeResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
}
