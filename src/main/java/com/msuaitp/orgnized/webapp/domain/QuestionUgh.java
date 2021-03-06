package com.msuaitp.orgnized.webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionUgh {

	/*
	 * id survey_id question_text type possible_answers roles
	 */

	private int id;
	private Survey survey_id;
	private String question_text;
	private typeEnum type;
	private List<PossibleAnswerUgh> possible_answers;
	private List<Role> roles;
	private Date createdAt;
	private Date updatedAt;

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public Survey getSurvey_id () {
		return survey_id;
	}

	public void setSurvey_id (Survey survey_id) {
		this.survey_id = survey_id;
	}

	public String getQuestion_text () {
		return question_text;
	}

	public void setQuestion_text (String questionText) {
		this.question_text = questionText;
	}

	public List<Role> getRoles () {
		return roles;
	}

	public void setRoles (List<Role> roles) {
		this.roles = roles;
	}

	public List<PossibleAnswerUgh> getPossible_answers () {
		return possible_answers;
	}

	public void setPossible_answers (List<PossibleAnswerUgh> possible_answers) {
		this.possible_answers = possible_answers;
	}

	public typeEnum getType () {
		return type;
	}

	public void setType (typeEnum type) {
		this.type = type;
	}

	public Date getCreatedAt () {
		return createdAt;
	}

	public void setCreatedAt (Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt () {
		return updatedAt;
	}

	public void setUpdatedAt (Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public enum typeEnum {
		RADIO, CHECKBOX, TEXT
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		QuestionUgh question = (QuestionUgh) o;

		if (id != question.id) {
			return false;
		}
		if (survey_id != question.survey_id) {
			return false;
		}
		if (createdAt != null ? !createdAt.equals(question.createdAt)
				: question.createdAt != null) {
			return false;
		}
		if (possible_answers != null ? !possible_answers
				.equals(question.possible_answers)
				: question.possible_answers != null) {
			return false;
		}
		if (question_text != null ? !question_text
				.equals(question.question_text)
				: question.question_text != null) {
			return false;
		}
		if (roles != null ? !roles.equals(question.roles)
				: question.roles != null) {
			return false;
		}
		if (type != question.type) {
			return false;
		}
		return !(updatedAt != null ? !updatedAt.equals(question.updatedAt)
				: question.updatedAt != null);

	}

	@Override
	public int hashCode () {
		int result = id;
		result = 31 * result
				+ (question_text != null ? question_text.hashCode() : 0);
		result = 31 * result
				+ (possible_answers != null ? possible_answers.hashCode() : 0);
		result = 31 * result + (roles != null ? roles.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
		result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
		return result;
	}

	@Override
	public String toString () {
		return "Question{" + "id=" + id + ", survey_id=" + survey_id
				+ ", question_text='" + question_text + '\''
				+ ", possible_answers=" + possible_answers + ", roles=" + roles
				+ ", type=" + type + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + '}';
	}
}