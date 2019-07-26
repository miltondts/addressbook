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
public class ContactUpdateDto {
	@NotNull(message = "Name is mandatory")
	String name;
	
	@NotNull(message = "Steet is mandatory")
	String street;
	
	@NotNull(message = "City is mandatory")
	String city;
	
	@NotNull(message = "Zip Code is mandatory")
	String zipCode;
	
	@NotNull(message = "Country is mandatory")
	String country;

	@ApiModelProperty(value = "A phone numer consists of any number of digits preceded by zero or one '+' character.")
	@ValidPhoneNumbers
	List<String> phoneNumbers;
}
