package addressbook.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import addressbook.dto.ContactCreateDto;
import addressbook.dto.ContactDto;
import addressbook.dto.ContactUpdateAddressDto;
import addressbook.dto.ContactUpdateDto;
import addressbook.dto.ContactUpdatePhoneNumbersDto;
import addressbook.service.AddressBookService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Address Book Management System")
public class AddressBookController {
	@Autowired
	AddressBookService addressBookService;

	@PostMapping(path = "/contacts/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ContactDto createContact(@Valid @RequestBody final ContactCreateDto contactCreateDto) {
		return addressBookService.createContact(contactCreateDto);
	}

	@GetMapping(path = "/contacts/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ContactDto> getContacts() {
		return addressBookService.getContacts();
	}
	
	@GetMapping(path = "/contacts/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ContactDto getContact(@PathVariable final Long id) {
		return addressBookService.getContact(id);
	}

	@PutMapping(path = "/contacts/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ContactDto updateContact(@PathVariable final Long id,
			@Valid @RequestBody final ContactUpdateDto contactUpdateDto) {
		return this.addressBookService.updateContact(id, contactUpdateDto);
	}

	@PatchMapping(path = "/contacts/{id}/address", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ContactDto updateContactAddress(@PathVariable final Long id,
			@Valid @RequestBody final ContactUpdateAddressDto contactUpdateAddressDto) {
		return this.addressBookService.updateContactAddress(id, contactUpdateAddressDto);
	}

	@PatchMapping(path = "/contacts/{id}/phoneNumbers", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ContactDto updateContactAddress(@PathVariable final Long id,
			@Valid @RequestBody final ContactUpdatePhoneNumbersDto contactUpdatePhoneNumbersDto) {
		return this.addressBookService.updateContactPhoneNumbers(id, contactUpdatePhoneNumbersDto);
	}

	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable final Long id) {
		this.addressBookService.deleteContact(id);
	}
}
