package unam.ciencias.ids.playbit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.repositories.TournamentRepository;

@Service
public class TournamentServices {
    
    @Autowired
    TournamentRepository tournamentRepository;

    public boolean createTournament(Tournament tournament){
        List<Tournament> tournaments = tournamentRepository.getTournamentByName(tournament.getName());

        if(tournaments.size() > 0){
            return false;
        }

        tournamentRepository.save(tournament);
        return true;
    }

    public List<Tournament> getAllTournaments(){
        return (List<Tournament>)tournamentRepository.findAll();
    }
    
    public List<Tournament> findTournamentByName(String name){
        List<Tournament> tournaments = tournamentRepository.getTournamentByName(name);
        return tournaments;
    }


    public boolean findTournament(String id){
        List<Tournament> tournaments = tournamentRepository.getTournamentByName(id);
        return tournaments.size() > 0;
    }

    public boolean editTournament(Tournament tournament){
        List<Tournament> tournaments = tournamentRepository.getTournamentByName(tournament.getName());

        if(tournaments.size() == 0){
            return false;
        }

        tournamentRepository.delete(tournaments.get(0));

        tournamentRepository.save(tournament);
        return true;

    }


    public boolean deleteTournament(Tournament tournament){
        List<Tournament> tournaments = tournamentRepository.getTournamentByName(tournament.getName());

        if(tournaments.size() == 0){
            return false;
        }

        tournamentRepository.delete(tournaments.get(0));
        return true;
    }
}
