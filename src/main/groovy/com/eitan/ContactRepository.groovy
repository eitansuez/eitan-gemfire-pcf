package com.eitan

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component

@Component
interface ContactRepository extends CrudRepository<Contact, Long> {
  List<Contact> findByAgeGreaterThan(@Param("age") int age)
  List<Contact> findByAgeLessThan(@Param("age") int age)
  List<Contact> findByAge(@Param("age") int age)
}
