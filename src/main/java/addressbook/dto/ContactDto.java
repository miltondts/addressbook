package addressbook.dto;

import java.util.List;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {
	Long id;
	String name;
	String street;
	String city;
	String zipCode;
	String country;
	List<String> phoneNumbers;

	public ContactDto(Long id, String name, String street, String city, String zipCode, String country,
			List<String> phoneNumbers) {
		this.id = id;
		this.name = name;
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
		this.phoneNumbers = phoneNumbers;
	}
}
