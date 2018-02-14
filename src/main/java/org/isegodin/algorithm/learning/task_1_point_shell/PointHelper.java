package org.isegodin.algorithm.learning.task_1_point_shell;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author isegodin
 */
public class PointHelper {

//	public List<Point2D> orderPoints(List<Point2D> inputPoints) {
//
//	}

	private Point2D findNextPoint(Point2D point, List<Point2D> points) {
		Optional<Point2D> nextPoint;

		nextPoint = points.stream()
				.filter(p -> !p.equals(point) && p.getY() < point.getY())
				.sorted(Comparator.comparingDouble(o -> o.distance(point)))
				.findFirst();

		if (nextPoint.isPresent()) {
			return nextPoint.get();
		}

		nextPoint = points.stream()
				.filter(p -> !p.equals(point) && p.getX() > point.getX())
				.sorted(Comparator.comparingDouble(o -> o.distance(point)))
				.findFirst();

		if (nextPoint.isPresent()) {
			return nextPoint.get();
		}

		nextPoint = points.stream()
				.filter(p -> !p.equals(point) && p.getY() > point.getY())
				.sorted((o1, o2) -> Double.compare(o1.getX(), o2.getX()) * -1)
				.findFirst();

		if (nextPoint.isPresent()) {
			return nextPoint.get();
		}

		nextPoint = points.stream()
				.filter(p -> !p.equals(point) && p.getX() < point.getX())
				.sorted(Comparator.comparingDouble(o -> o.distance(point)))
				.findFirst();

		if (nextPoint.isPresent()) {
			return nextPoint.get();
		}

		return null;
	}

	private enum SearchDirection {
		UP, RIGHT, BOTTOM, LEFT;

		public List<SearchDirection> getNextDirections() {
			List<SearchDirection> result = new ArrayList<>();
			int idx = ordinal() == values().length - 1 ? 0 : ordinal();
			result.add(this);
			while (result.size() < values().length) {
				if (idx == values().length - 1) {
					idx = 0;
				} else {
					idx++;
				}
				result.add(values()[idx]);
			}
			return result;
		}
	}

	private class SearchResult {
		private final SearchDirection direction;
		private final Point2D point;

		public SearchResult(SearchDirection direction, Point2D point) {
			this.direction = direction;
			this.point = point;
		}

		public SearchDirection getDirection() {
			return direction;
		}

		public Point2D getPoint() {
			return point;
		}
	}
}
