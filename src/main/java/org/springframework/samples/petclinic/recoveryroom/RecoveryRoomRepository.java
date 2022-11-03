package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryRoomRepository extends CrudRepository<RecoveryRoom, Integer>{
    List<RecoveryRoom> findAll();

    @Query("SELECT rrtype from RecoveryRoomType rrtype where rrtype.name = ?1")
    RecoveryRoomType findRecoveryRoomTypeByName(String name);

    Optional<RecoveryRoom> findById(int id);
    RecoveryRoom findByName(String name);
    RecoveryRoom save(RecoveryRoom p);
    
    @Query("select recroomtype from RecoveryRoomType recroomtype")
    List<RecoveryRoomType> findAllRecoveryRoomTypes();
    RecoveryRoomType getRecoveryRoomType(@Param("name") String name);
}
