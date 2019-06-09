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
@Table (name="QUESTION")
@NamedQuery (name = "Question.findAll", query="select t from Question t")
@NoArgsConstructor
public class Question {
	
	@Id
	@Column (name = "QID")
	private Integer qid;
	
	@Column (name = "QUESTION")
	private String question;

}
