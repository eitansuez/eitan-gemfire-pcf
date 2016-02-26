package com.eitan

import com.gemstone.gemfire.cache.Region
import com.gemstone.gemfire.cache.client.ClientCache
import com.gemstone.gemfire.cache.client.ClientRegionShortcut
import com.gemstone.gemfire.pdx.ReflectionBasedAutoSerializer
import io.pivotal.spring.cloud.service.gemfire.GemfireServiceConnectorConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.java.AbstractCloudConfig
import org.springframework.cloud.service.ServiceConnectorConfig
import org.springframework.context.annotation.Bean
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories

@SpringBootApplication
@EnableGemfireRepositories(basePackages = 'com.eitan')
class ContactApplication extends AbstractCloudConfig {

  ServiceConnectorConfig createGemfireConnectorConfig() {
    def gemfireConfig = new GemfireServiceConnectorConfig()
    gemfireConfig.setPdxSerializer(new ReflectionBasedAutoSerializer("com.eitan.Contact"))
    gemfireConfig
  }

  @Bean
  ClientCache myClientCache() {
    cloud().getServiceConnector("my-gemfire-service",
        ClientCache,
        createGemfireConnectorConfig())
  }

  @Autowired
  @Bean @Qualifier("ContactsRegion")
  Region contactsRegion(ClientCache cache) {
    cache
        .createClientRegionFactory(ClientRegionShortcut.PROXY)
        .create("ContactsRegion")
  }

  @Bean
  CommandLineRunner loadDatabase(ContactRepository repo) {
    return { args ->
      20.times { i ->
        repo.save(new Contact(id: i, age: i, name: "Joe$i"))
      }
    }
  }

  static void main(String[] args) {
    SpringApplication.run(ContactApplication, args)
  }
}
