package org.isegodin.algorithm.learning.task_1_point_shell;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import org.isegodin.algorithm.learning.util.canvas.CanvasApplicationBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Find outer shell for multiple points
 * @author isegodin
 */
public class App extends Application {

	public static void main(String[] args) {
		Application.launch(App.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		CanvasApplicationBase base = new CanvasApplicationBase(300, 300);

		primaryStage.setTitle("Test");
		primaryStage.setScene(base.getScene());
		primaryStage.show();

		CanvasHelper canvasHelper = new CanvasHelper(base.getGraphicsContext());

		List<Point2D> points = drawPoints(canvasHelper);

		canvasHelper.connectPoints(points);
	}

	private List<Point2D> drawPoints(CanvasHelper canvasHelper) {
		List<Point2D> points = new ArrayList<>();
		Random random = new Random(1);

		for (int i = 0; i < 5; i++) {
			int x = random.nextInt((int) canvasHelper.getGraphicsContext().getCanvas().getWidth());
			int y = random.nextInt((int) canvasHelper.getGraphicsContext().getCanvas().getHeight());
			points.add(new Point2D(x, y));
			canvasHelper.addPoint(x, y);
		}

		return points;
	}
}
