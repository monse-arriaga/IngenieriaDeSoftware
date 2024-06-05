package unam.ciencias.ids.playbit.controllers;

import java.util.List;

import javax.swing.GroupLayout.Group;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import unam.ciencias.ids.playbit.models.Match;
import unam.ciencias.ids.playbit.models.Participant;
import unam.ciencias.ids.playbit.models.ParticipantMatchResult;
import unam.ciencias.ids.playbit.repositories.GroupRepository;
import unam.ciencias.ids.playbit.repositories.MatchRepository;
import unam.ciencias.ids.playbit.repositories.ParticipantMatchRepository;
import unam.ciencias.ids.playbit.repositories.ParticipantRepository;

@RestController
@RequestMapping("/match")
@CrossOrigin
public class MatchController {
    
    @Autowired
    MatchRepository matchRepository;
    
    @Autowired
    ParticipantMatchRepository participantMatchRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/all/")
    public List<Match> findAll(){
        return (List<Match>) matchRepository.findAll();
    }

    @PostMapping("/create/")
    @Transactional
    public long createMatch(@RequestBody Match[] matches){
    
        for (Match match : matches) {
            ParticipantMatchResult result1 = match.getOpponentOneResult();
            ParticipantMatchResult result2 = match.getOpponentTwoResult();
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
            
            ArrayList<ParticipantMatchResult> results = new ArrayList<>();

            if (result1 != null) {
                result1.setParticipant(participant1);
                match.setOpponentOneResult(result1);
                results.add(result1);    
            }

            if (result2 != null) {
                result2.setParticipant(participant2);
                match.setOpponentTwoResult(result2);    
                results.add(result2);
            }

            participantMatchRepository.saveAll(results);

        }
        matchRepository.saveAll(java.util.Arrays.asList(matches));
        return matchRepository.count();
    }


    @PostMapping("/edit/")
    @Transactional
    public void editMatch(@RequestBody Match match){
        List<Match> matches = matchRepository.getMatchById(match.getId());
        if(matches.size() == 0)
            throw new IllegalArgumentException("match does not exist");
        
            ParticipantMatchResult result1 = match.getOpponentOneResult();
            ParticipantMatchResult result2 = match.getOpponentTwoResult();
            result1.setId(matches.get(0).getOpponentOneResult().getId());
            System.out.println(result1.getId());
            result2.setId(matches.get(0).getOpponentTwoResult().getId());
            System.out.println(result2.getId());
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
            
            ArrayList<ParticipantMatchResult> results = new ArrayList<>();

            if (result1 != null) {
                result1.setParticipant(participant1);
                match.setOpponentOneResult(result1);
                results.add(result1);    
            }

            if (result2 != null) {
                result2.setParticipant(participant2);
                match.setOpponentTwoResult(result2);    
                results.add(result2);
            }

            participantMatchRepository.saveAll(results);
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
