package unam.ciencias.ids.playbit.models;

import java.util.List;

import org.hibernate.annotations.Collate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "participant")
@JsonIgnoreProperties({"matchResult", "matchGameResults"})
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
