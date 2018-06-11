package com.ds.microservices.sport.tabletennis.util;

import java.util.Comparator;

import com.ds.microservices.sport.tabletennis.model.Player;

public class PlayerComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if(o1.getPoints() > o2.getPoints()) return -1;
		else if (o1.getPoints() < o2.getPoints()) return 1;
		
		return 0;
	}

}
