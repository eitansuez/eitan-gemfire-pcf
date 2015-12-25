package com.eitan

import spock.lang.Specification

class ContactSpec extends Specification {

  def "should have a name"() {
    expect:
    new Contact(name: 'eitan').name == 'eitan'
  }
}
