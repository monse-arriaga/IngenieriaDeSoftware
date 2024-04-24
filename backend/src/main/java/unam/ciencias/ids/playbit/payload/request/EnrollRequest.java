package unam.ciencias.ids.playbit.payload.request;

import jakarta.validation.constraints.NotBlank;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;

public class EnrollRequest {
    @NotBlank
    private User user;

    @NotBlank
    private Tournament tournament;



    public User getUser(){
        return this.user;
    }

    public Tournament getTournament(){
        return this.tournament;
    }


    public void setUser(User user){
        this.user = user;
    }

    public void setTournament(Tournament tournament){
        this.tournament = tournament;
    }
}
