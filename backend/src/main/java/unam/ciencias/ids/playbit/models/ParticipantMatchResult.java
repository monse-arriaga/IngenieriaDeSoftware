package unam.ciencias.ids.playbit.models;

import org.hibernate.annotations.ManyToAny;

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
@Table(name = "ParticipantMatchResult")
public class ParticipantMatchResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partm_seq")
    @SequenceGenerator(name = "partm_seq", sequenceName = "partm_seq", initialValue = 0, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Column(name = "position")
    private int position;  

    @Column(name = "forfeit")
    private boolean forfeit;

    @Column(name = "score")
    private int score;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "matchresult")
    private MatchResult result;


}
