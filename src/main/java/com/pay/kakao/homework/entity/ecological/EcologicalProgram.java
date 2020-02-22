package com.pay.kakao.homework.entity.ecological;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class EcologicalProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "program_id")
    private List<ProgramThemes> themes = new ArrayList<>();

    private String serviceRegion;

    private String summary;

    private String description;
}

