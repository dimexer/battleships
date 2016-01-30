package com.dimo.games.battleships.model;

public class GameConfig {
	private int fieldWidth;
	private int fieldHeight;
	private int numberOfBattleships;
	private int numberOfDestroyers;

	public GameConfig(int fieldWidth, int fieldHeight, int numberOfBattleships, int numberOfDestroyers) {
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.numberOfBattleships = numberOfBattleships;
		this.numberOfDestroyers = numberOfDestroyers;
	}

	public int getFieldWidth() {
		return fieldWidth;
	}

	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	public int getFieldHeight() {
		return fieldHeight;
	}

	public void setFieldHeight(int fieldHeight) {
		this.fieldHeight = fieldHeight;
	}

	public int getNumberOfBattleships() {
		return numberOfBattleships;
	}

	public void setNumberOfBattleships(int numberOfBattleships) {
		this.numberOfBattleships = numberOfBattleships;
	}

	public int getNumberOfDestroyers() {
		return numberOfDestroyers;
	}

	public void setNumberOfDestroyers(int numberOfDestroyers) {
		this.numberOfDestroyers = numberOfDestroyers;
	}

	@Override
	public String toString() {
		return String.format("[WIDTH: %s; HEIGHT: %s; BATTLESHIPS: %d; DESTROYERS: %d]", this.fieldWidth,
				this.fieldHeight, this.numberOfBattleships, this.numberOfDestroyers);
	}
}
