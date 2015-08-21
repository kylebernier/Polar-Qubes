
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;
import jfxtras.labs.scene.layout.ScalableContentPane;
import jfxtras.labs.util.event.MouseControlUtil;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // create a scalable content pane
        ScalableContentPane scaledPane = new ScalableContentPane();

        // use it's predefined content pane as root pane
        Pane root = scaledPane.getContentPane();

        // add rectangles and make them draggable
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Rectangle rect = new Rectangle(30, 30);
                rect.setLayoutX(x*(rect.getWidth() + 10));
                rect.setLayoutY(y*(rect.getHeight() + 10));
                MouseControlUtil.makeDraggable(rect);
                root.getChildren().add(rect);
            }
        }

        // add the scalable pane to the scene
        Scene scene = new Scene(scaledPane, 600, 400);

        // setup the stage
        primaryStage.setTitle("Scalable Content");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

