package Minesweeper;

import javax.swing.JFrame;

public class MinesweeperGUI {
	public static void main(String[] args) {
		MinesweeperFrame mf = new MinesweeperFrame();
		mf.setTitle("Minesweeper");
		mf.setSize(600, 615);
		mf.setLocationRelativeTo(null);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setResizable(false);
		mf.setVisible(true);
	}
}
