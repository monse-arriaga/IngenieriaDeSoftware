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
        List<Tournament> tournaments = tournamentRepository.getTournamentById(tournament.getID());

        if(tournaments.size() > 0){
            return false;
        }

        tournamentRepository.save(tournament);
        return true;
    }

    public boolean findTournament(int id){
        List<Tournament> tournaments = tournamentRepository.getTournamentById(id);

        return tournaments.size() > 0;
    }

    public boolean editTournament(Tournament tournament){
        List<Tournament> tournaments = tournamentRepository.getTournamentById(tournament.getID());

        if(tournaments.size() == 0){
            return false;
        }

        tournamentRepository.delete(tournaments.get(0));

        tournamentRepository.save(tournament);
        return true;

    }


    public boolean deleteTournament(Tournament tournament){
        List<Tournament> tournaments = tournamentRepository.getTournamentById(tournament.getID());

        if(tournaments.size() == 0){
            return false;
        }

        tournamentRepository.delete(tournament);
        return true;
    }
}
