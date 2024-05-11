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
    private int id;

    // @Column(name = "stageId")    
    // private int stageId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id",referencedColumnName = "id")
    private Stage stage;

    @Column(name = "size")
    private int size;

    // @ElementCollection(targetClass = SeedOrdering.class) 
    // @CollectionTable(name = "seed_ordering_setting", joinColumns = @JoinColumn(name = "stage_setting_id")) 
    // @Enumerated(EnumType.STRING) 
    // @Column(name = "seed_ordering") 
    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "stagesettings")
    // private List<SeedOrdering> seedOrdering;

    @Column(name = "balanceByes")
    private boolean balanceByes;

    @Column(name = "matchesChildCount")
    private int matchesChildCount;

    @Column(name = "groupCount")
    private int groupCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "roundRobinMode")
    private RoundRobinMode roundRobinMode;

}
