package com.pay.kakao.homework.entity.eco;

import com.pay.kakao.homework.entity.common.AuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;



@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Builder
public class EcoProgram extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "program_themes", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "themes")
    private List<String> themes;

    private String serviceRegion;

    private String summary;

    @Column(length = 1000)
    private String description;

    public void update(EcoProgram modifiedEcoProgram) {
        this.name = modifiedEcoProgram.getName();
        this.serviceRegion = modifiedEcoProgram.getServiceRegion();
        this.summary = modifiedEcoProgram.getSummary();
        this.description = modifiedEcoProgram.getDescription();
        this.themes = modifiedEcoProgram.getThemes();
//        this.themes.forEach(programThemes -> programThemes.setEcologicalProgram(this));
    }


}

