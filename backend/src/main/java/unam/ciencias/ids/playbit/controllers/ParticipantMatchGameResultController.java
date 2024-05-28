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

import unam.ciencias.ids.playbit.models.ParticipantMatchGameResult;
import unam.ciencias.ids.playbit.repositories.ParticipantMatchGameRepository;

@RestController
@RequestMapping("/participantmatchgame")
@CrossOrigin
public class ParticipantMatchGameResultController {
    
    @Autowired
    ParticipantMatchGameRepository participantMatchGameRepository;

    @GetMapping("/all/")
    public List<ParticipantMatchGameResult> findAll(){
        return (List<ParticipantMatchGameResult>) participantMatchGameRepository.findAll();
    }

    @PostMapping("/create/")
    public void createParticipantMatchGame(@RequestBody ParticipantMatchGameResult participantMatchGameResult){
        List<ParticipantMatchGameResult> participantmatchgame = participantMatchGameRepository
                .getParticipantMatchGameById(participantMatchGameResult.getId());

        if(participantmatchgame.size() > 0)
            throw new IllegalArgumentException("participantMatchGame already exist");

        participantMatchGameRepository.save(participantMatchGameResult);
    }


    @PostMapping("/edit/")
    public void editParticipantMatchGame(@RequestBody ParticipantMatchGameResult participantMatchGameResult){
        List<ParticipantMatchGameResult> participantmatchgame = participantMatchGameRepository
                .getParticipantMatchGameById(participantMatchGameResult.getId());

        if(participantmatchgame.size() == 0)
            throw new IllegalArgumentException("participantMatchGame does not exist");
        
        
        participantMatchGameRepository.delete(participantmatchgame.get(0));
        participantMatchGameRepository.save(participantMatchGameResult);
    }


    @PostMapping("/delete/")
    public void deleteParticipantMatchGame(@RequestBody ParticipantMatchGameResult participantMatchGameResult) {
        List<ParticipantMatchGameResult> participantmatchgame = participantMatchGameRepository
                .getParticipantMatchGameById(participantMatchGameResult.getId());

        if (participantmatchgame.size() == 0)
            throw new IllegalArgumentException("participantMatchGame does not exist");

        participantMatchGameRepository.delete(participantmatchgame.get(0));
    }


    @GetMapping("/find/{id}")
    public List<ParticipantMatchGameResult> findParticipantMatchGame(@PathVariable int id){
        List<ParticipantMatchGameResult> participantmatchgame = participantMatchGameRepository
                .getParticipantMatchGameById(id);


        if(participantmatchgame.size() == 0)
            throw new IllegalArgumentException("participantMatchGame does not exist");

        return participantmatchgame;
    }
}
