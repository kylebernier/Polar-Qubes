package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;

public class ModelFrame extends JInternalFrame {
	final Group root = new Group();
	final Xform axis = new Xform();
	final Xform model = new Xform();
	final Xform world = new Xform();
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

		buildCamera();
		buildAxes();
		buildModel();

		Scene scene = new Scene(root, 600, 600, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.BLACK);
		handleKeyboard(scene, world);
		handleMouse(scene, world);

		scene.setCamera(camera);

		fxPanel.setScene(scene);
	}
	
	private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.GREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, .1, .1);
        final Box yAxis = new Box(.1, AXIS_LENGTH, .1);
        final Box zAxis = new Box(.1, .1, AXIS_LENGTH);
        final Box oAxis = new Box(.1, .1, .1);
        
		xAxis.setTranslateX(AXIS_LENGTH/2 + .05);
        yAxis.setTranslateY(AXIS_LENGTH/2 + .05);
		zAxis.setTranslateZ(AXIS_LENGTH/2 + .05);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);
        oAxis.setMaterial(new PhongMaterial(Color.BLACK));

        axis.getChildren().addAll(xAxis, yAxis, zAxis, oAxis);
        world.getChildren().add(axis);
    }
	
	public void addBox(int x, int y, int z, java.awt.Color c) {
		Box qube = new Box(2, 2, 2);
		qube.setMaterial(new PhongMaterial(new Color((double)c.getRed()/255, (double)c.getGreen()/255, (double)c.getBlue()/255, (double)c.getAlpha()/255)));
		qube.setTranslateX(x * 2 + 1);
		qube.setTranslateY(y * 2 + 1);
		qube.setTranslateZ(z * 2 + 1);
		qube.setDrawMode(DrawMode.FILL);
		qube.setCullFace(CullFace.BACK);
		model.getChildren().add(qube);
	}
	
	private void buildModel() {
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.LIGHTGRAY);
		material.setSpecularColor(Color.rgb(30, 30, 30));

		world.getChildren().add(model);
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