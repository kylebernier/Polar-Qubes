package gui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import core.Model;
import gui.grid.GridFrame;
import gui.model.ModelFrame;
import gui.toolbars.ToolsFrame;
import gui.toolbars.ToolbarFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 2853176700479564421L;
	private JMenuBar menuBar;
	
	private static ModelFrame modelFrame;
	
	private static GridFrame gridFrame;
	
	private static Model currentModel;
	
	public static int currentTool = 0;
	
	Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
	
	public static Model getModel() {
		return currentModel;
	}
	
	public static GridFrame getGrid() {
		return gridFrame;
	}
	
	public static ModelFrame getModelFrame() {
		return modelFrame;
	}

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		
		setSize(bounds.width, bounds.height);
		
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
		modelFrame.setLocation(bounds.width/2, 0);
		modelFrame.setVisible(true);
		desktop.add(modelFrame);
		
		ColorFrame colorFrame = new ColorFrame();
		colorFrame.setLocation(300, 800);
		colorFrame.setVisible(true);
		desktop.add(colorFrame);

		gridFrame = new GridFrame();
		gridFrame.setLocation(300, 100);
		gridFrame.setVisible(true);
		desktop.add(gridFrame);

		ToolsFrame toolFrame = new ToolsFrame();
		toolFrame.setLocation(0, 0);
		toolFrame.setVisible(true);
		desktop.add(toolFrame);

		ToolbarFrame toolsFrame = new ToolbarFrame();
		toolsFrame.setLocation(30, 0);
		toolsFrame.setVisible(true);
		desktop.add(toolsFrame);
		
		LayerFrame layerFrame = new LayerFrame();
		layerFrame.setLocation(0, 500);
		layerFrame.setVisible(true);
		desktop.add(layerFrame);
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