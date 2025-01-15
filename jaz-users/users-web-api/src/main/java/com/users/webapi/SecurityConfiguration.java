package com.users.webapi;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**
     * SecurityFilterChain pozwala na konfigurację zabezpieczeń HTTP.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /**
         * Metoda authorizeHttpRequests w Spring Security służy
         * do definiowania reguł dostępu do różnych ścieżek (endpointów) w Twojej aplikacji.
         * Pozwala ona na określenie, które zasoby są dostępne publicznie, a które wymagają uwierzytelnienia
         * lub posiadania określonych uprawnień/role.
         */

        http
                .authorizeHttpRequests(
                        /**
                         * http
                         *     .authorizeHttpRequests(authorize ->
                         *         authorize
                         *             .antMatchers("/public/**").permitAll()  // Dostęp publiczny
                         *             .antMatchers("/admin/**").hasRole("ADMIN")  // Tylko dla użytkowników z rolą ADMIN
                         *             .anyRequest().authenticated()  // Wszystkie inne ścieżki wymagają uwierzytelnienia
                         *     );
                         *
                         *     antMatchers(String... patterns): Metoda ta przyjmuje wzorce URL i stosuje do nich następne reguły. Możesz określić konkretną ścieżkę, taką jak /login, lub użyć symboli wieloznacznych, takich jak /admin/** (co oznacza wszystkie ścieżki rozpoczynające się od /admin/).
                         *
                         * permitAll(): Ta reguła umożliwia dostęp do określonego endpointu dla wszystkich użytkowników, niezależnie od tego, czy są uwierzytelnieni, czy nie.
                         *
                         * authenticated(): Wymaga, aby użytkownik był uwierzytelniony (zalogowany), aby uzyskać dostęp do danej ścieżki.
                         *
                         * Zawsze umieszczaj bardziej specyficzne reguły przed ogólniejszymi.
                         * Na przykład, reguła dla /admin/** powinna pojawić się przed regułą dla anyRequest().
                         * Możesz łączyć różne reguły, a
                         * by stworzyć kompleksowe zasady kontroli dostępu,
                         * które odpowiadają wymaganiom Twojej aplikacji.
                         */

                        x->
                                x.requestMatchers("/api/**").permitAll()
                                        .anyRequest().authenticated()

                        )
                .formLogin(formLogin ->
                        formLogin

                                //.loginPage()
                                .defaultSuccessUrl("/chat", true)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/login")
                );

        return http.build();
    }

}
