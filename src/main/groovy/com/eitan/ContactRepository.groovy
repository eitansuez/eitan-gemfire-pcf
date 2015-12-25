package com.eitan

import org.springframework.data.repository.CrudRepository

interface ContactRepository extends CrudRepository<Contact, Long> {
}
