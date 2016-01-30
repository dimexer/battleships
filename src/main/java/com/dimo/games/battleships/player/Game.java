package com.dimo.games.battleships.player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.dimo.games.battleships.model.GameConfig;
import com.dimo.games.battleships.model.HitType;
import com.dimo.games.battleships.service.BattlefieldService;

public class Game {
	private static final Pattern inputPattern = Pattern.compile("show|[a-z][0-9]{1,2}");
	private BattlefieldService service;
	private char[][] userBoard;
	private int shotsFired;
	private int enemyShips;

	public Game(BattlefieldService service) {
		this.service = service;
		this.shotsFired = 0;
	}

	public void play() {
		GameConfig config = this.service.initNewGame();
		System.out.println("Starting game with the following config:");
		System.out.println(config);
		System.out.println("Initializing user board...");
		this.userBoard = new char[config.getFieldWidth()][config.getFieldHeight()];
		this.enemyShips = config.getNumberOfBattleships() + config.getNumberOfDestroyers();

		fillBoard(this.userBoard, '.');

		Scanner sc = null;
		try {
			sc = new Scanner(System.in); // Scanner is not the best option, but
											// the code is cleaner
			while (true) {
				System.out.print("Enter Coordinates (row, col), e.g. A5: ");
				String input = sc.nextLine().trim().toLowerCase();

				if (!inputPattern.matcher(input).matches()) {
					System.out.println("Invalid input, please try again!");
					continue;
				}

				if (input.equals("show")) {
					List<int[]> shipsCoords = this.service.getShipsCoordinates();
					char[][] hint = new char[this.userBoard.length][this.userBoard[0].length];
					this.fillBoard(hint, ' ');
					for (int[] coords : shipsCoords) {
						hint[coords[0]][coords[1]] = 'X';
					}
					printBoard(hint);
					continue;
				}

				int xCoord = input.charAt(0) - 'a';
				int yCoord = Integer.parseInt(input.substring(1)) - 1;

				HitType response = this.service.shootOnPosition(xCoord, yCoord);
				this.handleHitResponse(response, xCoord, yCoord);
				printBoard(this.userBoard);
				if (this.enemyShips == 0) {
					System.out.println(String.format("Well done! You completed the game in %d shots", this.shotsFired));
					break;
				}
			}
		} finally { // ensure that we will close Scanner
			sc.close();
		}
	}

	private void handleHitResponse(HitType ht, int xCoord, int yCoord) {
		this.shotsFired++;
		
		switch (ht) {
		case MISS: {
			System.out.println("Miss");
			this.userBoard[xCoord][yCoord] = '-';
			break;
		}
		case HIT: {
			System.out.println("Hit");
			this.userBoard[xCoord][yCoord] = 'X';
			break;
		}
		case SUNK: {
			System.out.println("Sunk");
			this.userBoard[xCoord][yCoord] = 'X';
			this.enemyShips--;
			break;
		}
		}
	}

	private void printBoard(char[][] board) {
		char startRow = 'A';
		System.out.print("  ");
		for (int i = 1; i <= board.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (j == 0) {
					System.out.print(startRow + " ");
					startRow++;
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void fillBoard(char[][] board, char fill) {
		for (int i = 0; i < board.length; i++) {
			Arrays.fill(board[i], fill);
		}
	}
}
