package unam.ciencias.ids.playbit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unam.ciencias.ids.playbit.models.StageSettings;
import unam.ciencias.ids.playbit.repositories.StageSettingsRepository;

@RestController
@RequestMapping("/stagesettings")
@CrossOrigin
public class StageSettingsController {
    

    @Autowired
    StageSettingsRepository stageSettingsRepository;


    @PostMapping("/create/")
    public void createStageSettings(@RequestBody StageSettings stageSettings){
        List<StageSettings> sslist = stageSettingsRepository.getStageSettingsById(stageSettings.getId());

        if(sslist.size() > 0)
            throw new IllegalArgumentException("stage setting already created");

        stageSettingsRepository.save(stageSettings);
    }



    @PostMapping("/edit/")
    public void editStageSettings(@RequestBody StageSettings stageSettings){
        List<StageSettings> sslist = stageSettingsRepository.getStageSettingsById(stageSettings.getId());


        if(sslist.size() == 0)
            throw new IllegalArgumentException("stage setting does not exist");

        stageSettingsRepository.delete(sslist.get(0));

        stageSettingsRepository.save(stageSettings);
    }


    @PostMapping("/delete/")
    public void deleteStageSettings(@RequestBody StageSettings stageSettings){
        List<StageSettings> sslist = stageSettingsRepository.getStageSettingsById(stageSettings.getId());

        if (sslist.size() == 0)
            throw new IllegalArgumentException("stage setting does not exist");

        stageSettingsRepository.delete(sslist.get(0));
    }



    @GetMapping("/find/{id}")
    public List<StageSettings> findStageSettings(@PathVariable int id){
        List<StageSettings> sslist = stageSettingsRepository.getStageSettingsById(id);

        if (sslist.size() == 0)
            throw new IllegalArgumentException("stage setting does not exist");

        return sslist;
    }
}
