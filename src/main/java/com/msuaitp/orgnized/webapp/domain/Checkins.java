package com.msuaitp.orgnized.webapp.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.msuaitp.orgnized.webapp.controller.CheckinsController;

import java.util.Date;
import java.util.logging.Logger;

@JsonSerialize
@JsonDeserialize
public class Checkins {
	private final static Logger LOG = Logger.getLogger(CheckinsController.class.getName());

	private int id;
	private Person email; // Based on person stored in DB
	private Date date_scanned;
	private Date createdAt;
	private Date updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getEmail () {
		return email;
	}

	public void setEmail (Person email) {
		this.email = email;
	}

	public Date getDate_scanned() {
		return date_scanned;
	}

	public void setDate_scanned(Date date_scanned) {
		this.date_scanned = date_scanned;
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

		Checkins checkins = (Checkins) o;

		if (id != checkins.id) {
			return false;
		}
		if (createdAt != null ? !createdAt.equals(checkins.createdAt)
				: checkins.createdAt != null) {
			return false;
		}
		if (date_scanned != null ? !date_scanned.equals(checkins.date_scanned)
				: checkins.date_scanned != null) {
			return false;
		}
		if (email != null ? !email.equals(checkins.email)
				: checkins.email != null) {
			return false;
		}
		return !(updatedAt != null ? !updatedAt.equals(checkins.updatedAt)
				: checkins.updatedAt != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result
				+ (date_scanned != null ? date_scanned.hashCode() : 0);
		result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
		result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Checkins{" + "id=" + id + ", email='" + email + '\''
				+ ", date_scanned=" + date_scanned + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + '}';
	}


}