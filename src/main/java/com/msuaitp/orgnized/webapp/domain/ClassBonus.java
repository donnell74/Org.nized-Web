package com.msuaitp.orgnized.webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassBonus {
	private int id; //id
	private String course_code;
	private String semester;
	private Date createdAt;
	private Date updatedAt;

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getCourseCode() {
		return course_code;
	}

	public void setCourseCode(String courseCode) {
		this.course_code = courseCode;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((course_code == null) ? 0 : course_code.hashCode());
		result = prime * result
				+ ((semester == null) ? 0 : semester.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ClassBonus other = (ClassBonus) obj;
		if (course_code == null) {
			if (other.course_code != null) {
				return false;
			}
		} else if (!course_code.equals(other.course_code)) {
			return false;
		}
		if (semester == null) {
			if (other.semester != null) {
				return false;
			}
		} else if (!semester.equals(other.semester)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ClassBonus [id=" + id + ", courseCode="
				+ course_code + ", semester=" + semester + "]";
	}

}