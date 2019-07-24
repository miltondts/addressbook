package addressbook.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import addressbook.validator.ValidPhoneNumbers;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Validated
@Getter @Setter
public class ContactUpdatePhoneNumbersDto {
	
	@ApiModelProperty(value = "A phone numer consists of any number of digits preceded by zero or one '+' character.")
	@ValidPhoneNumbers
	List<String> phoneNumbers;
}
