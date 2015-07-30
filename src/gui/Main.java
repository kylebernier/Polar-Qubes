package gui;

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import core.Model;
import gui.grid.GridFrame;
import gui.model.ModelFrame;
import gui.toolbars.ToolFrame;
import gui.toolbars.ToolsFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 2853176700479564421L;
	private JMenuBar menuBar;
	
	public static ModelFrame modelFrame;
	
	public static GridFrame gridFrame;
	
	public static Model currentModel;
	
	public static int currentTool;
	
	public static Model getModel() {
		return currentModel;
	}

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		currentModel = new Model();
		
		loadSwing();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void loadSwing() {
		menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.add(new JMenuItem("Item 1"));
		file.add(new JMenuItem("Item 2"));
		file.add(new JMenuItem("Item 3"));
		menuBar.add(file);

		setJMenuBar(menuBar);
		

		JDesktopPane desktop = new JDesktopPane();
		this.add(desktop);

		modelFrame = new ModelFrame();
		modelFrame.setVisible(true);
		desktop.add(modelFrame);
		
		ColorFrame colorFrame = new ColorFrame();
		colorFrame.setVisible(true);
		desktop.add(colorFrame);

		gridFrame = new GridFrame();
		gridFrame.setVisible(true);
		desktop.add(gridFrame);

		ToolFrame toolFrame = new ToolFrame();
		toolFrame.setVisible(true);
		desktop.add(toolFrame);

		ToolsFrame toolsFrame = new ToolsFrame();
		toolsFrame.setVisible(true);
		desktop.add(toolsFrame);
		
		LayerFrame layerFrame = new LayerFrame();
		layerFrame.setVisible(true);
		desktop.add(layerFrame);

		this.setSize(1024, 768);
	}
	
	public static void setView(int n) {
		
	}
	
	public static void scaleGrid(int n) {
		if (n == 0)
			gridFrame.getGridPanel().increaseSize();
		if (n == 1)
			gridFrame.getGridPanel().decreaseSize();
	}
	
	public static void changeLayer(int n) {
		
	}
}