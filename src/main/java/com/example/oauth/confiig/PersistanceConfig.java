package com.example.oauth.confiig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * spring-boot-autoconfigure spring.factories
 * org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
 * 已經自動配置，這邊再撰寫一次，可以進行覆蓋
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.example.oauth.domain.repository")
public class PersistanceConfig {

}
