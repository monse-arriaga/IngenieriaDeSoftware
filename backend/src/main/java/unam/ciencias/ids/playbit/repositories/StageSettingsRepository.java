package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.StageSettings;

public interface StageSettingsRepository extends CrudRepository<StageSettings, Integer> {


    @Query(nativeQuery = true, value = "SELECT * FROM stage_settings WHERE id = :stagesetid")
    public List<StageSettings> getStageSettingsById(@Param("stagesetid") int id);
}
