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
	JButton QABtn;
	boolean gameState = false; // 判斷遊戲開始
	int size; // 幾x幾
	int width; // 每格寬度
	String[][] map; // 原炸彈位置圖
	String[][] numMap; // 數字圖
	String[][] clickMap; // 做記號的圖 最後要跟炸彈圖比對 決定輸贏
	int max; // map邊界
	
	MinesweeperFrame() {
		size = Util.givingSize();
		width = 500 / size; 
		
		controlPanel = new JPanel();
		startBtn = new JButton("START");
		restartBtn = new JButton("RESTART");
		restartBtn.setEnabled(false);
		QABtn = new JButton("我不會玩QQ");
		controlPanel.add(startBtn);
		controlPanel.add(restartBtn);
		controlPanel.add(QABtn);

		boardPanel = new JPanel();
		boardPanel.setBackground(new Color(180, 180, 180));

		add(controlPanel, BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.SOUTH);
		add(boardPanel, BorderLayout.CENTER);

		map = new String[size][size];
		numMap = new String[size][size];
		clickMap = new String[size][size];
		max = size - 1;
		
		map = Util.bombSetting(); //set bomb

		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				restartBtn.setEnabled(true);
				startBtn.setEnabled(false);
				gameState = true;
				
				for (int i = 0; i < size; i++) { // initial the click map with "*"
					for (int j = 0; j < size; j++) {
						clickMap[i][j] = "*";
					}
				}
				
				for (int i = 0; i < size; i++) { // make the number map
					for (int j = 0; j < size; j++) {
						numMap[i][j] = Util.search(map, i, j, max) + "";
					}
				}

				Graphics g = boardPanel.getGraphics(); // draw the board
				Util.drawLine(g, size, width, numMap);

				for (int i = 0; i < size; i++) { // Answer!!!!
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
					
					clickMap[mouseY][mouseX] = null;
					
					Util.checkGame(map, clickMap, size);
				}
			}
		});

		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startBtn.setEnabled(true);
				restartBtn.setEnabled(false);
				gameState = false;

				boardPanel.repaint();

				size = Util.givingSize();
				width = 500 / size;
				map = new String[size][size];
				numMap = new String[size][size];
				clickMap = new String[size][size];
				max = size - 1;
				map = Util.bombSetting();
			}
		});
		
		QABtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RuleFrame rf = new RuleFrame();
				rf.setTitle("Rules");
				rf.setSize(550, 150);
				rf.setLocationRelativeTo(null);
				rf.setResizable(false);
				rf.setVisible(true);
			}
		});

	}
}
