package unam.ciencias.ids.playbit.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="Torneo")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "noJugadores")
    private int players;

    @Column(name = "informacion")
    private String description;

    @Column(name = "estado")
    private String state;

    @Column(name = "tipoTorneo")
    private String tournamentType;

    @Column(name = "tipoParticipante")
    private String playerType;

    @Column(name = "fecha")
    private LocalDate date;

    @Column(name="precio")
    private int prize;

    public Tournament(){
        
    }

    public Tournament(
        int id,
        String name,
        int players,
        String description,
        String state,
        String tournamentType,
        String playerType,
        LocalDate date,
        int prize){


        this.id = id;
        this.name = name;
        this.players = players;
        this.description = description;
        this.state = state;
        this.tournamentType = tournamentType;
        this.playerType = playerType;
        this.date = date;
        this.prize = prize;
        
    }

    public int getID() {
        return this.id;

    }

    public String getName() {
        return this.name;
    }


    public int getPlayer() {
        return this.players;
    }

    public String getDescription() {
        return this.description;
    }

    public String getState() {
        return this.state;
    }

    public String getTournamentType() {
        return this.tournamentType;
    }

    public String getPlayerType() {
        return this.playerType;
    }
    
    public LocalDate getDate() {
        return this.date;
    }

    public int getPrize(){
        return this.prize;
    }


    public void setID(int id) {
        this.id = id;
    }

    public void setPlayer(int players) {
        this.players = players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTournamentType(String tournamentType) {
        this.tournamentType = tournamentType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }


    public void setDate(LocalDate date){
        this.date = date;
    }
    

}
