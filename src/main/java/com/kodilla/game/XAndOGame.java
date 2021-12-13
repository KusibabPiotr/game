package com.kodilla.game;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class XAndOGame extends Application {

    private static final int PANE_HEIGHT = 600;
    private static final int PANE_WIDTH = 605;
    private Image imgBackground = new Image("file:src/main/resources/paladin.jpg");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createParent());
        primaryStage.setScene(scene);
        primaryStage.setTitle("X's and O's Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createParent(){
        AnchorPane root = new AnchorPane();
        root.setPrefSize(PANE_WIDTH,PANE_HEIGHT);
        root.setBackground(createBackground());
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Square tile = new Square();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);
            }
        }
        addButtons(root);
        return root;
    }

    private void addButtons(AnchorPane root) {
        addNewGameButton(root);
        addGameRulesButton(root);
        addExitButton(root);
    }

    private void addExitButton(AnchorPane root) {
        Button exit = new Button("Exit");
        exit.setLayoutX(403.0);
        exit.setLayoutY(1.0);
        exit.setPrefHeight(25);
        exit.setPrefWidth(200);
        root.getChildren().add(exit);
    }

    private void addGameRulesButton(AnchorPane root) {
        Button game_rules = new Button("Game Rules");
        game_rules.setLayoutX(203.0);
        game_rules.setLayoutY(1.0);
        game_rules.setPrefHeight(25);
        game_rules.setPrefWidth(200);
        root.getChildren().add(game_rules);
    }

    private void addNewGameButton(AnchorPane root) {
        Button new_game = new Button("New Game");
        new_game.setLayoutY(1.0);
        new_game.setLayoutX(5.0);
        new_game.setPrefHeight(25);
        new_game.setPrefWidth(198);
        root.getChildren().add(new_game);
    }


    private Background createBackground(){
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imgBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }
}
