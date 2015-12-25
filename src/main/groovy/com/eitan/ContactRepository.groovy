package com.eitan

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface ContactRepository extends CrudRepository<Contact, Long> {
}
