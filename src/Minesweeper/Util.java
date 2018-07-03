package Minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

public class Util {

	final static void drawLine(Graphics g, int size, int width, String[][] map) { // 畫格子
		int lineLength = size * width + 20; // 每條線長
		g.setColor(Color.BLACK);

		for (int i = 40; i <= lineLength + 20; i += width) { // 畫行
			g.drawLine(i, 20, i, lineLength);
			g.drawLine(i + 1, 20, i + 1, lineLength);
			g.drawLine(i + 2, 20, i + 2, lineLength);
		}
		for (int i = 20; i <= lineLength; i += width) { // 畫列
			g.drawLine(40, i, lineLength + 22, i);
			g.drawLine(40, i + 1, lineLength + 22, i + 1);
			g.drawLine(40, i + 2, lineLength + 22, i + 2);
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < size; i++) { //畫數字上去
			for (int j = 0; j < size; j++) {
				g.setFont(new Font("", Font.PLAIN, width));
				
				//稍微調整位置
				if (size <= 5) { 
					g.drawString(map[i][j], j * width + (40 + width / 4), (i + 1) * width + 5);
				} else if (size <= 10) {
					g.drawString(map[i][j], j * width + (40 + width / 4), (i + 1) * width + (width / 4));
				} else {
					g.drawString(map[i][j], j * width + (40 + width / 4), (i + 1) * width + 10 + (width / 4));
				}
			}
		}
	}
	
	final static int search(String[][] map, int valueX, int valueY, int max) {
		int countNum = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int X = valueX + i;
				int Y = valueY + j;

				if (X < 0 || X > max || Y < 0 || Y > max || (X == valueX && Y == valueY))
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
