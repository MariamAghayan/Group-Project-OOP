package set.ui;

import set.core.Board;

import javax.swing.*;

public class SetCard extends JButton {
    private Board board = new Board();
    private int x;
    private int y;
    public SetCard(int x) throws CloneNotSupportedException {
        this.x = x;
    }
    public void displayCards(String piece){
        for (int i = 0; i < board.array.length; i++){
            this.setIcon(new ImageIcon(board.array[i].toString() + ".gif"));
        }
    }
}
