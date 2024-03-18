package unam.ciencias.ids.playbit.models;
import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;


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


    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String pswd){
        this.password = pswd;
    }

}
