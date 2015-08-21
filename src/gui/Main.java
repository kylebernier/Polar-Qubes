package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import core.Model;
import gui.grid.GridFrame;
import gui.model.ModelFrame;
import gui.toolbars.ToolsFrame;
import javafx.application.Platform;
import gui.toolbars.ToolbarFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 2853176700479564421L;
	private JMenuBar menuBar;
	
	private static ModelFrame modelFrame;
	
	private static GridFrame gridFrame;
	
	private static Model currentModel;
	
	public static int currentTool = 0;
	
	Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
	
	public static int height = 64;
	public static int width = 64;
	public static int depth = 64;
	
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
		setLayout(new BorderLayout());
		setSize(bounds.width, bounds.height);
		
		currentModel = new Model();
		
		loadSwing();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void loadSwing() {
		StatusPanel statusPanel = new StatusPanel();
		add(statusPanel, BorderLayout.SOUTH);

		JDesktopPane desktop = new JDesktopPane();
		this.add(desktop);
		
		menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.add(new JMenuItem("Item 1"));
		file.add(new JMenuItem("Item 2"));
		file.add(new JMenuItem("Item 3"));
		menuBar.add(file);
		setJMenuBar(menuBar);

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
	
	public static void setUp(int width, int height, int depth) {
		
	}
		
	public static void scaleGrid(int n) {
		if (n == 0)
			gridFrame.getGridPanel().increaseSize();
		if (n == 1)
			gridFrame.getGridPanel().decreaseSize();
	}
	
	public static void setView(int n) {
		getGrid().getGridPanel().setView(n);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				getModelFrame().setView(n);
			}
		});
	}
	
	public static void setLayer(int n) {
		getGrid().getGridPanel().setLayer(n);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				getModelFrame().setLayer(n);
			}
		});
	}
	
	public static void changeLayer(int n) {
		getGrid().getGridPanel().changeLayer(n);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				getModelFrame().changeLayer(n);
			}
		});
	}
}