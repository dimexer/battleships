package com.dimo.games.battleships.service;

import java.util.List;

import com.dimo.games.battleships.model.GameConfig;
import com.dimo.games.battleships.model.HitType;

public interface BattlefieldService {
	GameConfig initNewGame();
	
	HitType shootOnPosition(int x, int y);

	List<int[]> getShipsCoordinates();
}
