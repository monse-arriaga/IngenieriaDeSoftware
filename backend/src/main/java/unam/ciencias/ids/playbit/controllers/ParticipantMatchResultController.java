package unam.ciencias.ids.playbit.controllers;

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
import unam.ciencias.ids.playbit.models.Participant;
import unam.ciencias.ids.playbit.models.ParticipantMatchResult;
import unam.ciencias.ids.playbit.repositories.ParticipantMatchRepository;
import unam.ciencias.ids.playbit.repositories.ParticipantRepository;

@RestController
@RequestMapping("/participantmatch")
@CrossOrigin
public class ParticipantMatchResultController {
    

    @Autowired
    ParticipantMatchRepository participantMatchRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @GetMapping("/all/")
    public List<ParticipantMatchResult> findAll(){
        return (List<ParticipantMatchResult>) participantMatchRepository.findAll();
    }

    @PostMapping("/create/")
    @Transactional
    public long createParticipantMatch(@RequestBody ParticipantMatchResult[] participantMatchResults){
        for (ParticipantMatchResult participantMatchResult : participantMatchResults) {            
            
            Participant participant = participantRepository.findById(participantMatchResult.getParticipant().getId())
            .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
            
            participantMatchResult.setParticipant(participant);

            List<ParticipantMatchResult> participantmatch = participantMatchRepository
            .getParticipantMatchById(participantMatchResult.getId());

            if(participantmatch.size() > 0)
                throw new IllegalArgumentException("participantMatch already exist");

        }
        participantMatchRepository.saveAll(Arrays.asList(participantMatchResults));
        return participantMatchRepository.count() - 1;
    }


    @PostMapping("/edit/")
    public void editParticipantMatch(@RequestBody ParticipantMatchResult participantMatchResult){
        List<ParticipantMatchResult> participantmatch = participantMatchRepository
                .getParticipantMatchById(participantMatchResult.getId());

        if(participantmatch.size() == 0)
            throw new IllegalArgumentException("participantMatch does not exist");
        
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
