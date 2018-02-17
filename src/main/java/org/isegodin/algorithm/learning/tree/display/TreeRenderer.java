package org.isegodin.algorithm.learning.tree.display;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.isegodin.algorithm.learning.tree.Node;
import org.isegodin.algorithm.learning.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author isegodin
 */
public class TreeRenderer {

    private static final int NODE_SIZE = 20;
    private static final int NODE_X_SPACE = 10;
    private static final int NODE_Y_SPACE = 30;

    public void renderTree(Tree tree, GraphicsContext graphicsContext) {

        List<List<Node>> levels = getTreeLevels(tree);

        int treeMaxLevel = levels.size() - 1;

        for (int i = 0; i < levels.size(); i++) {
            List<Node> levelNodes = levels.get(i);
            for (int j = 0; j < levelNodes.size(); j++) {
                Node node = levelNodes.get(j);
                if (node != null) {
                    Point2D point = calculateNodePosition(i, j, treeMaxLevel);
                    drawNode(graphicsContext, point, i, j, treeMaxLevel, node.getData());
                }
            }
        }
    }

    private void drawNode(GraphicsContext graphicsContext, Point2D center, int level, int index, int treeMaxLevel, Object value) {
        text(graphicsContext, center, value.toString());
        strokeCircle(graphicsContext, center);

        if (level > 0) {
            int parentIndex = (int) Math.floor(index / 2);
            Point2D parentCenter = calculateNodePosition(level - 1, parentIndex, treeMaxLevel);

            line(graphicsContext,
                    new Point2D(center.getX(), center.getY() - (NODE_SIZE / 2)),
                    new Point2D(parentCenter.getX(), parentCenter.getY() + (NODE_SIZE / 2))
            );

        }
    }

    private Point2D calculateNodePosition(int level, int index, int treeMaxLevel) {
        int subElementsNumber = (int) Math.pow(2, treeMaxLevel - level);

        double nodeWidth = subElementsNumber == 0 ? NODE_SIZE : (subElementsNumber * NODE_SIZE + (subElementsNumber - 1) * NODE_X_SPACE);

        double x = index * (nodeWidth + NODE_X_SPACE) + nodeWidth / 2 + (NODE_SIZE / 2); //  + (NODE_SIZE / 2) because x and y are center coordinates, we need offset to draw in positive coordinates
        double y = level * NODE_Y_SPACE + (level + 1) * NODE_SIZE - NODE_SIZE / 2;

        return new Point2D(x, y);
    }

    private List<List<Node>> getTreeLevels(Tree tree) {
        Node rootNode = tree.getRootNode();
        if (rootNode == null) {
            return new ArrayList<>(0);
        }
        List<List<Node>> levels = new ArrayList<>();
        List<Node> currentLevel = new ArrayList<>();
        currentLevel.add(rootNode);

        boolean isEmpty = false;

        while (!isEmpty) {
            isEmpty = true;
            List<Node> nextLevel = new ArrayList<>();

            for (Node n : currentLevel) {
                if (n != null) {
                    nextLevel.add(n.getLeft());
                    nextLevel.add(n.getRight());
                    if (n.getLeft() != null || n.getRight() != null) {
                        isEmpty = false;
                    }
                } else {
                    nextLevel.add(null);
                    nextLevel.add(null);
                }
            }
            levels.add(currentLevel);
            currentLevel = nextLevel;
        }
        return levels;
    }

    private void text(GraphicsContext graphicsContext, Point2D point, String value) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setLineWidth(1);
        graphicsContext.setTextBaseline(VPos.CENTER);

        Bounds textBounds = getTextBounds(value);
        double deltaX = textBounds.getWidth() / 2;
        graphicsContext.fillText(value, point.getX() - deltaX, point.getY());
    }

    private void strokeCircle(GraphicsContext graphicsContext, Point2D point) {
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1);

        double delta = NODE_SIZE / 2;
        graphicsContext.strokeOval(point.getX() - delta, point.getY() - delta, NODE_SIZE, NODE_SIZE);
    }

    private void line(GraphicsContext graphicsContext, Point2D p1, Point2D p2) {
        graphicsContext.setStroke(Color.GREEN);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private Bounds getTextBounds(String text) {
        return new Text(text).getBoundsInLocal();
    }
}
