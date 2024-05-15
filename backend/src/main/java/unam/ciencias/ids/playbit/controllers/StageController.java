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

import unam.ciencias.ids.playbit.models.Stage;
import unam.ciencias.ids.playbit.repositories.StageRepository;

@RestController
@RequestMapping("/stage")
@CrossOrigin
public class StageController {

    @Autowired
    StageRepository stageRepository;


    @PostMapping("/create/")
    public void createStage(@RequestBody Stage stage){
        List<Stage> stages = stageRepository.getStageById(stage.getId());

        if(stages.size() > 0)
            throw new IllegalArgumentException("Stage already created");

        stageRepository.save(stage);

    }


    @PostMapping("/edit/")
    public void editStage(@RequestBody Stage stage){
        List<Stage> stages = stageRepository.getStageById(stage.getId());

        if(stages.size() == 0)
            throw new IllegalArgumentException("Stage does not exist");


        stageRepository.delete(stages.get(0));
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
