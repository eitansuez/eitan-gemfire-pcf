package com.eitan

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories

@SpringBootApplication
@ImportResource("clientCache.xml")
@EnableGemfireRepositories(basePackages='com.eitan')
class ContactApplication {

  static void main(String[] args) {
    SpringApplication.run(ContactApplication, args)
  }
}
