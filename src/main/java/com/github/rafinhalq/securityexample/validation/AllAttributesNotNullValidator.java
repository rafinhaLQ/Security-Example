package com.github.rafinhalq.securityexample.validation;

import com.github.rafinhalq.securityexample.exception.GenericException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AllAttributesNotNullValidator implements ConstraintValidator<AllAttributesNotNull, Object> {

    private boolean isRecord;

    @Override
    public void initialize(AllAttributesNotNull constraintAnnotation) {
        isRecord = constraintAnnotation.isRecord();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var clazz = value.getClass();
        var getterMethods = getGetterMethods(clazz);

        var optional = getterMethods.stream()
            .map(method -> {
                try {
                    return method.invoke(value);
                } catch (Exception e) {
                    throw new GenericException(e, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            })
            .filter(Objects::nonNull)
            .findFirst();


        return optional.isPresent();
    }

    private List<String> getGetterNamesForClasses(Class<?> clazz) {
        var fields = clazz.getDeclaredFields();
        return Arrays.stream(fields)
            .map(Field::getName)
            .map(fieldName -> String.join(
                "",
                "get",
                fieldName.substring(0, 1).toUpperCase(),
                fieldName.substring(1)
            ))
            .toList();
    }

    private List<String> getGetterNamesForRecords(Class<?> clazz) {
        var fields = clazz.getDeclaredFields();
        return Arrays.stream(fields)
            .map(Field::getName)
            .toList();
    }

    private List<Method> getGetterMethods(Class<?> clazz) {
        List<String> getterMethods = isRecord ? getGetterNamesForRecords(clazz):getGetterNamesForClasses(clazz);

        return getterMethods
            .stream()
            .map(fieldName -> {
                try {
                    return clazz.getDeclaredMethod(fieldName);
                } catch (NoSuchMethodException e) {
                    throw new GenericException(e, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            })
            .toList();
    }
}
