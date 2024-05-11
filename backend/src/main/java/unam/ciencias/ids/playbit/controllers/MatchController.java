package unam.ciencias.ids.playbit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unam.ciencias.ids.playbit.models.Match;
import unam.ciencias.ids.playbit.repositories.MatchRepository;

@RestController
@RequestMapping("/match")
@CrossOrigin
public class MatchController {
    
    @Autowired
    MatchRepository matchRepository;


    @PostMapping("/create/")
    public void createMatch(@RequestBody Match match){
        List<Match> matches = matchRepository.getMatchById(match.getId());

        if(matches.size() > 0)
            throw new IllegalArgumentException("match already created");

        matchRepository.save(match);
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
}
