/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.JwtAuthenticationFilter;
import com.example.demo.service.impl.UserDetailsServiceImpl;

/**
 * [OVERVIEW] Web Security Configuration.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder - let Spring Security use to encode user's password.
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Prevent request from another domain.
                .authorizeRequests()
                // User
                .antMatchers(HttpMethod.POST, "/api/user/registration", "/api/login").permitAll()
                // Book
                .antMatchers("/api/book/{bookId}", "/api/getbookbyname", "/api/getbooksbyauthor", "/api/getbooksbycategory",
                        "/api/getbookbypublicationdate", "/api/book/search", "/api/book").permitAll()
                // Book item
                .antMatchers("/api/getlistbookitembybookid/{bookId}",
                		"/api/countBookItem").permitAll()
                // Category
                .antMatchers(HttpMethod.GET, "/api/categories-list", "/api/departments-list").permitAll()
                // Department
                .antMatchers(HttpMethod.GET, "/api/departments-list").permitAll()
                // Heath check
                .antMatchers(HttpMethod.GET, "/api", "/api/ping").permitAll()
                .anyRequest().authenticated();
        // Except for the API(s) above, all other requests must be verified before access.
        // Add another class of Filter to check JSON Web Tokens.
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.logout().logoutSuccessUrl("/api/login").logoutUrl("/logout").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}
