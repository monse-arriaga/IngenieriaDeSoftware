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

import unam.ciencias.ids.playbit.models.MatchGame;
import unam.ciencias.ids.playbit.models.ParticipantMatchGameResult;
import unam.ciencias.ids.playbit.repositories.MatchGameRepository;
import unam.ciencias.ids.playbit.repositories.ParticipantMatchGameRepository;

@RestController
@RequestMapping("/matchgame")
@CrossOrigin
public class MatchGameController {
    
    @Autowired
    MatchGameRepository matchGameRepository;

    @Autowired
    ParticipantMatchGameRepository participantMatchGameRepository;


    @GetMapping("/all/")
    public List<MatchGame> findAll(){
        return (List<MatchGame>)matchGameRepository.findAll();
    }

    @PostMapping("/create/")
    public void createMatchGame(@RequestBody MatchGame matchGame, ParticipantMatchGameResult result1, ParticipantMatchGameResult result2){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(matchGame.getId());

        if(matchgames.size() > 0)
            throw new IllegalArgumentException("match game already exists");
        
        matchGame.setOpponentOneResult(result1);
        matchGame.setOpponentTwoResult(result2);

        participantMatchGameRepository.save(result1);
        participantMatchGameRepository.save(result2);
            
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


    @GetMapping("/find/{id}")
    public List<MatchGame> findMatchGame(@PathVariable int id){
        List<MatchGame> matchgames = matchGameRepository.getMatchGameById(id);
        
        if (matchgames.size() == 0)
            throw new IllegalArgumentException("match does not exist");

        return matchgames;
    }
}
