package com.eitan

import com.gemstone.gemfire.cache.client.ClientCache
import com.gemstone.gemfire.pdx.ReflectionBasedAutoSerializer
import io.pivotal.spring.cloud.service.gemfire.GemfireServiceConnectorConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.java.AbstractCloudConfig
import org.springframework.cloud.service.ServiceConnectorConfig
import org.springframework.context.annotation.Bean
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories

@SpringBootApplication
@EnableGemfireRepositories(basePackages = 'com.eitan')
class ContactApplication extends AbstractCloudConfig {

  public ServiceConnectorConfig createGemfireConnectorConfig() {
    GemfireServiceConnectorConfig gemfireConfig = new GemfireServiceConnectorConfig();
    gemfireConfig.setPdxSerializer(new ReflectionBasedAutoSerializer("com.eitan.Contact"))
    gemfireConfig
  }

  @Bean
  public ClientCache myClientCache() {
    ClientCache cache = cloud().getServiceConnector("my-gemfire-service",
        ClientCache.class,
        createGemfireConnectorConfig())
    cache
  }

  static void main(String[] args) {
    SpringApplication.run(ContactApplication, args)
  }
}
