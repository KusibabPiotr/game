package com.kodilla.game;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Square extends StackPane {
    private static final int SQUARE_HEIGHT = 200;
    private static final int SQUARE_WIDTH = 200;
    private Text text = new Text();

    public Square(){
        Rectangle border = new Rectangle(SQUARE_WIDTH,SQUARE_HEIGHT);
        border.setFill(null);
        border.setStroke(Color.WHITE);
        border.setStrokeWidth(5);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border,text);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY){
                if (text.getText().equals("")){
                    drawX();
                }
            } else if (event.getButton() == MouseButton.SECONDARY){
                if (text.getText().equals("")){
                    drawO();
                }
            }
        });
    }

    private void drawX(){
        text.setText("X");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Brush Script MT",100));
    }

    private void drawO(){
        text.setText("O");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Brush Script MT",100));
    }
}
