package kz.spring.analysisservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long analysisId;
    private String description;

    @OneToMany(mappedBy="analysis")
    private Set<CustomerAnalysis> customerAnalyses;
}
