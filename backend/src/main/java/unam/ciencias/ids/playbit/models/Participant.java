package unam.ciencias.ids.playbit.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "participant")
@JsonIgnoreProperties({"matchResult", "matchGameResults"})
public class Participant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_seq")
    @SequenceGenerator(name = "part_seq", sequenceName = "part_seq", initialValue = 0, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tournamentId")
    private String tournament_id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "participant")
    private List<ParticipantMatchResult> matchResult;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "participant")
    private List<ParticipantMatchGameResult> matchGameResults;
}