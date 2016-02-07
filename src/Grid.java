
import javax.swing.JInternalFrame;

import gui.Main;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Grid extends JInternalFrame{
	int size = 10;
	int height = 64;
	int width = 64;
	int depth = 64;
	
	int layer = 0;
	
	int view = 0;
	
	boolean toggleGrid = true;
	
	public void toggleGrid() {
		toggleGrid = !toggleGrid;
	}
	
	public void setView(int n) {
		view = n;
		revalidate();
		repaint();
	}
	
	public void changeLayer(int n) {	
		if(n == 0)
			if(view == 0 && layer+1 <= depth)
				layer++;
			else
				if(view == 1 && layer+1 <= width)
					layer++;
				else
					if(view == 2 && layer+1 <= height)
						layer++;
		if(n == 1)
			if(view == 0 && layer-1 >= 0)
				layer--;
			else
				if(view == 1 && layer-1 >= 0)
					layer--;
				else
					if(view == 2 && layer-1 >= 0)
						layer--;
		repaint();
	}
	
	public void setLayer(int n) {
		layer = n;
		repaint();
	}
	
	public void increaseSize() {
    	size++;
    	cube.getChildren().remove(grid);
    	grid = new Group();
    	cube.getChildren().add(grid);
    	createCanvasGrid();
		
	}
	
	public void decreaseSize() {
		size--;
		cube.getChildren().remove(grid);
		grid = new Group();
		cube.getChildren().add(grid);
		createCanvasGrid();
	}
	
	VBox root;
	ScrollPane scroll;
	Group grid;
	Group cube;
	
	private void initAndShowGUI() {
		final JFXPanel fxPanel = new JFXPanel();
		add(fxPanel);
		
		initFX(fxPanel);
	}

	private void initFX(JFXPanel fxPanel) {
		grid = new Group();
    	cube = new Group();
    	cube.getChildren().add(grid);
        createCanvasGrid();
        
        scroll = new ScrollPane();
        scroll.setContent(cube);
        
        root = new VBox();
        root.getChildren().add(scroll);
        VBox.setVgrow(scroll, Priority.ALWAYS);
        
        
        Scene scene = new Scene(root);
        
        fxPanel.setScene(scene);

        cube.getParent().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent m) {
            	int x = (int)m.getX()/size;
            	int y = (int)m.getY()/size;
            	if (x <= width-1 && x >=0 && y <= height-1 && y >=0)
                	addCube(x, y);
            }
        });
        
        cube.getParent().addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent m) {
            	int x = (int)m.getX()/size;
            	int y = (int)m.getY()/size;
            	if (x <= width-1 && x >=0 && y <= height-1 && y >=0)
                	addCube(x, y);
            }
        });
	}
	
	private void createCanvasGrid() {
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
    }
    
    public void addCube(int x, int y) {
    	int z = layer;
		
		int x1 = width - x - 1 - width;
		int y1 = height - y - 1;
		int z1 = z;
		
		if (view == 1) {
			x1 = x;
			y1 = height - y - 1 - height;
			z1 = depth - z - 1;
			
			int temp = x;
			x = z;
			z = width - temp;
		}
		if (view == 2) {
			x1 = width - x - 1;
			y1 = y;
			z1 = depth - z - 1 - depth;
			
			int temp = z;
			z = y;
			y = depth - temp;
		}
		
		
		
		if (x < width && y < height && x >= 0 && y >= 0) {
			if (Main.getModel().getColor(x, y, z) == null && Main.currentTool == 0) {
				Main.getModel().addQube(x, y, z, Main.getModel().currentColor);
				addRect(x, y, Main.getModel().currentColor);
				final int x11 = x1;
				final int y11 = y1;
				final int z11 = z1;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {				
						Main.getModelFrame().addBox(x11, y11, z11, Main.getModel().currentColor);
					}
				});
			}
			if (Main.getModel().getColor(x, y, z) != null && Main.currentTool == 1) {				
				int n = Main.getModel().getIndex(x, y, z);
				Main.getModel().removeQube(x, y, z);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.getModelFrame().removeCube(n);
					}
				});
			}
		}
    }
    
    public void addRect(int x, int y, Color c) {
    	Rectangle rect = new Rectangle(x*size, y*size, size-1, size-1);
        rect.setFill(c);
        cube.getChildren().add(rect);
    }
}