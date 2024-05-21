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

import unam.ciencias.ids.playbit.models.ParticipantMatchResult;
import unam.ciencias.ids.playbit.repositories.ParticipantMatchRepository;

@RestController
@RequestMapping("/participantmatch")
@CrossOrigin
public class ParticipantMatchResultController {
    

    @Autowired
    ParticipantMatchRepository participantMatchRepository;

    @GetMapping("/all/")
    public List<ParticipantMatchResult> findAll(){
        return (List<ParticipantMatchResult>) participantMatchRepository.findAll();
    }

    @PostMapping("/create/")
    public void createParticipantMatch(@RequestBody ParticipantMatchResult participantMatchResult){
        List<ParticipantMatchResult> participantmatch = participantMatchRepository
                .getParticipantMatchById(participantMatchResult.getId());

        if(participantmatch.size() > 0)
            throw new IllegalArgumentException("participantMatch already exist");

        participantMatchRepository.save(participantMatchResult);
    }


    @PostMapping("/edit/")
    public void editParticipantMatch(@RequestBody ParticipantMatchResult participantMatchResult){
        List<ParticipantMatchResult> participantmatch = participantMatchRepository
                .getParticipantMatchById(participantMatchResult.getId());

        if(participantmatch.size() == 0)
            throw new IllegalArgumentException("participantMatch does not exist");
        
        
        participantMatchRepository.delete(participantmatch.get(0));
        participantMatchRepository.save(participantMatchResult);
    }


    @PostMapping("/delete/")
    public void deleteParticipantMatch(@RequestBody ParticipantMatchResult participantMatchResult) {
        List<ParticipantMatchResult> participantmatch = participantMatchRepository
                .getParticipantMatchById(participantMatchResult.getId());

        if (participantmatch.size() == 0)
            throw new IllegalArgumentException("participantMatch does not exist");

        participantMatchRepository.delete(participantmatch.get(0));
    }


    @GetMapping("/find/{id}")
    public List<ParticipantMatchResult> findParticipantMatch(@PathVariable int id){
        List<ParticipantMatchResult> participantmatch = participantMatchRepository
                .getParticipantMatchById(id);


        if(participantmatch.size() == 0)
            throw new IllegalArgumentException("participantMatch does not exist");

        return participantmatch;
    }
}
