
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import gui.Main;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Grid extends JPanel{
	int size = 10;
	int height = 64;
	int width = 64;
	
	int layer = 0;
	
	boolean toggleGrid = true;
	
	public void toggleGrid() {
		toggleGrid = !toggleGrid;
	}
	
	public void changeLayer(int n) {
		if(n == 0)
			layer++;
		if(n == 1)
			layer--;
	}
	
	public void setLayer(int n) {
		layer = n;
	}
	
	public void increaseSize() {
		size++;
		setPreferredSize(new Dimension(size*width+1, size*height+1));
		revalidate();
		repaint();
	}
	
	public void decreaseSize() {
		size--;
		setPreferredSize(new Dimension(size*width+1, size*height+1));
		revalidate();
		repaint();
	}

	private void initAndShowGUI() {
		final JFXPanel fxPanel = new JFXPanel();
		add(fxPanel);
		
		initFX(fxPanel);
	}
	
	Group group;
	VBox root;
	
	Scene scene;

	private void initFX(JFXPanel fxPanel) {
		group = new Group();
        root = new VBox();       
        root.getChildren().add(group);
        
        scene = new Scene(root);
        
        fxPanel.setScene(scene);
	}
	
	public Grid() {
		//setTitle("Grid");
		//setResizable(true);
		setSize(600, 600);
		//setIconifiable(true);
		initAndShowGUI();
		handleMouse();
		addGrid();
	}
	
	private void handleMouse() {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				if (m.isPrimaryButtonDown()) {
					paintQ(m);
					repaint();
				}
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				if (m.isPrimaryButtonDown()) {
					paintQ(m);
					repaint();
				}
			}
		});
	}
	
	private void addGrid() {
		Group grid = new Group();
		for (int x = 0; x <= width * size; x+=size) {
            double x1 ;
            x1 = x - 0.5 ;
            Line line = new Line(x1, 0, x1, height*size-1);
            line.setStrokeWidth(1);
            line.setStroke(Color.BLACK);
            grid.getChildren().add(line);
        }

        for (int y = 0; y <= height * size; y+=size) {
        	double y1 ;
            y1 = y - 0.5 ;
            Line line = new Line(0, y1, width*size-1, y1);
            line.setStrokeWidth(1);
            line.setStroke(Color.BLACK);
            grid.getChildren().add(line);
        }
        
        group.getChildren().add(grid);
	}
	
	private void addRect(int x, int y, java.awt.Color c) {
			Rectangle rect = new Rectangle(x * size, y * size, size, size);
	        int r = c.getRed();
	        int g = c.getGreen();
	        int b = c.getBlue();
	        int a = c.getAlpha();
	        double opacity = a / 255.0 ;
	        rect.setFill(Color.rgb(r, g, b, opacity));
	        group.getChildren().add(rect);
	}
	
	private void removeRect(int x, int y) {
		Rectangle rect = new Rectangle(x * size, y * size, size, size);
		group.getChildren().remove(rect);
	}
	
	private void paintQ(MouseEvent m) {
		int x = (int) (m.getX() / size);
		int y = (int) (m.getY() / size);
		int z = 2;
		
		int x1 = transformX(x);
		int y1 = transformY(y);
		int z1 = transformZ(z);
		
		if (x < width && y < height && x >= 0 && y >= 0) {
			if (Main.getModel().getColor(x, y, z) == null && Main.currentTool == 0) {
				Main.getModel().addQube(x, y, z, Main.getModel().currentColor);
				addRect(x, y, Main.getModel().currentColor);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.getModelFrame().addBox(x, y, z, Main.getModel().currentColor);
					}
				});
			}
			if (Main.getModel().getColor(x, y, z) != null && Main.currentTool == 1) {
				Main.getModel().removeQube(x, y, z);
				removeRect(x, y);
				//Main.modelFrame.removeQube();
			}
		}
	}
	
	private int transformX(int x) {
		return width - x - 1;
	}
	
	private int transformY(int y) {
		return height - y - 1;
	}

	private int transformZ(int z) {
		return z;
	}
}
