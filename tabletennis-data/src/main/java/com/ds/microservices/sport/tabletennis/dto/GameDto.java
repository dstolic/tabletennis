package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@JsonRootName("Game")
public class GameDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Timestamp created;

	private String description;

	private byte finished;

	@Temporal(TemporalType.DATE)
	@Column(name="played_data")
	private Date playedData;

	@Column(name="result_away")
	private short resultAway;

	@Column(name="result_home")
	private short resultHome;

	private Timestamp updated;

	//bi-directional many-to-one association to League
	@ManyToOne
	private CompetitionDto league;

	//bi-directional many-to-one association to LeUser
	@ManyToOne
	@JoinColumn(name="player_away_id")
	private PlayerDto leUser1;

	//bi-directional many-to-one association to LeUser
	@ManyToOne
	@JoinColumn(name="player_home_id")
	private PlayerDto leUser2;

	//bi-directional many-to-one association to LeagueGame
	@OneToMany(mappedBy="game")
	private List<LeagueGameDto> leagueGames;

	public GameDto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getFinished() {
		return this.finished;
	}

	public void setFinished(byte finished) {
		this.finished = finished;
	}

	public Date getPlayedData() {
		return this.playedData;
	}

	public void setPlayedData(Date playedData) {
		this.playedData = playedData;
	}

	public short getResultAway() {
		return this.resultAway;
	}

	public void setResultAway(short resultAway) {
		this.resultAway = resultAway;
	}

	public short getResultHome() {
		return this.resultHome;
	}

	public void setResultHome(short resultHome) {
		this.resultHome = resultHome;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public CompetitionDto getLeague() {
		return this.league;
	}

	public void setLeague(CompetitionDto league) {
		this.league = league;
	}

	public PlayerDto getLeUser1() {
		return this.leUser1;
	}

	public void setLeUser1(PlayerDto leUser1) {
		this.leUser1 = leUser1;
	}

	public PlayerDto getLeUser2() {
		return this.leUser2;
	}

	public void setLeUser2(PlayerDto leUser2) {
		this.leUser2 = leUser2;
	}

	public List<LeagueGameDto> getLeagueGames() {
		return this.leagueGames;
	}

	public void setLeagueGames(List<LeagueGameDto> leagueGames) {
		this.leagueGames = leagueGames;
	}

	public LeagueGameDto addLeagueGame(LeagueGameDto leagueGame) {
		getLeagueGames().add(leagueGame);
		leagueGame.setGame(this);

		return leagueGame;
	}

	public LeagueGameDto removeLeagueGame(LeagueGameDto leagueGame) {
		getLeagueGames().remove(leagueGame);
		leagueGame.setGame(null);

		return leagueGame;
	}

}