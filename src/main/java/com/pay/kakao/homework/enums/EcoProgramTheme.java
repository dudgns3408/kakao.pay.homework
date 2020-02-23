package com.pay.kakao.homework.enums;

import com.pay.kakao.homework.exception.HomeworkException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EcoProgramTheme {
    CULTURE_ECOLOGICAL_EXPERIENCE("문화생태체험"),
    NATURE_ECOLOGICAL_EXPERIENCE("자연생태체험"),
    CHILD_YOUTH_EXPERIENCE("아동·청소년 체험학습"),
    HEALTH_SHARE_CAMP("건강나누리캠프"),
    RURAL_FISHING_VILLAGE_ECOLOGICAL_EXPERIENCE("농어촌생태체험"),
    FOREST_CURE("숲 치유"),
    NATURE_ECOLOGICAL("자연생태"),
    FEELING("느낄거리"),
    PLAYING("놀거리"),
    WATCHING("볼거리"),
    HISTORY_CULTURE("역사문화"),
    COAST_ECOLOGICAL("해안생태"),
    PL01("PL01"),
    ;

    private String description;

    EcoProgramTheme(String description) {
        this.description = description;
    };

    public static EcoProgramTheme of(String description) {
        for ( EcoProgramTheme theme : EcoProgramTheme.values() ) {
            if (description.equals(theme.getDescription())) {
                return theme;
            }
        }

        throw new HomeworkException("정의되지 않은 테마별 분류값입니다. {" + description + "}", HttpStatus.BAD_REQUEST);
    }
}
