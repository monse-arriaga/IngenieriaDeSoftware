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

import io.jsonwebtoken.lang.Arrays;
import jakarta.transaction.Transactional;
import unam.ciencias.ids.playbit.models.Match;
import unam.ciencias.ids.playbit.models.ParticipantMatchResult;
import unam.ciencias.ids.playbit.repositories.MatchRepository;
import unam.ciencias.ids.playbit.repositories.ParticipantMatchRepository;

@RestController
@RequestMapping("/match")
@CrossOrigin
public class MatchController {
    
    @Autowired
    MatchRepository matchRepository;
    
    @Autowired
    ParticipantMatchRepository participantMatchRepository;


    @GetMapping("/all/")
    public List<Match> findAll(){
        return (List<Match>) matchRepository.findAll();
    }

    @PostMapping("/create/")
    @Transactional
    public void createMatch(@RequestBody Match[] matches){
    
        for (Match match : matches) {
            ParticipantMatchResult result1 = match.getOpponentOneResult();
            ParticipantMatchResult result2 = match.getOpponentTwoResult();
            participantMatchRepository.save(result1);
            participantMatchRepository.save(result2);
        }
        matchRepository.saveAll(java.util.Arrays.asList(matches));
    }


    @PostMapping("/edit/")
    public void editMatch(@RequestBody Match match){
        List<Match> matches = matchRepository.getMatchById(match.getId());

        if(matches.size() == 0)
            throw new IllegalArgumentException("match does not exist");

        matchRepository.delete(matches.get(0));
        matchRepository.save(match);
    }


    @PostMapping("/delete/")
    public void deleteMatch(@RequestBody Match match){
        List<Match> matches = matchRepository.getMatchById(match.getId());

        if (matches.size() == 0)
            throw new IllegalArgumentException("match does not exist");

        matchRepository.delete(matches.get(0));
    }

    @GetMapping("/find/{id}")
    public List<Match> findMatch(@PathVariable int id){
        List<Match> matches = matchRepository.getMatchById(id);

        if(matches.size() == 0)
            throw new IllegalArgumentException("match does not exist");
    
        return matches;
    }
}
