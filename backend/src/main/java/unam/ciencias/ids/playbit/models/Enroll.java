package unam.ciencias.ids.playbit.models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Inscribir")
public class Enroll {

    @Id
    @Column(name = "usuario_id")
    int userID;

    @Id
    @Column(name = "torneo_id")
    int tournamentID;

    public Enroll(){

    }


    public Enroll(int userID, int tournamentID){
        this.userID = userID;
        this.tournamentID = tournamentID;
    }

    public int getUserID(){
        return this.userID;
    }

    public int getTournamentID(){
        return this.tournamentID;
    }


    public void setUserID(int id){
        this.userID = id;
    }

    public void setTournamentID(int id){
        this.tournamentID = id;
    }
    
}
