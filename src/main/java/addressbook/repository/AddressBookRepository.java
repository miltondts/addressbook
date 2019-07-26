package addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import addressbook.entity.Contact;

@Repository
public interface AddressBookRepository extends JpaRepository<Contact, Long>{

}
