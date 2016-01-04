package com.eitan

import org.springframework.data.annotation.Id
import org.springframework.data.gemfire.mapping.Region

@Region("ContactsRegion")
class Contact {
  @Id Long id
  int age
  String name
}