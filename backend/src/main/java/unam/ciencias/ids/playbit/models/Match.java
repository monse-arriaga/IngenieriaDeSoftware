package unam.ciencias.ids.playbit.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "match")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","games"})
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "matchstatus")
    private MatchStatus matchStatus;

    // @Column(name = "opponent1Id")
    // private int opponentOneResultid;

    // @Column(name = "opponent2Id")
    // private int opponentTwoResultid;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "opponent1_result_id",referencedColumnName = "id")
    private ParticipantMatchResult opponentOneResult;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "opponent2_result_id",referencedColumnName = "id")
    private ParticipantMatchResult opponentTwoResult;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "round_id")
    private Round round;

    @Column(name = "number")
    private int number;

    @Column(name = "childCount")
    private int childCount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match")
    private List<MatchGame> games; 


}
