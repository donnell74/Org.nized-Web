package com.msuaitp.orgnized.webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonClassBonus implements Serializable {

	private Person email;
	private int class_bonus_id;

	public Person getEmail () {
		return email;
	}

	public void setEmail (Person email) {
		this.email = email;
	}

	public int getClass_bonus_id () {
		return class_bonus_id;
	}

	public void setClass_bonus_id (int class_bonus_id) {
		this.class_bonus_id = class_bonus_id;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PersonClassBonus that = (PersonClassBonus) o;

		if (getClass_bonus_id() != that.getClass_bonus_id()) {
			return false;
		}
		return getEmail().equals(that.getEmail());

	}

	@Override
	public int hashCode () {
		int result = getEmail().hashCode();
		result = 31 * result + getClass_bonus_id();
		return result;
	}
}
