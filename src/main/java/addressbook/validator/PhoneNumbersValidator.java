package addressbook.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumbersValidator implements ConstraintValidator<ValidPhoneNumbers, List<String>> {
	@Override
	public void initialize(final ValidPhoneNumbers constraintAnnotation) {
	}

	@Override
	public boolean isValid(final List<String> phoneNumbers, final ConstraintValidatorContext context) {
		if(phoneNumbers == null || phoneNumbers.size() == 0)
			return false;
		for(String phoneNumber : phoneNumbers) {
			if(!phoneNumber.matches("\\+?\\d+"))
				return false;
		}
		return true;
	}
}
