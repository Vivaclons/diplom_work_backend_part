package kz.spring.authentication.config;

import kz.spring.authentication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerService customerService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/customerAuth/auth/admin").hasAuthority("ADMIN")
                .antMatchers("/customerAuth/auth/user").hasAuthority("USER")
//                .antMatchers("/customer/auth/doctor").hasAuthority("DOCTOR")
//                .antMatchers("/customer/auth/medCenter").hasAuthority("MEDCENTER")
                .antMatchers(
                        "/customerAuth/**",
                        "/customerAuth/delete/**",
                        "/auth/**",
                        "/registration/add-customer",
                        "/registration/activate/**").permitAll()
                .antMatchers("/customerAuth/admin/create").hasAuthority("ADMIN")
                .antMatchers("/customerAuth/user/create").hasAuthority("USER")
//                .antMatchers("/customer/doctor/create").hasAuthority("DOCTOR")
//                .antMatchers("/customer/medCenter/create").hasAuthority("MEDCENTER")
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService).passwordEncoder(passwordEncoder());
    }
}
