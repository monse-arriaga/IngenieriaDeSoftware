package unam.ciencias.ids.playbit.models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @Column(name = "jugadores")
    private int players;

    @Column(name = "informacion")
    private String description;

    @Column(name = "estado")
    private String state;

    @Column(name = "tipoTorneo")
    private String tournamentType;

    @Column(name = "jugadores_por_equipo")
    private int playersBT;

    @Column(name = "fecha")
    private LocalDate date;

    @Column(name = "inscritos")
    private int inPlayers;

    @Column(name="premio")
    private int prize;

    @Column(name="hora")
    private LocalTime time;

    public Tournament(){

    }

    public Tournament(
        int id,
        String name,
        int players,
        String description,
        String state,
        String tournamentType,
        int playersBT,
        LocalDate date,
        int prize,
        int inPlayers,
        LocalTime time){


        this.id = id;
        this.name = name;
        this.players = players;
        this.description = description;
        this.state = state;
        this.tournamentType = tournamentType;
        this.playersBT = playersBT;
        this.date = date;
        this.prize = prize;
        this.inPlayers = inPlayers;
        this.time = time;

    }

    public int getID() {
        return this.id;

    }

    public String getName() {
        return this.name;
    }


    public int getPlayers() {
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

    public int getPlayersBT() {
        return this.playersBT;
    }
    
    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return time;
    }
    
    public int getInPlayers(){
        return this.inPlayers;
    }
    public void setInPlayers(int inPlayers){
        this.inPlayers = inPlayers;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setPlayers(int players) {
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

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTournamentType(String tournamentType) {
        this.tournamentType = tournamentType;
    }

    public void setPlayersBT(int playersBT) {
        this.playersBT = playersBT;
    }


    public void setDate(LocalDate date){
        this.date = date;
    }
    
    public int getPrize(){
        return this.prize;
    }

}
