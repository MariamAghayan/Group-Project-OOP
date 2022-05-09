package set.ui;

import set.core.Card;

import javax.swing.*;

public class SetCard extends JButton {
    private int x;
    private int y;
    public SetCard(int x) throws CloneNotSupportedException {
        this.x = x;
    }

    public void displayCards(Card piece) throws CloneNotSupportedException {
            this.setIcon(new ImageIcon( "images\\" + piece.toString() + ".gif"));

    }

}
