package com.selection.quiz.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
 
@Configuration
public class JDBCConfig  {
 
	private DataSource datasource;
	
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(datasource);
	}
	
   
}
