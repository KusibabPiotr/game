package com.kodilla.game;

import java.util.Random;

public class GameLogic {
    private boolean isXTurn = true;
    private String[][] table;
    private final Random random = new Random();

    public boolean isxTurn() {
        return isXTurn;
    }

    public void setxTurn(boolean isXTurn) {
        this.isXTurn = isXTurn;
    }

    public void createGameTableWithDefaultValues(){
        String[][] table = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = "";
            }
        }
        this.table = table;
        this.isXTurn = true;
    }


    public boolean checkIfPlayerWon(){
        boolean result = false;
        if (table[0][0].equals("X")&&table[0][0].equals(table[0][1])&&table[0][0].equals(table[0][2])){
            result = true;
        } else if (table[1][0].equals("X")&&table[1][0].equals(table[1][1])&&table[1][0].equals(table[1][2])){
            result = true;
        } else if (table[2][0].equals("X")&&table[2][0].equals(table[2][1])&&table[2][0].equals(table[2][2])){
            result = true;
        } else if (table[0][0].equals("X")&&table[0][0].equals(table[1][0])&&table[0][0].equals(table[2][0])){
            result = true;
        }else if (table[0][1].equals("X")&&table[0][1].equals(table[1][1])&&table[0][1].equals(table[2][1])){
            result = true;
        }else if (table[0][2].equals("X")&&table[0][2].equals(table[1][2])&&table[0][2].equals(table[2][2])){
            result = true;
        }else if (table[0][0].equals("X")&&table[0][0].equals(table[1][1])&&table[0][0].equals(table[2][2])){
            result = true;
        }else if (table[0][2].equals("X")&&table[0][2].equals(table[1][1])&&table[0][2].equals(table[2][0])){
            result = true;
        }
        return result;
    }

    public boolean checkIfComputerWon(){
        boolean result = false;
        if (table[0][0].equals("O")&&table[0][0].equals(table[0][1])&&table[0][0].equals(table[0][2])){
            result = true;
        } else if (table[1][0].equals("O")&&table[1][0].equals(table[1][1])&&table[1][0].equals(table[1][2])){
            result = true;
        } else if (table[2][0].equals("O")&&table[2][0].equals(table[2][1])&&table[2][0].equals(table[2][2])){
            result = true;
        } else if (table[0][0].equals("O")&&table[0][0].equals(table[1][0])&&table[0][0].equals(table[2][0])){
            result = true;
        }else if (table[0][1].equals("O")&&table[0][1].equals(table[1][1])&&table[0][1].equals(table[2][1])){
            result = true;
        }else if (table[0][2].equals("O")&&table[0][2].equals(table[1][2])&&table[0][2].equals(table[2][2])){
            result = true;
        }else if (table[0][0].equals("O")&&table[0][0].equals(table[1][1])&&table[0][0].equals(table[2][2])){
            result = true;
        }else if (table[0][2].equals("O")&&table[0][2].equals(table[1][1])&&table[0][2].equals(table[2][0])){
            result = true;
        }
        return result;
    }

    public boolean checkIfDraw(){
        int count = 0;
        for (String[] strings : table) {
            for (String string : strings) {
                if (!string.equals("")){
                    count++;
                }
            }
        }

        return count == 9;
    }

    public void markAMove(XAndOGame.Square square) {
        String text = square.getText().getText();
        String[] split = square.getId().split("");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        table[x][y] = text;
    }


    public void computerMove(XAndOGame.Square[][] squares) {
        while (!isXTurn){
            int i = random.nextInt(3);
            int j = random.nextInt(3);
            if (table[i][j].equals("")){
                XAndOGame.Square square = squares[i][j];
                square.drawO();
                table[i][j] = "O";
                isXTurn = true;
            }
        }
    }
}
