package addressbook.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import addressbook.validator.ValidPhoneNumbers;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
public class ContactCreateDto {
	@ApiModelProperty(example = "Cy D. Fect")
	@NotNull(message = "Name is mandatory")
	String name;

	@ApiModelProperty(example = "Meaning road, 42")
	@NotNull(message = "Steet is mandatory")
	String street;

	@ApiModelProperty(example = "Zeto City")
	@NotNull(message = "City is mandatory")
	String city;

	@ApiModelProperty(example = "0451-451")
	@NotNull(message = "Zip Code is mandatory")
	String zipCode;

	@ApiModelProperty(example = "The World")
	@NotNull(message = "Country is mandatory")
	String country;

	@ApiModelProperty(value = "A phone numer consists of any number of digits preceded by zero or one '+' character.", example = "[\"910910910\", \"+351987654321\"]")
	@ValidPhoneNumbers
	List<String> phoneNumbers;
}
