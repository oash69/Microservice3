package be.abvv.bali.member.persistence.rpg.dao;

import be.abvv.bali.member.persistence.rpg.domain.BaliFUSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Repository
@Transactional
/*
   Retrieves Fus Informations
 */
public interface BaliFUSDAO extends JpaRepository<BaliFUSEntity, String> {

  @Query("select c from BaliFUSEntity c where trim(upper(c.systemUsername)) = trim(upper(:username))")
  Optional<BaliFUSEntity> findFusByUsername(@Param("username") String username);

}
