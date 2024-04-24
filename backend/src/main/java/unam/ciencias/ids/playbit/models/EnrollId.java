package unam.ciencias.ids.playbit.models;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.*;

@Embeddable
public class EnrollId implements Serializable {
    @Column(name = "usuario_id")
    private int userID;

    @Column(name = "torneo_id")
    private int tournamentID;

    public EnrollId() {
    }

    public EnrollId(int userID, int tournamentID) {
        this.userID = userID;
        this.tournamentID = tournamentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

}