package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.Round;

public interface RoundRepository extends CrudRepository<Round, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM round WHERE id = :roundid")
    public List<Round> getRoundById(@Param("roundid") int id);
}
