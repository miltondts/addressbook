package addressbook.entity;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Contact {
  @Id
  @GeneratedValue
  Long id;

  @Column(nullable = false)
  String name;
  
  @Column(nullable = false)
  String street;
  
  @Column(nullable = false)
  String city;
  
  @Column(nullable = false)
  String zipCode;
  
  @Column(nullable = false)
  String country;
  
  @ElementCollection
  @Column(nullable = false)
  Set<String> phoneNumbers;
  
  String createdDate;
  String updatedDate;

  @PrePersist
  void createdAt() {
    final Instant instant = Instant.now();
    final OffsetDateTime odt = instant.atOffset(ZoneOffset.UTC);
    final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    this.createdDate = this.updatedDate = odt.format(dateTimeFormatter1);
  }

  @PreUpdate
  void updatedAt() {
    final Instant instant = Instant.now();
    final OffsetDateTime odt = instant.atOffset(ZoneOffset.UTC);
    final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    this.updatedDate = odt.format(dateTimeFormatter1);
  }
}
