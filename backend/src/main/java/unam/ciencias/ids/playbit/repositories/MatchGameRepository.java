package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.MatchGame;

public interface MatchGameRepository extends CrudRepository<MatchGame,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM matchgame WHERE id = :matchgameid")
    public List<MatchGame> getMatchGameById(@Param("matchgameid") int id);
}
