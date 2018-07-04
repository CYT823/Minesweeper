package Minesweeper;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RuleFrame extends JFrame {
	JLabel ruleTitle;
	JLabel explain;
	JPanel explainPanel = new JPanel();
	RuleFrame() {
		ruleTitle = new JLabel("Rules:");
		ruleTitle.setFont(new Font("Serif", Font.PLAIN, 28));
		
		explainPanel.add(new JLabel("Click 'start' to begin."));
		explainPanel.add(new JLabel("The number in the box is trying to tell you how many safe places around this box."));
		explainPanel.add(new JLabel("At the moment you click the bomb, game is over."));
		explainPanel.add(new JLabel("Caution bomb and good luck!"));
		
		add(ruleTitle, BorderLayout.NORTH);
		add(explainPanel, BorderLayout.CENTER);
	}
}
