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

	public List<int[]> getCoords() {
		List<int[]> coords = new ArrayList<int[]>();
		for (BattlefieldCell c : this.positionsToShips.keySet()) {
			coords.add(new int[] { c.getX(), c.getY() });
		}
		return coords;
	}

	private boolean placeShipOnPosition(int x, int y, Ship s, boolean horizontal) {
		int length = s.getShipType().getSize();

		if (!horizontal) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		for (int i = x; i < x + length; i++) {
			if (i > (horizontal? this.width : this.height) - 1 || positionsToShips
					.containsKey(horizontal ? new BattlefieldCell(i, y) : new BattlefieldCell(y, i))) {
				for (int j = i - 1; j >= x; j--) {
					// can't add, revert
					positionsToShips.remove(horizontal ? new BattlefieldCell(j, y) : new BattlefieldCell(y, j));
				}
				return false;
			} else {
				positionsToShips.put(horizontal ? new BattlefieldCell(i, y) : new BattlefieldCell(y, i), s);
			}
		}
		return true;
	}
}
