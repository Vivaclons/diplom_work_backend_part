package kz.spring.zuulserver.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()

                //Auth
                .antMatchers(HttpMethod.POST, "/auth-service/auth/**").permitAll()
                .antMatchers("/auth-service/registration/**").permitAll()
                .antMatchers("/auth-service/admin/**").hasAnyAuthority("ADMIN")

                //Customer
                .antMatchers("/client-service/customer/public/**").hasAnyAuthority("USER", "DOCTOR", "ADMIN")
                .antMatchers("/client-service/customer/private/**").hasAnyAuthority("USER", "ADMIN")

                //MedCenter
                .antMatchers("/med-service/doctor/public/**").hasAnyAuthority("DOCTOR", "MEDCENTER", "ADMIN")
                .antMatchers("/med-service/medCenter/public/**").hasAnyAuthority("DOCTOR", "MEDCENTER", "ADMIN")
                .antMatchers("/med-service/medCenter/private/**").hasAnyAuthority("MEDCENTER", "ADMIN")
                .antMatchers("/med-service/doctor/private/**").hasAnyAuthority("DOCTOR", "ADMIN")

                //Analysis
                .antMatchers("/analysis-service/**").permitAll()

                //Appointment
                .antMatchers("/appointment-service/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}

