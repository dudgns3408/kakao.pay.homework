package com.pay.kakao.homework.entity.ecological;

import com.pay.kakao.homework.enums.EcologicalProgramTheme;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ProgramThemes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EcologicalProgramTheme theme;
}
