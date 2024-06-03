package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.Participant;

public interface ParticipantRepository extends CrudRepository<Participant,Integer>{
    
    @Query(nativeQuery = true, value = "SELECT * FROM participant WHERE id = :participantid")
    public List<Participant> getParticipantById(@Param("participantid") int id);
}
