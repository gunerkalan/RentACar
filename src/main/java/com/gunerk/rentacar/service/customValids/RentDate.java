package com.gunerk.rentacar.service.customValids;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TcValid.class)
public @interface RentDate {

    String message() default "İnvalid Rent Date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
