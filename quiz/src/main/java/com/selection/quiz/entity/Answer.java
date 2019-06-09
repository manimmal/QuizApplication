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
@Table (name="ANSWER")
@NamedQuery (name = "Answer.findAll", query="select t from Answer t")
@NoArgsConstructor
public class Answer {
	
	@Id
	@Column (name = "AID")
	private Integer aid;
	
	@Column (name = "QID")
	private Integer qid;
	
	@Column (name = "ANSWER")
	private String answer;
	
	@Column (name = "CORRECT")
	private String correct;

}
