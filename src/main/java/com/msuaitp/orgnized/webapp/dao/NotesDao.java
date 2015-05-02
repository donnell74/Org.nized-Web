package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.Note;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Created by ryan_000 on 4/28/2015.
 */
@Component
public class NotesDao {
	private final static Logger LOG = Logger.getLogger(NotesDao.class.getName());

	RestTemplate restTemplate = new RestTemplate();

	public Note findOneNote (int id) {
		String url = "http://reorconsultants.com:1337/notes/find/" + id;
		System.out.println(url);
		ResponseEntity<Note> response = restTemplate.getForEntity(url, Note.class);
		// restTemplate.getForObject(url, Person.class);

		return response.getBody();
	}

	public Note[] getAllNotes () {
		String url = "http://reorconsultants.com:1337/notes";
		System.out.println(url);
		ResponseEntity<Note[]> response = restTemplate.getForEntity(url, Note[].class);
		// restTemplate.getForObject(url, Person.class);

		return response.getBody();
	}

	public void saveNote (Note note) {
		String url = "http://reorconsultants.com:1337/notes/create";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("person_email", note.getPerson_email().getEmail());
		LOG.info("Email: " + note.getPerson_email().getEmail());
		map.add("public_to_person", String.valueOf(note.getPublicToPerson()));
		LOG.info("Public: " + String.valueOf(note.getPublicToPerson()));
		map.add("title", note.getTitle());
		LOG.info("Title: " + note.getTitle());
		map.add("text", note.getText());
		LOG.info("Text: " + note.getText());
		System.out.println(url);
		restTemplate.postForEntity(url, map, Void.class);
	}

	public void deleteNote (int id) {
		String url = "http://reorconsultants.com:1337/notes/destroy/" + id;
		restTemplate.getForEntity(url, Void.class);
	}

	public void editNote (Note note) {
		String url = "http://reorconsultants.com:1337/notes/update";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id", String.valueOf(note.getId()));
		map.add("person_email", note.getPerson_email().getEmail());
		map.add("public_to_person", String.valueOf(note.getPublicToPerson()));
		map.add("title", note.getTitle());
		map.add("text", note.getText());
		System.out.println(url);
		restTemplate.postForEntity(url, map, Void.class);
	}
}
