package com.eitan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
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
    if (repository.exists(id)) {
      return repository.findOne(id)
    }
    throw new ResourceNotFoundException("resource for id $id not found")
  }

  @RequestMapping(value="/", method=POST)
  ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
    repository.save(contact)
    new ResponseEntity<Contact>(CREATED)
  }
}
