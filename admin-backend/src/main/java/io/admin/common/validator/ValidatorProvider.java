package io.admin.common.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;


@AllArgsConstructor
@Getter
public class ValidatorProvider {
    private final Validator validator;

    public <T> ViolationBuild validate(T object) {
        Set<ConstraintViolation<T>> violations;
        try {
            violations = validator.validate(object);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (ValidationException ve) {
            throw ve;
        }
        return ViolationBuild.build(violations);
    }

    public <T> ViolationBuild validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations;
        try {
            violations = validator.validate(object, groups);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (ValidationException ve) {
            throw ve;
        }
        return ViolationBuild.build(violations);
    }

    public <T> ViolationBuild validateProperty(T object, String propertyName, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations;
        try {
            violations = validator.validateProperty(object, propertyName, groups);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (ValidationException ve) {
            throw ve;
        }
        return ViolationBuild.build(violations);
    }

    public <T> ViolationBuild validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations;
        try {
            violations = validator.validateValue(beanType, propertyName, value, groups);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (ValidationException ve) {
            throw ve;
        }
        return ViolationBuild.build(violations);
    }
}
