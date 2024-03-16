package com.github.rafinhalq.securityexample.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllAttributesNotNullValidator.class)
public @interface AllAttributesNotNull {
    String message() default "At least one attribute must not be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean isRecord() default false;
}
