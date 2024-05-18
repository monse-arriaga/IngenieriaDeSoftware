package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.ParticipantMatchResult;

public interface ParticipantMatchRepository extends CrudRepository<ParticipantMatchResult, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM ParticipantMatchResult WHERE id = :participantid")
    public List<ParticipantMatchResult> getParticipantMatchById(@Param("participantid") int id);
}
