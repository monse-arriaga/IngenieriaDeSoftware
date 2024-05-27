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
import unam.ciencias.ids.playbit.repositories.ParticipantRepository;

@RestController
@RequestMapping("/participant")
@CrossOrigin
public class ParticipantController {
    
    @Autowired
    ParticipantRepository participantRepository;


    @GetMapping("/all/")
    public List<Participant> findAll(){
        return (List<Participant>) participantRepository.findAll();
    }

    @PostMapping("/create/")
    @Transactional
    public void createParticipant(@RequestBody Participant[] participant){
        
        participantRepository.saveAll(Arrays.asList(participant));
    }



    @PostMapping("/edit/")
    public void editParticipant(@RequestBody Participant participant) {
        List<Participant> participants = participantRepository.getParticipantById(participant.getId());

        if(participants.size() == 0)
            throw new IllegalArgumentException("participant does not exist");


        participantRepository.delete(participants.get(0));

        participantRepository.save(participant);
    }


    @PostMapping("/delete/")
    public void deleteParticipant(@RequestBody Participant participant) {
        List<Participant> participants = participantRepository.getParticipantById(participant.getId());

        if(participants.size() == 0)
            throw new IllegalArgumentException("participant does not exist");

        participantRepository.delete(participants.get(0));

    }



    @GetMapping("/find/{id}")
    public List<Participant> findParticipant(@PathVariable int id) {

        List<Participant> participants = participantRepository.getParticipantById(id);

        if(participants.size() == 0)
            throw new IllegalArgumentException("participant does not exist");

        return participants;
    }
}
