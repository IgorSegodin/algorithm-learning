package org.isegodin.algorithm.learning.util.canvas;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * @author isegodin
 */
public class CanvasApplicationBase {

    private final Group root;
    private final Scene scene;

    private final ArrayList<Canvas> layers = new ArrayList<>();

    private int selectedLayer = 0;

    public CanvasApplicationBase(double width, double height) {
        this.root = new Group();

        ScrollPane scrollPane = new ScrollPane(this.root);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        this.scene = new Scene(scrollPane, width, height, Color.WHITE);

        newLayer();
        selectLayer(selectedLayer);
    }

    public GraphicsContext getGraphicsContext() {
        return layers.get(layers.size() -1).getGraphicsContext2D();
    }

    public Scene getScene() {
        return scene;
    }

    public void newLayer() {
        Canvas canvas = new Canvas(scene.getWidth() * 2, scene.getHeight() * 2);
        canvas.setVisible(false);
        layers.add(canvas);
        Platform.runLater(
                () -> {
                    root.getChildren().add(canvas);
                }
        );
    }

    public void selectPrevious() {
        selectLayer(selectedLayer - 1);
    }

    public void selectNext() {
        selectLayer(selectedLayer + 1);
    }

    public void selectLayer(int index) {
        if (index < 0 || index >= layers.size()) {
            return;
        }

        Canvas previous = layers.get(selectedLayer);
        Canvas selected = layers.get(index);

        if (previous != null && selected != null) {
            previous.setVisible(false);
        }
        if (selected != null) {
            selected.setVisible(true);
            selectedLayer = index;
        }
    }

    public void clear() {
        getGraphicsContext().clearRect(0, 0, scene.getWidth(), scene.getHeight());
    }
}
