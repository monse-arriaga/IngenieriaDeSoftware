package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.Stage;

public interface StageRepository extends CrudRepository<Stage,Integer> {
    
    @Query(nativeQuery = true,value = "SELECT * FROM stage WHERE id = :stageid")
    public List<Stage> getStageById(@Param("stageid") int id);
}
