package unam.ciencias.ids.playbit.controllers;

import java.util.ArrayList;
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
import unam.ciencias.ids.playbit.models.MatchGame;
import unam.ciencias.ids.playbit.models.Participant;
import unam.ciencias.ids.playbit.models.ParticipantMatchGameResult;
import unam.ciencias.ids.playbit.repositories.MatchGameRepository;
import unam.ciencias.ids.playbit.repositories.ParticipantMatchGameRepository;
import unam.ciencias.ids.playbit.repositories.ParticipantRepository;

@RestController
@RequestMapping("/matchgame")
@CrossOrigin
public class MatchGameController {
    
    @Autowired
    MatchGameRepository matchGameRepository;

    @Autowired
    ParticipantMatchGameRepository participantMatchGameRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @GetMapping("/all/")
    public List<MatchGame> findAll(){
        return (List<MatchGame>)matchGameRepository.findAll();
    }

    @PostMapping("/create/")
    @Transactional
    public long createMatchGame(@RequestBody MatchGame[] matchGames){
        System.out.println("soy matchgame");
        for (MatchGame match : matchGames) {
            ParticipantMatchGameResult result1 = match.getOpponentOneResult();
            ParticipantMatchGameResult result2 = match.getOpponentTwoResult();
            Participant participant1;
            Participant participant2;
            try {
                Participant participant1id = result1.getParticipant();
                participant1 = participantRepository.findById(participant1id.getId()).orElseThrow(
                    () -> new IllegalArgumentException("paricipant not found"));
            } catch (Exception e) {
                participant1 = null;
            }
            try {
                Participant participant2id = result2.getParticipant();
                participant2 = participantRepository.findById(participant2id.getId()).orElseThrow(
                    () -> new IllegalArgumentException("paricipant not found"));
            } catch (Exception e) {
                participant2 = null;
            }
            
            ArrayList<ParticipantMatchGameResult> results = new ArrayList<>();

            if (result1 != null) {
                result1.setParticipant(participant1);
                results.add(result1);    
            }

            if (result2 != null) {
                result2.setParticipant(participant2);    
                results.add(result2);
            }

            participantMatchGameRepository.saveAll(results);
            System.out.println(1);
        }
        System.out.println(2);
        matchGameRepository.saveAll(java.util.Arrays.asList(matchGames));
    System.out.println(3);
        return matchGameRepository.count();
    }


    @PostMapping("/edit/")
    public void editMatchGame(@RequestBody MatchGame matchGame){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(matchGame.getId());

        if(matchgames.size() == 0)
            throw new IllegalArgumentException("match does not exist");

        matchGameRepository.save(matchGame);
    }


    @PostMapping("/delete/")
    public void deleteMatchGame(@RequestBody MatchGame matchGame){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(matchGame.getId());

        if(matchgames.size() == 0)
            throw new IllegalArgumentException("match does not exist");
            

        matchGameRepository.delete(matchgames.get(0));
    }


    @GetMapping("/find/{id}")
    public List<MatchGame> findMatchGame(@PathVariable int id){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(id);
        
        if (matchgames.size() == 0)
            throw new IllegalArgumentException("match does not exist");

        return matchgames;
    }
}
