package tn.encar.gestnotes.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import tn.encar.gestnotes.models.enums.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

	    private final JwtAuthenticationFilter jwtAuthFilter;
	    private final AuthenticationProvider authenticationProvider;

	@Bean
	  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
       http
       	.csrf()
       	.disable()
       	.authorizeHttpRequests()
       	.requestMatchers("/api/**")
       	.permitAll()
       	.anyRequest()
       	.authenticated()
       	.and()
       	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
       	.and()
       	.authenticationProvider(authenticationProvider)
       	.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
	  }
  /* 	.requestMatchers("/api/enseignants/**").hasRole(Role.ENSEIGNANT.name())
    .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
    .requestMatchers(HttpMethod.POST,"api/note/**").hasAnyAuthority(Role.ADMIN.name(),Role.ENSEIGNANT.name())
    .requestMatchers(HttpMethod.GET,"api/note/**").hasAnyAuthority(Role.ADMIN.name(),Role.ENSEIGNANT.name(),Role.ETUDIANT.name())
    .requestMatchers(HttpMethod.POST,"api/departement/**").hasRole(Role.ADMIN.name())
    .requestMatchers(HttpMethod.GET,"api/departement/**").hasAnyAuthority(Role.ADMIN.name(),Role.ENSEIGNANT.name(),Role.ETUDIANT.name())
    .requestMatchers(HttpMethod.POST,"api/matiere/**").hasRole(Role.ADMIN.name())
    .requestMatchers(HttpMethod.GET,"api/matiere/**").hasAnyAuthority(Role.ADMIN.name(),Role.ENSEIGNANT.name(),Role.ETUDIANT.name())*/
	
	

}
