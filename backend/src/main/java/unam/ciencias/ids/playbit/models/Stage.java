package unam.ciencias.ids.playbit.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Stage")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","groups","rounds", "matches","matchgames"})
public class Stage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tournamentId")
    private String tournamentId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    // @Column(name = "settingsId")
    // private int settingsId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private StageSettings stageSettings;

    @Column(name = "numberId")
    private int number;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "stage")
    private List<Group> groups;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stage")
    private List<Round> rounds;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stage")
    private List<Match> matches;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stage")
    private List<MatchGame> matchGames;

    
}
