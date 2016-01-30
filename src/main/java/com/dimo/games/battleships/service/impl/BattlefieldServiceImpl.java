package com.dimo.games.battleships.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dimo.games.battleships.model.Battlefield;
import com.dimo.games.battleships.model.GameConfig;
import com.dimo.games.battleships.model.HitType;
import com.dimo.games.battleships.model.Ship;
import com.dimo.games.battleships.model.ShipType;
import com.dimo.games.battleships.service.BattlefieldService;

@Service
public class BattlefieldServiceImpl implements BattlefieldService {
	//these values can be loaded from .properties file
	private static final int FIELD_WIDTH = 10;
	private static final int FIELD_HEIGHT = 10;
	private static final int NUMBER_OF_BATTLESHIPS = 1;
	private static final int NUMBER_OF_DESTROYERS = 2;
	
	private Battlefield battlefield;
	
	public GameConfig initNewGame() {
		this.battlefield = new Battlefield(FIELD_WIDTH, FIELD_HEIGHT);
		
		// could be filled with passing a strategy to the method
		for(int i=0;i<NUMBER_OF_BATTLESHIPS;i++){
			this.battlefield.placeShip(new Ship(ShipType.BATTLESHIP));
		}
		for(int i=0;i<NUMBER_OF_DESTROYERS;i++){
			this.battlefield.placeShip(new Ship(ShipType.DESTROYER));
		}
		
		return new GameConfig(FIELD_WIDTH,FIELD_HEIGHT,NUMBER_OF_BATTLESHIPS,NUMBER_OF_DESTROYERS);
	}
	
	public HitType shootOnPosition(int x, int y) {
		return this.battlefield.getShotAt(x, y);
	}

	public List<int[]> getShipsCoordinates() {
		return this.battlefield.getCoords();
	}
}
