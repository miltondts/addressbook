package addressbook.dto;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter @Setter
public class ContactUpdateAddressDto {
	@NotNull(message = "Steet is mandatory")
	String street;
	
	@NotNull(message = "City is mandatory")
	String city;
	
	@NotNull(message = "Zip Code is mandatory")
	String zipCode;
	
	@NotNull(message = "Country is mandatory")
	String country;
}
