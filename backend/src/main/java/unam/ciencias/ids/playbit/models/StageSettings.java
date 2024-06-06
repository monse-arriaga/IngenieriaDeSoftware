package unam.ciencias.ids.playbit.models;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "StageSettings")
public class StageSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // @Column(name = "stageId")    
    // private int stageId;

    @Column(name = "size")
    private Integer size;

    // @ElementCollection(targetClass = SeedOrdering.class) 
    // @CollectionTable(name = "seed_ordering_setting", joinColumns = @JoinColumn(name = "stage_setting_id")) 
    // @Enumerated(EnumType.STRING) 
    // @Column(name = "seed_ordering") 
    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "stagesettings")
    // private List<SeedOrdering> seedOrdering;

    @Column(name = "balanceByes")
    private Boolean balanceByes;

    @Column(name = "matchesChildCount")
    private Integer matchesChildCount;

    @Column(name = "groupCount")
    private Integer groupCount;

    @Column(name = "seedOrdering")
    private String[] seedOrdering;

    @Enumerated(EnumType.STRING)
    @Column(name = "roundRobinMode")
    private RoundRobinMode roundRobinMode;

    @Column(name = "consolationFinal")
    private Boolean consolationFinal;

    @Column(name = "skipFirstRound")
    private Boolean skipFirstRound;

    @Column(name = "grandFinal")
    private GrandFinalType grandFinal;
    
}
