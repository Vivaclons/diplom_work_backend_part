package kz.spring.analysisservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "analysis_id")
    private Long analysisId;
    private String description;

//    @OneToMany(mappedBy="analysis")
//    private Set<CustomerAnalysis> customerAnalyses;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

//    @ManyToMany
//    @JoinTable(
//            name = "customer_analysis",
//            joinColumns = {@JoinColumn(name = "analysis_id")},
//            inverseJoinColumns = {@JoinColumn(name = "medcenter_id"), @JoinColumn(name = "customer_id"), @JoinColumn(name = "doctor_id")}
//    )
//    private Set<Analysis> customerAnalysis = new HashSet<>();
}
