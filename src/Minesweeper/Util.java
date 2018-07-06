package Minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

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

		for (int i = 0; i < size; i++) { // 畫數字上去
			for (int j = 0; j < size; j++) {
				g.setFont(new Font("", Font.PLAIN, width));

				// 稍微調整位置
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

	final static int search(String[][] map, int valueX, int valueY, int max) { // 找周圍安全的位置有幾個
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

	final static boolean checkPixelBound(int x, int y, int size) { // 檢查滑鼠點選位置
		if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
			return false;
		}
		return true;
	}

	final static boolean drawAnswer(Graphics g, int x, int y, int size, String[][] map) { // draw answer
		int width = 500 / size;

		if (checkPixelBound(x, y, size) && map[y][x] == null) {
			g.setColor(new Color(0, 0, 255, 100)); // 畫RGBA
			g.fillRect(x * width + 43, y * width + 23, width - 3, width - 3);
			return true;
		} else if (checkPixelBound(x, y, size) && map[y][x].equals("*")) {
			drawAllAns(g, size, map);
			JOptionPane.showMessageDialog(null, "Nice try", "QQ", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	final static void drawAllAns(Graphics g, int size, String[][] map) { // 錯了顯示所有答案
		int width = 500 / size;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (map[y][x] == null) {
					g.setColor(new Color(0, 255, 0, 100)); // 安全地畫綠色
					g.fillRect(x * width + 43, y * width + 23, width - 3, width - 3);
				} else { // 炸彈畫紅色
					g.setColor(new Color(255, 0, 0, 100));
					g.fillRect(x * width + 43, y * width + 23, width - 3, width - 3);
				}
			}
		}
	}

	final static void checkGame(String[][] map, String[][] clickMap, int size) { // 看遊戲結束了沒
		boolean flag = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] != clickMap[i][j]) {
					flag = false;
					break;
				}
			}
		}
		if (flag == true) {
			JOptionPane.showMessageDialog(null, "Congratulation!!!", "~YA~", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	static int size;

	final static int givingSize() { // 給格子個數
		size = Integer.parseInt(JOptionPane.showInputDialog(null, "大小Size <<suggest less than 17>>"));
		if (size < 2 || size > 17) {
			JOptionPane.showMessageDialog(null, "Suggest more than 1 and less than 17 plz");
			givingSize();
		}
		return size;
	}

	final static String[][] bombSetting() { // 排炸彈
		String[][] map = new String[size][size];
		for (int row = 0; row < size; row++) {
			int bomb = (int) (Math.random() * 100) % (size + 1);
			if (bomb < size)
				map[row][bomb] = "*";
		}
		return map;
	}
}
