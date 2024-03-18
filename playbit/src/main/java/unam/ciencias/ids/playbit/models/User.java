package unam.ciencias.ids.playbit.models;
import jakarta.persistence.*;

@Entity
@Table(name="userapp")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "mail")
    private String email;


    @Column(name = "userpassword")
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
