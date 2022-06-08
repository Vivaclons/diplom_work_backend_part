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
                .antMatchers("/auth-service/doctor/**").hasAnyAuthority("DOCTOR", "ADMIN")
                .antMatchers("/auth-service/customer/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/auth-service/medCenter/**").hasAnyAuthority("MED_CENTER", "ADMIN")
                //Customer
                .antMatchers("/client-service/customer/public/**").permitAll()
//                .antMatchers("/client-service/customer/public/**").hasAnyAuthority("USER", "DOCTOR", "ADMIN")
                .antMatchers("/client-service/customer/private/**").hasAnyAuthority("USER", "ADMIN")

                //MedCenter
                .antMatchers("/med-service/doctor/public/**").permitAll()
//                .antMatchers("/med-service/doctor/public/**").hasAnyAuthority("DOCTOR", "MED_CENTER", "ADMIN")
                .antMatchers("/med-service/medCenter/public/**").permitAll()
//                .antMatchers("/med-service/MED_CENTER/public/**").hasAnyAuthority("DOCTOR", "MEDCENTER", "ADMIN")
                .antMatchers("/med-service/medCenter/private/**").hasAnyAuthority("MED_CENTER", "ADMIN")
                .antMatchers("/med-service/doctor/private/**").hasAnyAuthority("DOCTOR", "ADMIN")

                //Specialty
                .antMatchers("/med-service/specialty/**").permitAll()

                //Analysis
                .antMatchers("/analysis-service/**").hasAnyAuthority("USER", "ADMIN", "DOCTOR", "MED_CENTER")

                //Appointment
                .antMatchers("/appointment-service/**").hasAnyAuthority("USER", "ADMIN", "DOCTOR", "MED_CENTER")

                .antMatchers("/appointment-service/private/**").hasAnyAuthority("ADMIN", "USER", "DOCTOR", "MED_CENTER")

                .antMatchers("/appointment-service/public/**").permitAll()
//                .antMatchers("/appointment-service/public/**").hasAnyAuthority("ADMIN", "DOCTOR", "MED_CENTER", "USER")

                //Review
                .antMatchers("/review-service/review/doctor/private/**").hasAnyAuthority("USER", "ADMIN", "DOCTOR", "MED_CENTER")
                .antMatchers("/review-service/review/medCenter/private/**").hasAnyAuthority("USER", "ADMIN", "DOCTOR", "MED_CENTER")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                //Payment
                .antMatchers("payment-service/pay/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}

