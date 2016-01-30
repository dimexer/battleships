package com.dimo.games.battleships.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Battlefield {
	private Map<BattlefieldCell, Ship> positionsToShips;
	private int width;
	private int height;

	public Battlefield(int width, int height) {
		this.positionsToShips = new HashMap<BattlefieldCell, Ship>();
		this.width = width;
		this.height = height;
	}

	public void placeShip(Ship ship) {
		boolean placed = false;
		while (!placed) {
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);

			if (positionsToShips.containsKey(new BattlefieldCell(x, y))) {
				continue;
			}

			boolean horizontal = Math.random() < 0.5;
			placed = this.placeShipOnPosition(x, y, ship, horizontal);
		}
	}

	public HitType getShotAt(int x, int y) {
		Ship target = this.positionsToShips.get(new BattlefieldCell(x, y));
		if (target != null) {
			target.takeAHit();
			if (target.getLife() == 0) {
				return HitType.SUNK;
			}
			return HitType.HIT;
		}
		return HitType.MISS;
	}

	public List<int[]> getCoords(){
		List<int[]> coords = new ArrayList<int[]>();
		for(BattlefieldCell  c : this.positionsToShips.keySet()){
			coords.add(new int[]{c.getX(), c.getY()});
		}
		return coords;
	}
	
	private boolean placeShipOnPosition(int x, int y, Ship s, boolean horizontal) {
		int length = s.getShipType().getSize();
		if (horizontal) {
			for (int i = x; i < x + length; i++) {
				if (i > this.width - 1 || positionsToShips.containsKey(new BattlefieldCell(i, y))) {
					for (int j = i - 1; j >= x; j--) {
						positionsToShips.remove(new BattlefieldCell(j, y));
					}
					return false;
				} else {
					positionsToShips.put(new BattlefieldCell(i, y), s);
				}
			}
		} else {
			for (int i = y; i < y + length; i++) {
				if (i > this.height - 1 || positionsToShips.containsKey(new BattlefieldCell(x, i))) {
					for (int j = i - 1; j >= y; j--) {
						positionsToShips.remove(new BattlefieldCell(x, j));
					}
					return false;
				} else {
					positionsToShips.put(new BattlefieldCell(x, i), s);
				}
			}
		}
		return true;
	}
}
