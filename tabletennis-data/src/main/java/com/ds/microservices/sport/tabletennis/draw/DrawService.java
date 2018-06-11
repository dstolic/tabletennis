package com.ds.microservices.sport.tabletennis.draw;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;


@Service
public class DrawService {
	
	public final static String FORMAT = "CUP";
	public final static int NUMBER_OF_PLAYERS = 32;
	public final static int NUMBER_OF_SEEDS = 8;

	protected Logger logger = Logger.getLogger(DrawService.class.getName());

	public void doDraw() {
		
	}

	
	
//	Testing
	public static void main(String[] args) {
		
		DrawService ds = new DrawService();
		ds.logger.info("TEST NUMBER_OF_SEEDS (" + DrawService.NUMBER_OF_SEEDS + ")");
		
		ds.doDraw();
		
	}
}
