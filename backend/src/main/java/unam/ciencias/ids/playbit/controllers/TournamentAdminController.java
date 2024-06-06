package unam.ciencias.ids.playbit.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unam.ciencias.ids.playbit.models.Enroll;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.TournamentAdmin;
import unam.ciencias.ids.playbit.models.TournamentAdminId;
import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.repositories.TournamentAdminRepository;
import unam.ciencias.ids.playbit.repositories.TournamentRepository;
import unam.ciencias.ids.playbit.repositories.UserRepository;
import unam.ciencias.ids.playbit.services.TournamentServices;
import unam.ciencias.ids.playbit.services.UserServices;
import java.util.Optional;

@RestController
@RequestMapping("/tournamentAdmin")
@CrossOrigin
public class TournamentAdminController {
    
    @Autowired
    TournamentAdminRepository tournamentAdminRepository;

    @Autowired
    TournamentServices tournamentServices;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/create/{user_id}/{tournament_name}")
    public void addTournament(@PathVariable int user_id, @PathVariable String tournament_name){

        List<Tournament> tournamentList = tournamentServices.findTournamentByName(tournament_name);

        Optional<User> userToFind = userRepository.findById(user_id);

        if(!userToFind.isPresent())
            throw new IllegalArgumentException("user not found");

        if(tournamentList.size() == 0)
            throw new IllegalArgumentException("tournament not found");

        
        List<TournamentAdmin> userTournamentAdmins = tournamentAdminRepository.getUserTournamentAdmins(user_id, tournament_name);

        if(userTournamentAdmins.size() > 0)
            throw new IllegalArgumentException("user is already admin of the tournament");

        TournamentAdminId tournamentAdminId = new TournamentAdminId(user_id,tournament_name);
        TournamentAdmin tournamentAdmin = new TournamentAdmin(tournamentAdminId);
        tournamentAdminRepository.save(tournamentAdmin);

    }



    @GetMapping("/user_tournaments/{user_id}")
    public List<String> getUserTournaments(@PathVariable int user_id){
        Optional<User> user = userRepository.findById(user_id);

        if(!user.isPresent())
            throw new IllegalArgumentException("user not found");

        List<TournamentAdmin> tournamentsAdmin = tournamentAdminRepository.getTournamentAdminByUser(user_id);

        List<String> tournaments = new LinkedList<>();

        for(TournamentAdmin tournament : tournamentsAdmin){
            tournaments.add(tournament.getTournamentAdminId().getTournamentID());
        }

        if(tournaments.size() == 0)
            throw new IllegalArgumentException("no user tournaments");
        
        return tournaments; 
        
    }


    @PostMapping("/delete/{tournament_name}")
    public void deleteTournamentAdminByTournament(@PathVariable String tournament_name){
        List<Tournament> tournamentList = tournamentServices.findTournamentByName(tournament_name);

        if(tournamentList.size() == 0)
            throw new IllegalArgumentException("tournament not found");

        List<TournamentAdmin> tournamentsAdmin = tournamentAdminRepository.getTournamentAdminByTournament(tournament_name);

        if(tournamentsAdmin.size() == 0)
            throw new IllegalArgumentException("tournament not found");

        
        tournamentAdminRepository.delete(tournamentsAdmin.get(0));

    }



}
