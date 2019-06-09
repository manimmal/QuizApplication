package com.selection.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name="USER_ANSWER")
@NamedQuery (name = "UserAnswer.findAll", query="select t from UserAnswer t")
@NoArgsConstructor
public class UserAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UAID", updatable = false, nullable = false)
	private Integer uaid;
	
	@Column (name = "UID")
	private Integer uid;
	
	@Column (name = "QID")
	private Integer qid;
	
	@Column (name = "AID")
	private Integer aid;
	
	@Column (name = "ORDER_NUM")
	private Integer orderNum;

}
