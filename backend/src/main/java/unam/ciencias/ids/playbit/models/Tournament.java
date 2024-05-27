package unam.ciencias.ids.playbit.models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.persistence.*;

@Entity
@Table(name="Torneo")
public class Tournament {
        
    @Id
    @Column(name = "nombre")
    private String name;

    @Column(name = "jugadores")
    private int players;

    @Column(name = "informacion")
    private String description;

    @Column(name = "estado")
    private String state;

    @Column(name = "tipo_torneo")
    private String tournamentType;

    @Column(name = "tipo_juego")
    private String tournamentGame;

    @Column(name = "jugadores_por_equipo")
    private int playersBT;

    @Column(name = "imagen")
    private String image;

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
        String name,
        String image,
        int players,
        String description,
        String state,
        String tournamentType,
        String tournamentGame,
        int playersBT,
        LocalDate date,
        int prize,
        int inPlayers,
        LocalTime time){


        this.name = name;
        this.image = image;
        this.players = players;
        this.description = description;
        this.state = state;
        this.tournamentType = tournamentType;
        this.tournamentGame = tournamentGame;
        this.playersBT = playersBT;
        this.date = date;
        this.prize = prize;
        this.inPlayers = inPlayers;
        this.time = time;

    }



    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
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

    public String getTournamentGame() {
        return this.tournamentGame;
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

 

    public void setPlayers(int players) {
        this.players = players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setTournamentGame(String tournamentGame) {
        this.tournamentGame = tournamentGame;
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
