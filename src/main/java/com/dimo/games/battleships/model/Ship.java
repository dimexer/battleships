package com.dimo.games.battleships.model;

public class Ship {
	private ShipType shipType;
	private int size;
	private int life;

	public Ship(ShipType shipType) {
		super();
		this.shipType = shipType;
		this.size = shipType.getSize();
		this.life = shipType.getSize();
	}

	public void takeAHit(){
		this.life--;
	}
	
	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}
