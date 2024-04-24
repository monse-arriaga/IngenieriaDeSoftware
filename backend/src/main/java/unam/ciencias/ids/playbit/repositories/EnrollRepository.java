package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.Enroll;

public interface EnrollRepository  extends CrudRepository<Enroll, Integer>{
    @Query(nativeQuery = true, value = "SELECT * FROM Inscribir WHERE usuario_id = :idparam")
    public List<Enroll> getEnrollmentByUser(@Param("idparam") int id);

    @Query(nativeQuery = true, value = "SELECT * FROM Inscribir WHERE torneo_id = :idparam")
    public List<Enroll> getEnrollmentByTournament(@Param("idparam") int id);
}
