package org.isegodin.algorithm.learning.task_1_point_shell;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.Period;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * @author isegodin
 */
public class CanvasHelper {

	private final GraphicsContext graphicsContext;

	private final static double DEFAULT_POINT_SIZE = 4;
	private final static double DEFAULT_POINT_MARKER_SIZE = DEFAULT_POINT_SIZE * 2;

	public CanvasHelper(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	public void addPoint(double x, double y) {
		graphicsContext.setFill(Color.RED);
		double delta = DEFAULT_POINT_SIZE / 2;
		graphicsContext.fillOval(x - delta,y - delta, DEFAULT_POINT_SIZE, DEFAULT_POINT_SIZE);
	}

	public void markPoint(Point2D point) {
		graphicsContext.setStroke(Color.YELLOW);
		graphicsContext.setLineWidth(1);

		double delta = DEFAULT_POINT_MARKER_SIZE / 2;
		graphicsContext.strokeOval(point.getX() - delta,point.getY() - delta, DEFAULT_POINT_MARKER_SIZE, DEFAULT_POINT_MARKER_SIZE);
	}

	public void line(Point2D p1, Point2D p2) {
		graphicsContext.setStroke(Color.GREEN);
		graphicsContext.setLineWidth(1);
		graphicsContext.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}


	public void connectPoints(List<Point2D> points) {
		graphicsContext.setStroke(Color.GREEN);
		graphicsContext.setLineWidth(1);

		Timer timer = new Timer();

		timer.schedule(new FindTask(points), 0, Duration.ofSeconds(2).toMillis());
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	private class FindTask extends TimerTask {

		private final List<Point2D> points;
		private final Point2D firstPoint;
		private Point2D currentPoint;

		private Set<Point2D> processedPoints = new HashSet<>();

		public FindTask(List<Point2D> points) {
			this.points = points;
			this.currentPoint = this.firstPoint = points.get(0);
			processedPoints.add(this.firstPoint);
			markPoint(this.firstPoint);
		}

		@Override
		public void run() {
			List<Point2D> unprocessedPoints = points.stream()
					.filter(p -> !processedPoints.contains(p))
					.collect(Collectors.toList());

//			Point2D nextPoint = findNextPoint(this.currentPoint, unprocessedPoints);
			Point2D nextPoint = null;
			if (nextPoint != null) {
				line(this.currentPoint, nextPoint);
				this.currentPoint = nextPoint;
				processedPoints.add(nextPoint);
				System.out.println("Found: " + nextPoint);
			} else {
				cancel();
				System.out.println("Cancelled");
				line(this.currentPoint, this.firstPoint);
			}
		}
	}
}
