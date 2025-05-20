// validation/annotation/NotDuplicatedMission.java
package umc.spring.validation.annotation;

import jakarta.validation.*;
import umc.spring.validation.NotDuplicatedMissionValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotDuplicatedMissionValidator.class)
public @interface NotDuplicatedMission {
    String message() default "이미 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
