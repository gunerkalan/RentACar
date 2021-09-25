package com.gunerk.rentacar.service.customValids;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Date;

public class RentDateValid  implements ConstraintValidator<RentDate, Date> {

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return false;
    }

    public static boolean isValid(Date rendDate){
        boolean isValid =false;

        LocalDate dateToday = LocalDate.now();

        return isValid;
    }
}
