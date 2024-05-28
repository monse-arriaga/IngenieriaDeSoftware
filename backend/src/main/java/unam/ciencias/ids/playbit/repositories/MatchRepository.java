package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.Match;

public interface MatchRepository extends CrudRepository<Match,Integer> {
    

    @Query(nativeQuery = true, value = "SELECT * FROM match WHERE id = :matchid")
    public List<Match> getMatchById(@Param("matchid") int id);
}
