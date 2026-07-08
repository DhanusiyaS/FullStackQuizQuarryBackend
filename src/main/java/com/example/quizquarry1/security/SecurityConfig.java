package com.example.quizquarry1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.quizquarry1.service.CustomUserDetailsService;
import com.example.quizquarry1.util.JwtFilter;

@Configuration
public class SecurityConfig {

        @Autowired
        private JwtFilter jwtFilter;

        @Autowired
        private CustomUserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {

                DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

                provider.setPasswordEncoder(passwordEncoder());

                return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration configuration)
                        throws Exception {

                return configuration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)
                        throws Exception {

                http.cors(Customizer.withDefaults())

                                .csrf(csrf -> csrf.disable())

                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .authorizeHttpRequests(auth -> auth

                                                .requestMatchers("/auth/register",
                                                                "/auth/login",
                                                                "/auth/validate")
                                                .permitAll()

                                                .requestMatchers("/users/**")
                                                .hasRole("INSTRUCTOR")

                                                /*
                                                 * .requestMatchers("/users/add",
                                                 * "/users/update/**",
                                                 * "/users/delete/**",
                                                 * "/users/patchUser/**")
                                                 * .hasRole("INSTRUCTOR")
                                                 */

                                                .requestMatchers("/postQuestionBank")
                                                .hasRole("INSTRUCTOR")

                                                .requestMatchers("/getQuestionBanks",
                                                                "/getQuestionBank/**")
                                                .hasAnyRole("INSTRUCTOR", "STUDENT")

                                                .requestMatchers("/putQuestionBank/**",
                                                                "/patchQuestionBank/**",
                                                                "/deleteQuestionBank/**")
                                                .hasRole("INSTRUCTOR")

                                                .requestMatchers("/bankquestion/post")
                                                .hasRole("INSTRUCTOR")

                                                .requestMatchers("/bankquestion/getAll",
                                                                "/bankquestion/get/**")
                                                .hasAnyRole("INSTRUCTOR", "STUDENT")

                                                .requestMatchers("/bankquestion/update/**",
                                                                "/bankquestion/patchBankQuestion/**",
                                                                "/bankquestion/delete/**")
                                                .hasRole("INSTRUCTOR")

                                                .requestMatchers("/postQuizAssessment")
                                                .hasRole("INSTRUCTOR")

                                                .requestMatchers("/getQuizAssessments",
                                                                "/getQuizAssessment/**")
                                                .hasAnyRole("INSTRUCTOR", "STUDENT")

                                                .requestMatchers("/putQuizAssessment/**",
                                                                "/patchQuizAssessment/**",
                                                                "/deleteQuizAssessment/**")
                                                .hasRole("INSTRUCTOR")
                                                .requestMatchers("/postStudentAttempt")
                                                .hasRole("STUDENT")

                                                .requestMatchers("/getStudentAttempts",
                                                                "/getStudentAttempt/**")
                                                .hasAnyRole("INSTRUCTOR", "STUDENT")

                                                .requestMatchers("/putStudentAttempt/**",
                                                                "/patchStudentAttempt/**",
                                                                "/deleteStudentAttempt/**")
                                                .hasRole("STUDENT")
                                                .requestMatchers("/postAttemptAnswer")
                                                .hasRole("STUDENT")

                                                .requestMatchers("/getAttemptAnswers",
                                                                "/getAttemptAnswer/**")
                                                .hasAnyRole("INSTRUCTOR", "STUDENT")

                                                .requestMatchers("/putAttemptAnswer/**",
                                                                "/patchAttemptAnswer/**",
                                                                "/deleteAttemptAnswer/**")
                                                .hasRole("STUDENT")

                                                .anyRequest()
                                                .authenticated())

                                .authenticationProvider(authenticationProvider())

                                .addFilterBefore(jwtFilter,
                                                UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
