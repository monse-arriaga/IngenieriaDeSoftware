package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;

public interface TournamentRepository extends CrudRepository<Tournament,Integer>{
    @Query(nativeQuery = true , value = "SELECT * FROM Torneo WHERE  = :idparam")
    public List<Tournament> getTournamentById(@Param("idparam") int id);
}