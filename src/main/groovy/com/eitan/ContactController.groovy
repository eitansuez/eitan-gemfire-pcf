package com.eitan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
@RequestMapping("/contacts2")
class ContactController {
  @Autowired
  ContactRepository repository

  @RequestMapping("/")
  List<Contact> contacts() {
    repository.findAll().toList()
  }

  @RequestMapping("/{id}")
  Contact contact(@PathVariable Long id) {
    repository.findOne(id)
  }

  @RequestMapping(value="/", method=POST)
  ResponseEntity<Contact> saveContact(Contact contact) {
    repository.save(contact)
    new ResponseEntity<Contact>(CREATED)
  }
}
