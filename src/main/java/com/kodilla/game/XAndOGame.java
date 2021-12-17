package com.kodilla.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class XAndOGame extends Application {

    private static final int PANE_HEIGHT = 600;
    private static final int PANE_WIDTH = 605;
    private final Image imgBackground = new Image("file:src/main/resources/paladin.jpg");
    private final GameLogic gameLogic = new GameLogic();
    private final Square[][]squares = new Square[3][3];

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createParent());
        primaryStage.setScene(scene);
        primaryStage.setTitle("X's and O's Game");
        primaryStage.setResizable(false);
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
                tile.setId(""+i+j);
                squares[i][j] = tile;
                root.getChildren().add(tile);
            }
        }
        addButtons(root);
        gameLogic.createGameTableWithDefaultValues();
        return root;
    }

    private void addButtons(AnchorPane root) {
        addNewGameButton(root);
        addGameRulesButton(root);
        addExitButton(root);
    }

    private void addExitButton(AnchorPane root){
        Button exit = new Button("Exit");
        exit.setLayoutX(403.0);
        exit.setLayoutY(1.0);
        exit.setPrefHeight(25);
        exit.setPrefWidth(197);
        exit.setId("exit");
        root.getChildren().add(exit);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
    }

    private void addGameRulesButton(AnchorPane root) {
        Button game_rules = new Button("Game Rules");
        game_rules.setLayoutX(203.0);
        game_rules.setLayoutY(1.0);
        game_rules.setPrefHeight(25);
        game_rules.setPrefWidth(200);
        game_rules.setId("rules");
        root.getChildren().add(game_rules);
        game_rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game rules");
                alert.setHeaderText("There is one rule!!");
                alert.setContentText("*Put 3 crosses in column, row or across board to win!");
                alert.setResizable(false);
                alert.setHeight(20);
                alert.show();
            }
        });
    }

    private void addNewGameButton(AnchorPane root) {
        Button new_game = new Button("New Game");
        new_game.setLayoutY(1.0);
        new_game.setLayoutX(5.0);
        new_game.setPrefHeight(25);
        new_game.setPrefWidth(198);
        new_game.setId("new");
        root.getChildren().add(new_game);
        new_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameLogic.createGameTableWithDefaultValues();
                for (Square[] square : squares) {
                    for (Square square1 : square) {
                        square1.getText().setText("");
                    }
                }
            }
        });
    }


    private Background createBackground(){
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imgBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    public class Square extends StackPane {
        private static final int SQUARE_HEIGHT = 200;
        private static final int SQUARE_WIDTH = 200;
        private final Text text = new Text();

        public Square(){
            Rectangle border = new Rectangle(SQUARE_WIDTH,SQUARE_HEIGHT);
            border.setFill(null);
            border.setStroke(Color.WHITE);
            border.setStrokeWidth(5);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border,text);

            setOnMouseClicked(event -> {
                if(gameLogic.isxTurn()) {
                    if (text.getText().equals("")) {
                        drawX();
                        gameLogic.markAMove(this);
                        gameLogic.setxTurn(false);
                        checkIfPlayerWon();
                        checkIfComputerWon();
                        checkIfDraw();
                    }
                }

                if (!gameLogic.isxTurn()) {
                    gameLogic.computerMove(squares);
                    gameLogic.setxTurn(true);
                    checkIfPlayerWon();
                    checkIfComputerWon();
                    checkIfDraw();
                }
            });
        }

        private void checkIfDraw() {
            if (gameLogic.checkIfDraw()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Draw game!!");
                alert.setContentText("It was a typical draw!");
                alert.setTitle("Draw game!!");
                alert.setResizable(false);
                alert.setHeight(20);
                alert.show();
                gameLogic.createGameTableWithDefaultValues();
                cleanGameBoard();
            }
        }

        private void checkIfComputerWon() {
            if (gameLogic.checkIfComputerWon()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Looser!!");
                alert.setContentText("Computer won this game!");
                alert.setTitle("Looser!!");
                alert.setResizable(false);
                alert.setHeight(20);
                alert.show();
                gameLogic.createGameTableWithDefaultValues();
                cleanGameBoard();
            }
        }

        private void checkIfPlayerWon() {
            if (gameLogic.checkIfPlayerWon()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Winner!!");
                alert.setContentText("Player won this game!");
                alert.setTitle("Winner!!");
                alert.setResizable(false);
                alert.setHeight(20);
                alert.show();
                gameLogic.createGameTableWithDefaultValues();
                cleanGameBoard();
            }
        }

        private void cleanGameBoard() {
            for (Square[] square : squares) {
                for (Square square1 : square) {
                    square1.getText().setText("");
                }
            }
        }

        private void drawX(){
            text.setText("X");
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Brush Script MT",100));
        }

        public void drawO(){
            text.setText("O");
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Brush Script MT",100));
        }

        public Text getText() {
            return text;
        }
    }

}
