package org.springframework.cloud.localconfig

import spock.lang.Specification

/**
 * Created by esuez on 1/15/16.
 */
class GemfireServiceInfoCreatorSpec extends Specification {
  def "should produce locator string from uri"() {
    expect:
    GemfireServiceInfoCreator.extractLocatorFromUri("gemfire://localhost:1234") == "localhost[1234]"
  }
  def "should produce locator string from uri with trailing slash"() {
    expect:
    GemfireServiceInfoCreator.extractLocatorFromUri("gemfire://localhost:1234/") == "localhost[1234]"
  }
}
