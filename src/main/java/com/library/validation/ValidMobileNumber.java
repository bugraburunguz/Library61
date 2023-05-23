package com.library.validation;


import com.library.validation.impl.MobileNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = MobileNumberValidator.class)
@Target({FIELD,TYPE_PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidMobileNumber {

    String message() default "Invalid Mobile Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
