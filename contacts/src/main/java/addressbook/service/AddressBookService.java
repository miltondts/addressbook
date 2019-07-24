package addressbook.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import addressbook.dto.ContactCreateDto;
import addressbook.dto.ContactDto;
import addressbook.dto.ContactUpdateAddressDto;
import addressbook.dto.ContactUpdateDto;
import addressbook.dto.ContactUpdatePhoneNumbersDto;
import addressbook.entity.Contact;
import addressbook.repository.AddressBookRepository;

@Service
public class AddressBookService {
	AddressBookRepository contactRepository;

	@Autowired
	public AddressBookService(final AddressBookRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	private ContactDto entToDto(Contact contact) {
		return new ContactDto(contact.getId(), contact.getName(), contact.getStreet(), contact.getCity(),
				contact.getZipCode(), contact.getCountry(), new ArrayList<String>(contact.getPhoneNumbers()));
	}

	public ContactDto createContact(@Valid ContactCreateDto contactCreateDto) {
		Contact contact = new Contact();
		contact.setName(contactCreateDto.getName());
		contact.setStreet(contactCreateDto.getStreet());
		contact.setCity(contactCreateDto.getCity());
		contact.setZipCode(contactCreateDto.getZipCode());
		contact.setCountry(contactCreateDto.getCountry());
		contact.setPhoneNumbers(new HashSet<>(contactCreateDto.getPhoneNumbers()));
		contactRepository.save(contact);
		return entToDto(contact);
	}

	public ContactDto getContact(Long id) {
		final Contact contact = contactRepository.getOne(id);
		return entToDto(contact);
	}

	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}

	public ContactDto updateContact(Long id, @Valid ContactUpdateDto contactUpdateDto) {
		final Contact contact = contactRepository.getOne(id);
		contact.setName(contactUpdateDto.getName());
		contact.setStreet(contactUpdateDto.getStreet());
		contact.setCity(contactUpdateDto.getCity());
		contact.setZipCode(contactUpdateDto.getZipCode());
		contact.setCountry(contactUpdateDto.getCountry());
		contact.setPhoneNumbers(new HashSet<>(contactUpdateDto.getPhoneNumbers()));
		contactRepository.save(contact);
		return entToDto(contact);
	}

	public ContactDto updateContactAddress(Long id, @Valid ContactUpdateAddressDto contactUpdateAddressDto) {
		final Contact contact = contactRepository.getOne(id);
		contact.setStreet(contactUpdateAddressDto.getStreet());
		contact.setCity(contactUpdateAddressDto.getCity());
		contact.setZipCode(contactUpdateAddressDto.getZipCode());
		contact.setCountry(contactUpdateAddressDto.getCountry());
		contactRepository.save(contact);
		return entToDto(contact);
	}

	public ContactDto updateContactPhoneNumbers(Long id,
			@Valid ContactUpdatePhoneNumbersDto contactUpdatePhoneNumbersDto) {
		final Contact contact = contactRepository.getOne(id);
		contact.setPhoneNumbers(new HashSet<>(contactUpdatePhoneNumbersDto.getPhoneNumbers()));
		contactRepository.save(contact);
		return entToDto(contact);
	}

	public List<ContactDto> getContacts() {
		return contactRepository.findAll().stream().map(contact -> entToDto(contact)).collect(Collectors.toList());
	}
}
