package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.NotesDao;
import com.msuaitp.orgnized.webapp.dao.PersonDao;
import com.msuaitp.orgnized.webapp.domain.Note;
import com.msuaitp.orgnized.webapp.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ryan_000 on 4/28/2015.
 */
@Controller
@RequestMapping("notes")
public class NotesController {
	private final static Logger LOG = Logger.getLogger(NotesController.class.getName());

	NotesDao notesDao = new NotesDao();
	PersonDao personDao = new PersonDao();

	@RequestMapping("create")
	public String create (Model model) {
		model.addAttribute("note", new Note());
		return "note-create";
	}

	@RequestMapping("delete")
	public String delete (Model model, @RequestParam int id) {
		notesDao.deleteNote(id);
		List<Note> theNotes = Arrays.asList(notesDao.getAllNotes());
		model.addAttribute("theNotes", theNotes);
		return "redirect:/notes";
	}

	@RequestMapping("edit")
	public String edit (Model model, int id) {
		model.addAttribute("note", notesDao.findOneNote(id));
		return "note-edit";
	}

	@RequestMapping("create-note")
	public String createFormSubmission (Model model, @Valid @ModelAttribute Note note, BindingResult result) {
		Person thePerson = new Person();
		try {
			thePerson = personDao.getPersonByEmail(note.getPerson_email().getEmail());
			LOG.info(thePerson.getFirst_name() + " " + thePerson.getLast_name());
		} catch (NullPointerException e) {
			result.rejectValue("person_email", "valid");
			return "note-create";
		}

		if (!result.hasErrors()) {
			notesDao.saveNote(note);
			List<Note> theNotes = Arrays.asList(notesDao.getAllNotes());
			model.addAttribute("theNotes", theNotes);
			return "redirect:/notes";
		}
		return "note-create";
	}

	@RequestMapping("note-edit")
	public String editFormSubmission (Model model, @Valid @ModelAttribute Note note, BindingResult result) {
		Person thePerson = new Person();
		try {
			thePerson = personDao.getPersonByEmail(note.getPerson_email().getEmail());
			LOG.info(thePerson.getFirst_name() + " " + thePerson.getLast_name());
		} catch (NullPointerException e) {
			result.rejectValue("person_email", "valid");
			return "note-edit";
		}

		if (!result.hasErrors()) {
			notesDao.editNote(note);
			List<Note> theNotes = Arrays.asList(notesDao.getAllNotes());
			model.addAttribute("theNotes", theNotes);
			return "redirect:/notes";
		}
		return "note-edit";
	}
}
