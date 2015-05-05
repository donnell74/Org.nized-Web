package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.PossibleAnswerDao;
import com.msuaitp.orgnized.webapp.dao.QuestionDao;
import com.msuaitp.orgnized.webapp.dao.SurveyDao;
import com.msuaitp.orgnized.webapp.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("poss-answers")
public class PossibleAnswerController {
	private final static Logger LOG = Logger.getLogger(PossibleAnswerController.class.getName());

	QuestionDao questionDao = new QuestionDao();
	SurveyDao surveyDao = new SurveyDao();
	PossibleAnswerDao possDao = new PossibleAnswerDao();

	@RequestMapping("new")
	public String newPossAnswers (@RequestParam int questionId, @RequestParam String questionType, Model model, final
	RedirectAttributes
			redirectAttributes) {

		QuestionUgh question = questionDao.findOneUgh(String.valueOf(questionId));

		if (questionType.equalsIgnoreCase("TEXT")) {
			redirectAttributes.addFlashAttribute("message", "Cannot add answers for text questions");

			Survey survey = surveyDao.getOneSurvey(String.valueOf(question.getSurvey_id().getId()));
			List<Question> questions = survey.getQuestions();
			for (Question q : questions) {
				q.setPossible_answers(possDao.getPossAnswers(q.getId()));
				LOG.info("Iterated thru PA Loop");
			}
			model.addAttribute("survey", survey);
			return "redirect:/surveys/edit?id=" + survey.getId();
		}

		for (int i = 0; i < 10; i++) {
			model.addAttribute("poss" + i, new PossibleAnswerUgh());
		}
		model.addAttribute("question", question);


		return "question-add-answers";
	}

	@RequestMapping("add-answers")
	public String addAnswers (@RequestParam String[] text, @RequestParam int question_id, Model model) {
		for (int i = 0; i < text.length; i++) {
			if ((!text[i].equalsIgnoreCase("")) || (text[i] != null)) {
				possDao.saveAnswer(question_id, text[i]);
			}

		}
		QuestionUgh question = questionDao.findOneUgh(String.valueOf(question_id));

		model.addAttribute("survey", surveyDao.getOneSurveyWithExtras(String.valueOf(question.getSurvey_id().getId())));
		return "redirect:/surveys/edit?id=" + question.getSurvey_id().getId();
	}

	@RequestMapping("edit")
	public String editAnswer (@RequestParam int answer_id, @RequestParam String question_id, Model model) {
		model.addAttribute("question", questionDao.findOne(question_id));
		model.addAttribute("answer", possDao.getOne(answer_id));
		return "question-edit-answer";
	}

	@RequestMapping("delete")
	public String deleteAnswer (@RequestParam int answer_id, Model model) {
		PossibleAnswer pa = possDao.getOne(answer_id);
		possDao.delete(answer_id);
		Question question = questionDao.findOne(String.valueOf(pa.getQuestion_id().getId()));
		Survey survey = surveyDao.getOneSurvey(String.valueOf(question.getSurvey_id()));
		List<Question> questions = survey.getQuestions();
		for (Question q : questions) {
			q.setPossible_answers(possDao.getPossAnswers(q.getId()));
			LOG.info("Iterated thru PA Loop");
		}
		model.addAttribute("survey", survey);
		return "survey-add-questions";
	}

	@RequestMapping("edit-save")
	public String saveEditedAnswer (@RequestParam String text, @RequestParam int question_id, @RequestParam int
			answer_id, Model model) {

		if ((!text.equalsIgnoreCase("")) || (text != null)) {
			possDao.saveAnswer(question_id, answer_id, text);
		}

		QuestionUgh question = questionDao.findOneUgh(String.valueOf(question_id));

		model.addAttribute("survey", surveyDao.getOneSurveyWithExtras(String.valueOf(question.getSurvey_id().getId())));
		return "redirect:/surveys/edit?id=" + question.getSurvey_id().getId();
	}
}
