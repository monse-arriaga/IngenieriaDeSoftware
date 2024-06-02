package unam.ciencias.ids.playbit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.payload.request.TournamentAdminRequest;
import unam.ciencias.ids.playbit.services.TournamentServices;

@RestController
@CrossOrigin
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    TournamentServices tournamentServices;

    // @PostMapping("/create/")
    // public void createTournament(@RequestBody Tournament tournament){
    //     if (!tournamentServices.createTournament(tournament))
    //         throw new IllegalArgumentException("tournament already created");
    // }

    @PostMapping("/create/")
    public void createTournament2(@RequestBody TournamentAdminRequest tournamentAdminRequest){
        if(!tournamentServices.createTournament2(tournamentAdminRequest.getTournament(),tournamentAdminRequest.getUserID()))
            throw new IllegalArgumentException("tournament already created");
    }



    @GetMapping("/all/")
    public List<Tournament> getAllTournaments(){
        List<Tournament> tournaments = tournamentServices.getAllTournaments();

        if(tournaments.size() == 0){
            throw new IllegalArgumentException("There are no tournaments");
        }

        return tournaments;
    }


    @GetMapping("/find/{name}")
    public List<Tournament> findTournamentsByName(@PathVariable String name){
        List<Tournament> tournaments = tournamentServices.findTournamentByName(name);
       
        if(tournaments.size() == 0){
            throw new IllegalArgumentException("tournament not found");
        }

        return tournaments;


    }


    @PostMapping("/edit/")
    public void editTournament(@RequestBody Tournament tournament){
        if(!tournamentServices.editTournament(tournament))
            throw new IllegalArgumentException("tournament not found");
    }


    @PostMapping("/delete/")
    public void deleteTournament(@RequestBody Tournament tournament){
        if(!tournamentServices.deleteTournament(tournament))
            throw new IllegalArgumentException("tournament not found");
    }


}

