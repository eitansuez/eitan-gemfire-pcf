package org.springframework.cloud.localconfig

import io.pivotal.spring.cloud.service.common.GemfireServiceInfo

/**
 * Created by esuez on 1/15/16.
 */
class GemfireServiceInfoCreator extends LocalConfigServiceInfoCreator<GemfireServiceInfo> {

  GemfireServiceInfoCreator() {
    super(["gemfire"])
  }

  @Override
  GemfireServiceInfo createServiceInfo(String id, String uri) {
    String locator = extractLocatorFromUri(uri)
    new GemfireServiceInfo(id, [locator])
  }

  /**
   * translates gemfire://localhost:1234 into localhost[1234]
   */
  static String extractLocatorFromUri(uri) {
    def matcher = uri =~ /gemfire:\/\/(\w+):(\d+)\/*/
    if (!matcher.matches()) {
      throw new RuntimeException("invalid gemfire uri: $uri")
    }
    def (hostname, port) = [matcher.group(1), matcher.group(2)]
    "$hostname[$port]"
  }
}
