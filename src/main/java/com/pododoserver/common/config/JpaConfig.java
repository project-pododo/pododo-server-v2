package com.pododoserver.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> Optional.of(1L); //todo: 스프링 시큐리티로 인증 기능 붙일떄, 수정
    }
}
