package basilium.basiliumspring.configuration;

import basilium.basiliumspring.service.user.BrandUserService;
import basilium.basiliumspring.service.user.NormalUserService;
import basilium.basiliumspring.service.user.SuperUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.validation.Valid;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final NormalUserService normalUserService;
    private final BrandUserService brandUserService;
    private final SuperUserService superUserService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/v1/normalUser/signup", "/v1/normalUser/login").permitAll()
                .antMatchers("/v1/brandUser/signup", "/v1/brandUser/login").permitAll()
                .antMatchers("/v1/superUser/signup", "/v1/superUser/login").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/normalUser/**").hasRole("NORMAL_USER")
                .antMatchers(HttpMethod.POST, "/v1/brandUser/**").hasRole("BRAND_USER")
                .antMatchers(HttpMethod.POST, "/v1/superUser/**").hasRole("SUPER_USER")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(normalUserService, brandUserService, superUserService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}