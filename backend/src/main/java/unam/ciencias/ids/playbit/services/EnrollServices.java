package unam.ciencias.ids.playbit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unam.ciencias.ids.playbit.models.Enroll;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.repositories.EnrollRepository;

@Service
public class EnrollServices {

    @Autowired
    EnrollRepository enrollRepository;

    public boolean enrollUser(User user, Tournament tournament){
        List<Enroll> userEnrollments = enrollRepository.getEnrollmentByUser(user.getID());
        if(userEnrollments.size() > 0){
            return false;
        }

        List<Enroll> tournamentEnrollments =  enrollRepository.getEnrollmentByTournament(tournament.getID());

        if(tournamentEnrollments.size() > tournament.getPlayersBT()){
            return false;
        }

        Enroll enroll = new Enroll(user.getID(),tournament.getID());
        enrollRepository.save(enroll);

        return true;
    }


}
