package unam.ciencias.ids.playbit.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "grupo")
public class Group {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Column(name = "number")
    private int number;
    

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private List<Round> rounds;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private List<Match> matches;


}
