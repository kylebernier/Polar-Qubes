package gui.model;

import javax.swing.JInternalFrame;

import core.ModelMesh;
import core.Xform;
import gui.Main;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ModelFrame extends JInternalFrame {
	final Group root = new Group();
	final Group axis = new Group();
	final Group lay = new Group();
	final Group model = new Group();
	final Group world = new Group();
	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform = new Xform();
	final Xform cameraXform2 = new Xform();
	final Xform cameraXform3 = new Xform();
	private static final double CAMERA_INITIAL_DISTANCE = -30;
	private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
	private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;
	private static final double AXIS_LENGTH = 32;
	private static final double CONTROL_MULTIPLIER = 0.1;
	private static final double SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1;
	private static final double ROTATION_SPEED = 2.0;
	private static final double TRACK_SPEED = 0.3;

	double mousePosX;
	double mousePosY;
	double mouseOldX;
	double mouseOldY;
	double mouseDeltaX;
	double mouseDeltaY;
	
	public int layer;
	public int view;
	
	ModelMesh mesh;
	
	PhongMaterial material;
	
	public void setView(int n) {
		view = n;
		buildLayer();
	}
	
	public void changeLayer(int n) {
		if(n == 0)
			if(view == 0 && layer+1 <= Main.depth)
				layer++;
			else
				if(view == 1 && layer+1 <= Main.width)
					layer++;
				else
					if(view == 2 && layer+1 <= Main.height)
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
		buildLayer();
	}
	
	public void setLayer(int n) {
		layer = n;
		buildLayer();
	}

	public ModelFrame() {
		setTitle("Model");
		setResizable(true);
		setSize(600, 600);
		setLocation(80, 80);
		setIconifiable(true);
		initAndShowGUI();
	}

	private void initAndShowGUI() {
		final JFXPanel fxPanel = new JFXPanel();
		add(fxPanel);
		
		initFX(fxPanel);
	}

	private void initFX(JFXPanel fxPanel) {
		root.getChildren().add(world);
		root.setDepthTest(DepthTest.ENABLE);
		
		world.getChildren().add(lay);

		buildCamera();
		buildAxes();
		buildModel();
		buildLayer();
		
		root.getChildren().add(new AmbientLight(Color.WHITE));

		Scene scene = new Scene(root, 600, 600, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.BLACK);
		handleKeyboard(scene, world);
		handleMouse(scene, world);

		scene.setCamera(camera);

		fxPanel.setScene(scene);
	}
	
	private void buildLayer() {
		lay.getChildren().clear();
		
		int x = 0;
		int y = 0;
		int z = layer;
		
		int x1 = Main.depth;
		int y1 = Main.width;
		int z1 = Main.height;
		
		if (view == 1) {
			x = layer;
			y = 0;
			z = 0;
		}
		if (view == 2) {
			x = 0;
			y = layer;
			z = 0;
		}
		
		Cylinder a = createConnection(new Point3D(-x,y,z), new Point3D(-x1,y,z));
		Cylinder b = createConnection(new Point3D(-x,y,z+1), new Point3D(-x1,y,z+1));
		Cylinder c = createConnection(new Point3D(-x,y1,z), new Point3D(-x1,y1,z));
		Cylinder d = createConnection(new Point3D(-x,y1,z+1), new Point3D(-x1,y1,z+1));
		
		Cylinder e = createConnection(new Point3D(-x,y,z), new Point3D(-x,y1,z));
		Cylinder f = createConnection(new Point3D(-x,y,z+1), new Point3D(-x,y1,z+1));
		Cylinder g = createConnection(new Point3D(-x1,y,z), new Point3D(-x1,y1,z));
		Cylinder h = createConnection(new Point3D(-x1,y,z+1), new Point3D(-x1,y1,z+1));
		
		Cylinder i = createConnection(new Point3D(-x1,y,z), new Point3D(-x1,y,z+1));
		Cylinder j = createConnection(new Point3D(-x1,y1,z), new Point3D(-x1,y1,z+1));
		Cylinder k = createConnection(new Point3D(-x,y,z), new Point3D(-x,y,z+1));
		Cylinder l = createConnection(new Point3D(-x,y1,z), new Point3D(-x,y1,z+1));

        a.setMaterial(new PhongMaterial(Color.YELLOW));
        b.setMaterial(new PhongMaterial(Color.YELLOW));
        c.setMaterial(new PhongMaterial(Color.YELLOW));
        d.setMaterial(new PhongMaterial(Color.YELLOW));
        
        e.setMaterial(new PhongMaterial(Color.YELLOW));
        f.setMaterial(new PhongMaterial(Color.YELLOW));
        g.setMaterial(new PhongMaterial(Color.YELLOW));
        h.setMaterial(new PhongMaterial(Color.YELLOW));
        
        i.setMaterial(new PhongMaterial(Color.YELLOW));
        j.setMaterial(new PhongMaterial(Color.YELLOW));
        k.setMaterial(new PhongMaterial(Color.YELLOW));
       l.setMaterial(new PhongMaterial(Color.YELLOW));
        
        lay.getChildren().addAll(a,b,c,d,e,f,g,h,i,j,k,l);
	}
	
	private void buildAxes() {
        final Cylinder xAxis = createConnection(new Point3D(0,0,0), new Point3D(-AXIS_LENGTH, 0, 0));
        final Cylinder yAxis = createConnection(new Point3D(0,0,0), new Point3D(0, AXIS_LENGTH, 0));
        final Cylinder zAxis = createConnection(new Point3D(0,0,0), new Point3D(0, 0, AXIS_LENGTH));

        xAxis.setMaterial(new PhongMaterial(Color.RED));
        yAxis.setMaterial(new PhongMaterial(Color.BLUE));
        zAxis.setMaterial(new PhongMaterial(Color.GREEN));

        axis.getChildren().addAll(xAxis, yAxis, zAxis);
        world.getChildren().add(axis);
    }
	
	public Cylinder createConnection(Point3D origin, Point3D target) {
	    Point3D yAxis = new Point3D(0, 1, 0);
	    Point3D diff = target.subtract(origin);
	    double height = diff.magnitude();

	    Point3D mid = target.midpoint(origin);
	    Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

	    Point3D axisOfRotation = diff.crossProduct(yAxis);
	    double angle = Math.acos(diff.normalize().dotProduct(yAxis));
	    Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

	    Cylinder line = new Cylinder(.1, height);

	    line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

	    return line;
	}
	
	public void removeCube(int n) {
		mesh.getChildren().remove(n);
	}
	
	public void addBox(int x, int y, int z, Color currentColor) {
		mesh.addCube(x, y, z, currentColor);
	}
	
	private void buildModel() {
		mesh = new ModelMesh();

		world.getChildren().add(mesh);
	}

	private void buildCamera() {
		root.getChildren().add(cameraXform);
		cameraXform.getChildren().add(cameraXform2);
		cameraXform2.getChildren().add(cameraXform3);
		cameraXform3.getChildren().add(camera);
		cameraXform3.setRotateZ(180.0);

		camera.setNearClip(CAMERA_NEAR_CLIP);
		camera.setFarClip(CAMERA_FAR_CLIP);
		camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
		cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
		cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
	}

	private void handleMouse(Scene scene, final Node root) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mouseOldX = mousePosX;
				mouseOldY = mousePosY;
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseDeltaX = (mousePosX - mouseOldX);
				mouseDeltaY = (mousePosY - mouseOldY);

				double modifier = 1.0;

				if (me.isControlDown()) {
					modifier = CONTROL_MULTIPLIER;
				}
				if (me.isShiftDown()) {
					modifier = SHIFT_MULTIPLIER;
				}
				if (me.isPrimaryButtonDown()) {
					cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * MOUSE_SPEED * modifier * ROTATION_SPEED);
					cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * MOUSE_SPEED * modifier * ROTATION_SPEED);
				} else if (me.isSecondaryButtonDown()) {
					double z = camera.getTranslateZ();
					double newZ = z + mouseDeltaY * MOUSE_SPEED * modifier;
					camera.setTranslateZ(newZ);
				} else if (me.isMiddleButtonDown()) {
					cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED);
					cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED);
				}
			}
		});
	}

	private void handleKeyboard(Scene scene, final Node root) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case Z:
					cameraXform2.t.setX(0.0);
					cameraXform2.t.setY(0.0);
					camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
					cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
					cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
					break;
				case V:
					model.setVisible(!model.isVisible());
					break;
				default:
					break;
				}
			}
		});
	}
}