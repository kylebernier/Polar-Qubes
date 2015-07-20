package gui;

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import core.Model;

public class Main extends JFrame {

	private static final long serialVersionUID = 2853176700479564421L;
	private JMenuBar menuBar;
	
	public static ModelFrame modelFrame;
	
	public static Model model;
	
	public static Color currentColor = Color.BLACK;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		model = new Model();
		
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
		
		JInternalFrame colorFrame = new ColorFrame();
		colorFrame.setVisible(true);
		desktop.add(colorFrame);

		JInternalFrame gridFrame = new GridFrame();
		gridFrame.setVisible(true);
		desktop.add(gridFrame);

		JInternalFrame paletteFrame = new PaletteFrame();
		paletteFrame.setVisible(true);
		desktop.add(paletteFrame);

		JInternalFrame toolsFrame = new ToolsFrame();
		toolsFrame.setVisible(true);
		desktop.add(toolsFrame);

		this.setSize(1024, 768);
	}
}