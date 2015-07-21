package gui.toolbars;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolsFrame extends JInternalFrame {
	public ToolsFrame() {
		setTitle("ToolBar");
		setSize(600, 600);
		setVisible(true);
		setIconifiable(true);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(null);
		getContentPane().add(toolBar);

		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		toolBar.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		toolBar.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("New button");
		toolBar.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("New button");
		toolBar.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("New button");
		toolBar.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("New button");
		toolBar.add(btnNewButton_7);

		pack();
	}
}
