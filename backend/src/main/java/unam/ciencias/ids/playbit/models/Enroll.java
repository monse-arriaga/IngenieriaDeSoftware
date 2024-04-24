package unam.ciencias.ids.playbit.models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.*;



@Entity
@Table(name = "Inscribir")
public class Enroll {

    @EmbeddedId
    private EnrollId enrollId;

    public Enroll(){

    }

    public Enroll(EnrollId enrollId){

        this.enrollId = enrollId;
    }

    public EnrollId getEnrollId(){
        return this.enrollId;
    }

    public int getUserID(){
        return this.enrollId.getUserID();
    }

    public int getTournamentID(){
        return this.enrollId.getTournamentID();
    }


    public void setUserID(int id){
        this.enrollId.setUserID(id);
    }

    public void setTournamentID(int id){
        this.enrollId.setTournamentID(id);
    }
    
}
