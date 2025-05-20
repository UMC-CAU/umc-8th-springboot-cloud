// validation/ExistingStoreValidator.java
package umc.spring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.spring.repository.store.StoreRepository;

@RequiredArgsConstructor
public class ExistingStoreValidator
        implements ConstraintValidator<umc.spring.validation.annotation.ExistingStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && storeRepository.existsById(value);
    }
}
