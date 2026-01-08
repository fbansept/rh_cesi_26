package edu.ban7.rh_cesi_26.dao;

import edu.ban7.rh_cesi_26.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends JpaRepository<Resource, Integer> {
}
