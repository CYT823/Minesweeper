package Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MinesweeperFrame extends JFrame {
	JPanel controlPanel;
	JPanel boardPanel;
	JButton startBtn;
	JButton restartBtn;
	boolean gameState = false; // 判斷遊戲開始
	int size; //幾x幾
	int width; //每格寬度
	String[][] map; //原炸彈位置圖
	String[][] newMap; //數字圖
	int max; //map邊界
	
	MinesweeperFrame() {
		size = Integer.parseInt(JOptionPane.showInputDialog(null, "大小Size<<suggest less than 17>>"));
		width = 500 / size;
		
		controlPanel = new JPanel();
		startBtn = new JButton("START");
		restartBtn = new JButton("RESTART");
		restartBtn.setEnabled(false);
		controlPanel.add(startBtn);
		controlPanel.add(restartBtn);

		boardPanel = new JPanel();
		boardPanel.setBackground(new Color(180, 180, 180));

		add(controlPanel, BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.SOUTH);
		add(boardPanel, BorderLayout.CENTER);

		map = new String[size][size];
		newMap = new String[size][size];
		max = size - 1;

		for (int row = 0; row < size; row++) { // 排炸彈
			int bomb = (int) (Math.random() * 100) % (size + 1);
			if (bomb < size)
				map[row][bomb] = "*";
		}

		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				restartBtn.setEnabled(true);
				startBtn.setEnabled(false);
				gameState = true;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						newMap[i][j] = Util.search(map, i, j, max) + "";
					}
				}

				Graphics g = boardPanel.getGraphics();
				Util.drawLine(g, size, width, newMap);
				

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						System.out.print(map[i][j] + "\t");
					}
					System.out.println();
				}
			}
		});

		boardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				if (gameState) {
					int mouseX = (mouseEvent.getX() - 40) / width;
					int mouseY = (mouseEvent.getY() - 20) / width;
					Graphics g = boardPanel.getGraphics();
					Util.drawAnswer(g, mouseX, mouseY, size, map);
					/*
					 * if (!check) { clickCount--; }
					 * 
					 * boolean ch1 = Util.checkHorizontalWin(data, mouseX - 1, mouseY); // 左右連線檢查
					 * boolean ch2 = Util.checkVerticalWin(data, mouseX - 1, mouseY);// 上下連線檢查
					 * boolean ch3 = Util.checkTopLeftToBottomRight(data, mouseX - 1, mouseY);//
					 * 左上到右下連線檢查 boolean ch4 = Util.checkTopRightToBottomLeft(data, mouseX - 1,
					 * mouseY);// 由上到左下連線檢查 if (ch1 || ch2 || ch3 || ch4) { if (data[mouseX -
					 * 1][mouseY] == 1) JOptionPane.showMessageDialog(null, "黑方獲勝", "",
					 * JOptionPane.INFORMATION_MESSAGE); else JOptionPane.showMessageDialog(null,
					 * "白方獲勝", "", JOptionPane.INFORMATION_MESSAGE);
					 * 
					 * Util.restartGame(g);// 初始化 clickCount = 0; data = new int[17][17]; gameState
					 * = false; restartBtn.setEnabled(false); startBtn.setEnabled(true); return; }
					 */
				}
			}
		});

/*
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Graphics g = boardPanel.getGraphics();
				Util.restartGame(g);
				clickCount = 0; // 初始化
				data = new int[17][17];
			}
		});
*/
	}
}
