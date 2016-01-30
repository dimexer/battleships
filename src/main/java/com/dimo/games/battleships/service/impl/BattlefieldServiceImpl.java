package com.dimo.games.battleships.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dimo.games.battleships.model.Battlefield;
import com.dimo.games.battleships.model.GameConfig;
import com.dimo.games.battleships.model.HitType;
import com.dimo.games.battleships.model.Ship;
import com.dimo.games.battleships.model.ShipType;
import com.dimo.games.battleships.service.BattlefieldService;

@Service
public class BattlefieldServiceImpl implements BattlefieldService {
	private Battlefield battlefield;

	@Value("${game.battlefield.width}")
	private int fieldWidth;
	@Value("${game.battlefield.length}")
	private int fieldLength;
	@Value("${game.number.of.battleships}")
	private int numberOfBattleships;
	@Value("${game.number.of.destroyers}")
	private int numberOfDestroyers;

	public GameConfig initNewGame() {
		this.battlefield = new Battlefield(this.fieldWidth, this.fieldLength);

		// could be filled with passing a strategy to the method
		for (int i = 0; i < this.numberOfBattleships; i++) {
			this.battlefield.placeShip(new Ship(ShipType.BATTLESHIP));
		}
		for (int i = 0; i < this.numberOfDestroyers; i++) {
			this.battlefield.placeShip(new Ship(ShipType.DESTROYER));
		}

		return new GameConfig(this.fieldWidth, this.fieldLength, this.numberOfBattleships, this.numberOfDestroyers);
	}

	public HitType shootOnPosition(int x, int y) {
		return this.battlefield.getShotAt(x, y);
	}

	public List<int[]> getShipsCoordinates() {
		return this.battlefield.getCoords();
	}
}
