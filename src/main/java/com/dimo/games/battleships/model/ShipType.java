package com.dimo.games.battleships.model;

public enum ShipType { // if some more attributes/behavior is required - make them classes
	BATTLESHIP(5),
	DESTROYER(4);
	
	private int size;
	
	private ShipType(int size){
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int sizeInBlocks) {
		this.size = sizeInBlocks;
	}
	
	
}
