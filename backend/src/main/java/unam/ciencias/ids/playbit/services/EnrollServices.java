package unam.ciencias.ids.playbit.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unam.ciencias.ids.playbit.models.Enroll;
import unam.ciencias.ids.playbit.models.EnrollId;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.repositories.EnrollRepository;
import unam.ciencias.ids.playbit.repositories.TournamentRepository;

@Service
public class EnrollServices {

    @Autowired
    EnrollRepository enrollRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    TournamentServices tournamentServices;

    public List<String> getUserTournaments(int userid){
        List<Enroll> userEnrolls = enrollRepository.getEnrollmentByUser(userid);


        if(userEnrolls.size() == 0){
            throw new IllegalArgumentException("no user enrollments");
        }

        List<String> userTournaments = new LinkedList<>();

        for(Enroll enroll : userEnrolls){
            userTournaments.add(enroll.getTournamentID());
        }

        return userTournaments;
    }


    public boolean deleteEnrollment(User user, Tournament tournament){
        
        List<Enroll> userEnrollments = enrollRepository.getUserEnrollInTournament(user.getID(), tournament.getName());


        if(userEnrollments.size() == 0)
            return false;

        enrollRepository.delete(userEnrollments.get(0));

        return true;


    }

    public boolean enrollUser(User user, Tournament tournament){
        
        List<Enroll> userEnrollments = enrollRepository.getUserEnrollInTournament(user.getID(),tournament.getName());
        
        if(userEnrollments.size() > 0){
            return false;
        }

        List<Enroll> tournamentEnrollments =  enrollRepository.getEnrollmentByTournament(tournament.getName());

        if(tournamentEnrollments.size() > tournament.getPlayersBT()){
            return false;
        }

        EnrollId enrollId = new EnrollId(user.getID(),tournament.getName());

        Enroll enroll = new Enroll(enrollId);
        enrollRepository.save(enroll);

        tournament.setInPlayers(tournament.getInPlayers()+1);
        tournamentRepository.save(tournament);


        return true;
    }


}
