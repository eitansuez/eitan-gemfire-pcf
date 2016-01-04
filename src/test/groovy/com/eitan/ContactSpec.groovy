package com.eitan

import spock.lang.Specification

class ContactSpec extends Specification {

  def "should have a name"() {
    expect:
    new Contact(name: 'eitan').name == 'eitan'
  }

  def "should have an age"() {
    expect:
    new Contact(name: 'eitan', age: 45).age == 45
  }
}
