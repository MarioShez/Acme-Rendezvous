///*
// * AdministratorController.java
// * 
// * Copyright (C) 2017 Universidad de Sevilla
// * 
// * The use of this project is hereby constrained to the conditions of the
// * TDG Licence, a copy of which you may download from
// * http://www.tdg-seville.info/License.html
// */
//
//package controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import services.AdminService;
//
//@Controller
//@RequestMapping("/administrator")
//public class AdministratorController extends AbstractController {
//
//	@Autowired
//	private AdminService administratorService;
//
//	// Constructors -----------------------------------------------------------
//
//	public AdministratorController() {
//		super();
//	}
//
//	// Action-1 ---------------------------------------------------------------
//
//	@RequestMapping("/action-1")
//	public ModelAndView action1() {
//		ModelAndView result;
//
//		result = new ModelAndView("administrator/action-1");
//
//		return result;
//	}
//
//	// Action-2 ---------------------------------------------------------------
//
//	@RequestMapping("/action-2")
//	public ModelAndView action2() {
//		ModelAndView result;
//
//		result = new ModelAndView("administrator/action-2");
//
//		return result;
//	}
//
//	// information
//	// --------------------------------------------------------------
//
//	@RequestMapping(value = "/information", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView result;
//		result = new ModelAndView("administrator/information");
//
//		Object avgSqtrUser[];
//		Double ratioUserRendezvous;
//		Object avgSqtrUserPerRendezvous[];
//		Object avgSqtrUser2[];
//		Object topRendezvous[];
//		Object avgSqtrAnnouncementPerRendezvous[];
//		Object rendezvousNumberAnnouncements[];
//		Object rendezvousLinked[];
//		Object avgSqtrQuestionsPerRendezvous[];
//		Object avgSqtrAnswersPerRendezvous[];
//		Object avgSqtRrepliesPerComment[];
//
//		avgSqtrUser = this.administratorService.avgSqtrUser();
//		ratioUserRendezvous = this.administratorService.ratioUserRendezvous();
//		avgSqtrUserPerRendezvous = this.administratorService.avgSqtrUserPerRendezvous();
//		avgSqtrUser2 = this.administratorService.avgSqtrUser2();
//		topRendezvous = this.administratorService.topRendezvous();
//		avgSqtrAnnouncementPerRendezvous = this.administratorService.avgSqtrAnnouncementPerRendezvous();
//		rendezvousNumberAnnouncements = this.administratorService.rendezvousNumberAnnouncements();
//		rendezvousLinked = this.administratorService.rendezvousLinked();
//		avgSqtrQuestionsPerRendezvous = this.administratorService.avgSqtrQuestionsPerRendezvous();
//		avgSqtrAnswersPerRendezvous = this.administratorService.avgSqtrAnswersPerRendezvous();
//		avgSqtRrepliesPerComment = this.administratorService.avgSqtRrepliesPerComment();
//
//		result.addObject("rendezvousPerUser", avgSqtrUser);
//		result.addObject("ratioUserRendezvous", ratioUserRendezvous);
//		result.addObject("userPerRendezvous", avgSqtrUserPerRendezvous);
//		result.addObject("rsvpdPerUser", avgSqtrUser2);
//		result.addObject("topRendezvous", topRendezvous);
//		result.addObject("announcementsPerRendezvous", avgSqtrAnnouncementPerRendezvous);
//		result.addObject("rendezvousNumberAnnouncements", rendezvousNumberAnnouncements);
//		result.addObject("rendezvousLinked", rendezvousLinked);
//		result.addObject("questionsPerRendezvous", avgSqtrQuestionsPerRendezvous);
//		result.addObject("answersPerRendezvous", avgSqtrAnswersPerRendezvous);
//		result.addObject("repliesPerComment", avgSqtRrepliesPerComment);
//
//		return result;
//	}
//
//}
