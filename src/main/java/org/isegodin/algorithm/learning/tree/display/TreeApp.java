package org.isegodin.algorithm.learning.tree.display;

import javafx.application.Application;
import javafx.stage.Stage;
import org.isegodin.algorithm.learning.tree.BinaryTree;
import org.isegodin.algorithm.learning.tree.Tree;
import org.isegodin.algorithm.learning.util.canvas.CanvasApplicationBase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author isegodin
 */
public class TreeApp extends Application {


    public static void main(String[] args) {
        Application.launch(TreeApp.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        CanvasApplicationBase base = new CanvasApplicationBase(800, 800);

        primaryStage.setTitle("Test");
        primaryStage.setScene(base.getScene());
        primaryStage.show();

        BinaryTree<Integer> tree = new BinaryTree<>();

//        addToTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, tree, base);

//        addToTree(new int[]{
//                6,
//                2, 1, 4, 3, 5,
//                8, 7, 10, 9, 11
//        }, tree, base);

        addToTree(new int[]{
                8,
                4, 12,
                2, 14, 6, 10,
                1, 15, 3, 13, 5, 11, 7, 9
        }, tree, base);

        base.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A: {
                    base.selectPrevious();
                    break;
                }
                case D: {
                    base.selectNext();
                    break;
                }
            }
        });
    }

    private void addToTree(int[] data, Tree tree, CanvasApplicationBase base) {
        TreeRenderer renderer = new TreeRenderer();

        for (int i = 0; i < data.length; i++) {
            int val = data[i];
            tree.add(val);
            if (i % 5 == 0 || i == data.length - 1) {
                base.newLayer();
                renderer.renderTree(tree, base.getGraphicsContext());
            }

        }
    }

}
