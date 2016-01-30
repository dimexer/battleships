package com.dimo.games.battleships.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class BattlefieldTest {

	@Test
	public void testProperNumberOfPointsAreTakenByShips() {
		Battlefield bf = new Battlefield(10, 10);
		assertEquals(0, bf.getCoords().size());
		bf.placeShip(new Ship(ShipType.BATTLESHIP)); // 5
		bf.placeShip(new Ship(ShipType.DESTROYER)); // 4
		bf.placeShip(new Ship(ShipType.DESTROYER)); // 4
		assertEquals(13, bf.getCoords().size());
	}

	@Test
	public void testShotResultAtPoint() {
		Battlefield bf = new Battlefield(10, 10);
		bf.placeShip(new Ship(ShipType.BATTLESHIP)); // 5
		bf.placeShip(new Ship(ShipType.DESTROYER)); // 4
		bf.placeShip(new Ship(ShipType.DESTROYER)); // 4

		List<int[]> coords = bf.getCoords();

		for (int i = 0; i < 100; i++) {
			int randX = (int) (Math.random() * 10);
			int randY = (int) (Math.random() * 10);

			HitType resp = bf.getShotAt(randX, randY);
			if (resp.equals(HitType.HIT) || resp.equals(HitType.SUNK)) {
				boolean found = false;
				for (int[] c : coords) {
					if (c[0] == randX && c[1] == randY) {
						found = true;
						break;
					}
				}
				assertTrue(found);
			} else {
				boolean found = false;
				for (int[] c : coords) {
					if (c[0] == randX && c[1] == randY) {
						found = true;
						break;
					}
				}
				assertFalse(found);
			}
		}
	}
}
