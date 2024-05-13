package unam.ciencias.ids.playbit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unam.ciencias.ids.playbit.models.MatchGame;
import unam.ciencias.ids.playbit.repositories.MatchGameRepository;

@RestController
@RequestMapping("/matchgame")
@CrossOrigin
public class MatchGameController {
    
    @Autowired
    MatchGameRepository matchGameRepository;

    @PostMapping("/create/")
    public void createMatchGame(@RequestBody MatchGame matchGame){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(matchGame.getId());

        if(matchgames.size() > 0)
            throw new IllegalArgumentException("match game already exists");

        matchGameRepository.save(matchGame);
    }


    @PostMapping("/edit/")
    public void editMatchGame(@RequestBody MatchGame matchGame){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(matchGame.getId());

        if(matchgames.size() == 0)
            throw new IllegalArgumentException("match does not exist");

        matchGameRepository.delete(matchgames.get(0));
        matchGameRepository.save(matchGame);
    }


    @PostMapping("/delete/")
    public void deleteMatchGame(@RequestBody MatchGame matchGame){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(matchGame.getId());

        if(matchgames.size() == 0)
            throw new IllegalArgumentException("match does not exist");

        matchGameRepository.delete(matchgames.get(0));
    }
}
