// validation/annotation/ExistingStore.java
package umc.spring.validation.annotation;

import jakarta.validation.*;
import umc.spring.validation.ExistingStoreValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingStoreValidator.class)
public @interface ExistingStore {
    String message() default "존재하지 않는 Store 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
