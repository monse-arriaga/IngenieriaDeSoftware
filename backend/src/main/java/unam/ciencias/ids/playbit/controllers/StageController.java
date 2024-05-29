package unam.ciencias.ids.playbit.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import unam.ciencias.ids.playbit.models.Group;
import unam.ciencias.ids.playbit.models.Stage;
import unam.ciencias.ids.playbit.models.StageSettings;
import unam.ciencias.ids.playbit.repositories.GroupRepository;
import unam.ciencias.ids.playbit.repositories.StageRepository;
import unam.ciencias.ids.playbit.repositories.StageSettingsRepository;

@RestController
@RequestMapping("/stage")
@CrossOrigin
public class StageController {

    @Autowired
    StageRepository stageRepository;

    @Autowired
    StageSettingsRepository stageSettingsRepository;

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/all/")
    public List<Stage> findAll(){
        return (List<Stage>) stageRepository.findAll();
    }

    @PostMapping("/create/")
    @Transactional
    public long createStage(@RequestBody Stage[] stages){
        for (Stage stage : stages) {
            StageSettings settings = stage.getStageSettings();
            stage.setStageSettings(settings);
            stageSettingsRepository.save(settings);        
        }
        stageRepository.saveAll(Arrays.asList(stages));
        return stageRepository.count();

    }


    @PostMapping("/edit/")
    public void editStage(@RequestBody Stage stage){
        List<Stage> stages = stageRepository.getStageById(stage.getId());

        if(stages.size() == 0)
            throw new IllegalArgumentException("Stage does not exist");

        List<Group> groups = (List<Group>) groupRepository.findAll();
        ArrayList<Group> groups2 = new ArrayList<Group>(); 

        stageRepository.save(stage);
    }


    @PostMapping("/delete/")
    public void deleteStage(@RequestBody Stage stage){
        List<Stage> stages = stageRepository.getStageById(stage.getId());

        if(stages.size() == 0)
            throw new IllegalArgumentException("Stage does not exist");

        stageRepository.delete(stages.get(0));
    }


    @GetMapping("/find/{id}")
    public List<Stage> findStage(@PathVariable int id){
        List<Stage> stages = stageRepository.getStageById(id);

        if (stages.size() == 0)
            throw new IllegalArgumentException("Stage does not exist");

        return stages;

    }
}
