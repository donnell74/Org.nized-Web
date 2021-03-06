package com.msuaitp.orgnized.webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PossibleAnswer {

	/*
	 * id question_id text
	 */

	private int id;
	private Question question_id; // from question_id in DB
	private String text;
	private Date createdAt;
	private Date updatedAt;
	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question getQuestion_id () {
		return question_id;
	}

	public void setQuestion_id (Question question_id) {
		this.question_id = question_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PossibleAnswer that = (PossibleAnswer) o;

		if (id != that.id) {
			return false;
		}
		if (question_id != that.question_id) {
			return false;
		}
		if (createdAt != null ? !createdAt.equals(that.createdAt)
				: that.createdAt != null) {
			return false;
		}
		if (text != null ? !text.equals(that.text) : that.text != null) {
			return false;
		}
		return !(updatedAt != null ? !updatedAt.equals(that.updatedAt)
				: that.updatedAt != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		//result = 31 * result + question_id;
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
		result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PossibleAnswer{" + "id=" + id + ", question_id=" + question_id
				+ ", text='" + text + '\'' + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + '}';
	}

	public int getCount () {
		return count;
	}

	public void setCount (int count) {
		this.count = count;
	}
}