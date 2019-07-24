package addressbook.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { PhoneNumbersValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPhoneNumbers {
	String message() default "At least one phone number is needed. Must be all digits. Optionally can have a '+' int the beginnning. E.g. +351910910910";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
