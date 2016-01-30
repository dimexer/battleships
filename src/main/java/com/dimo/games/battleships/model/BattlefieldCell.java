package com.dimo.games.battleships.model;

public class BattlefieldCell {
	private int x;
	private int y;

	public BattlefieldCell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		// cantor pairing function (tested for collisions)
		return ((x + y) * (x + y + 1)) * 2 + y;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof BattlefieldCell) {
			BattlefieldCell other = (BattlefieldCell) o;
			if (other.getX() == this.x && other.getY() == this.y) {
				return true;
			}
			return false;
		}
		return false;
	}
}
