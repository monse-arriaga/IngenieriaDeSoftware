package unam.ciencias.ids.playbit.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Stage")
public class Stage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tournamentId")
    private String tournamentId;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private StageType type;

    // @Column(name = "settingsId")
    // private int settingsId;

    @OneToOne(mappedBy = "stage")
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
