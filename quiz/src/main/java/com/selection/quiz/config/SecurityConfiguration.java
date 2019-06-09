package com.selection.quiz.config;

import java.util.Base64;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
     
    @Bean
    public DataSource getDataSource () {
    	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    	EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
    			.addScript("scripts/create-db.sql")
    			.addScript("scripts/insert-data.sql")
    			.build();
    	return db;
    }
    
    @Override
   	protected void configure(HttpSecurity http) throws Exception {
   		http.csrf().disable().
   				authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
   				.and().httpBasic();
   	}


@Bean
public PasswordEncoder customPasswordEncoder() {
    return new PasswordEncoder() {
        @Override
        public String encode(CharSequence charSequence) {
        	if (charSequence == null) {
                return null;
              }
              byte[] barr = new byte[charSequence.length()];
              for (int i = 0; i < barr.length; i++) {
                barr[i] = (byte) charSequence.charAt(i);
              }
            return Base64.getEncoder().encodeToString(barr);
        }
        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
        	if (rawPassword == null) {
                return false;
              }
              byte[] barr = new byte[rawPassword.length()];
              for (int i = 0; i < barr.length; i++) {
                barr[i] = (byte) rawPassword.charAt(i);
              }
            return encodedPassword.equals(new String (barr));
        }
    };
}

}

