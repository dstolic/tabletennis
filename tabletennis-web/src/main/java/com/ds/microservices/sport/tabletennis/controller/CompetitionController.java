package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
//import org.springframework.web.bind.annotation.;
import com.ds.microservices.sport.tabletennis.service.CompetitionService;

@Controller
public class CompetitionController {
	
	@Autowired
	protected CompetitionService competitionService;
	
	protected Logger logger = Logger.getLogger(CompetitionController.class.getName());
	
	public CompetitionController(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	// List - radi	
	@RequestMapping(value = "/competition")
	public String all(Model model) {
		logger.info("web-service all() ");

		List<CompetitionDto> competitions = competitionService.all();

		logger.info("web-service all() found: " + competitions);

		if (competitions != null)
			model.addAttribute("competitions", competitions);

		logger.info("web-service all() END ");
		return "competitions";
	}
	

//	in progress
	@RequestMapping("/competition/{id}")
	public String byId(Model model, @PathVariable("id") Long id) {

		logger.info("web-service byId() invoked: " + id);

		CompetitionDto competitionDto = competitionService.findById(id);
		logger.info("web-service byId() found: " + competitionDto);
//		if(leagueDTO.getLeaguePlayersDTO() != null) {
//			logger.info("web-service league players found (" + leagueDTO.getLeaguePlayersDTO().size() + ")");
//			for (LeaguePlayerDTO leaguePlayerDTO : leagueDTO.getLeaguePlayersDTO()) {
//				logger.info(" Player " + leaguePlayerDTO.getPlayerDTO());
//			}
//		} else {
//			logger.info("web-service league players not found ");
//		}
		
		
		
		model.addAttribute("competitionDto", competitionDto);
		return "competition";
	}
	

	@RequestMapping(value = "/add")
	public String searchForm(Model model) {
		model.addAttribute("leagueDTO", new CompetitionDto());
		model.addAttribute("success", "");
		return "leagueForm";
	}

	@RequestMapping(value = "/competition/doadd")
	public String doAdd(Model model, CompetitionDto leagueDTO, BindingResult result) {
		logger.info("web-service doAdd() league invoked: " + leagueDTO);
		String success = "Success Test" ;

//		leagueDTO.validate(result);

		if (result.hasErrors()) {
			logger.info("web-service result.hasErrors() ");
			return "leagueForm";
		}
		
		CompetitionDto leagueDTO_response = competitionService.save(leagueDTO);
//		String playerId = criteria.getAccountNumber(); 
//		if (StringUtils.hasText(playerId)) {
//			return idSearch(model, playerId);
//		} else {
//			
//			String searchText = criteria.getSearchText();
//			logger.info("web-service searchText " + searchText);
//			return emailSearch(model, searchText);
//		}
		model.addAttribute("leagueDTO", leagueDTO_response);
		model.addAttribute("success", success);
		return "leagueForm";
	}

	@RequestMapping(value = "/competition/delete")
	public String delete(Model model, CompetitionDto leagueDTO) {
		logger.info("web-service delete() : " + leagueDTO.getId());

		competitionService.delete(leagueDTO);

		all(model);
		model.addAttribute("leagueDTO", leagueDTO);
		logger.info("web-service delete() invoked: " + leagueDTO.getId());

		return "leagues";
	}

}
