package com.ds.microservices.sport.tabletennis.report.util;

import java.util.Comparator;

import com.ds.microservices.sport.tabletennis.report.dto.PlayerDto;

public class PlayerDtoComparator implements Comparator<PlayerDto> {

	@Override
	public int compare(PlayerDto player1, PlayerDto player2) {
		if(player1.getGroupPoints() > player2.getGroupPoints()) return -1;
		else if (player1.getGroupPoints() < player2.getGroupPoints()) return 1;
		else {
			int pointsDiffPlayer1 = player1.getGamesPointsFor() - player1.getGamesPointsAgainst();
			int pointsDiffPlayer2 = player2.getGamesPointsFor() - player2.getGamesPointsAgainst();
			
			if(pointsDiffPlayer1 > pointsDiffPlayer2) return -1;
			else if (pointsDiffPlayer1 < pointsDiffPlayer2) return 1;
			else return 0;
		}
			
		
//		return 0;
	}

}
