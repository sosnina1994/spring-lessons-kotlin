package ru.rtk.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() } // Отключаем CSRF (не рекомендуется для продакшена) }// Не создаем сессию
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Открытый доступ к сваггеру
                    .anyRequest().authenticated()
            } // Все остальные запросы требуют аутентификации

            //.httpBasic(Customizer.withDefaults()) //  аутенитификация для запросов сваггера
            .formLogin(Customizer.withDefaults()) // Включаем форму логина

        return http.build()
    }

 /*   • Способ аутентификации: httpBasic() использует заголовки HTTP, тогда как formLogin() использует HTML-формы.

    • Цель использования: httpBasic() обычно используется для API, тогда как formLogin()
     предназначен для веб-приложений с пользовательским интерфейсом.

    • Безопасность: Оба метода должны использоваться с HTTPS для обеспечения безопасности.
    Однако formLogin() может предложить более гибкие возможности для управления процессом аутентификации
    и пользовательским интерфейсом.

*/
    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()
    }

    @Bean
    fun userDetailsManager(dataSource: DataSource): JdbcUserDetailsManager {
        return JdbcUserDetailsManager(dataSource)
    }
}