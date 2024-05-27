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

import jakarta.transaction.Transactional;
import unam.ciencias.ids.playbit.models.Round;
import unam.ciencias.ids.playbit.repositories.RoundRepository;

@RestController
@RequestMapping("/round")
@CrossOrigin
public class RoundController {

    @Autowired
    RoundRepository roundRepository;

    @GetMapping("/all/")
    public List<Round> findAll(){
        return (List<Round>) roundRepository.findAll();
    }
    

    @PostMapping("/create/")
    @Transactional
    public void createRound(@RequestBody Round round){
        List<Round> rounds = roundRepository.getRoundById(round.getId());

        if(rounds.size() > 0)
            throw new IllegalArgumentException("Round already created");

        roundRepository.save(round);
    }


    @PostMapping("/edit/")
    public void editRound(@RequestBody Round round){
        List<Round> rounds = roundRepository.getRoundById(round.getId());

        if(rounds.size() == 0)
            throw new IllegalArgumentException("Round does not exist");

        roundRepository.delete(rounds.get(0));
        roundRepository.save(round);
    }


    @PostMapping("/delete/")
    public void deleteRound(@RequestBody Round round){
        List<Round> rounds = roundRepository.getRoundById(round.getId());

        if (rounds.size() == 0)
            throw new IllegalArgumentException("Round does not exist");
        
        roundRepository.delete(rounds.get(0));
    }


    @GetMapping("/find/{id}")
    public List<Round> findRound(@PathVariable int id){
        List<Round> rounds = roundRepository.getRoundById(id);

        if(rounds.size() == 0)
            throw new IllegalArgumentException("Round does not exist");
        
        return rounds;
    }
}
