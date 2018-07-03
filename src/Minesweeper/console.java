package Minesweeper;

import java.util.Scanner;

public class console {
	static int maxX;
	static int maxY;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("How big: ");
		int size = sc.nextInt();
		
		String[][] map = new String[size][size];
		String[][] newMap = new String[size][size];
		maxX = size-1;
		maxY = size-1;
		for (int row = 0; row < size; row++) {
			int bomb = (int) (Math.random() * 100) % (size+1);
			if (bomb < size)
				map[row][bomb] = "*";
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newMap[i][j] = search(map, i, j) + "";
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(newMap[i][j] + " ");
			}
			System.out.println();
		}

	}

	static int search(String[][] map, int valueX, int valueY) {
		int countNum = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int X = valueX + i;
				int Y = valueY + j;

				if (X < 0 || X > maxX || Y < 0 || Y > maxY || (X == valueX && Y == valueY))
					continue;
				else {
					if (map[X][Y] != "*")
						countNum++;
				}
			}
		}
		return countNum;
	}
}
