package be.abvv.bali.member.persistence.rpg.dao;

import be.abvv.bali.member.persistence.rpg.domain.BaliFLIEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
/*
   Retrieves Member Information regarding an ABVV Member
 */
public interface BaliFLIDAO extends JpaRepository<BaliFLIEntity, String> {

  @Query(value = "SELECT COUNT(f.LINOMX) FROM FLI f " +
          "WHERE SUBSTRING(f.LINPEN,1,2) >= :maxYearOfBirth " +
          "AND f.LINOCO BETWEEN :lastName AND :lastNameZZ " +
          "AND f.LINPEN > ' ' " +
          "AND f.LICHDI > ' ' " +
          "AND f.LICREG IN ( :regions ) " +
          "AND f.LINOCO LIKE %:firstName%",
          nativeQuery = true)
  long getNumberOfFLIByNameAndRegion(@Param("lastName") String lastName,
                                     @Param("lastNameZZ") String lastNameZZ,
                                     @Param("firstName") String firstName,
                                     @Param("maxYearOfBirth") int maxYearOfBirth,
                                     @Param("regions") List<String> regions);

  @Query(value = "SELECT f.* FROM FLI f " +
          "WHERE SUBSTRING(f.LINPEN,1,2) >= :maxYearOfBirth " +
          "AND f.LINOCO BETWEEN :lastName AND :lastNameZZ " +
          "AND f.LINPEN > ' ' " +
          "AND f.LICHDI > ' ' " +
          "AND f.LICREG IN ( :regions ) " +
          "AND f.LINOCO LIKE %:firstName%",
          countQuery = "SELECT f.* FROM FLI f " +
                  "WHERE SUBSTRING(f.LINPEN,1,2) >= :maxYearOfBirth " +
                  "AND f.LINOCO BETWEEN :lastName AND :lastNameZZ " +
                  "AND f.LINPEN > ' ' " +
                  "AND f.LICHDI > ' ' " +
                  "AND f.LICREG IN ( :regions ) " +
                  "AND f.LINOCO LIKE %:firstName%",
          nativeQuery = true)
  Page<BaliFLIEntity> getFLIByNameAndRegion(@Param("lastName") String lastName,
                                       @Param("lastNameZZ") String lastNameZZ,
                                       @Param("firstName") String firstName,
                                       @Param("regions") List<String> regions,
                                       @Param("maxYearOfBirth") int maxYearOfBirth,
                                       Pageable pageable);
}
