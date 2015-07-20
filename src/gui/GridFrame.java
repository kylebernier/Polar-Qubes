package gui;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;


public class GridFrame extends JInternalFrame {
	public GridFrame() {
		setTitle("Grid");
		setResizable(true);
		setSize(600, 600);
		setVisible(true);
		((JComponent) getContentPane()).setOpaque(false);
		setIconifiable(true);
		
		add(new GridPanel());
	}
}
