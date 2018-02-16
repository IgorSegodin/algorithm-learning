package org.isegodin.algorithm.learning.tree.display;

import javafx.application.Application;
import javafx.stage.Stage;
import org.isegodin.algorithm.learning.tree.BinaryTree;
import org.isegodin.algorithm.learning.util.canvas.CanvasApplicationBase;

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

        TreeRenderer renderer = new TreeRenderer();

        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.add(6);
        tree.add(2);
        tree.add(1);
        tree.add(4);
        tree.add(3);
        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(10);
        tree.add(9);
        tree.add(11);

        renderer.renderTree(tree, base.getGraphicsContext());


    }


}
