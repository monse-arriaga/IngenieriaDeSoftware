package unam.ciencias.ids.playbit.models;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;


    @Column(name = "contraseña")
    private String password;

    @Column(name = "nacimiento")
    private LocalDate bornDate;

    @Column(name = "biografia")
    private String bio;

    public String getName(){
        return this.name;
    }


    public int getID(){
        return this.id;
        
    }
    public String getEmail(){
        return this.email;
    }


    public String getPassword(){
        return this.password;
    }

    public LocalDate getBornDate(){
        return this.bornDate;
    }

    public String getBio(){
        return this.bio;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String pswd){
        this.password = pswd;
    }

    public void setBornDate(LocalDate date){
        this.bornDate = date;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

}
