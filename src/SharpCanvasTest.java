import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SharpCanvasTest extends Application {
	VBox root;
	Group group;
	
	int width = 64;
	int height = 64;
	int size = 10;
	
    @Override
    public void start(Stage primaryStage) {
    	group = new Group();
        createCanvasGrid();
        root = new VBox();
        
        root.getChildren().add(group);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void createCanvasGrid() {
    	Rectangle rect = new Rectangle(0, 0, size, size);
        rect.setMouseTransparent(true);
        rect.setFill(Color.BLUE);
        group.getChildren().add(rect);
        
        Rectangle rect1 = new Rectangle(10, 10, size, size);
        rect1.setMouseTransparent(true);
        rect1.setFill(Color.RED);
        group.getChildren().add(rect1);
        
        for (int x = 0; x <= width * size; x+=size) {
            double x1 ;
            x1 = x - 0.5 ;
            Line line = new Line(x1, 0, x1, height*size-1);
            line.setStrokeWidth(1);
            line.setStroke(Color.BLACK);
            group.getChildren().add(line);
        }

        for (int y = 0; y <= height * size; y+=size) {
        	double y1 ;
            y1 = y - 0.5 ;
            Line line = new Line(0, y1, width*size-1, y1);
            line.setStrokeWidth(1);
            line.setStroke(Color.BLACK);
            group.getChildren().add(line);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}