package be.abvv.bali.member.persistence.rpg.dao;

import be.abvv.bali.member.persistence.rpg.domain.BaliFZZEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
   Retrieves FZZ Informations
 */
@Service
@Repository
@Transactional
public interface BaliFZZDAO extends JpaRepository<BaliFZZEntity, String> {

  @Query("select c from BaliFZZEntity c where trim(upper(c.firstName)) = trim(upper(:firstName)) and trim(upper(c.lastName)) = trim(upper(:lastName))")
  List<BaliFZZEntity> findGsmByFirstNameAndLastName(@Param("firstName") String firstname, @Param("lastName") String lastName);

  @Modifying
  @Query("update BaliFZZEntity u set u.gsmNumber = :phone where u.internalLidNumber = :id")
  void updatePhone(@Param(value = "id") String id, @Param(value = "phone") String phone);

}
