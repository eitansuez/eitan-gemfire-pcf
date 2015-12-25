package com.eitan

import com.gemstone.gemfire.cache.client.ClientCache
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.gemfire.client.ClientRegionFactoryBean
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories

import static com.gemstone.gemfire.cache.client.ClientRegionShortcut.PROXY

@SpringBootApplication
@EnableGemfireRepositories(basePackages='com.eitan')
class ContactApplication {

  @Autowired
  @Bean(name="contactsRegion")
  public ClientRegionFactoryBean<Long, Contact> contactsRegion(ClientCache cache) {
    ClientRegionFactoryBean<Long, Contact> contactRegion = new ClientRegionFactoryBean<>()
    contactRegion.setName("ContactsRegion")
    contactRegion.setCache(cache)
    contactRegion.setShortcut(PROXY)
    contactRegion
  }

  static void main(String[] args) {
    SpringApplication.run(ContactApplication, args)
  }
}
