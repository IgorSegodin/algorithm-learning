package org.isegodin.algorithm.learning.util.canvas;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author isegodin
 */
public class CanvasApplicationBase {

	private final Scene scene;
	private final GraphicsContext graphicsContext;

	public CanvasApplicationBase(double width, double height) {
		Group root = new Group();
		this.scene = new Scene(root, width, height, Color.BLACK);

		Canvas canvas = new Canvas(scene.getWidth(),scene.getHeight());
		this.graphicsContext = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public Scene getScene() {
		return scene;
	}
}
