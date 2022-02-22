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
}
