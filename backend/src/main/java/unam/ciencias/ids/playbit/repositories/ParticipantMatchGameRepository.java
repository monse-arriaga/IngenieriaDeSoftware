package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.ParticipantMatchGameResult;

public interface ParticipantMatchGameRepository extends CrudRepository<ParticipantMatchGameResult, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM ParticipantMatchResult WHERE id = :participantid")
    public List<ParticipantMatchGameResult> getParticipantMatchGameById(@Param("participantid") int id);
}
