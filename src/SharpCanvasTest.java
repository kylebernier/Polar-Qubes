import java.awt.Dimension;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SharpCanvasTest extends Application {
	VBox root;
	Group grid;
	Group cube;
	
	int width = 32;
	int height = 32;
	int size = 16;
	ScrollPane scroll;
	
    @Override
    public void start(Stage primaryStage) {
    	final ColorPicker colorPicker = new ColorPicker();
    	 colorPicker.setOnAction(new EventHandler() {
    	     public void handle1(Event t) {
    	         Color c = colorPicker.getValue();
    	         System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
    	     }

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				
			}
    	 });
    	
    	grid = new Group();
    	cube = new Group();
    	cube.getChildren().add(grid);
    	cube.getChildren().add(colorPicker);
        createCanvasGrid();
        
        scroll = new ScrollPane();
        scroll.setContent(cube);
        
        root = new VBox();
        root.getChildren().add(scroll);
        VBox.setVgrow(scroll, Priority.ALWAYS);
        
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();

        cube.getParent().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent m) {
            	int x = (int)m.getX()/size;
            	int y = (int)m.getY()/size;
            	if (x <= width-1 && x >=0 && y <= height-1 && y >=0)
                	addCube(x, y, Color.BLUE);
            }
        });
        
        cube.getParent().addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent m) {
            	int x = (int)m.getX()/size;
            	int y = (int)m.getY()/size;
            	if (x <= width-1 && x >=0 && y <= height-1 && y >=0)
                	addCube(x, y, Color.BLUE);
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
    
    public void addCube(int x, int y, Color c) {
    	Rectangle rect = new Rectangle(x*size, y*size, size-1, size-1);
        rect.setFill(c);
        cube.getChildren().add(rect);
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

    public static void main(String[] args) {
        launch(args);
    }
}