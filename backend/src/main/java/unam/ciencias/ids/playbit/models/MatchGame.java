package unam.ciencias.ids.playbit.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "matchgame")
public class MatchGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "matchstatus")
    private MatchStatus matchStatus;


    @OneToOne(mappedBy = "opponentOneMatchGame")
    private ParticipantMatchGameResult opponentOneResult;
    // @Column(name = "opponent1id")
    // private int opponentOneResultId;
    
    @OneToOne(mappedBy = "opponentTwoMatchGame")
    private ParticipantMatchGameResult opponentTwoResult;
    // @Column(name = "opponent2id")
    // private int opponentTwoResultId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "number")
    private int number;
}
