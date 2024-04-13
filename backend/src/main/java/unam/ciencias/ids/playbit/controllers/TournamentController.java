package unam.ciencias.ids.playbit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.services.TournamentServices;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    TournamentServices tournamentServices;

    @PostMapping("/create/")
    public void createTournament(@RequestBody Tournament tournament){
        if(!tournamentServices.createTournament(tournament))
            throw new IllegalArgumentException("tournament already created");
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
