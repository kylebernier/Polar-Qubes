package gui.grid;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;


public class GridFrame extends JInternalFrame {
	GridPanel gridPanel;
	
	public GridFrame() {
		setTitle("Grid");
		setResizable(true);
		setSize(600, 600);
		setVisible(true);
		((JComponent) getContentPane()).setOpaque(false);
		setIconifiable(true);
		
		gridPanel = new GridPanel();
		JScrollPane scroll = new JScrollPane(gridPanel, 22, 32);
		add(scroll);
	}
	
	public GridPanel getGridPanel() {
		return gridPanel;
	}
}
