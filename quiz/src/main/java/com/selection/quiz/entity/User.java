package com.selection.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name="USER")
@NamedQuery (name = "User.findAll", query="select t from User t")
@NoArgsConstructor
public class User {
	
	@Id
	@Column (name = "UID")
	private Integer uid;
	
	@Column (name = "USERNAME")
	private String userName;
	
	@Column (name = "ROLE")
	private String role;
	
	@Column (name = "PASSWORD")
	private String password;

}
