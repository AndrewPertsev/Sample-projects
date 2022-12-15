package by.pertsev.hotel.hiber.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtUserDSImpl jwtUserDS;
    private final JwtFilter jwtFilter;
    private static final String ADMIN_ENDPOINT_PAGE = "/users/admin";
    private static final String USER_ENDPOINT_SUCCESS_LOGIN = "/main";
    private static final String COMMON_ENDPOINT_LOGIN = "/auth/login";
    private static final String COMMON_ENDPOINT_LOGOUT = "/logout";
    private static final String COMMON_ENDPOINT_REGISTRATION = "/auth/registration";
    private static final String COMMON_ENDPOINT_ERROR = "/error";
    private static final String LOGIN_PROCESSING_URL = "/login_process";

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        log.info("configure AuthenticationManagerBuilder : build  jwtUserDS");

        builder.userDetailsService(jwtUserDS)
        //      .passwordEncoder(passwordEncoder())    //BCRYPT
        ;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("configure HttpSecurity");


        httpSecurity

                .csrf().disable() //for jwt json
                .authorizeRequests()
                //       .antMatchers("/**").permitAll()
                .antMatchers(ADMIN_ENDPOINT_PAGE, "/users", "/requestusers", "/apartments", "/offers", "/timesheets").hasRole("ADMIN")  //not ROLE_ADMIN ->  hasAuthorityROLE_ADMIN @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_BIG_BOSS')")
                .antMatchers(COMMON_ENDPOINT_LOGIN, COMMON_ENDPOINT_REGISTRATION, COMMON_ENDPOINT_ERROR, "/users", "/requestusers", "/apartments", "/offers", "/timesheets").permitAll()
                // //  .anyRequest().hasAnyRole("0", "1", "2")
                ////                .antMatchers("/**").permitAll();
                .anyRequest().hasAnyRole("USER", "ADMIN", "GUEST")
                .and()
                .formLogin().loginPage(COMMON_ENDPOINT_LOGIN)
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .defaultSuccessUrl(USER_ENDPOINT_SUCCESS_LOGIN, true)/////////////////////
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl(COMMON_ENDPOINT_LOGOUT)
                .logoutSuccessUrl(COMMON_ENDPOINT_LOGIN)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();//TODO
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() { //BCRYPT
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.info("configure super AuthenticationManagerBean");

        return super.authenticationManagerBean();  //check password in authcontroller
    }
}

