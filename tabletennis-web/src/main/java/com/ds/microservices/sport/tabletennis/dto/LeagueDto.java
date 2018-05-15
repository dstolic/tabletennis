package com.ds.microservices.sport.tabletennis.dto;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

//import com.ds.microservices.sport.tabletennis.data.temp.League;
//import com.ds.microservices.sport.tabletennis.data.temp.LeaguePlayer;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("League")
public class LeagueDto {

	private Long id;
	private String description;
	private String name;
	private String season;

//	private List<LeaguePlayerDTO> leaguePlayersDTO;
	private List<PlayerDto> playersDTO;

	public LeagueDto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public boolean isValid() {
		if (StringUtils.hasText(name)) {
			return !(StringUtils.hasText(name));
		} else if (!StringUtils.hasText(season)) {
			return (StringUtils.hasText(season));
		} else  {
			return (StringUtils.hasText(season));
		}
	}

	public boolean validate(Errors errors) {
		if (!StringUtils.hasText(name)) {
			errors.rejectValue("name", "nonEmpty", "Name is mandatory field.");
		} else if (!StringUtils.hasText(season)) {
			errors.rejectValue("season", "nonEmpty", "Season is mandatory field.");
		} 
		return errors.hasErrors();
	}

//	public List<LeaguePlayerDTO> getLeaguePlayersDTO() {
//		return leaguePlayersDTO;
//	}
//
//	public void setLeaguePlayers(List<LeaguePlayerDTO> leaguePlayersDTO) {
//		this.leaguePlayersDTO = leaguePlayersDTO;
//	}
//




	@Override
	public String toString() {
		return id + ": " + name + " " + season + " [" + description + "]";
	}

//	public void setLeaguePlayersDTO(List<LeaguePlayerDTO> leaguePlayersDTO) {
//		this.leaguePlayersDTO = leaguePlayersDTO;
//	}

	public List<PlayerDto> getPlayersDTO() {
		return playersDTO;
	}

	public void setPlayersDTO(List<PlayerDto> playersDTO) {
		this.playersDTO = playersDTO;
	}


}