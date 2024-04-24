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
    private String tournamentID;

    public EnrollId() {
    }

    public EnrollId(int userID, String tournamentID) {
        this.userID = userID;
        this.tournamentID = tournamentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(String tournamentID) {
        this.tournamentID = tournamentID;
    }

}