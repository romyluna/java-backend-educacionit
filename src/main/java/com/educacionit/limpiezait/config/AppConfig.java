package com.educacionit.limpiezait.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;


@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Limpieza IT",
        version = "1.0",
        description  = "api de Limpieza IT"
))
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                 .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
    }

    //SEGURIDAD DE LOGUEO:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((request) -> {
            //que randomuser no requiera autorizacion/login para ingresar a su pagina
            request.requestMatchers("/randomuser").permitAll()
            //cualquiera que venga que requiera autorizacion
            .anyRequest().authenticated();
        }).formLogin(formLogin -> {
            formLogin.permitAll();
            //.httpBasic(Customizer.withDefaults()); //puedo usar este en vez de esto
        });

        return http.build();
    }

    //OTRA FORMA: EN MEMORIA:
    //QUE PISA A LA FORMA DE LOGUEO  (ES VULNERABLE SOLO SE ENSEÑO PARA MOSTRAR)

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails user = User
                .withUsername("admin")
                .password(passwordEncoder.encode( "admin"))
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    //para encriptar las password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
